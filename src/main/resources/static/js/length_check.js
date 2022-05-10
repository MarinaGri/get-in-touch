$(document).ready(() => {

    let counter = document.getElementsByClassName('count')[0];
    let name = counter.id.substring(counter.id.indexOf('.') + 1);
    let length = parseInt(counter.innerText.substring(counter.innerText.indexOf('/') + 1));

    let element = document.getElementById(name);

    let isValid = function () {
        counter.innerText = element.value.length + '/' + length;

        if (element.value.length > length || element.value.length < 5) {
            counter.style.color = 'red';
            return false;
        } else {
            counter.style.color = 'black';
            return true;
        }
    }

    element.oninput = element.onchange = element.onload = isValid;

});
