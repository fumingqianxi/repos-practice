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
    <title>$Title$</title>
  </head>
  <%
    System.out.println("hello jsp");
    String contextPath = request.getContextPath();
    System.out.println("contexPath: " + contextPath);
    out.write(contextPath);
  %>
  <h1>hi, jsp</h1>
  <body>
  $END$
  </body>
</html>
