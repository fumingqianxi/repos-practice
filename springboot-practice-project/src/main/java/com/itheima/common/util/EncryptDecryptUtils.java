package com.itheima.common.util;

import com.itheima.common.mybatis.EncryptDecryptField;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.springframework.beans.factory.annotation.Value;

/**
 * 可逆非对称加密工具类.
 *
 * @author 蔡立杰.
 * @since 2024/1/26 11:07 AM
 */
@Slf4j
public class EncryptDecryptUtils {

  private static PublicKey publicKey;
  private static PrivateKey privateKey;

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Value(
      "${reversible.private"
          + ".key:MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgkKNHt3g37xQkQIfZkHqGkN8KAUlkCo"
          + "+Orz9Qadvs6R+gCgYIKoEcz1UBgi2hRANCAAT4CD+G7/AmDFFZv0k8PJuFESwAKZtnwyFxqlK"
          + "+V8QO9FIDhWdP5V+Qr/nG8zHNSxJ56fIqRl65ADWgiNZbC6or}")
  private String sreversiblePrivateKey;

  @Value(
      "${reversible.public.key:MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE+Ag/hu"
          + "/wJgxRWb9JPDybhREsACmbZ8MhcapSvlfEDvRSA4VnT+VfkK/5xvMxzUsSeenyKkZeuQA1oIjWWwuqKw==}")
  private String sreversiblePublicKey;

  public EncryptDecryptUtils() {}

  /**
   * 多field加密方法
   *
   * @param declaredFields
   * @param parameterObject
   * @param <T>
   * @return
   * @throws IllegalAccessException
   */
  public static <T> T doEncrypt(Field[] declaredFields, T parameterObject)
      throws IllegalAccessException {
    for (Field field : declaredFields) {
      EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
      if (Objects.isNull(annotation)) {
        continue;
      }
      doEncrypt(field, parameterObject);
    }
    return parameterObject;
  }

  /**
   * 单个field加密方法
   *
   * @param field
   * @param parameterObject
   * @param <T>
   * @return
   * @throws IllegalAccessException
   */
  public static <T> T doEncrypt(Field field, T parameterObject) throws IllegalAccessException {
    field.setAccessible(true);
    Object object = field.get(parameterObject);
    if (object instanceof String) {
      // 定制String类型的加密算法
      String value = (String) object;
      field.set(parameterObject, encrypt(value));
    } else if (object instanceof BigDecimal) {
      BigDecimal value = (BigDecimal) object;
      long longValue =
          value.movePointRight(4).subtract(BigDecimal.valueOf(Integer.MAX_VALUE >> 3)).longValue();
      field.set(parameterObject, BigDecimal.valueOf(longValue));
    }
    return parameterObject;
  }

  /**
   * 加密-单个.
   *
   * @param encryptStr 待加密数据
   * @return
   */
  public static String encrypt(String encryptStr) {

      if (CheckEmptyUtil.isEmpty(encryptStr)) {
          return "";
      }
      try {
          ECPublicKeyParameters localEcPublicKeyParameters = null;
          if (publicKey instanceof BCECPublicKey) {
              BCECPublicKey localEcPublicKey = (BCECPublicKey) publicKey;
              ECParameterSpec localEcParameterSpec = localEcPublicKey.getParameters();
              ECDomainParameters localEcDomainParameters =
                      new ECDomainParameters(
                              localEcParameterSpec.getCurve(),
                              localEcParameterSpec.getG(),
                              localEcParameterSpec.getN());
              localEcPublicKeyParameters =
                      new ECPublicKeyParameters(localEcPublicKey.getQ(), localEcDomainParameters);
          }
          SM2Engine localsm2Engine = new SM2Engine();
          localsm2Engine.init(
                  true, new ParametersWithRandom(localEcPublicKeyParameters, new SecureRandom()));
          byte[] encryptBytes =
                  localsm2Engine.processBlock(encryptStr.getBytes(), 0, encryptStr.getBytes().length);
          return Base64.getEncoder().encodeToString(encryptBytes);
      } catch (Exception e) {
          log.error("SM2加密-错误：" + e.getMessage());
          return "";
      }
  }

  /**
   * 解密方法
   *
   * @param result
   * @param <T>
   * @return
   * @throws IllegalAccessException
   */
  public static <T> T doDecrypt(T result) throws IllegalAccessException {
    Class<?> parameterObjectClass = result.getClass();
    Field[] declaredFields = parameterObjectClass.getDeclaredFields();
    doDecrypt(declaredFields, result);
    return result;
  }

  /**
   * 多个field解密方法
   *
   * @param declaredFields
   * @param result
   * @throws IllegalAccessException
   */
  public static void doDecrypt(Field[] declaredFields, Object result)
      throws IllegalAccessException {
    for (Field field : declaredFields) {
      EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
      if (Objects.isNull(annotation)) {
        continue;
      }
      doDecrypt(field, result);
    }
  }

  /**
   * 单个field解密方法
   *
   * @param field
   * @param result
   * @throws IllegalAccessException
   */
  public static void doDecrypt(Field field, Object result) throws IllegalAccessException {
      field.setAccessible(true);
      Object object = field.get(result);
      if (Objects.isNull(object)) {
          return;
      }
      // 赋值原始数据，供前端获取明文使用
      try {
          String fieldName = field.getName();
          Field originalField =
                  result
                          .getClass()
                          .getDeclaredField(
                                  "original" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
          originalField.setAccessible(true);
          originalField.set(result, object);
      } catch (NoSuchFieldException e) {
          return;
      }

      if (object instanceof String) {
          // 定制String类型的解密算法
          String value = (String) object;
          field.set(result, decrypt(value));
      } else if (object instanceof BigDecimal) {
          BigDecimal value = (BigDecimal) object;
          double doubleValue =
                  value.add(BigDecimal.valueOf(Integer.MAX_VALUE >> 3)).movePointLeft(4).doubleValue();
          field.set(result, BigDecimal.valueOf(doubleValue));
      }
  }

  /**
   * 解密-单个.
   *
   * @param decryptStr 解密数据
   * @return
   */
  public static String decrypt(String decryptStr) {
      if (CheckEmptyUtil.isEmpty(decryptStr)) {
          return "";
      }
      try {
          SM2Engine localsm2Engine = new SM2Engine();
          BCECPrivateKey sm2PriK = (BCECPrivateKey) privateKey;
          ECParameterSpec localEcParameterSpec = sm2PriK.getParameters();
          ECDomainParameters localEcDomainParameters =
                  new ECDomainParameters(
                          localEcParameterSpec.getCurve(),
                          localEcParameterSpec.getG(),
                          localEcParameterSpec.getN());
          ECPrivateKeyParameters localEcPrivateKeyParameters =
                  new ECPrivateKeyParameters(sm2PriK.getD(), localEcDomainParameters);
          localsm2Engine.init(false, localEcPrivateKeyParameters);
          byte[] encrypByte = Base64.getDecoder().decode(decryptStr);
          byte[] sourceByte = localsm2Engine.processBlock(encrypByte, 0, encrypByte.length);
          return new String(sourceByte);
      } catch (Exception e) {
          log.error("解密-单个异常,异常信息:{}", e.getMessage(), e);
          //解密异常,返回密文数据
          return decryptStr;
      }
  }

  /**
   * 将Base64转码的公钥串，转化为公钥对象.
   *
   * @param publicKey 公钥
   * @return
   */
  private static PublicKey createPublicKey(String publicKey) {
      try {
          X509EncodedKeySpec publicKeySpec =
                  new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
          KeyFactory keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
          return keyFactory.generatePublic(publicKeySpec);
      } catch (Exception e) {
          log.error("将Base64转码的公钥串，转化为公钥对象异常,异常信息:{}", e.getMessage(), e);
          return null;
      }
  }

  /**
   * 将Base64转码的私钥串，转化为私钥对象.
   *
   * @param privateKey 私钥
   * @return
   */
  private static PrivateKey createPrivateKey(String privateKey) {
      try {
          PKCS8EncodedKeySpec pkcs8EncodedKeySpec =
                  new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
          KeyFactory keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
          return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
      } catch (Exception e) {
          log.error("将Base64转码的公钥串，转化为私钥对象异常,异常信息:{}", e.getMessage(), e);
          return null;
      }
  }

  /** 预加载配置信息. */
  @PostConstruct
  public void init() {
    if (CheckEmptyUtil.isNotEmpty(sreversiblePublicKey)) {
      publicKey = createPublicKey(sreversiblePublicKey);
    }
    if (CheckEmptyUtil.isNotEmpty(sreversiblePrivateKey)) {
      privateKey = createPrivateKey(sreversiblePrivateKey);
    }
  }
}
