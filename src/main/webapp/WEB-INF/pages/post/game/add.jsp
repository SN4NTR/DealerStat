<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 15.09.2019
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Game</title>
</head>
<h2>Choose the game:</h2>
<p>1. CS:GO</p>
<p>2. Dota</p>
<p>3. Fifa</p>
<p>4. Team Fortress</p>
<body>
<form action="/addGame" method="post">
    <label for="id">Your choice</label>
    <input type="text" name="id" id="id">
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
