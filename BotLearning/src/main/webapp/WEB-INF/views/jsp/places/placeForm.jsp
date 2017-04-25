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

    <spring:url value="/places/save" var="placeActionUrl"/>

    <form:form class="form-horizontal" method="post" modelAttribute="placeForm" action="${placeActionUrl}">

        <fieldset>
            <legend>
                <c:choose>
                    <c:when test="${isNew==true}">
                        <h1><spring:message code="label.addplace"/></h1>
                    </c:when>
                    <c:otherwise>
                        <h1><spring:message code="label.updateplace"/></h1>
                    </c:otherwise>
                </c:choose>
            </legend>

            <form:hidden path="id"/>

            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label"><spring:message code="label.name"/></label>
                    <div class="col-sm-5">
                        <form:input path="name" type="text" class="form-control " id="name" placeholder="Name"/>
                        <form:errors path="name" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="type">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label"><spring:message code="label.type"/></label>
                    <div class="col-sm-5">
                        <form:input path="type" type="text" class="form-control " id="type" placeholder="Type"/>
                        <form:errors path="type" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <%--<spring:bind path="manufacturer">--%>
                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                    <%--<label class="col-sm-2 control-label"><spring:message code="label.manufacturer"/></label>--%>
                    <%--<div class="col-sm-5">--%>
                        <%--<form:select path="manufacturer" class="form-control">--%>
                            <%--<form:option value="NONE" label="--- Select ---"/>--%>
                            <%--<form:options items="${manufacturerList}"/>--%>
                        <%--</form:select>--%>
                        <%--<form:errors path="manufacturer" class="control-label"/>--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-5"></div>--%>
                <%--</div>--%>
            <%--</spring:bind>--%>

            <spring:bind path="description">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label"><spring:message code="label.description"/></label>
                    <div class="col-sm-5">
                        <form:input path="description" type="text" class="form-control " id="description"
                                    placeholder="Description"/>
                        <form:errors path="description" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="worktime">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label"><spring:message code="label.worktime"/></label>
                    <div class="col-sm-5">
                        <form:input path="worktime" type="text" class="form-control " id="worktime"
                                    placeholder="Worktime"/>
                        <form:errors path="worktime" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-5">
                    <c:choose>
                        <c:when test="${isNew==true}">
                            <button type="submit" class="btn-lg btn-primary pull-right"><spring:message
                                    code="label.add"/></button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right"><spring:message
                                    code="label.update"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </fieldset>
    </form:form>

</div>
</body>
</html>