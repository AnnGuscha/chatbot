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
            {
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            },
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