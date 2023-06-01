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
        <%@include file="/components/headerEditCourse.jspf" %>
        <div class="edit-lesson-container">
            <%@include file="/components/editCourseNavbar.jspf" %>
            <div class="content-pane">
                <main>
                    <h1 class="m-4">
                        Edit Lesson
                    </h1>
                    <form method="post">
                        <input type="hidden" name="courseId" value="${courseId}"/>
                        <input type="hidden" name="chapterId" value="${chapterId}"/>
                        <input type="hidden" name="lessonNumber" value="${lessonNumber}"/>
                        <div class="fields">
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
                            <input type="text" id="lesson-vid" name="lessonVid" value="${lesson.video}"  required/>
                            <label for="lesson-desc">Description</label>
                            <textarea height="300px" id="lesson-desc" name="lessonDesc">${lesson.description}</textarea>
                        </div>
                        <div class="action-container">
                            <input type="submit" name="action" value="Delete" class="btn-del"/>
                            <input type="submit" name="action" value="Save" class="btn-save"/>
                        </div>

                    </form>
                </main>

            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>