$(document).ready(() => {
    let i = 1;

    $('#btn-add-skill').click(() => {
        $('#skills').append(
            '<div class="form-group col-md-2" id="' + i + '">' +
            '<input type="text" name="skills[' + i + ']" class="form-control"/>' +
            '</div>'
        );
        i++;
    })

    $('#btn-delete-skill').click (() => {
        i--;
        $('#' + i).remove();
    })
});
