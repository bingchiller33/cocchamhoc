/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function collapseEvent(e) {
    const targetId = e.getAttribute("data-target");
    const target = document.getElementById(targetId);

    if ([...target.classList].indexOf("collapsed") === -1) {
        target.classList.add("collapsed");
    } else {
        target.classList.remove("collapsed");
    }
}

function selectFilter(e) {
    let target = e.target;
    let name = target.getAttribute("name");
    let value = target.getAttribute("value");
    applyFilter(name, value);
}

function applyFilter(name, value) {
    let searches = new URLSearchParams(location.search);
    searches.set(name, value);
    location.search = searches.toString();
}