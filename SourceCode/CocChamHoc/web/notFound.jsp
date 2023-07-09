<%-- 
Document   : notfound
Created on : Jun 15, 2023, 11:18:26 PM
Author     : Yui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page not found!</title>
        <%@include file="/components/headCommon.jspf" %>
        <style>
        #root-404 {
            margin: 5rem;
            display: flex;
        }

        #root-404 > img {
            object-fit: none;
            margin: auto;
        }

    </style>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div id="root-404">
            <img src="/assets/img/img-404.jpg" alt="Page not found image"/>
        </div>
        <%@include file="/components/footer.jspf" %>
    </body>
</html>
