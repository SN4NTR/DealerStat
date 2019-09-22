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
<div>
    <h2>
        <a href="/profile">Profile</a> |
        <a href="/">Home</a> |
        <a href="/logout">Logout</a>
    </h2>
</div>

<h1>Dealer Profile</h1>

<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>E-mail</th>
        <th>Rating</th>
        <th>Created At</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${user.rating}</td>
        <td>${user.createdAt}</td>
    </tr>
</table>

<c:if test="${!empty user.posts}">
    <h1>Posts</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Created At</th>
        </tr>
        <c:forEach var="post" items="${user.posts}">
            <tr>
                <td><a href="/post/${post.id}">${post.id}</a></td>
                <td>${post.title}</td>
                <td>${post.createdAt}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty user.comments}">
    <h2><a href="/comment/add/${user.id}">Add comment</a></h2>
</c:if>

<c:if test="${!empty user.comments}">
    <h1>Comments</h1>
    <h2><a href="/comment/add/${user.id}">Add comment</a></h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Message</th>
            <th>Rating</th>
            <th>Created At</th>
        </tr>
        <c:forEach var="comment" items="${user.comments}">
            <c:choose>
                <c:when test="${comment.approved == true}">
                    <tr>
                        <td>${comment.id}</td>
                        <td>${comment.message}</td>
                        <td>${comment.rating}</td>
                        <td>${comment.createdAt}</td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
