<%@ page import="com.itheima.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: HL
  Date: 2021/7/20
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("name", "张三");
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        request.setAttribute("user", user);
    %>
    ${requestScope.name}<br>
    ${requestScope.user.password}<br>
    ${user.username}
</body>
</html>
