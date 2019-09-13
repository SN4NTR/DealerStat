<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 12.09.2019
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Post</title>
</head>
<body>
<form action="/editPost" method="post">
    <input type="hidden" name="id" value="${post.id}">
    <label for="title">Title</label>
    <input type="text" name="title" id="title">
    <input type="date" name="createdAt" value="${post.createdAt}" readonly>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
