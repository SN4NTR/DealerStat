<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 11.09.2019
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form action="/add" method="post">
    <label for="firstName">First Name</label>
    <input type="text" name="firstName" id="firstName">
    <label for="lastName">Last Name</label>
    <input type="text" name="lastName" id="lastName">
    <label for="email">E-mail</label>
    <input type="text" name="email" id="email">
    <label for="password">Password</label>
    <input type="password" name="password" id="password">
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
