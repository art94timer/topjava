const mealAjaxUrl = 'profile/meals/'
const ctx = {
    ajaxUrl: mealAjaxUrl
};

function filterMeals() {
    const filterForm = $("#filterForm");
    $.get(mealAjaxUrl + "filter", filterForm.serialize(), function (data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    });
}

function clearFilter() {
    $("#filterForm")[0].reset();
    updateTable();
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    )
});

