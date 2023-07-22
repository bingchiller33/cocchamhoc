<%-- 
    Document   : learn
    Created on : Jun 6, 2023, 2:53:20 PM
    Author     : Yui
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="courseId" value="${empty param.courseId ? -1 : param.courseId}"></c:set>
<c:set var="chapterId" value="${empty param.chapterId ? -1 : param.chapterId}"></c:set>
<c:set var="lessonNumber" value="${empty param.lessonNumber ? -1 : param.lessonNumber}"></c:set>
<c:set var="progress" value="${progress}" />
<c:set var="1" value="${1}" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesson: ${lesson.name} | COC CHAM HOC</title>
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/learnVideo.css"/>
    </head>
    <body>

        <div class="course-editor-frame">
            <div class="course-editor-header">
                <%@include file="/components/headerEditCourse.jspf" %>
            </div>
            <div id="course-editor-nav" class="course-editor-nav">
                <%@include file="/components/learnCourseNavbar.jspf" %>
            </div>
            <div class="course-editor-title-bar">
                <button onclick="collapseEvent(this)" data-target="course-editor-nav"><i class="fa-solid fa-bars"></i></button>
                <h1 class="editor-default-title">
                    Video Lesson
                </h1>
            </div>

            <main class="course-editor-main">
                <h1>${lesson.name}</h1>
                <div class="course-nav-ctn">
                    <a class="course-nav" href="${prevUrl}"><i class="fa-solid fa-chevron-left"></i> Previous Lesson</a>
                    <a class="course-nav" href="${nextUrl}">Next Lesson <i class="fa-solid fa-chevron-right"></i></a>
                </div>
                <div class="video-frame" >
                    <div id="played" ></div>
<!--                    <iframe id="${lesson.video}" src="${lesson.video}" class="video-frame" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
                            allowfullscreen></iframe>-->
                    <script class="video-frame" src="http://www.youtube.com/player_api"></script>


                        <span>Progress status: </span>

                        <c:if test="${progress == true}">  

                            <span name="progressd" value="1" class="status complete">Complete</span>
                        </c:if> 
                        <c:if test="${progress == false}">  

                            <span name="progressd" value="0" class="status incomplete">Incomplete</span>
                        </c:if> 
                        </div>
                        <div>
                            <span id="played"></span> 

                            <!-- <span id="duration"></span> -->
                        </div>
                    </div>

                    <h2>Description</h2>
                    <p>${lesson.description}</p>
                    <br/>
                    <div class="course-nav-ctn" >
                        <a class="course-nav" href="${prevUrl}"><i class="fa-solid fa-chevron-left"></i>Previous Lesson</a>
                        <a class="course-nav" href="${nextUrl}">Next Lesson <i class="fa-solid fa-chevron-right"></i></a>
                    </div>
                </main>
            </div>
            <script src="/assets/js/base.js"></script>
            <style>

                #status span.status {
                    display: none;
                    font-weight: bold;
                }
                span.status.complete {
                    color: green;
                }
                span.status.incomplete {
                    color: red;
                }
                #status.complete span.status.complete {
                    display: inline;
                }
                #status.incomplete span.status.incomplete {
                    display: inline;
                }
            </style>



            <script>
                    // create youtube player
                    const youTubeIdFromLink = (url) => url.match(/(?:https?:\/\/)?(?:www\.|m\.)?youtu(?:be)?\.(?:com|be)(?:\/watch\/?\?v=|\/embed\/|\/)([^\s&\?\/\#]+)/)[1];
                    var player;
                    function onYouTubePlayerAPIReady() {


                        player = new YT.Player('played', {
                            height: '100%',
                            width: '100%',
                            videoId: youTubeIdFromLink('${lesson.video}'),
                            events: {
                                'onReady': onPlayerReady,
                                'onStateChange': onPlayerStateChange
                            }
                        });
                    }

                    // autoplay video
                    function onPlayerReady(event) {
                        event.target.playVideo();
                    }

                    // when video ends
                    function onPlayerStateChange(event) {
                        if (event.data === 0) {
//                            
                            window.location.href = "/update-progress?courseId=${courseId}&chapterId=${chapterId}&lessonNumber=${lessonNumber}";
                        }
                    }
            </script>
        </body>
    </html>