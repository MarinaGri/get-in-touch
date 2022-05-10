$(document).ready(() => {

    addListeners();

    function addListeners() {

        $('.edit').each((index, value) => {
            $(value).click(() => {
                showEditArea(getId(value));
            })
        });

        $('.trash').each((index, value) => {
            $(value).click(() => {
                deleteEntity(getId(value));
            })
        });
    }

    $.ajaxSetup({
        headers:
            {'X-CSRF-TOKEN': $('#csrf').val()}
    });

    function deleteEntity(id) {
        $.ajax({
            url: ctx + '/posts/' + id,
            method: 'DELETE',
            success: () => {
                document.getElementById(id).remove();
            },
            error: (msg) => {
                $('#error').innerText = msg;
            }
        });
    }

    $('#btn').click(() => {
        let text = CKEDITOR.instances['text'].getData();
        addPost($('#title').val(), text);
    })

    function addPost(title, text) {

        let post = {
            "title": title,
            "text": text,
        };

        $.ajax({
            url: ctx + '/posts',
            method: 'PUT',
            dataType: 'json',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(post),
            success: (response) => {
                $('#error').text('')
                addPostHtml(response)
                addListeners();
            },
            error: (request) => {
                $('#error').text(JSON.parse(request.responseText).errors);
            }
        });
    }


    function editPost(title, text, id) {

        let post = {
            "id": id,
            "title": title,
            "text": text,
        };

        $.ajax({
            url: ctx + '/posts/' + id,
            method: 'PATCH',
            dataType: 'json',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(post),
            success: (response) => {
                addPostHtml(response)
                addListeners();
            },
            error: (request) => {
                addPostHtml(post);
                showEditArea(id);
                $('#error-' + id).text(JSON.parse(request.responseText).errors);
            }
        });
    }

    function showEditArea(editId) {
        let textId = 'text-edit-' + editId;
        let titleValue = document.getElementById(editId).getElementsByClassName('title')[0].innerText;
        let textValue = document.getElementById(editId).getElementsByClassName('text')[0].innerText;

        let html = '';
        html += '<div class="mb-3">';
        html += '<label class="form-label">Title</label>';
        html += '<input type="text" id="title-edit" name="title" value="' + titleValue + '" class="form-control" required>';
        html += '<span class="form-text" id="error-' + editId + '"></span>';
        html += '</div>';
        html += '<div class="mb-3">';
        html += '<textarea id="' + textId + '" name="text" class="form-control" rows="2">';
        html += '</textarea>';
        html += '</div>';
        html += '<button type="submit" id="btn-edit" class="button">Edit</button>'

        document.getElementById(editId).innerHTML = html;


        CKEDITOR.replace(textId);

        CKEDITOR.instances[textId].setData(textValue);

        $('#btn-edit').click(() => {

            let title = document.getElementById('title-edit').value;
            let text = CKEDITOR.instances[textId].getData();

            document.getElementById(editId).remove();

            editPost(title, text, editId);
        });
    }

    function getId(value) {
        return value.id.split('-')[1];
    }

    function addPostHtml(post) {
        $('#posts').append(
            '<div class="post" id="' + post.id + '">' +
            '<div class="post post-block">' +
            '<img class="trash" id="trash-' + post.id + '" src="' + ctx + '/img/svg/trash.svg" alt="trash">' +
            '<img class="edit" id="edit-' + post.id + '" src="' + ctx + '/img/svg/pencil-square.svg" alt="pencil">' +
            '<div class="title">' + post.title + '</div>' +
            '<div class="text">' + post.text + '</div>' +
            '</div>'
        );
    }
})
