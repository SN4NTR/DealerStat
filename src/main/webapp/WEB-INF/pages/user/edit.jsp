<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 11.09.2019
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Page</title>
</head>
<body>
<form action="/editUser" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <label for="firstName">First Name</label>
    <input type="text" name="firstName" id="firstName">
    <label for="lastName">Last Name</label>
    <input type="text" name="lastName" id="lastName">
    <label for="email">E-mail</label>
    <input type="text" name="email" id="email">
    <label for="password">Password</label>
    <input type="password" name="password" id="password">
    <input type="date" name="createdAt" value="${user.createdAt}" hidden>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
