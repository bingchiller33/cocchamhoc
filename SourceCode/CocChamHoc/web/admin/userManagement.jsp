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
                <div class="admin-sidebar">
                    <a href="/admin"><i class="fa-solid fa-book-open"></i> Course Management</a>
                    <a href="/admin/users"><i class="fa-solid fa-users"></i>  User Management</a>
                    <a href="/admin/certificates"><i class="fa-solid fa-certificate"></i>  Certificate Management</a>
                </div>
                <div class="admin-content">
                    <h1>User management</h1>
                    <div class="filters">
                        <form>
                            Role:  
                            <select value="${empty param.role ? -1 : param.role}" onchange="applyFilter('role', event.target.value)">
                                <option value="-1">All roles</option>
                                <option value="1" ${role == 1 ? 'selected' : ''}> User </option>
                                <option value="2" ${role == 2 ? 'selected' : ''}> Lecturer </option>
                                <option value="3" ${role == 3 ? 'selected' : ''}> Admin </option>
                            </select>
                        </form>

                        <%@include file="/components/searchBar.jspf" %>
                    </div>

                    <table id="user-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Enrolled</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="x" items="${users}">
                                <tr>
                                    <td>${x.userID}</td>
                                    <td>${x.fullName}</td>
                                    <td>${x.email}</td>
                                    <td>${UserUtils.getRoleName(x.role)}</td>
                                    <td>${enrollMap[x.userID]} course</td>
                                    <td><a class="btn-detail" href="#">Detail</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <%@include file="/components/courseListingPagination.jspf" %>
                </div>
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
    <style>
        .admin-content {
            padding: 1rem;
        }

        .search {
            justify-content: flex-end;
            padding: 0.5rem;
            width: 25vw;
        }

        .search > form {
            width: 100%;
            border-radius: 3px;
        }

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

        .btn-detail {
            background: #FCD980;
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 5px;
            cursor: pointer;
        }

        .filters {
            display: flex;
            align-content: flex-start;
            align-items: center;
            justify-content: space-between;
        }

        .filters > form > select {

            width: 25vw;
        }
    </style>
</html>
