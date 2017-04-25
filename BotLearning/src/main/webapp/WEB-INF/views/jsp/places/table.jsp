<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../fragments/header.jsp"/>
<script type='text/javascript'>
    $(document).ready(function () {
        var table = $('#places').dataTable({
            "bServerSide": true,
            "language": {"url": "/resources/datatable/lang/dataTables.${pageContext.response.locale}"},
            "sAjaxSource": "/api/places",
            "bProcessing": true,
            "bRetrieve": true,
            "searching": true,
            "columnDefs": [
                {
                    "render": function (data, type, row) {
                        return ' <a  data = \"' + data + "\" href=\"/places/update/" + data + '\" > <img src="/resources/images/pen-20.png"/></a> |' +
                                //'<a href=\"/places/' + data + '\">Details</a> |' +
                                ' <a href=\"/places/delete/' + data + '\"><img src="/resources/images/delete-20.png"/></a> ';
                    },
                    "width": "70px",
                    "targets": 0
                },
            ],
            "columns": [
                {"data": "id"},
                {"data": "name"},
                {"data": "type"},
                {"data": "description"},
                {"data": "worktime"},
            ]
        });

        $('#places tbody').on('click', 'tr', function () {

            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            var href = $('a:contains(" ")', this).attr('data');
            window.location.href = "/places/" + href;

        });
    });
</script>
<body>
<jsp:include page="../fragments/menu.jsp"/>
<div id="demo">
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <%--<div class="btn-group btn-group-justified pull-right">--%>
        <%--<a href="/bestbyprice" class="btn btn-default btn-success">ghgh</a>--%>
        <%--<a href="/best" class="btn btn-default btn-success">ghhhhhhh</a>--%>
    <%--</div>--%>

    <h2><spring:message code="label.places"/></h2>
    <p>
        <a href="/places/add"><spring:message code="label.addplace"/></a>
    </p>
    <table id="places" class="table table-striped table-bordered hover" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th></th>
            <th><spring:message code="label.name"/></th>
            <th><spring:message code="label.type"/></th>
            <th><spring:message code="label.description"/></th>
            <th><spring:message code="label.worktime"/></th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>