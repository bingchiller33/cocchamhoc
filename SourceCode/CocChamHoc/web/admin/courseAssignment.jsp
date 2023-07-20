<%-- 
    Document   : courseAssignment
    Created on : Jul 19, 2023, 5:21:47 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/admin.css">
        <title>Course Assignment</title>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div class="article">
            <div class="row">
                <%@include file="/components/adminNavBar.jspf" %>
                <div class="admin-content">
                    <h1>Course Asssignment</h1>
                    <%@include file="/components/courseListingPagination.jspf" %>
                </div>
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>
