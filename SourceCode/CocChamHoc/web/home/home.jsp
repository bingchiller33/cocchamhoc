<%-- 
    Document   : home
    Created on : May 20, 2023, 5:50:08 PM
    Author     : Yui
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/components/headCommon.jspf" %>

    </head>
    <body>
        <%@include file="/components/header.jsp" %>
        <h1>Hello World!</h1>
        <%--<%@include file="/components/filterDialog.jspf" %>--%>
        <%--<%@include file="/components/courseListingPagination.jspf" %>--%>
        <%@include file="/components/courseFinder.jspf" %>
        <%@include file="/components/footer.jsp" %>
    </body>
</html>
