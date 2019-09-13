<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 13.09.2019
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create post</title>
</head>
<body>
<form action="/addPost" method="post">
    <label for="title">Title</label>
    <input type="text" name="title" id="title">
    <input name="user" value="${post.user}">
    <input type="submit" value="Submit">
</form>
</body>
</html>
