<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 13.09.2019
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create comment</title>
</head>
<body>
<form action="/addComment" method="post">
    <label for="message">Message</label>
    <input type="text" name="message" id="message">
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
