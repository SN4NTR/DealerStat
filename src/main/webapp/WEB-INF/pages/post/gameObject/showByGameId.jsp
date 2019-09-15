<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 15.09.2019
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Objects</title>
    <link href="<c:url value="/res/style.css"/> " rel="stylesheet" type="text/css">
</head>
<body>

<h1>Game Objects By Game Filter</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Text</th>
        <th>Status</th>
        <th>Created At</th>
        <th>Updated At</th>
        <th>Game</th>
    </tr>
    <c:forEach var="gameObject" items="${gameObjects}">
        <tr>
            <td>${gameObject.id}</td>
            <td>${gameObject.title}</td>
            <td>${gameObject.text}</td>
            <td>${gameObject.status}</td>
            <td>${gameObject.createdAt}</td>
            <td>${gameObject.updatedAt}</td>
            <td>${gameObject.game.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
