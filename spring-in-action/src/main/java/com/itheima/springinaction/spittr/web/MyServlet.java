package com.itheima.springinaction.spittr.web;

import javax.servlet.*;
import java.io.IOException;

//@WebServlet("/my-servlet")
public class MyServlet implements Servlet {

  @Override
  public void init(ServletConfig servletConfig) throws ServletException {}

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

  @Override
  public void service(ServletRequest servletRequest, ServletResponse servletResponse)
      throws ServletException, IOException {
    System.out.println("myservlet被执行了....");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public void destroy() {}
}
