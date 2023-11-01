package com.itheima.designpattern.templatemethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 胡磊
 * @since 2022/6/4 10:43
 */
public class LocalSetting extends Setting{
  private Map<String, String> cache = new HashMap<>();
  @Override
  protected String lookupCache(String key) {
    return cache.get(key);
  }

  @Override
  protected void putIntoCache(String key, String value) {
    cache.put(key, value);
  }
}
