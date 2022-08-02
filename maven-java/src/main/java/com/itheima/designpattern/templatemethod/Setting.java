package com.itheima.designpattern.templatemethod;

/**
 * @author {胡磊}
 * @since 2022/6/4 10:19
 */
public abstract class Setting {

  public final String getSetting(String key) {
    String value = lookupCache(key);
    if (value == null) {
      value = readFromDb(key);
      putIntoCache(key, value);
    }
    return value;
  }

  private String readFromDb(String key) {
    return "";
  }

  protected abstract String lookupCache(String key);

  protected abstract void putIntoCache(String key, String value);
}
