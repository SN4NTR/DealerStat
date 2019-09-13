<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 12.09.2019
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Posts</title>
    <link href="<c:url value="/res/style.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<h1>Posts</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Created At</th>
        <th>Dealer ID</th>
        <th>Action</th>
    </tr>
    <c:forEach var="post" items="${postList}">
        <tr>
            <td>
                <a href="/post/${post.id}">${post.id}</a>
            </td>
            <td>${post.title}</td>
            <td>${post.createdAt}</td>
            <td><a href="/user/${post.user.id}">${post.user.id}</a></td>
            <td>
                <a href="/editPost/${post.id}">Edit</a>
                |
                <a href="/deletePost/${post.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
