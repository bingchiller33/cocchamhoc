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
            <div id="course-editor-nav" class="course-editor-nav" style="width: 350px; font-size: 19px">
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
                    <div class="form-header">
                        <h2>Assign Designers</h2>
                         <p class="course-name">*Assign Course to Designer*</p>
                    </div>
                   <%@include file="/components/searchBarAssignCourse.jspf" %>
                    <form action="/admin/assign-course" method="post" id="assignForm">
                        <div class="form-group">
                            <table>
                                <tr>
                                    <th class="content-center">ID</th>
                                    <th>Full Name</th>
                                    <th class="content-center">Action</th>
                                </tr>
                                <c:forEach items="${unassignedList}" var="designer">
                                    <tr>
                                        <td class="content-center">${designer.userID}</td>
                                        <td><a class="link-hover" href="/admin/userDetail?id=${designer.userID}">${designer.fullName}</a></td>
                                        <td class="content-center">
                                            <button class="btn-assign" type="submit" name="designerIds" value="${designer.userID}">Assign</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <c:if test="${empty unassignedList}">
                                <p>No designers available.</p>
                            </c:if>
                        </div>
                        <input type="hidden" name="courseId" value="${courseId}" />
                    </form>
                    <c:if test="${!empty unassignedList}">
                        <%@include file="/components/unassignedListingPagination.jspf" %>
                    </c:if>
                </div>
                <div class="form-container">
                    <div class="form-header">
                        <h2>Assigned Designers</h2>
                        <p class="course-name">*Unassign Course to Designer*</p>
                    </div>
                    <%@include file="/components/searchBarUnassignCourse.jspf" %>
                    <form action="/admin/unassign-course" method="post" id="unassignForm">
                        <div class="form-group">
                            <table class="content-center">
                                <tr>
                                    <th class="content-center">ID</th>
                                    <th>Full Name</th>
                                    <th class="content-center">Action</th>
                                </tr>
                                <c:forEach items="${assignedList}" var="designer">
                                    <tr>
                                        <td class="content-center">${designer.userID}</td>
                                        <td><a class="link-hover" href="/admin/userDetail?id=${designer.userID}">${designer.fullName}</a></td>
                                        <td class="content-center">
                                            <button class="btn-unassign" type="submit" name="designerIds" value="${designer.userID}">Unassign</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <c:if test="${empty assignedList}">
                                <p>No designers assigned to this course.</p>
                            </c:if>
                        </div>
                        <input type="hidden" name="courseId" value="${courseId}" />
                    </form>
                    <c:if test="${!empty assignedList}">
                        <%@include file="/components/assignedListingPagination.jspf" %>
                    </c:if>
                </div>
            </main>
        </div>
        <script src="../assets/js/course.js"></script>
        <script src="/assets/js/base.js"></script>
    </body>
</html>
