<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" contentType="text/html; charset=gb2312" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Register</h1>

    <sf:form method="POST" commandName="spitter" >
      <sf:errors path="*" element="div" cssClass="errors" />
      <sf:label path="fullName"
          cssErrorClass="error">Full Name</sf:label>:
        <sf:input path="fullName" cssErrorClass="error" /><br/>
      <sf:label path="email" 
          cssErrorClass="error">Email</sf:label>: 
        <sf:input path="email" cssErrorClass="error" /><br/>
      <sf:label path="username" 
          cssErrorClass="error">Username</sf:label>: 
        <sf:input path="username" cssErrorClass="error" /><br/>
      <sf:label path="password" 
          cssErrorClass="error">Password</sf:label>: 
        <sf:password path="password" cssErrorClass="error" /><br/>
      <input type="submit" value="Register" />
    </sf:form>
  </body>
</html>
