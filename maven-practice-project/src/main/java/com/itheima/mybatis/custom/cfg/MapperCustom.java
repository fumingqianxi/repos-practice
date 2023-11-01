package com.itheima.mybatis.custom.cfg;

import lombok.Data;

/**
 * 用于封装执行的SQL语句和结果类型的全限定类名.
 *
 * @author 胡磊
 * @since 2023/9/25 11:05
 */
@Data
public class MapperCustom {

  private String queryString; // SQL
  private String resultType; // 实体类的全限定类名
}
