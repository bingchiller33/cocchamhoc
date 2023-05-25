<%-- 
    Document   : home
    Created on : May 20, 2023, 5:50:08 PM
    Author     : Yui
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/components/header.jsp" %>
        <c:forEach items="${list}" var="i">
            <h1>${i.title}</h1>
            <p>${i.imgUrl}</p>
            <p>${i.description}</p>
            <p>${i.publishDate}</p>
            <p>${i.level}</p>
            <p>
        </c:forEach>
        <%@include file="/components/footer.jsp" %>

    </body>
</html>
