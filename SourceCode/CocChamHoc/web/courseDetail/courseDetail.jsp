<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
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
                    <div><a href="/learn/video?courseId=${courseID}">Go To Course</a></div>
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
                <div id="rev2" class="hidden">
                    <c:if test="${review==null}">
                        <p>No Review</p>
                    </c:if>
                    <c:if test="${review!=null}">
                        <p>${Review}</p>
                    </c:if>
                </div>
                <div id="syl2" class="hidden">
                    <c:if test="${syllabus==null}">
                        <p>No Syllabus</p>
                    </c:if>
                    <c:if test="${syllabus!=null}">
                        <p>Syllabus</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <img src="data:image/jpeg;base64,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" alt="Course Image">              
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
        <script>
            function view(obj) {
                var des = document.getElementById("des");
                var des2 = document.getElementById("des2");
                var rev = document.getElementById("rev");
                var rev2 = document.getElementById("rev2");
                var syl = document.getElementById("syl");
                var syl2 = document.getElementById("syl2");
                if (des.className === "active"){
                    des.className = "";
                    des2.className = "hidden";
                }
                if (rev.className === "active"){
                    rev.className = "";
                    rev2.className = "hidden";
                }
                if (syl.className === "active"){
                    syl.className = "";
                    syl2.className = "hidden";
                }
                obj.className = "active";
                if(obj === des)
                    des2.className = "visible";
                if(obj === rev)
                    rev2.className = "visible";
                if(obj === syl)
                    syl2.className = "visible";
            }
        </script>
    </body>
</html>
