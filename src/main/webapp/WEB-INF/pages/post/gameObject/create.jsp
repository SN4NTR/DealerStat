<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 13.09.2019
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Game Object</title>
</head>
<body>
<form action="/addGameObject" method="post">
    <label for="title">Title</label>
    <input type="text" name="title" id="title">
    <br><br>
    <label for="text">Text</label>
    <input type="text" name="text" id="text">
    <br>
    <input type="submit" value="Choose game tag">
</form>
</body>
</html>
