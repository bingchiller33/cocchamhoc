
const ratingRadios = document.querySelectorAll('input[name="rating"]');
ratingRadios.forEach(radio => {
    radio.addEventListener('change', () => {
        var href = window.location.href.split("?");
        var splitHref = href[1].split('&');
        var conponentHref = '';
        splitHref.forEach((s, i) => {
            if (i < splitHref.length - 2) {
                conponentHref += `${s}&`;
            } else if (i < splitHref.length - 1) {
                conponentHref += `${s}`;
            }
        });
        if (conponentHref === '') {
            window.location.href = `${href[0]}?${href[1]}&rating=${radio.value}`;
        } else {
            window.location.href = `${href[0]}?${conponentHref}&rating=${radio.value}`;
        }
    });
});
        