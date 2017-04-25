<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="manager.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="Header.jsp"/>
<fmt:setLocale value="<%=Locale.DEFAULT.toString()%>"/>
<fmt:setBundle basename="properties.resfile" var="loc"/>
<body>
<div class="container">
    <h2 class="form-signin-heading"><fmt:message bundle="${loc}" key="registration"/></h2>
    <form action="/registr" method=post role="form" data-toggle="validator">
        <div class="form-group col-xs-4">
            <label for="login" class="control-label col-xs-4"><fmt:message bundle="${loc}" key="login"/>:</label>
            <input type="text" name="login" id="login" class="form-control" value=""
                   required="true"/>
            <br/>
            <label for="password" class="control-label col-xs-4"><fmt:message bundle="${loc}" key="password"/>:</label>
            <input type="text" name="password" id="password" class="form-control" value=""
                   required="true"/>
            <br/>
            <label for="locale" class="control-label col-xs-4"><fmt:message bundle="${loc}" key="locale"/>:</label>
            <select id="locale" name="locale" class="form-control">
                <c:forEach var="locale" items="<%= Locale.values()  %>">
                    <option value="${locale.toString()}" class="form-control">
                            ${locale.getName()}
                    </option>
                </c:forEach>
            </select>
            <br/>
            <button type="submit" class="btn btn-primary  btn-md"><fmt:message bundle="${loc}"
                                                                               key="registration"/></button>
        </div>
    </form>
</div>
</body>
</html>
