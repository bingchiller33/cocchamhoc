<%-- 
    Document   : certificate
    Created on : Jun 28, 2023, 10:55:41 AM
    Author     : LAPTOP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<link rel="stylesheet" href="../assets/css/notFound.css">
<style>
    .container_certificate {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
    }

    .certificate_img {
        height: 54px;
    }

    .img_link {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .item_certificate {
        border: 1px solid #ccc;
        width: 86%;
        background-color: white;
        margin: 12px 0;
    }

    .certificate_head {
        display: flex;
        margin: 12px 12px 2px;
        border-bottom: 1px solid rgb(199, 199, 199);
    }

    .certificate_name {
        display: flex;
        align-items: center;
        margin-left: 20px;
    }

    .name_teacher {
        margin: 0 0 10px 0;
        font-size: 12px;
    }

    .name_course {
        color: #2262a6;
        margin: 0 0 8px 0;
        text-decoration: none;
    }
    .name_course:hover, img_link:hover {
        opacity: 0.8;
    }
    .name_link {
        text-decoration: none;
    }

    .certificate_footer {
        display: flex;
        justify-content: center;
        margin: 0 12px 2px;
    }

    .btn_link-item {
        font-size: 12px;
        cursor: pointer;
        padding: 2px 0;
        background-color: white;
        width: 100%;
        border: none;
        border-radius: 8px;
    }

    .btn_link-item:focus {
        border: 2px solid #000;
    }

    .btn_link-item:hover {
        color: #2262a6;
    }
    .certificate_body {
        display: none;
        margin: 0 12px;
        border-bottom: 1px solid #ccc;
        margin-bottom: 3px;
    }
    .certificate_ins {
        font-weight: bold;
        margin: 6px 0;
        font-size: 14px;
    }
    .title_name::before {
        content: '• ';
    }
    .title_name {
        font-size: 14px;
        min-width: 180px;
        color: rgb(72, 72, 72);
    }
    .name_desc {
        font-size: 14px;
        color: rgb(72, 72, 72);
    }

    .not_found-btn {
        cursor: pointer;
    }
</style>   
<div class="container_certificate">
    <c:if test="${!listCourse.isEmpty()}">
        <c:forEach var="item" items="${listCourse}">
            <div class="item_certificate">
                <div class="certificate_head">
                    <div class="certificate_img">
                        <a href="/certificate?id=${item.id}">
                            <img class="img_link" src="https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://d2j5ihb19pt1hq.cloudfront.net/certificates/cert-specialization.png?auto=format%2Ccompress&dpr=1&w=&h=72" alt="">
                        </a>
                    </div>
                    <div class="certificate_name">
                        <div>
                            <a href="/certificate?id=${item.id}" class="name_link">
                                <p class="name_course">${item.title}</p>
                            </a>
                            <p class="name_teacher">${item.lecturer}</p>
                        </div>
                    </div>
                </div>
                <div class="certificate_body">
                    <p class="certificate_ins">Instructions</p>
                    <table> 
                        <tbody>
                            <tr>
                                <td class="title_name">Name:</td>
                                <td class="name_desc">${item.title}</td>
                            </tr>
                            <tr>
                                <td class="title_name">Issuing Organization:</td>
                                <td class="name_desc">COK cham hoc</td>
                            </tr>
                            <tr>
                                <td class="title_name">Durations:</td>
                                <td class="name_desc">${item.durationInSeconds}s</td>
                            </tr>
                            <tr>
                                <td class="title_name">Level:</td>
                                <td class="name_desc">${item.level.description}</td>
                            </tr>
                            <tr>
                                <td class="title_name">Category:</td>
                                <td class="name_desc">${item.category.description}</td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="certificate_footer">
                    <button class="btn_link-item" onclick="handleClick(event)">Infomation <i class="fa-solid fa-angle-down"></i></button>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${listCourse.isEmpty()}">
        <div class="course_list_not_found">
            <div class="not_found-item">
                <div class="not_found_item-head">
                    <h1 class="not_found_item_head-title">
                        <span class="not_found_item_head-first">4</span><span class="not_found_item_head-second">0</span><span class="not_found_item_head-third">4</span></h1>
                </div>
                <div class="not_found_item_body">
                    <h1 class="not_found_item_body-title">Oops... You have not completed this course</h1>
                    <p class="not_found_item_body-desc">
                        <span>Sorry, can't found any certificate, you must enroll some course before and complete it</span> <br/>
                        <span>Make sure you've taken the course </span>
                    </p>
                </div>
                <div class="not_found-btn">
                    <a class="not_found_btn-link" onclick="window.history.back()">Back</a>
                </div>
            </div>
        </div>
    </c:if>
</div>
<script>
    function  getParentElement(element, parent) {
        while (element.parentElement) {
            if (element.parentElement.matches(parent)) {
                return element.parentElement;
                break
            }
            element = element.parentElement;
        }
    }
    function handleClick(event) {
        var parentElement = getParentElement(event.target, '.item_certificate');
        var btn = event.target;
        var btnParent = getParentElement(event.target, '.certificate_footer');
        var icon = btnParent.querySelector('.fa-solid');
        console.log(icon.className)
        icon.className = icon.className === 'fa-solid fa-angle-down' ? 'fa-solid fa-angle-up' : 'fa-solid fa-angle-down';
        parentElement.querySelector('.certificate_body').style.display
                = getComputedStyle(parentElement.querySelector('.certificate_body')).display
                === 'none' ? 'block' : 'none';
    }
</script>  
