<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 17.09.2019
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Comments</title>
    <link href="<c:url value="/res/style.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<div>
    <h2>
        <a href="/profile">Profile</a> |
        <a href="/">Home</a> |
        <a href="/logout">Logout</a>
    </h2>
</div>

<c:if test="${empty comments}">
    <h2>No comments to approve!</h2>
</c:if>
<c:if test="${!empty comments}">
    <table>
        <tr>
            <th>ID</th>
            <th>Message</th>
            <th>Created At</th>
            <th>Approved</th>
            <th>Action</th>
        </tr>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.message}</td>
                <td>${comment.createdAt}</td>
                <td>${comment.approved}</td>
                <td>
                    <a href="/comment/approve/${comment.id}">Approve</a>
                    |
                    <a href="/comment/decline/${comment.id}">Decline</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
