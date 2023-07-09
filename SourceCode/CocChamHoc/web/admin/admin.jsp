<%-- 
    Document   : manage
    Created on : May 30, 2023, 9:21:58 AM
    Author     : Viet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            <div class="row">
                <div class="admin-sidebar">
                    <a href="/admin"><i class="fa-solid fa-book-open"></i> Course Management</a>
                    <a href="/admin/users"><i class="fa-solid fa-users"></i>  User Management</a>
                    <a href="/admin/certificates"><i class="fa-solid fa-certificate"></i>  Certificate Management</a>
                </div>
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
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>
