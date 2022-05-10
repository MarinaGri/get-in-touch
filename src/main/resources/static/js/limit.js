$(document).ready(() => {

    let innerText = document.getElementById('limit').innerText;
    let mess = innerText.split(': ')[0];

    let param = '?limit=';

    let index = document.location.search.lastIndexOf(param) + param.length;
    let limit = parseInt(document.location.search.substring(index));

    if(isNaN(limit)){
        limit = 1;
    }

    document.getElementById('limit').innerText = mess + ': ' + limit;

    $('#plus').click(() => {
        limit++;
        setLimit();
    });

    $('#dash').click(() => {
        if (limit > 1) {
            limit--;
            setLimit();
        }
    });

    function setLimit() {
        $('.limit').each((index, value) => {
            let ind = value.href.lastIndexOf(param) + param.length;
            value.href = value.href.substring(0, ind) + limit;
        });
        document.getElementById('limit').innerText = mess + ': ' + limit;
    }
})
