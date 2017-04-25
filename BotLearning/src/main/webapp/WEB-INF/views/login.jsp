<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <link rel='stylesheet' href="/resources/css/hello.css">
    <link rel='stylesheet' href="/resources/css/bootstrap.min.css">
    <link rel='stylesheet' href="/resources/css/jquery.dataTables.min.css">
    <link rel='stylesheet' href="/resources/css/signin.css">
    <link rel='stylesheet' href="/resources/js/hello.js">
    <script type='text/javascript' src="/ext/jquery/js/jquery.js"></script>
    <script type='text/javascript' src="/ext/bootstrap/js/bootstrap.js"></script>
    <script type='text/javascript' src="/resources/js/jquery.dataTables.min.js"></script>
</head>
<body>
<div class="container">

    <c:if test="${not empty param.error}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong><spring:message code="label.loginerror"/>
                : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</strong>
        </div>
    </c:if>
    <form class="form-signin" method="POST" action="<c:url value="/j_spring_security_check" />">
        <h2 class="form-signin-heading"><spring:message code="label.please_sign_in"/></h2>
        <label class="sr-only"><spring:message code="label.login"/></label>
        <input type="login" name="j_username" class="form-control" placeholder="<spring:message code="label.login" />"
               required autofocus/>

        <label class="sr-only"><spring:message code="label.password"/></label>
        <input type="password" name="j_password" class="form-control"
               placeholder="<spring:message code="label.password" />" required>

        <div class="checkbox">
            <label>
                <input type="checkbox" name="_spring_security_remember_me" value="remember-me"> <spring:message
                    code="label.remember"/>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" value="Login"><spring:message
                code="label.signin"/></button>
        <button class="btn btn-lg btn-primary btn-block" type="reset" value="Reset"><spring:message
                code="label.reset"/></button>
    </form>
</div>
</body>
</html>