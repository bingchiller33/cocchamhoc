/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const getParentElement = (element, parent) => {
    while (element.parentElement) {
        if (element.parentElement.matches(parent)) {
            return element.parentElement;
        }
        element = element.parentElement;
    }
};

const handleClick = (e) => {
    let courseId = getParentElement(e.target, '.courseRow').querySelector('.course_id');
    let cid = getParentElement(e.target, '#formRevork').querySelector('.cId');
    let status = getParentElement(e.target, '#formRevork').querySelector('.status');
    cid.value = courseId.innerText;  
    if(e.target.innerText==='UnRevoke'){
        status.value = 'Normal';
    }
    if (e.target.innerText === 'Revoke'){
        status.value = 'Revoke';
    }
    document.querySelector('#formRevork').submit();
};