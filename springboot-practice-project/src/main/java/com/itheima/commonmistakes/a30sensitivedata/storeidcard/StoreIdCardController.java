package com.itheima.commonmistakes.a30sensitivedata.storeidcard;

import com.itheima.dao.jpa.UserRepository;
import com.itheima.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 保存姓名和身份证示例.
 *
 * @author 胡磊
 * @since 2024/2/12 13:09
 */
@RestController
@Slf4j
@RequestMapping("storeidcard")
public class StoreIdCardController {

  private static final String KEY = "secretkey1234567";
  private static final String initVector = "abcdefghijklmnop";

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CipherService cipherService;

  private static SecretKeySpec setKey(String secret) {
    return new SecretKeySpec(secret.getBytes(), "AES");
  }

  private static String idCard(String idCard) {
    String num = StringUtils.right(idCard, 4);
    return StringUtils.leftPad(num, StringUtils.length(idCard), "*");
  }

  public static String chineseName(String chineseName) {
    String name = StringUtils.left(chineseName, 1);
    return StringUtils.rightPad(name, StringUtils.length(chineseName), "*");
  }

  private static void test(Cipher cipher, AlgorithmParameterSpec parameterSpec) throws Exception {
    cipher.init(Cipher.ENCRYPT_MODE, setKey(KEY), parameterSpec);
    System.out.println("一次：" + Hex.encodeHexString(cipher.doFinal("abcdefghijklmnop".getBytes())));
    System.out.println(
        "两次：" + Hex.encodeHexString(cipher.doFinal("abcdefghijklmnopabcdefghijklmnop".getBytes())));
    byte[] sender = "1000000000012345".getBytes();
    byte[] receiver = "1000000000034567".getBytes();
    byte[] money = "0000000010000000".getBytes();

    //加密发送方账号
    System.out.println("发送方账号：" + Hex.encodeHexString(cipher.doFinal(sender)));
    //加密接收方账号
    System.out.println("接收方账号：" + Hex.encodeHexString(cipher.doFinal(receiver)));
    //加密金额
    System.out.println("金额：" + Hex.encodeHexString(cipher.doFinal(money)));
    byte[] result = cipher.doFinal(ByteUtils.concatAll(sender, receiver, money));
    //加密三个数据
    System.out.println("完整数据：" + Hex.encodeHexString(result));
    byte[] hack = new byte[result.length];
    //把密文前两段交换
    System.arraycopy(result, 16, hack, 0, 16);
    System.arraycopy(result, 0, hack, 16, 16);
    System.arraycopy(result, 32, hack, 32, 16);
    cipher.init(Cipher.DECRYPT_MODE, setKey(KEY), parameterSpec);
    //尝试解密
    System.out.println("原始明文：" + new String(ByteUtils.concatAll(sender, receiver, money)));
    System.out.println("操纵密文：" + new String(cipher.doFinal(hack)));
  }

  @GetMapping("wrong")
  public User wrong(
      @RequestParam(value = "name", defaultValue = "朱晔") String name,
      @RequestParam(value = "idcard", defaultValue = "300000000000001234") String idCard) {
    User user = new User();
    user.setId(1L);
    user.setName(name);
    user.setIdCard(idCard);
    return userRepository.save(user);
  }

  @GetMapping("right")
  public User right(
      @RequestParam(value = "name", defaultValue = "朱晔") String name,
      @RequestParam(value = "idcard", defaultValue = "300000000000001234") String idCard,
      @RequestParam(value = "aad", required = false) String aad) throws Exception {
    User user = new User();
    user.setId(1L);
    //脱敏姓名
    user.setName(chineseName(name));
    //脱敏身份证
    user.setIdCard(idCard(idCard));
    //加密姓名
    CipherResult cipherResultName = cipherService.encrypt(name, aad);
    user.setNameCipherId(cipherResultName.getId());
    user.setNameCipherText(cipherResultName.getCipherText());
    //加密身份证
    CipherResult cipherResultIdCard = cipherService.encrypt(idCard, aad);
    user.setIdCardCipherId(cipherResultIdCard.getId());
    user.setIdCardCipherText(cipherResultIdCard.getCipherText());
    return userRepository.save(user);
  }

  @GetMapping("read")
  public void read(@RequestParam(value = "aad", required = false) String aad) throws Exception {
    User user = userRepository.findById(1L).get();
    log.info(
        "name : {} idcard : {}",
        cipherService.decrypt(user.getNameCipherId(), user.getNameCipherText(), aad),
        cipherService.decrypt(user.getIdCardCipherId(), user.getIdCardCipherText(), aad));

  }

  @GetMapping("ecb")
  public void ecb() throws Exception {
    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
    test(cipher, null);
  }

  @GetMapping("cbc")
  public void cbc() throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
    test(cipher, iv);
  }
}
