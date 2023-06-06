<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <title>JSP Page</title>
        <style>
            .courseDetail-container{
                padding: 50px 0 ;
                width: 70vw;
                margin: 0 auto;
            }
            .courseDetail-container .row:nth-of-type(1){
                justify-content: space-between;
                width: 100%;
                margin-bottom: 10%;
            }
            .courseDetail-container .row:nth-of-type(1) a{
                padding: 10px 30px;
                background-color: #FCD980;
                border-radius: 6px;
            }
            .courseDetail-container .row:nth-of-type(2){
                justify-content: flex-start;
                margin-bottom: 2%;
            }
            .courseDetail-container .row:nth-of-type(2) a{
                background-color: #0099ff;
                padding: 5px 20px;
                border-radius: 20px;
                margin: 0 20px;
            }
            .active{
                color: white;
            }
            .courseDetail-container .row:nth-of-type(3){
                justify-content: flex-start;
                margin-bottom: 2%;
            }
            .courseDetail-container .row:nth-of-type(4) img{
                width: 100%;
                object-fit: contain;
                border-radius: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div class="courseDetail-container">
            <div class="row">
                <div>
                    <h1>${courseData.title}</h1>
                    <p><i class="fas fa-graduation-cap"></i> Lecturer: ${courseData.lecturer}</p>
                </div>
                <c:if test="${isEnroll == true}">
                    <div><a href="#">Go To Course</a></div>
                </c:if>
                <c:if test="${isEnroll == false}">
                    <div><a href="enroll?id=${courseID}">Enroll Now</a></div>
                </c:if>
            </div>
            <div class="row">
                <a class="active">Description</a>
                <a>Reviews</a>
                <a>Syllabus</a>
            </div>
            <div class="row">
                <p>${courseData.description}</p>
            </div>
            <div class="row">
                <img src="https://cdn.vox-cdn.com/thumbor/Si2spWe-6jYnWh8roDPVRV7izC4=/0x0:1192x795/1400x788/filters:focal(596x398:597x399)/cdn.vox-cdn.com/uploads/chorus_asset/file/22312759/rickroll_4k.jpg">
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>
