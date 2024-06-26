package com.itheima.springinaction.spittr.config;

import java.util.regex.Pattern;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

/**
 * @author 胡磊
 * @since 2023/5/1 18:47
 */
@Configuration
@ComponentScan(basePackages = {"com.itheima.springinaction.spittr"},
    excludeFilters = {@Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class)})
public class RootConfig {
  public static class WebPackage extends RegexPatternTypeFilter {
    public WebPackage() {
      super(Pattern.compile("com\\.itheima\\.springinaction\\.spittr\\.web"));
    }
  }
}
