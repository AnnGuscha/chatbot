<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.gstu.bot.learning.manager.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<div class="container">
    <fmt:requestEncoding value="utf-8"/>
    <fmt:setLocale value="<%=Locale.DEFAULT.toString()%>"/>
    <fmt:setBundle basename="properties.resfile" var="loc"/>
    <h2 class="form-signin-heading">
        <fmt:message bundle="${loc}" key="login.authentication"/>
    </h2>
    <form action="/login" method=post role="form" data-toggle="validator" class="form-signin">
        <div class="form-group col-xs-4">
            <input type="text" name="login" id="login" class="form-control" placeholder="Login"
                   required autofocus/>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password"
                   required/>
            <label class="checkbox">
                <input type="checkbox" name="isRem" value="remember-me">
                <fmt:message bundle="${loc}" key="login.remember_me"/>
            </label>
            <button type="submit" class="btn btn-lg btn-primary  btn-md btn-block">
                <fmt:message bundle="${loc}" key="login.sign_in"/>
            </button>
            <a href="/registr" type="button" class="btn btn-lg btn-primary  btn-md btn-block">
                <fmt:message bundle="${loc}" key="registration"/>
            </a>
        </div>
    </form>
</div>
</body>
</html>