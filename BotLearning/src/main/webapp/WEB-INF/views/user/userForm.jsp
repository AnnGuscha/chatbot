<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp"/>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<div class="container">

    <spring:url value="/user/update" var="userActionUrl"/>

    <form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${userActionUrl}">

        <fieldset>
            <legend>
                <h1><spring:message code="label.edituser"/></h1>
            </legend>

            <form:hidden path="id"/>

            <spring:bind path="login">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label"><spring:message code="label.login"/></label>
                    <div class="col-sm-3">
                        <form:input path="login" type="text" class="form-control " id="login" placeholder="login"/>
                        <form:errors path="login" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label"><spring:message code="label.password"/></label>
                    <div class="col-sm-3">
                        <form:input path="password" type="password" class="form-control " id="password"
                                    placeholder="password"/>
                        <form:errors path="password" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                <%--<label class="col-sm-2 control-label"><spring:message code="label.confirmpassword"/></label>--%>
                <%--<div class="col-sm-5">--%>
                <%--<form:input path="confirmpassword" type="text" class="form-control " id="confirmpassword"--%>
                <%--placeholder="confirmpassword"/>--%>
                <%--<form:errors path="confirmpassword" class="control-label"/>--%>
                <%--</div>--%>
                <%--</div>--%>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-3">
                    <button type="submit" class="btn-lg btn-primary pull-right"><spring:message
                            code="label.edit"/></button>
                </div>
            </div>
        </fieldset>
    </form:form>

</div>
</body>
</html>