$(document).ready(() => {

    $('.trash').each((index, value) => {
        $(value).click(() => {
            deleteEntity(getId(value));
        })
    });

    $.ajaxSetup({
        headers:
            {'X-CSRF-TOKEN': $('#csrf').val()}
    });

    function deleteEntity(id) {
        $.ajax({
            url: ctx + '/resumes/' + id,
            method: 'DELETE',
            success: () => {
                document.getElementById(id).remove();
            },
            error: (msg) => {
                console.log(JSON.parse(msg.responseText).errors);
            }
        });
    }

    function getId(value) {
        return value.id.split('-')[1];
    }
});
