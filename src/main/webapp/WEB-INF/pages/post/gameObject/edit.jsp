<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 13.09.2019
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Game Object</title>
</head>
<body>
<form action="/gameObject/edit" method="post">
    <input name="id" value="${gameObject.id}" hidden>
    <label for="title">Title</label>
    <input type="text" name="title" id="title">
    <br><br>
    <label for="text">Text</label>
    <input type="text" name="text" id="text">
    <input type="date" name="createdAt" value="${gameObject.createdAt}" hidden>
    <input type="date" name="updatedAt" value="${gameObject.updatedAt}" hidden>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
