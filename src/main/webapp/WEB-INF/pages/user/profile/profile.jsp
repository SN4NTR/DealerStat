<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 17.09.2019
  Time: 12:08
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
        <th>Action</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${user.rating}</td>
        <td>${user.createdAt}</td>
        <td>
            <a href="/user/edit/${user.id}">Edit</a>
            |
            <a href="/user/delete/${user.id}">Delete</a>
        </td>
    </tr>
</table>

<c:if test="${empty user.posts}">
    <h2><a href="/post/add">Add post</a></h2>
</c:if>
<c:if test="${!empty user.posts}">
    <h1>Posts</h1>
    <h2><a href="/post/add">Add post</a></h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Created At</th>
            <th>Action</th>
        </tr>
        <c:forEach var="post" items="${user.posts}">
            <tr>
                <td><a href="/profile/post/${post.id}">${post.id}</a></td>
                <td>${post.title}</td>
                <td>${post.createdAt}</td>
                <td>
                    <a href="/post/edit/${post.id}">Edit</a>
                    |
                    <a href="/post/delete/${post.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${!empty user.comments}">
    <h1>Comments</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Message</th>
            <th>Created At</th>
            <th>Action</th>
        </tr>
        <c:forEach var="comment" items="${user.comments}">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.message}</td>
                <td>${comment.createdAt}</td>
                <td><a href="/comment/delete/${comment.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
