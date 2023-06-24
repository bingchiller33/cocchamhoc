<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/rating.css"/>
        <style>
            .courseDetail-container{
                padding: 50px 0 ;
                width: 70vw;
                min-width: 720px;
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
                justify-content: center;
                margin-bottom: 2%;
            }
            .courseDetail-container .row:nth-of-type(2) p{
                margin: 20px 0;
                padding: 10px 20px;
                cursor: pointer;
            }
            .active{
                border-bottom: 4px solid transparent;
                border-image: linear-gradient(to right, #1C1E53 0%,  #FCD980 100%);
                border-image-slice: 1;
                border-width: 0px 0px 4px 0px;
                color: #2405F2;
            }
            .hidden{
                display: none;
            }
            .visible{
                display: inline;
                width: 100%;
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
        <%! 
            Random random = new Random();
            public String getNameRandom() {
                int randomIndex1 = random.nextInt(26);
                int randomIndex2 = random.nextInt(26);
                char randomLetter1 = (char) ('A' + randomIndex1);
                char randomLetter2 = (char) ('A' + randomIndex2);
                return randomLetter1 + "" + randomLetter2;
            }
        %>
        <%@include file="/components/header.jspf" %>
        <div class="courseDetail-container">
            <div class="row">
                <div>
                    <h1>${courseData.title}</h1>
                    <p><i class="fas fa-graduation-cap"></i> Lecturer: ${courseData.lecturer}</p>
                    <div class="overview_rate">
                        <%@include file="/components/rating.jsp" %>
                        <div class="overview_rate">
                            <small class="avg_ratings">${rateAvg}</small> 
                            <small class="total_ratings">${countRating} ratings</small>
                            <small class="separate_ratings">-</small>
                            <small class="total_reviewings">${reviewNo} reviews</small>
                        </div>
                    </div>
                </div>
                <c:if test="${isEnroll == true}">
                    <div><a href="/gotoLearn?courseId=${courseID}">Go To Course</a></div>
                </c:if>
                <c:if test="${isEnroll == false}">
                    <div><a href="enroll?id=${courseID}">Enroll Now</a></div>
                </c:if>
            </div>
            <div class="row">
                <p id="des" class="active" onclick="view(this)">DESCRIPTION</p>             
                <p id="rev" onclick="view(this)">REVIEWS</p>
                <p id="syl" onclick="view(this)">SYLLABUS</p>
            </div>
            <div class="row">
                <div id="des2" class="visible">
                    <p>${courseData.description}</p>
                </div>
                <div id="rev2" class="hidden width">
                    <c:if test="${review!=null}">
                        <div class="container_review">
                            <c:forEach var="item" items="${review}">
                                <div class="review">
                                    <div class="review-header">
                                        <div class="header-user"><p class="user-name"><%=getNameRandom()%></p></div>
                                        <span class="header-time">${item.rateDate}</span>
                                    </div>
                                    <div class="review-body">
                                        <p class="body-content">${item.review}</p>
                                    </div>
                                </div>
                            </c:forEach>    
                        </div
                    </c:if>
                    <c:if test="${review==null}">
                        <p>${Review}</p>
                    </c:if>
                </div>
                <div id="syl2" class="hidden">
                    <c:choose>
                        <c:when test="${empty lessonData}">
                            <p>No syllabus available</p>
                        </c:when>
                        <c:otherwise>
                            <%@include file="/components/viewSyllabus.jspf" %>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <img src="${courseData.imgUrl}"" alt="Course Image">
            </div>
        </div> 
        <%@include file="/components/review.jsp" %>
        <%@include file="/components/footer.jspf" %>
        <script>
            function view(obj) {
                var des = document.getElementById("des");
                var des2 = document.getElementById("des2");
                var rev = document.getElementById("rev");
                var rev2 = document.getElementById("rev2");
                var syl = document.getElementById("syl");
                var syl2 = document.getElementById("syl2");
                if (des.className === "active") {
                    des.className = "";
                    des2.className = "hidden";
                }
                if (rev.className === "active") {
                    rev.className = "";
                    rev2.className = "hidden";
                }
                if (syl.className === "active") {
                    syl.className = "";
                    syl2.className = "hidden";
                }
                obj.className = "active";
                if (obj === des)
                    des2.className = "visible";
                if (obj === rev)
                    rev2.className = "visible";
                if (obj === syl)
                    syl2.className = "visible";
            }
        </script>
        <script src="/assets/js/rate.js"></script>
    </body>
</html>
