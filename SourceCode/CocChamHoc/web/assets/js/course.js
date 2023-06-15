const usernameEle = document.getElementById('course-name');


const btnSubmit = document.getElementById('submit');
const inputEles = document.querySelectorAll('btn-save');

btnSubmit.addEventListener('click', function (e) {
    Array.from(inputEles).map((ele) =>
        ele.classList.remove('success', 'error')
    );
    let isValid = checkValidate();

    if (isValid) {
        alert('Gửi đăng ký thành công');
    }else{
        e.preventDefault()
    }
});

function checkValidate() {
    let usernameValue = usernameEle.value;
   
  
  console.log(typeof usernameValue);
    let isCheck = true;

    if ( usernameValue === '' ) {
        setError(usernameEle, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(usernameEle);
    }



    return isCheck;
}

function setSuccess(ele) {
    ele.parentNode.classList.add('success');
}

function setError(ele, message) {
    let parentEle = ele.parentNode;
    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}

