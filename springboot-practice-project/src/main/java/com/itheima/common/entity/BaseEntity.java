package com.itheima.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 基础Entity，用来定义一些通用的东西.
 *
 * @author 蔡立杰
 * @since 2021/07/09
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(value="id", type = IdType.AUTO)
    protected Integer id;

    @TableField(value = "create_user_code",fill = FieldFill.INSERT)
    protected String createUserCode;

    @TableField(value = "create_user_name",fill = FieldFill.INSERT)
    protected String createUserName;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    protected Date createTime;

    @TableField(value = "update_user_code",fill = FieldFill.INSERT_UPDATE)
    protected String updateUserCode;

    @TableField(value = "update_user_name",fill = FieldFill.INSERT_UPDATE)
    protected String updateUserName;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    @Version
    @TableField(value = "version",fill = FieldFill.INSERT_UPDATE)
    protected Integer version;

}
