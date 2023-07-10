<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %> 
<%@ page import="model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>In Progress</title>
        <link rel="stylesheet" href="../assets/css/sliderStyle3.css"/>
        <link rel="stylesheet" href="../assets/css/myCourse.css">
        <link rel="stylesheet" href="../assets/css/notFound.css">
        <link rel="stylesheet" href="../assets/css/base.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <%  
            User user = (User) request.getSession().getAttribute("user");
        %>
        <c:set var="listProgressCourse" value="${listProgressCourse}" />
        <c:set var="listChapter" value="${listChapter}" />
        <c:set var="lesson" value="${lesson}" />
        <c:set var="noCourse" value="${noCourse}" />
        <%@include file="/components/header.jspf" %>
        <%@include file="/components/slider.jspf" %>

        <div class="container_course">
            <div class="course_header"> 
                <div class="header_container">
                    <p class="header_container-header">Welcome back!</p>

                </div>
            </div>
            <div class="tab_list">
                <div class="tab_list-desc"><a href="mycourse">My Course</a></div>
                <div class="tab_list-desc"><a href="inprogress">In Progress</a></div>                                         
            </div>
            <div class="course_body"> 
                <c:if test="${!listProgressCourse.isEmpty()}">
                    <c:forEach var="item" items="${listProgressCourse}">

                        <div class="course_item">
                            <div class="course_component">
                                <div class="course_item-img">
                                    <img src="${item.imgUrl}" alt="" class="item_img-detail" />
                                </div>
                                <div class="course_item-desc">
                                    <div>
                                        <p class="item_desc-title"><a href="/gotoLearn?courseId=${item.id}" class="btn_course-link">Course | ${item.title} </a></p>
                                        <span class="item_desc-detail">${item.description}</span>
                                    </div>
                                    <br>
                                    <div id="progress-bar">
                                        <div id="progress"></div>

                                    </div>
                                </div>

                                <div class="btn_link_course">

                                    <p class="btn_course-duration"> 

                                        <span>Complete 10%</span></p>
                                </div>

                            </div>
                        </div>

                    </c:forEach>
                </c:if>
                <c:if test="${noCourse == null || user == null}">

                    <div id="root-404">

                        <img src="/assets/img/img-404.jpg" alt="Page not found image"/>

                    </div>

                </c:if>
            </div> 
        </div>
        <%@include file="/components/footer.jspf" %>
        <style>
            .tab_list-desc{
                width: 8%;
            }
            .cou2{
                color: blue;
            }
            .header_container-header{
                color: blue;
            }
            #root-404 {
                margin: 5rem;
                display: flex;
            }

            #root-404 > img {
                object-fit: none;
                margin: auto;
            }

            #progress-bar {
                width: 400px;
                height: 10px;
                background-color: #f2f2f2;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            }

            #progress {
                height: 100%;
                width:  10%;
                background-color: #007bff;
                border-radius: 10px;
                transition: width 0.5s ease-in-out;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            }
        </style>
    </body>
</html>
