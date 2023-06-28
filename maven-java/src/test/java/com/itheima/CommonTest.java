package com.itheima;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import com.itheima.enums.SysErrCodeEnums;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;

/**
 * @author 胡磊
 * @since 2023/6/27 16:16
 */
@Slf4j
public class CommonTest {

  /**
   * 替换字符串中转义字符.
   */
  @Test
  public void method01() {
    String a = "\\p";
    System.out.println(a); // \p
    System.out.println(a.replaceAll("\\\\", "\\\\\\\\")); // \\p
  }

  /**
   * 测试LocalDateTime和ZonedDateTime关于时区的问题
   */
  @Test
  public void method02() {
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime);
    ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
    System.out.println(zonedDateTime);
  }

  /**
   * 测试删除掉url部分前缀
   */
  @Test
  public void method03() {
    String url = "group2/M00/09/07/oYYBAGHdE2-ABP27AADAAOv5PNo673.xls";
    if (url.matches("group\\d/M00/.*")) {
      url = url.replaceFirst("group\\d/M00/", "");
    }
    System.out.println(url);
  }

  /**
   * 测试两个日期的小时差.
   */
  @Test
  public void method04() {
    Date date1 = new Date();
    Date date2 = new Date(date1.getTime() + 9100000000L);
    // 必须用1000.0，否则结果不会保留小数
    double diff = (date2.getTime() - date1.getTime()) / 1000.0 / 60 / 60;
    NumberFormat numberInstance = NumberFormat.getNumberInstance();
    numberInstance.setMaximumFractionDigits(2);
    numberInstance.setRoundingMode(RoundingMode.HALF_UP);
    numberInstance.setGroupingUsed(false);
    diff = Double.parseDouble(numberInstance.format(diff));
    System.out.println(diff);
  }

  /**
   * 测试获取异常类名称.
   */
  @Test
  public void method05() {
    try {
      int i = 1 / 0;
    } catch (Throwable e) {
      System.out.println(e.getClass().getSimpleName());
    }
  }

  /**
   * 测试将驼峰字符串转为大写下划线.
   *
   * @param str 原字符串
   */
  public String method06(String str) {
    String result = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, str);
    System.out.println(result);
    return result;
  }

  /**
   * 测试获取枚举类对象.
   */
  @Test
  public void method07() {
    String name = "NullPointerException";
    String exName = method06(name);
    SysErrCodeEnums anEnum = EnumUtils.getEnum(SysErrCodeEnums.class, exName);
    if (anEnum != null) {
      System.out.println(anEnum.getDesc());
    } else {
      System.out.println("未找到对应的对象");
    }
  }

  /**
   * 测试格式化消息，替换{0}之类的占位符.
   */
  @Test
  public void method08() {
    String str = "服务商id{0}，编码{1}报名id为{2}的活动监控执行中";
    Object[] args = {"asd", 111};
    String result = MessageFormat.format(str, args);
    System.out.println(result);
  }

  /**
   * 获取HDS token接口的credential.
   */
  @Test
  public void method9() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("subject", "22025467");
    byte[] data = "ldj&220510".getBytes();
    String password = Base64.getEncoder().encodeToString(data);
    jsonObject.put("password", password);
    jsonObject.put("type", "1");
    String jsonStr = jsonObject.toJSONString();
    String credential = Base64.getEncoder().encodeToString(jsonStr.getBytes());
    System.out.println(credential);
  }

  /**
   * 正则表达式提取字符串.
   */
  @Test
  public void method10() {
    String str =
        "[x_jwt=eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9"
            +
            ".eyJ1c2VyIjp7ImlkIjoiMjIwMjU0NjciLCJuYW1lIjoi6IOh56OKIiwibG9naW5Tb3VyY2UiOiJpYW0ifSwia2V5IjoieC1vYXV0aCIsImF1ZCI6Ingtb2F1dGgiLCJleHAiOjE2ODc5MDI5MTYsImlhdCI6MTY4Nzg1OTcxNiwiaXNzIjoieC1vYXV0aCIsInN1YiI6IjIyMDI1NDY3In0.lAzLEMbc7H1KRv-gITSarslkeH_TIuPNmbBNGZezO_-7qzT1OSJyxYl-TUBIJdKw-sBAtpV50ZfwTdDexHk1-3G7DTV889VrH9pI1yvQI8Vh3Wp-CwRCmX-xJBe5EsxxBsnLU0KV1Ksr8xAC1Om2e2VbtC5LC39MsJwXSbuQw1RBSKOpqAZromfc12peQJM-wa1-PaZWF4Dt6rk41fa9tmFrFU8JKldiynTaZD2K__CSdlS5jJilp10CVsFRx8CUceypmXEJslsotx9ho_pHyEzlcyqCH40FtJFltBJv36hVYSUBPcSh9cnLM3LS6dCY6KHghLl_DRELd4Fuh-JyJg; Path=/; Domain=haier.net; Expires=Tue, 27 Jun 2023 21:55:16 GMT, x_i_jwt=eyJhbGciOiJSUzI1NiJ9.eyJpbnN0YW5jZUlkIjoxMjkzNzE3MDU1Njk3NDQ3OTk4LCJsb2dpbk5hbWUiOiIyMjAyNTQ2NyIsImRvbWFpbiI6IklETSIsInRlbmFudElkIjoxLCJpZCI6MjIwODI0MTA0MzAzLCJhY2NvdW50TnVtYmVyIjoiMjIwMjU0NjciLCJsb2dpblNvdXJjZSI6IjYiLCJ1YyI6IjEiLCJqdGkiOiIxMDc4ZDE4OC1kMjExLTRjOTQtYjI2OS04YTk2ZDk2YWE0ZmEiLCJuYmYiOjE2ODc4MzIxMDcsImV4cCI6MTY4Nzg4MTYwMH0.ILNToGgqbPvoCaOKmo8jIdT8zVytJqv8c91WPA39f8qUIqqLzEnIrr4Bcnyid8Oj29-1QVEGd3RhT2zmddv0L7oBq8re8OvP5D6zVQ7ATeF_409ZbzdvHiOQX0Zb5FCIBvfnegpV_asTbvF0fm0wdipbxInO__CizPSqJr2nJ5rlo1sAdblRFBLsPR9yFhCCKsNC3JypXnloNWakHrzmRTfhxG-Dv2gg70Jjapr1H4XS_EstZK5VEGPYxdlU2AmLCc-ixdR9lTO_XlXlho-hN47Sc_JoBid_4yC7l33fSjsqtLdNxEb0BfJTm8gqUz_SXsk2wjbQ7nSYqicIrGJLBQ; Path=/; Domain=haier.net; Expires=Tue, 27 Jun 2023 21:55:16 GMT]";
    Pattern pattern = Pattern.compile("^.x_jwt=([\\s\\S]*?); Path.*$");
    Matcher matcher = pattern.matcher(str);
    if (matcher.matches()) {
      log.info("提取到的数据为：{}", matcher.group(1));
    }
  }

  /**
   * 分组匹配提取字符串.
   */
  @Test
  public void method11() {
    Pattern p = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
    // Pattern p = Pattern.compile("(\\d{3,4})-(\\d{7,8})");
    Matcher m = p.matcher("010-12345678");
    if (m.matches()) {
      String g1 = m.group(1);
      String g2 = m.group(2);
      System.out.println(g1); // 010
      System.out.println(g2); // 12345678
    } else {
      System.out.println("匹配失败!");
    }
  }
}
