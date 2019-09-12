<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 12.09.2019
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dealer Info</title>
    <link href="<c:url value="/res/style.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<h1>Dealer Info</h1>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>E-mail</th>
        <th>Created At</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${user.createdAt}</td>
    </tr>
</table>
</body>
</html>
