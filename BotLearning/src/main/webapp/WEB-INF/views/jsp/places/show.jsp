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

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1><spring:message code="label.details.place"/></h1>
    <br/>

    <%--<div class="row">--%>
    <%--<label class="col-sm-2">ID</label>--%>
    <%--<div class="col-sm-10">${camera.id}</div>--%>
    <%--</div>--%>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.name"/></label>
        <div class="col-sm-10">${place.name}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.type"/></label>
        <div class="col-sm-10">${place.type}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.description"/></label>
        <div class="col-sm-10">${place.description}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.worktime"/></label>
        <div class="col-sm-10">${place.worktime}</div>
    </div>
    <br/>
    <br/>
    <div class="row">
        <div class="col-sm-2">
            <a href="/places/update/${place.id}" class="btn-lg btn-primary">
                <spring:message code="label.edit"/>
            </a>
        </div>
        <div class="col-sm-2">
            <a href="/places/delete/${place.id}" class="btn-lg btn-primary">
                <spring:message code="label.delete"/>
            </a>
        </div>
    </div>
</div>

</body>
</html>