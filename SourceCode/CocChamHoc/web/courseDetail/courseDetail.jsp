<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
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
                margin-left: 20px;
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

            a.new-course-link {
                text-decoration: unset;
                color: rgb(0, 0, 238);
            }
            a.new-course-link:hover {
                text-decoration: underline;
            }

            a.disabled[disabled] {
                background: gray !important;
                opacity: 0.8;
                cursor: unset;
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
                    <div class="row">
                        <div><a href="/gotoLearn?courseId=${courseID}">Go To Course</a></div>
                    </div>
                </c:if>
                <c:if test="${isEnroll == false}">
                    <div>
                        <c:if test="${courseData.isDiscontinued == false}">
                            <a href="enroll?id=${courseID}">Enroll Now</a>
                        </c:if>
                        <c:if test="${courseData.isDiscontinued == true}">
                            <a href="#" class="disabled" disabled><i class="fa-solid fa-lock"></i> Discontinued</a>
                        </c:if>
                    </div>
                </c:if>
            </div>
            <c:if test="${courseData.newVersionId > 0}">
                <p><i class="fa-sharp fa-solid fa-lightbulb"></i> New version of this course <a class="new-course-link" href="/course?id=${courseData.newVersionId}">here</a>!</p>
            </c:if>



            <div class="row">
                <p id="des" class="active" onclick="view(this)">DESCRIPTION</p>             
                <p id="rev" onclick="view(this)">SYLLABUS</p>
                <p id="syl" onclick="view(this)">REVIEW</p>
            </div>
            <div class="row">
                <div id="des2" class="visible">
                    <p>${courseData.description}</p>
                </div>
                <div id="rev2" class="hidden">
                    <c:choose>
                        <c:when test="${empty lessonData}">
                            <p>No syllabus available</p>
                        </c:when>
                        <c:otherwise>
                            <%@include file="/components/viewSyllabus.jspf" %>
                        </c:otherwise>
                    </c:choose>
                </div> 
                <form action="/course" method="POST" id="form_status"> 
                    <%@include file="/components/review.jspf" %>
                    <div id="syl2" class="hidden">
                        <input type="hidden" id="id" name="id" value="${id}"> 
                        <div>
                            <div class="search_rate_star">
                                <ul class="search_rate_star_list">
                                    <li class="search_rate_star_item"><span onclick="hanleFilterStar(event)" class="rate_star_item">All</span>(${all})</li>                                  
                                    <li class="search_rate_star_item"><span onclick="hanleFilterStar(event)" class="rate_star_item">5 Star</span>(${five})</li>
                                    <li class="search_rate_star_item"><span onclick="hanleFilterStar(event)" class="rate_star_item">4 Star</span>(${four})</li>
                                    <li class="search_rate_star_item"><span onclick="hanleFilterStar(event)" class="rate_star_item">3 Star</span>(${three})</li>
                                    <li class="search_rate_star_item"><span onclick="hanleFilterStar(event)" class="rate_star_item">2 Star</span>(${two})</li>
                                    <li class="search_rate_star_item search_rate_star_item1"><span onclick="hanleFilterStar(event)" class="rate_star_item">1 Star</span>(${one})</li>
                                    <input type="hidden" value="" name="filterRate" class="filterRate"/>
                                </ul>
                            </div>
                        </div>
                        <c:if test="${!review.isEmpty()}"> 
                            <div class="container_review"> 
                                <c:forEach var="itemU" items="${users}">
                                    <c:forEach var="item" items="${review}" varStatus="i">
                                        <c:if test="${itemU.userID==item.userId}">
                                            <div class="review">
                                                <div class="review-header">
                                                    <div class="header-item"> 
                                                        <div class="item-head">
                                                            <div class="header-user">
                                                                <p class="user-icon"><i class="fa-solid fa-user"></i></p>
                                                            </div>
                                                            <div> 
                                                                <p class="user-name">
                                                                    ${itemU.fullName} 
                                                                </p>
                                                            </div>
                                                        </div>
                                                        <div class="user_choice">
                                                            <div onclick="handleClickChoice(event)" class="">
                                                                <i class="fa-solid fa-ellipsis user_choice-icon"></i>
                                                            </div>
                                                            <ul class="user_choice_item">
                                                                <c:if test="${itemU.userID==userId}">
                                                                    <li onclick="handleChocie(event)" class="user_choice-edit">Edit</li>
                                                                    </c:if> 
                                                                <li onclick="handleChocie(event)" class="user_choice-report">Report</li> 
                                                                    <c:if test="${itemU.userID==userId}">
                                                                    <li onclick="handleChocie(event)" class="user_choice-report">Delete</li>
                                                                    </c:if> 
                                                            </ul>
                                                            <input type="hidden" value="" name="status" id="status"/>  
                                                            <input type="hidden" id="rId" name="rId" value="${item.ratingId}">     
                                                        </div>
                                                    </div>
                                                    <div class="rate_infor">
                                                        <div>
                                                            <span class="header-time">${item.rateDate}</span>
                                                        </div>
                                                        <div id="rating_2">
                                                            <input type="radio" class="rate" id="star5-2" disabled ${item.rateNo==5?"checked":""} name=${i.index} value="5" />
                                                            <label class="full_1" id="label-1_1" for="star5-2" title="Awesome - 5 stars"></label>

                                                            <input type="radio" class="rate" id="star4-2" disabled ${item.rateNo==4?"checked":""} name=${i.index} value="4" />
                                                            <label class="full_1" id="label-2_1" for="star4-2" title="Pretty good - 4 stars"></label>

                                                            <input type="radio" class="rate" id="star3-2" disabled ${item.rateNo==3?"checked":""} name=${i.index} value="3" />
                                                            <label class="full_1" id="label-3_1" for="star3-2" title="Meh - 3 stars"></label>

                                                            <input type="radio" class="rate" id="star2-2" disabled ${item.rateNo==2?"checked":""} name=${i.index} value="2" />
                                                            <label class="full_1" id="label-4_1" for="star2-2" title="Kinda bad - 2 stars"></label>

                                                            <input type="radio" class="rate" id="star1-2" disabled ${item.rateNo==1?"checked":""} name=${i.index} value="1" />
                                                            <label class="full_1" id="label-5_1" for="star1-2" title="Sucks big time - 1 star"></label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="review-body">
                                                    <div> 
                                                        <p class="body-content">${item.review}</p>
                                                    </div>
                                                    <div>
                                                        <input type="hidden" value="" name='reviewUpdate' class="reviewUpdate" id="reviewUpdate"/>                                                         <input type="hidden" value="" name='reviewUpdate' class="reviewUpdate" id="reviewUpdate"/> 

                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>  
                                </c:forEach>    
                            </div>
                        </c:if> 
                        <c:if test="${review.isEmpty()}">
                            <p>No review available</p>
                        </c:if> 
                        <c:if test="${!review.isEmpty()}">
                            <div class="course-pagination course-pagination_space">
                                <p>Page: </p> 
                                <c:set var="page" value="${empty review ? 0 : pagination}"></c:set>
                                <c:set var="pages" value="${PaginationUtils.getWindow(page, pageCount, 5)}"></c:set>
                                <c:if test="${!PaginationUtils.contains(pages, 1)}">
                                    <input class="d-none" type="radio" name="page" value="1" id="pagination-choice-1" onfocus="selectFilterRate(this)" ${page == 1 ? "checked" : ""}/> 
                                    <label for="pagination-choice-1">1</label>
                                </c:if>
                                <c:if test="${PaginationUtils.includeDots(pages, pageCount, 2)}">
                                    <span>...</span>
                                </c:if>
                                <c:forEach var="item" items="${pages}">
                                    <input type="radio" name="page" class="page" value="${item}" id="pagination-choice-${item}" onfocus="selectFilterRate(this)" ${page == item ? "checked" : ""}/> 
                                    <label for="pagination-choice-${item}">${item}</label>
                                </c:forEach>
                                <c:if test="${PaginationUtils.includeDots(pages, pageCount, pageCount - 1)}">
                                    <span>...</span>
                                </c:if>
                                <c:if test="${!PaginationUtils.contains(pages, pageCount)}">
                                    <input type="radio" name="page" class="page" value="${pageCount}" id="pagination-choice-${pageCount}" onfocus="selectFilterRate(this)" ${page == pageCount ? "checked" : ""}/> 
                                    <label for="pagination-choice-${pageCount}">${pageCount}</label>
                                </c:if>
                                <input type="hidden" value="" name="pagination" class="pagination"/>
                            </div>
                        </c:if> 
                    </div>
                </form>
            </div>
            <div class="row">
                <img src="${courseData.imgUrl}"" alt="Course Image">
            </div>
            <!--</div>--> 
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
