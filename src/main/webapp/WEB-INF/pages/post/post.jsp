<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 12.09.2019
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Post</title>
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

<h1>Post ${post.id}</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Created At</th>
        <th>Dealer ID</th>
    </tr>
    <tr>
        <td>${post.id}</td>
        <td>${post.title}</td>
        <td>${post.createdAt}</td>
        <td><a href="/user/${post.user.id}">${post.user.id}</a></td>
    </tr>
</table>

<c:if test="${!empty post.gameObjects}">
    <h1>Game Objects</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Text</th>
            <th>Status</th>
            <th>Created at</th>
            <th>Updated at</th>
            <th>Game</th>
        </tr>
        <c:forEach var="gameObject" items="${post.gameObjects}">
            <tr>
                <td>${gameObject.id}</td>
                <td>${gameObject.title}</td>
                <td>${gameObject.text}</td>
                <td>${gameObject.status}</td>
                <td>${gameObject.createdAt}</td>
                <td>${gameObject.updatedAt}</td>
                <td>
                    <a href="/game/gameObjects/${gameObject.game.id}">${gameObject.game.name}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
