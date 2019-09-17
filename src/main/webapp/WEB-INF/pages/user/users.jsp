<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 11.09.2019
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dealers</title>
    <link href="<c:url value="/res/style.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<div>
    <h2>
        <a href="/profile">Profile</a> |
        <a href="/">Home</a> |
        <a href="/logout">Logout</a>
    </h2>
</div>

<h1>Dealers List</h1>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>E-mail</th>
        <th>Created At</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>
                <a href="/user/${user.id}">${user.id}</a>
            </td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.createdAt}</td>
        </tr>
    </c:forEach>
</table>

<br>
<h3><a href="/posts">Show posts</a></h3>
</body>
</html>
