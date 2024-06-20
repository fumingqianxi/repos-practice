package com.itheima.entity.mybatis;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.common.entity.BaseEntity;
import com.itheima.common.mybatis.EncryptDecryptField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收货地址表.
 *
 * @author 胡磊
 * @since 2024/1/3 11:41
 */
@TableName(value = "shipping_address")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShippingAddress extends BaseEntity {

    /**
     * 收货人.
     */
    private String consignee;

    /**
     * 手机号码.
     */
    @EncryptDecryptField
    private String mobilePhoneNum;

    @TableField(exist = false)
    private String originalMobilePhoneNum;

    /**
     * 省级行政区名称.
     */
    private String provinceName;

    /**
     * 地级行政区名称.
     */
    private String cityName;

    /**
     * 县级行政区名称.
     */
    private String countyName;

    /**
     * 详情地址.
     */
    @EncryptDecryptField
    private String detailedAddress;

    @TableField(exist = false)
    private String originalDetailedAddress;
}
