
const ratingRadios = document.querySelectorAll('input[name="rating_1"]');

const newHref = [];
ratingRadios.forEach(radio => {
    radio.addEventListener('change', (e) => {
        document.querySelector('#btn-submit').removeAttribute("disabled");
        document.querySelector('#btn-submit').classList.remove('btn-submit_o');
        document.querySelector('#btn-submit').classList.add('btn-submit');
        var href = window.location.href.split("?");
        var splitHref = href[1].split('&');
        var conponentHref = '';
        // cat link ra de tranh bi trong len nhau
        //Cut the link to avoid being planted on top of each other
        splitHref.forEach((s, i) => {
            if (i < splitHref.length - 2) {
                conponentHref += `${s}&`;
            } else if (i < splitHref.length - 1) {
                conponentHref += `${s}`;
            }
        });
        if (conponentHref === '') {
            newHref.push(`${href[0]}?${href[1]}&rating=${radio.value}`);
        } else {
            newHref.push(`${href[0]}?${conponentHref}&rating=${radio.value}`);
        }
    });
});

document.querySelector('#rating').addEventListener('click', () => {
    const cancel = document.querySelector('.containerRate');
    cancel.style.display = 'block';
});

document.querySelector('.cancel').addEventListener('click', (e) => {
    const cancel = document.querySelector('.containerRate');
    cancel.style.display = 'none';
});

var id = window.location.href.split("=");
document.getElementById('id').value = id[1]; 