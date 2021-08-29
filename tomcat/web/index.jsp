<%--
  Created by IntelliJ IDEA.
  User: HL
  Date: 2021/6/13
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" errorPage="500.jsp" language="java" %>
<html>
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="userinfo/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="userinfo/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="userinfo/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div >${user.name},欢迎您</div>
    <div align="center">
      <a href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息</a>
    </div>
  </body>
</html>
