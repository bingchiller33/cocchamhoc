<%-- 
    Document   : ReviewManagement
    Created on : Jul 14, 2023, 4:58:55 PM
    Author     : hoang
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
        <title>Review management</title>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div class="article">
            <div class="row">
                <%@include file="/components/adminNavBar.jspf" %>
                <div class="admin-content">
                    <h1>Review Management</h1>
                    <% String deleteMsg = (String) request.getSession().getAttribute("DeleteMSG"); %>
                    <% if (deleteMsg != null) { %>
                    <div class="message">
                        <%= deleteMsg %>
                    </div>
                    <% request.getSession().removeAttribute("DeleteMSG"); %>
                    <% } %>

                    <% String approveMsg = (String) request.getSession().getAttribute("UpdateMSG"); %>
                    <% if (approveMsg != null) { %>
                    <div class="message">
                        <%= approveMsg %>
                    </div>
                    <% request.getSession().removeAttribute("UpdateMSG"); %>
                    <% } %>

                    <table id="user-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>UserID</th>
                                <th>CourseID</th>
                                <th>Rating</th>
                                <th>RateTime</th>
                                <th class="td-review">Review</th>
                                <th colspan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${!reviews.isEmpty()}">
                                <c:forEach var="x" items="${reviews}">
                                    <tr>
                                        <td>${x.ratingId}</td>
                                        <td>${x.userId}</td>
                                        <td>${x.courseId}</td>
                                        <td>${x.rateNo}</td>
                                        <td>${x.rateDate}</td>
                                        <td>${x.review}</td>
                                        <td class="button-container">
                                            <form action="/admin/approveReport" method="POST">
                                                <input type="hidden" name="ratingId" value="${x.ratingId}" />
                                                <button class="btn-approve" type="submit">Approve</button>
                                            </form>
                                        </td>
                                        <td class="button-container">
                                            <form action="/admin/declineReport" method="POST">
                                                <input type="hidden" name="ratingId" value="${x.ratingId}" />
                                                <button class="btn-decline" type="submit">Decline</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${reviews.isEmpty()}">
                                <tr>
                                    <td colspan="7">There is no more ratings need to be reviewed! </td>
                                </tr>
                            </c:if>     
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

        .btn-approve {
            padding: 8px 16px;
            background-color: #ff0000;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-decline {
            padding: 8px 16px;
            background-color: #00ff00;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-approve:hover {
            background-color: #cc0000;
        }

        .btn-decline:hover {
            background-color: #00cc00;
        }

        .button-container {
            text-align: center;
        }

        .td-id {
            width: 5%;
        }

        .td-review{
            width: 40%
        }

    </style>
</html>
