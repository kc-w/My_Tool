$().ready(function () {
    $(".change").click(function () {
        var id=$(".change").val();
        $("#changeid").val(id);
    })
})