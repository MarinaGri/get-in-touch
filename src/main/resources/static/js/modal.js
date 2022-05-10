$(document).ready(() => {

    $('#delete-acc').click(() => {
        showConfirm();
    })

    function showConfirm() {
        if (ctx) {

            if (confirm('By clicking "OK" you confirm your action')) {
                deleteEntity();
            } else {
                window.location.pathname = ctx + '/profile'
            }
        }
    }

    $.ajaxSetup({
        headers:
            {'X-CSRF-TOKEN': $('#csrf').val()}
    });

    function deleteEntity() {
        $.ajax({
            url: ctx + '/profile/delete',
            method: 'DELETE',
            success: () => {
                alert('Account has been successfully deleted');
                window.location.pathname = ctx + '/logout'
            },
            error: (msg) => {
                console.log(JSON.parse(msg.responseText).errors);
            }
        });
    }
});
