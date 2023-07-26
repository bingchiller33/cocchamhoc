
const ratingRadios = document.querySelectorAll('input[name="rating_1"]');

const newHref = [];
ratingRadios.forEach(radio => {
    radio.addEventListener('change', (e, i) => {
        document.querySelector('#btn-submit').removeAttribute("disabled");
        document.querySelector('#btn-submit').classList.remove('btn-submit_o');
        document.querySelector('#btn-submit').classList.add('btn-submit');
        var href = window.location.href.split("?");
        var splitHref = href[1].split('&');
        var conponentHref = '';
        // cat link ra de tranh bi trong len nhau
        //Cut the link to avoid being planted on top of each other
        splitHref.forEach((s) => {
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
const formRate = document.querySelector('#rating');
formRate.addEventListener('click', () => {
    let querySelector = formRate.querySelector('#status_complete').value;
    if (parseInt(querySelector) === 1) {
        const cancel = document.querySelector('.containerRate');
        cancel.style.display = 'block';
    } else {
        alert("Please complete this course if you want to rate!");
    }
});

document.querySelector('.cancel').addEventListener('click', (e) => {
    const cancel = document.querySelector('.containerRate');
    cancel.style.display = 'none';
});

var id = window.location.href.split("=");
document.getElementById('id').value = id[1];
const getParentElement = (element, parent) => {
    while (element.parentElement) {
        if (element.parentElement.matches(parent)) {
            return element.parentElement;
        }
        element = element.parentElement;
    }
};
const handleClickChoice = (event) => {
    let userChoice_item = getParentElement(event.target, '.user_choice').querySelector('.user_choice_item');
    if (userChoice_item.style.display !== 'block') {
        userChoice_item.style.display = 'block';
    } else {
        userChoice_item.style.display = 'none';
    }
};
const handleChocie = (event) => {
    console.log(event.target);
    let userChoice_item = getParentElement(event.target, '.review').querySelector('.user_choice_item');
    let bodyContent = getParentElement(event.target, '.review').querySelector('.body-content');
    let reviewUpdate = getParentElement(event.target, '.review').querySelector('.reviewUpdate');
    let formStatus = document.querySelector('#form_status');
    if (event.target.innerText === 'Report') {
        document.querySelector('#status').value = event.target.innerText;
        formStatus.submit();
    }
    if (event.target.innerText === 'Edit') {
        reviewUpdate.type = 'text';
        reviewUpdate.value = bodyContent.innerText;
        reviewUpdate.focus();
        reviewUpdate.setSelectionRange(reviewUpdate.value.length, reviewUpdate.value.length);
        if (userChoice_item.style.display !== 'none') {
            userChoice_item.style.display = 'none';
        }
        reviewUpdate.addEventListener('keydown', (kevent) => {
            if (kevent.key === 'Enter') {
                formStatus.submit();
            }
        });
        reviewUpdate.addEventListener('blur', () => {
            if (reviewUpdate.type === 'text') {
                reviewUpdate.value = "";
                reviewUpdate.type = 'hidden';
            }
        });
    }
    if (event.target.innerText === 'Delete') {
        document.querySelector('#status').value = event.target.innerText;
        formStatus.submit();
    }
};
const hanleFilterStar = (event) => {
    let formStatus = document.querySelector('#form_status');
    let filterRate = getParentElement(event.target, '.search_rate_star').querySelector('.filterRate');
    if (event.target.innerText === 'All') {
        filterRate.value = -1;
        formStatus.submit();
    } else if (event.target.innerText === '5 Star') {
        filterRate.value = 5;
        formStatus.submit();
    } else if (event.target.innerText === '4 Star') {
        filterRate.value = 4;
        formStatus.submit();
    } else if (event.target.innerText === '3 Star') {
        filterRate.value = 3;
        formStatus.submit();
    } else if (event.target.innerText === '2 Star') {
        filterRate.value = 2;
        formStatus.submit();
    } else if (event.target.innerText === '1 Star') {
        filterRate.value = 1;
        formStatus.submit();
    }
    console.log(filterRate.value)
};
const selectFilterRate = (e) => {
    let formStatus = document.querySelector('#form_status');
    let pagination = getParentElement(event.target, '.course-pagination').querySelector('.pagination');
    pagination.value = e.value;
    console.log(e.value);
    formStatus.submit();
}