<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 11.09.2019
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Page</title>
</head>
<body>
<h2>Account Settings</h2>

<form action="/user/edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <input type="date" name="createdAt" value="${user.createdAt}" hidden>
    <table>
        <tr>
            <td>
                <input type="text" name="firstName" id="firstName" placeholder="First Name">
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="lastName" id="lastName" placeholder="Last Name">
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="email" id="email" placeholder="E-mail">
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" name="password" id="password" placeholder="Password">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
