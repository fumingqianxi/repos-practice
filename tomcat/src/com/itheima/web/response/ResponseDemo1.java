package com.itheima.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 */

@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("demo1........");

        request.setAttribute("msg","response");

        response.setStatus(302);

        response.setHeader("location", "/responseDemo2");

        //动态获取虚拟目录
//        String contextPath = request.getContextPath();

        //简单的重定向方法
//        response.sendRedirect(contextPath+"/responseDemo2");
        //response.sendRedirect("http://www.itcast.cn");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
