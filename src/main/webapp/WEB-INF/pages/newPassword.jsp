<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 17.09.2019
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Password</title>
</head>
<body>
<form action="/setNewPassword" method="post">
    <input type="hidden" name="id" value="${user.id}" hidden>
    <input type="text" name="firstName" value="${user.firstName}" hidden>
    <input type="text" name="lastName" value="${user.lastName}" hidden>
    <input type="text" name="email" value="${user.email}" hidden>
    <input type="date" name="createdAt" value="${user.createdAt}" hidden>
    <table>
        <tr>
            <td>
                <input type="password" name="password" placeholder="Password">
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" name="confirmPassword" placeholder="Confirm password">
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
