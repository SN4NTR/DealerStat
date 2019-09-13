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
        <td>${post.user.id}</td>
    </tr>
</table>
</body>
</html>
