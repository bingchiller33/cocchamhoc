<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %> 
<%@ page import="utils.*" %>
<%@ page import="model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Course</title>
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="../assets/css/sliderStyle3.css"/>
        <link rel="stylesheet" href="../assets/css/myCourse.css">
        <link rel="stylesheet" href="../assets/css/notFound.css">
        <link rel="stylesheet" href="../assets/css/base.css">
    </head>
    <style>
        .progress {
            margin-top: 5px;
            width: 100%;
            max-width: 500px;
            background: white;
            border-radius: 20px;
            background-color: rgb(230, 230, 230);
            overflow: hidden;
            height: 12px;
        }
        
        .progress .progress__bar {
            height: 100%;
            border-radius: 20px;
            background: repeating-linear-gradient(
                135deg,
                #036ffc,
                #036ffc 20px,
                #1163cf 20px,
                #1163cf 40px
                );
        }
    </style>
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
                                        <c:if test="${counts.get(item)==0}">
                                            <p>Progress: 0%</p>
                                        </c:if>
                                        <c:if test="${counts.get(item)!=0}">
                                            <p>Progress: ${progress.get(item)*100/counts.get(item)}%</p>
                                        </c:if>
                                        
                                    </div>
                                    <div class="progress"> 
                                        <c:if test="${counts.get(item)==0}">
                                            <div class="progress__bar" style="width: 0%;"></div>
                                        </c:if>
                                        <c:if test="${counts.get(item)!=0}">
                                            <div class="progress__bar" style="width: ${progress.get(item)*100/counts.get(item)}%;"></div>
                                        </c:if>
                                    </div>
                                    <div>
                                        <p class="item_desc-lecturer">
                                            <span class="desc_lecturer-title">Lecturer: </span>
                                            <span class="desc-lecturer-name">${item.lecturer}</span>
                                        </p>
                                    </div>
                                </div>
                                <div class="btn_link_course">
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
