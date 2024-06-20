package com.itheima.practice.dbfieldencryptdecrypt.controller;

import com.itheima.common.util.JsonUtil;
import com.itheima.dao.mybatis.ShippingAddressMapper;
import com.itheima.entity.mybatis.ShippingAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库敏感字段统一加解密测试.
 *
 * @author 胡磊
 * @since 2024/6/19 16:02
 */
@Slf4j
@RestController
@RequestMapping("/db/field/encrypt/decrypt")
public class DbFieldEncryptDecryptController {

  @Autowired
  private ShippingAddressMapper shippingAddressMapper;

  /**
   * http://localhost:45678/db/field/encrypt/decrypt/test.
   */
  @GetMapping("test")
  public String test() {
    ShippingAddress shippingAddress = new ShippingAddress();
    shippingAddress.setConsignee("胡磊");
    shippingAddress.setMobilePhoneNum("13054509532");
    shippingAddress.setProvinceName("山东省");
    shippingAddress.setCityName("青岛市");
    shippingAddress.setCountyName("李沧区");
    shippingAddress.setDetailedAddress("新世纪小区1单元302");
    shippingAddressMapper.insert(shippingAddress);
    ShippingAddress address = shippingAddressMapper.selectById(shippingAddress.getId());
    return JsonUtil.serializeObject(address);
  }
}
