<%-- 
    Document   : editLesson
    Created on : May 31, 2023, 5:07:15 PM
    Author     : Yui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="courseId" value="${empty param.courseId ? -1 : param.courseId}"></c:set>
<c:set var="chapterId" value="${empty param.chapterId ? -1 : param.chapterId}"></c:set>
<c:set var="lessonNumber" value="${empty param.lessonNumber ? -1 : param.lessonNumber}"></c:set>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Edit Lesson</title>
        <%@include file="/components/headCommon.jspf" %>
    </head>
    <body>
        <div class="course-editor-frame">
            <div class="course-editor-header">
                <%@include file="/components/headerEditCourse.jspf" %>
            </div>
            <div id="course-editor-nav" class="course-editor-nav">
                <%@include file="/components/editCourseNavbar.jspf" %>
            </div>
            <div class="course-editor-title-bar">
                <button onclick="collapseEvent(this)" data-target="course-editor-nav"><i class="fa-solid fa-bars"></i></button>
                <h1 class="editor-default-title">
                    Edit Lesson
                </h1>
            </div>
            <main class="course-editor-main">
                
                <form method="post">
                    <input type="hidden" name="courseId" value="${courseId}"/>
                    <input type="hidden" name="chapterId" value="${chapterId}"/>
                    <input type="hidden" name="lessonNumber" value="${lessonNumber}"/>
                    <div class="field-list">
                        <label for="lesson-name">Name</label>
                        <input type="text" id="lesson-name" name="lessonName" value="${lesson.name}" required/>
                        <label for="lesson-prev">Previous Lesson</label>
                        <select id="lesson-prev" name="lessonPrev" required>
                            <option value="0">None (First lesson in this chapter)</option>
                            <c:forEach var="item" items="${lessons}">
                                <option value="${item.lessonNumber}" ${prev.lessonNumber == item.lessonNumber ? "selected" : ""}>${item.lessonNumber}: ${item.name}</option>
                            </c:forEach>
                        </select>
                        <label for="lesson-vid">Video URL</label>
                        <input type="url" id="lesson-vid" name="lessonVid" value="${lesson.video}"  required/>
                        <label for="lesson-desc">Description</label>
                        <textarea height="300px" id="lesson-desc" name="lessonDesc">${lesson.description}</textarea>
                        <p style="color: red">${status}</p> 
                    </div>
                    <div class="action-container">
                        <input type="submit" name="action" value="Delete" class="btn-del"/>
                        <input type="submit" name="action" value="Save" class="btn-save"/>
                    </div>
                </form>
            </main>
        </div>
        <style>
            body {
                background: #F4F6FC;
            }
            
            .course-editor-main {
                padding: 2rem;
            }
        </style>
        <script src="/assets/js/base.js"></script>
    </body>
</html>