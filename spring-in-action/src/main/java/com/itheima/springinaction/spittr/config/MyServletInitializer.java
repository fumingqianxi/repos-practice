package com.itheima.springinaction.spittr.config;

import com.itheima.springinaction.spittr.web.MyFilter;
import com.itheima.springinaction.spittr.web.MyServlet;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author 胡磊
 * @since 2023/7/2 14:50
 */
public class MyServletInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    // 注册Servlet
    ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
    myServlet.addMapping("/custom/*");
    // 注册过滤器
    FilterRegistration.Dynamic filter = servletContext.addFilter("myFilter", MyFilter.class);
    filter.addMappingForUrlPatterns(null, false, "/*");
  }
}
