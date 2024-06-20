package com.itheima.common.config.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 提供单独获取spring管理的bean的类，用在非spring管理的类需要使用spring bean的场景.
 *
 * @author 蔡立杰
 * @since 22/12/2017
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  /** 在静态方法中获取Bean实例. */
  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }

  /**
   * 在静态方法中获取配置文件中的配置项的值.
   *
   * @param propertyName 配置项名称
   * @return 配置项的值
   */
  public static String getProperty(String propertyName) {
    return context.getEnvironment().getProperty(propertyName);
  }
}
