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
                    Edit Course Details
                </h1>
            </div>
            <main class="course-editor-main">
                <form method="post" onsubmit="handleSubmit(event)">
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

                    <div class="field-list">
                        <label for="course-name">Name</label>
                        <input type="text" id="course-name" name="courseName" value="${course.title}" required/>
                        <label for="course-category">Category</label>
                        <input type="text" list="category-list" id="course-category" name="courseCategory" value="${course.category.description}" required/>
                        <label for="level-category">Level</label>
                        <input type="text" list="level-list" id="course-level" name="courseLevel" value="${course.level.description}" required/>
                        <c:if test="${admin}">
                            <label for="course-publish-date">Publish Date</label>
                            <input type="date" id="course-publish-date" name="coursePublishDate" value="${course.publishDate}"/>
                        </c:if>
                        <label for="course-lecturer">Lecturer</label>
                        <input type="text" id="course-lecturer" name="courseLecturer" value="${course.lecturer}" required/>
                        <label for="course-img-url">Banner Image</label>
                        <input type="text" id="course-img-url" name="courseImgUrl" value="${course.imgUrl}" required/>
                        <label for="new-ver-id">New Version ID</label>
                        <select id="new-ver-id" name="newVersionId">
                            <option value="-1">None</option>
                            <c:forEach var="item" items="${nextCourses}">
                                <option value="${item.id}" ${item.id == course.newVersionId ? 'selected' : ''}>${item.id}: ${item.title}</option>
                            </c:forEach>
                        </select>
                        <label for="course-desc">Description</label>
                        <textarea height="300px" id="course-desc" name="courseDesc">${course.description}</textarea>
                    </div>
                    <p style="color: red">${status}</p>
                    <div class="action-container">
                        <c:if test="${course.publishDate == null}">
                            <input type="submit" name="action" value="Delete" class="btn-del"/>
                        </c:if>
                        <input type="submit" name="action" value="Save" class="btn-save"/>
                        <p>${session.getAttribute('user').role}</p>
                        <c:if test="${admin}">
                            <c:if test="${course.publishDate != null}">
                                <c:if test="${course.isDiscontinued == true}">
                                    <input type="submit" name="action" value="Recontinue" class="btn-save"/>
                                </c:if>
                                <c:if test="${course.isDiscontinued == false}">
                                    <input type="submit" name="action" value="Discontinue" class="btn-save"/>
                                </c:if>
                            </c:if>
                            <c:if test="${course.publishDate == null}">
                                <input type="submit" name="action" value="Publish" class="btn-save"/>
                            </c:if>
                        </c:if>

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

            .action-container input:first-child {
                margin-left: auto;
            }
        </style>
        <script src="../assets/js/course.js"></script>
        <script src="/assets/js/base.js"></script>
    </body>
</html>
