<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index"><spring:message code="label.places"/></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false"><spring:message code="label.places"/><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/places"><spring:message code="label.places"/></a></li>
                        <li><a href="/places"><spring:message code="label.places"/></a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false"><spring:message code="label.language"/><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a href="?lang=ru" class="language" rel="ru-RUS">
                                <img src="/resources/images/ru.gif" alt="Русский"/>
                                <spring:message code="label.russian"/>
                            </a>
                        </li>
                        <li>
                            <a href="?lang=en" class="language" rel="en-US">
                                <img src="/resources/images/en.gif" alt="English"/>
                                <spring:message code="label.english"/>
                            </a>
                        </li>
                    </ul>
                </li>
                <li><a href="/user"><spring:message code="label.setting"/></a></li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li><a href="/logout"><spring:message code="label.logout"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/login"><spring:message code="label.enter"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
