<%-- 
    Document   : editCourse
    Created on : Jun 1, 2023, 9:01:22 PM
    Author     : Yui
--%>

<%-- 
    Document   : editLesson
    Created on : May 31, 2023, 5:07:15 PM
    Author     : Yui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="courseId" value="${empty param.courseId ? -1 : param.courseId}"></c:set>
<html>
    <head>
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
                        Edit Course Details
                    </h1>
                    <form method="post">
                        <input type="hidden" name="courseId" value="${courseId}"/>
                        <datalist id="category-list">
                            <c:forEach var="item" items="${categories}">
                                <option value="${item.description}"></option>
                            </c:forEach>
                        </datalist>
                        <datalist id="level-list">
                            <c:forEach var="item" items="${levels}">
                                <option value="${item.description}"></option>
                            </c:forEach>
                        </datalist>

                        <div class="fields">
                            <label for="course-name">Name</label>
                            <input type="text" id="course-name" name="courseName" value="${course.title}" required/>
                            <label for="course-category">Category</label>
                            <input type="text" list="category-list" id="course-category" name="courseCategory" value="${course.category.description}" required/>
                            <label for="level-category">Level</label>
                            <input type="text" list="level-list" id="course-level" name="courseLevel" value="${course.level.description}" required/>
                            <label for="course-lecturer">Lecturer</label>
                            <input type="text" id="course-lecturer" name="courseLecturer" value="${course.lecturer}" required/>
                            <label for="course-img-url">Banner Image</label>
                            <input type="text" id="course-img-url" name="courseImgUrl" value="${course.imgUrl}" required/>
                            <label for="course-desc">Description</label>
                            <textarea height="300px" id="course-desc" name="courseDesc">${course.description}</textarea>
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

