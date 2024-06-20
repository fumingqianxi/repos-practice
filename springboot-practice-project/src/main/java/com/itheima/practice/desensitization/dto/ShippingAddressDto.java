package com.itheima.practice.desensitization.dto;

import com.itheima.practice.desensitization.annotation.PrivacyEncrypt;
import com.itheima.practice.desensitization.enums.PrivacyTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 收货地址Dto.
 *
 * @author 胡磊
 * @since 2024/1/3 9:05
 */
@Data
@ApiModel(value = "收货地址Dto")
public class ShippingAddressDto {

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("省级行政区名称")
    private String provinceName;

    @ApiModelProperty("地级行政区名称")
    private String cityName;

    @ApiModelProperty("县级行政区名称")
    private String countyName;

    @ApiModelProperty("乡级行政区名称")
    private String streetName;

    @ApiModelProperty("详细地址")
    @PrivacyEncrypt(type = PrivacyTypeEnum.ADDRESS)
    private String detailedAddress;

    @ApiModelProperty("手机号码")
    @PrivacyEncrypt(type = PrivacyTypeEnum.PHONE)
    private String mobilePhoneNum;
}
