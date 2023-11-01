package com.itheima.mybatis.custom.io;

import java.io.InputStream;

/**
 * 自定义资源加载类.
 *
 * @author 胡磊
 * @since 2023/9/25 10:42
 */
public class ResourcesCustom {

  public static InputStream getResourceAsStream(String filePath) {
    return ResourcesCustom.class.getClassLoader().getResourceAsStream(filePath);
  }
}
