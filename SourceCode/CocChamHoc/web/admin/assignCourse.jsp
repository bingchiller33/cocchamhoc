<%-- 
    Document   : assignCourse
    Created on : Jul 19, 2023, 9:01:22 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="courseId" value="${empty param.courseId ? -1 : param.courseId}"></c:set>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Course Assignment</title>
            <link rel="stylesheet" href="/assets/css/assignCourse.css">
        <%@include file="/components/headCommon.jspf" %>
    </head>
    <body>
        <div class="course-editor-frame">
            <div class="course-editor-header">
                <%@include file="/components/headerEditCourse.jspf" %>
            </div>
            <div id="course-editor-nav" class="course-editor-nav">
                <%@include file="/components/assignCourseNavbar.jspf" %>
            </div>
            <div class="course-editor-title-bar">
                <h1 class="editor-default-title">
                    Course Assignment
                </h1>
                <p class="course-name">Selected Course: ${course.title}</p>
            </div>
            <main class="course-editor-main">
                <div class="form-container">
                    <form action="/admin/assign-course" method="post" id="assignForm">
                        <div class="form-header">
                            <h2>Assign Designers</h2>
                        </div>
                        <div class="form-group">
                            <label for="designerIds">Select Designer(s):</label>
                            <c:if test="${not empty unassignedList}">
                                <select name="designerIds" id="designerIds" multiple>
                                    <c:forEach items="${unassignedList}" var="designer">
                                        <option value="${designer.userID}">${designer.fullName}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${empty unassignedList}">
                                <p>No designers available.</p>
                            </c:if>
                        </div>
                        <input type="hidden" name="courseId" value="${courseId}" />
                        <div class="form-group">
                            <input type="submit" value="Assign">
                        </div>
                    </form>
                </div>
                <div class="form-container">
                    <form action="/admin/assigned-course" method="get">
                        <div class="form-header">
                            <h2>Assigned Designers</h2>
                        </div>
                        <div class="table-container">
                            <c:if test="${not empty assignedList}">
                                <table>
                                    <tr>
                                        <th>Designer ID</th>
                                        <th>Full Name</th>
                                    </tr>
                                    <c:forEach items="${assignedList}" var="designer">
                                        <tr>
                                            <td>${designer.userID}</td>
                                            <td>${designer.fullName}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <c:if test="${empty assignedList}">
                                <p>No designers assigned to this course.</p>
                            </c:if>
                        </div>
                    </form>
                </div>
            </main>
        </div>
        <script src="../assets/js/course.js"></script>
        <script src="/assets/js/base.js"></script>
    </body>
</html>
