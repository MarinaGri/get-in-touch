$(document).ready(() => {

    $('.chat').each((index, value) => {
        $(value).click(() => {
            getComments(getId(value));
        })
    });

    $.ajaxSetup({
        headers:
            {'X-CSRF-TOKEN': $('#csrf').val()}
    });


    function addBtnListener(id){
        $('#btn-' + id).click(() => {
            addComment(id);
        })
    }

    function addDeleteListeners() {

        $('.trash').each((index, value) => {
            $(value).click(() => {
                deleteEntity(getId(value));
            })
        });
    }

    function deleteEntity(id) {
        $.ajax({
            url: ctx + '/comments/' + id,
            method: 'DELETE',
            success: () => {
                document.getElementById(id).remove();
            },
            error: (request) => {
                console.log(JSON.parse(request.responseText).message);
            }
        });
    }

    function getComments(id) {

        $.ajax({
            url: ctx + '/comments?vacancy-id=' + id,
            method: 'GET',
            dataType: 'json',
            success: (response) => {
                showComments(response, id);
                addBtnListener(id)
                addDeleteListeners();
            },
            error: (request) => {
                console.log(JSON.parse(request.responseText).errors);
            }
        });
    }

    function addComment(id) {
        let comment = {
            "text": document.getElementById('text-area-' + id).value,
            "vacancyId": id
        };

        $.ajax({
            url: ctx + '/comments/',
            method: 'POST',
            dataType: 'json',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(comment),
            success: (response) => {
                addCommentHtml(response);
                addDeleteListeners(id);
            },
            error: (request) => {
                alert(JSON.parse(request.responseText).message);
            }
        });
    }

    function addCommentHtml(comment) {
        document.getElementById('new-' + comment.vacancyId).innerHTML += getCommentHtml(comment);
    }

    function showComments(comments, id) {
        let html = '<div id="new-' + id + '">';

        for (let key in comments) {
            html += getCommentHtml(comments[key]);
        }

        html += '</div>'

        html += '<label for="exampleFormControlTextarea1" class="form-label"><b>Comments</b></label>';
        html += '<textarea name="comment" class="form-control" id="text-area-' + id + '" rows="2"></textarea>';
        html += '<button type="submit" id="btn-' + id + '" name="post" value="" class="button">Post</button>';

        document.getElementById(id).innerHTML = html;


    }

    function getId(value) {
        return value.id.split('-')[1];
    }

    function getCommentHtml(comment) {
        let userId = document.getElementById('user-id').value;

        let html = '';
        html += '<div class="comment" id="' + comment.id + '">';
        html += '<a href="' + ctx + '/profile/' + comment.user.id + '">';

        if (comment.user.avatarLink !== null) {

            html += '<div class="round-small">' +
                '<img class="avatar" src="' + ctx + '/files/' + comment.user.avatarLink.link + '" alt="avatar">' +
                '</div>';

        } else {
            html += '<img class="round-small" src="' + ctx + '/img/svg/person-circle.svg" alt="person">';
        }

        html += '</a>';
        html += '<span class="name">' + comment.user.firstName + ' ' + comment.user.lastName + '</span>';

        if (comment.user.id === userId) {
            html += '<img class="trash" id="trash-' + comment.id + '" src="' + ctx + '/img/svg/trash.svg" alt="trash">';
        }
        html += '<div class="text">' + comment.text + '</div>'
        html += '</div>';
        return html;
    }

});
