package com.itheima.dao.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entity.mybatis.ShippingAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收货地址表Mapper.
 *
 * @author 胡磊
 * @since 2024/1/3 21:24
 */
@Mapper
public interface ShippingAddressMapper extends BaseMapper<ShippingAddress> {}
