package com.itheima.practice.desensitization.controller;

import com.itheima.practice.desensitization.dto.ShippingAddressDto;
import com.itheima.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端字段脱敏测试.
 *
 * @author 胡磊
 * @since 2024/6/19 16:02
 */
@Slf4j
@RestController
@RequestMapping("/desensitization")
public class DesensitizationController {

  /**
   * http://localhost:45678/desensitization/test.
   */
  @GetMapping("test")
  public String test() {
    ShippingAddressDto resultDto = new ShippingAddressDto();
    resultDto.setProvinceName("山东省");
    resultDto.setCityName("青岛市");
    resultDto.setCountyName("李沧区");
    resultDto.setDetailedAddress("新世纪小区1单元302");
    resultDto.setMobilePhoneNum("13055502532");
    return JsonUtil.serializeObject(resultDto);
  }
}
