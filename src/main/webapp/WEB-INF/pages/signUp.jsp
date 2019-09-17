<%--
  Created by IntelliJ IDEA.
  User: SN4NTR
  Date: 11.09.2019
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>

<form:form method="post" modelAttribute="user">
    <h2>Creating account</h2>
    <spring:bind path="firstName">
        <div>
            <form:input path="firstName" type="text" placeholder="First Name"/>
        </div>
    </spring:bind>

    <spring:bind path="lastName">
        <div>
            <form:input path="lastName" type="text" placeholder="Last Name"/>
        </div>
    </spring:bind>

    <spring:bind path="email">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="email" type="email" placeholder="Email"/>
            <form:errors path="email"/>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="password" type="password" placeholder="Password"/>
            <form:errors path="password"/>
        </div>
    </spring:bind>

    <spring:bind path="confirmPassword">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="confirmPassword" type="password" placeholder="Confirm Password"/>
            <form:errors path="confirmPassword"/>
        </div>
    </spring:bind>

    <button type="submit">Submit</button>
</form:form>

</body>
</html>
