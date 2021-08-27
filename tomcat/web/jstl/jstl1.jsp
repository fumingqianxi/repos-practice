<%--
  Created by IntelliJ IDEA.
  User: HL
  Date: 2021/7/25
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="true">
        <h1>我是真。。。</h1>
    </c:if>

    <c:forEach begin="1" end="10" var="i" step="1">
        ${i}<br>
    </c:forEach>

</body>
</html>
