<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %> 
<%@ page import="model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Course</title>
        <link rel="stylesheet" href="../assets/css/myCourse.css">
        <link rel="stylesheet" href="../assets/css/notFound.css">
        <link rel="stylesheet" href="../assets/css/base.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <%  
            User user = (User) request.getSession().getAttribute("user");
        %>
        <c:set var="listMyCourse" value="${listMyCourse}" />
        <c:set var="noCourse" value="${noCourse}" />
        <%@include file="/components/header.jspf" %>
        <%@include file="/components/slider.jspf" %>
        <div class="container_course">
            <div class="course_header"> 
                <div class="header_container">
                    <p class="header_container-header">COC CHAM HOC</p>
                    <span>Success with COC CHAM HOC!</span>
                </div>
            </div>
            <div class="tab_list">
                <div class="tab_list-desc">My courses</div>
            </div>
            <div class="course_body"> 
                <c:if test="${!listMyCourse.isEmpty()}">
                    <c:forEach var="item" items="${listMyCourse}">
                        <div class="course_item">
                            <div class="course_component">
                                <div class="course_item-img">
                                    <img src="${item.imgUrl}" alt="" class="item_img-detail" />
                                </div>
                                <div class="course_item-desc">
                                    <div>
                                        <p class="item_desc-title">${item.title}</p>
                                        <span class="item_desc-detail">${item.description}</span>
                                    </div>
                                    <div>
                                        <p class="item_desc-lecturer">
                                            <span class="desc_lecturer-title">Lecturer: </span>
                                            <span class="desc-lecturer-name">${item.lecturer}</span>
                                        </p>
                                    </div>
                                </div>
                                <div class="btn_link_course">
                                    <p class="btn_course-duration"> <span>Duration</span> <span>${item.durationInSeconds}s</span></p>
                                    <a href="/gotoLearn?courseId=${item.id}" class="btn_course-link">Resume</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${noCourse == null || user == null}">
                    <div class="course_list_not_found">
                        <div class="not_found-item">
                            <div class="not_found_item-head">
                                <h1 class="not_found_item_head-title">
                                    <span class="not_found_item_head-first">4</span><span class="not_found_item_head-second">0</span><span class="not_found_item_head-third">4</span></h1>
                            </div>
                            <div class="not_found_item_body">
                                <h1 class="not_found_item_body-title">Oops... You Haven't Enrolled Any Course</h1>
                                <p class="not_found_item_body-desc">
                                    <span>Sorry, can't found any courses, you must enroll some course before</span> <br/>
                                    <span>Make sure you've taken the course</span>
                                </p>
                            </div>
                            <div class="not_found-btn">
                                <a class="not_found_btn-link" href="/">Homepage</a>
                            </div>
                        </div>
                    </div> 
                </c:if>
            </div> 
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>
