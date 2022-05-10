$(document).ready(() => {

    let link = document.getElementById('btn-link');
    let button = document.getElementById('btn')

    $.ajaxSetup({
        headers:
            {'X-CSRF-TOKEN': $('#csrf').val()}
    });

    $('#btn').click(() => {
        $.ajax({
            url: link.value,
            method: 'PATCH',
            success: () => {
                processResponse();
            },
            error: (msg) => {
                console.log(msg);
            }
        });
    })

    function processResponse() {
        let temp = button.innerText;
        button.innerText = button.name;
        button.name = temp;

        temp = link.value;
        link.value = link.name;
        link.name = temp;
    }
});
