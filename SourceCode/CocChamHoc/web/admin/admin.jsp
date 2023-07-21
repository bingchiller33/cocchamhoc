<%-- 
    Document   : manage
    Created on : May 30, 2023, 9:21:58 AM
    Author     : Viet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    .filter-btn {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .filter-button {
        background-color: #eaeaea;
        color: #333333;
        border: none;
        padding: 10px 20px;
        margin: 5px;
        cursor: pointer;
    }

    .filter-button:hover {
        background-color: #d4d4d4;
    }

    .filter-button.selected {
        background-color: #333333;
        color: #ffffff;
    }
</style>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/admin.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div class="article">
            <c:if test="${user.role == '3'}">
                <div class="row">
                    <%@include file="/components/adminNavBar.jspf" %>
                    <div class="admin-content">
                        <div class="form-container">
                            <div class="title">
                                <h1>Course List</h1><a href="/admin/create-course"><i class="fa fa-plus-circle" aria-hidden="true"></i> New Course</a>
                            </div>
                            <table>
                                <tr>
                                    <th>Course</th>
                                    <th>Option</th>
                                </tr>
                                <c:forEach items="${courseData}" var="i">
                                    <tr>
                                        <td>
                                            <div class="course-inf">
                                                <img src="${i.imgUrl}" alt="courseImage"/>
                                                <div class="inf">
                                                    <p>${i.title}</p>
                                                    <small>Publish Date: ${i.publishDate}</small>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="/admin/edit-course?courseId=${i.id}">Edit</a>
                                            <a href="/admin/edit-course?courseId=${i.id}&action=Delete">Delete</a>
                                            <a style="background-color: beige" href="/admin/assign-course?courseId=${i.id}">Assign</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>       
                </div>
            </c:if>

            <div class="row">
                <c:if test="${user.role == '2'}">
                    <%@include file="/components/adminNavBar.jspf" %>
                    <div class="admin-content">
                        <div class="form-container">
                            <div class="title">
                                <c:choose>
                                    <c:when test="${filterValue == 'assigned'}">
                                        <h1>Your Courses</h1>
                                    </c:when>
                                    <c:otherwise>
                                        <h1>All Courses</h1>
                                    </c:otherwise>
                                </c:choose>
                                <a href="/admin/create-course"><i class="fa fa-plus-circle" aria-hidden="true"></i> New Course</a>
                            </div>
                            <div class="filter-btn">
                                <button name="filterValue" value="all" onclick="selectFilter(event)" class="filter-button">All Courses</button>
                                <button name="filterValue" value="assigned" onclick="selectFilter(event)" class="filter-button">Assigned User Courses</button>
                            </div>
                            <table>
                                <tr>
                                    <th>Courses</th>
                                        <c:choose>
                                            <c:when test="${filterValue == 'assigned'}">
                                            <th>Option</th>
                                            </c:when>
                                        </c:choose>
                                </tr>
                                <c:choose>
                                    <c:when test="${filterValue eq 'assigned'}">
                                        <c:forEach items="${designerCourses}" var="x">
                                            <tr>
                                                <td>
                                                    <div class="course-inf">
                                                        <img src="${x.imgUrl}" alt="courseImage"/>
                                                        <div class="inf">
                                                            <p>${x.title}</p>
                                                            <small>Publish Date: ${x.publishDate}</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <a href="/admin/edit-course?courseId=${x.id}">Edit</a>
                                                    <a href="/admin/edit-course?courseId=${x.id}&action=Delete">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${courseData}" var="i">
                                            <tr>
                                                <td>
                                                    <div class="course-inf">
                                                        <img src="${i.imgUrl}" alt="courseImage"/>
                                                        <div class="inf">
                                                            <p>${i.title}</p>
                                                            <small>Publish Date: ${i.publishDate}</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <!-- Option for all courses -->
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>

        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Gắn sự kiện nghe (event listener) cho các phần tử filter
        const filterButtons = document.querySelectorAll('[name="filterValue"]');
        filterButtons.forEach(function (button) {
            button.addEventListener('click', selectFilter);
        });
    });

    function selectFilter(e) {
        let target = e.target;

        // Remove 'selected' class from all filter buttons
        let filterButtons = document.querySelectorAll('.filter-button');
        filterButtons.forEach(function (button) {
            button.classList.remove('selected');
        });

        // Add 'selected' class to the clicked filter button
        target.classList.add('selected');

        // Get the value of the selected filter
        let name = target.getAttribute('name');
        let value = target.getAttribute('value');
        applyFilter(name, value);
        e.preventDefault();
    }


    function applyFilter(name, value) {
        let searches = new URLSearchParams(location.search);
        searches.set(name, value);
        location.search = searches.toString();
    }


</script>