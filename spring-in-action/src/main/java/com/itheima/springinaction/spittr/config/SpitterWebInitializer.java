package com.itheima.springinaction.spittr.config;

import com.itheima.springinaction.spittr.web.MyFilter;
import com.itheima.springinaction.spittr.web.WebConfig;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author 胡磊
 * @since 2023/5/1 15:04
 */
public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{RootConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[]{new MyFilter()};
  }
}
