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