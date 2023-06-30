<%-- 
    Document   : userManagement
    Created on : May 30, 2023, 9:21:58 AM
    Author     : Quan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.*" %>
<c:set var="role" value="${empty param.role ? -1 : param.role}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/admin.css">
        <title>User management</title>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div class="article">
            <div class="row">
                <%@include file="/components/adminNavBar.jspf" %>
                <div class="admin-content" style="padding: 1rem">
                    <h1>User Details</h1>
                    <h2 style="margin-top: 1rem">User profile</h2>

                    <h2 style="margin-top: 1rem">Course Enrolled (${courses.size()})</h2>
                    <table id="user-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Publish date</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="x" items="${courses}">
                                <tr>
                                    <th>${x.id}</th>
                                    <th>${x.title}</th>
                                    <th>${x.publishDate}</th>
                                    <th>${statusMap[x.id]}</th>
                                    <th><a class="btn-del" href="#">Unenroll</a></th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <h2 style="margin-top: 1rem">Restrict User</h2>
                    <em>User will no longer have access to the website if you ban the user. </em>
                    <form method="POST">
                        <div class="field-list">
                            <label>Restrict Until </label>
                            <input type="date" name="banUntil" value="${user.restrictUntil}"/>
                            <label>Reason</label>
                            <input type="text" name="banReason" value="${user.restrictReason}"/>
                        </div>
                        <p style="color: red">${restrictStatus}</p>
                        <div>
                            <input class="btn-save" type="submit" name="action" value="Restrict"/>                            
                            <input class="btn-save" type="submit" name="action" value="Appeal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
    <style>
        #user-table {
            border-collapse: collapse;
            width: 100%;
        }

        #user-table td, #user-table th {
            padding: 1rem;
            border: 1px solid #ddd;
        }

        #user-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #user-table thead {
            background-color: #1C1E53;
            color: white;
        }
    </style>
</html>
