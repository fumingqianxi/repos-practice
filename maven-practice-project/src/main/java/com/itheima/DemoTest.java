package com.itheima;

import com.alibaba.fastjson.JSONObject;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
public class DemoTest {

  public static void main(String[] args) throws Exception {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("businessId", "123");
    System.out.println(jsonObject.toJSONString());
    String str = UUID.randomUUID().toString().replace("-", "");
    System.out.println(str);
    String nonce = RandomStringUtils.randomAlphanumeric(18);
    System.out.println(nonce);
  }
}
