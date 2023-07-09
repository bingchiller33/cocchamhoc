<%-- 
    Document   : certificate
    Created on : Jul 5, 2023, 3:39:28 PM
    Author     : LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certificate</title> 
        <%@include file="/components/headCommon.jspf" %> 
        <link rel="stylesheet" href="../assets/css/certificate.css"/> 
        <link rel="stylesheet" href="../assets/css/notFound.css">
    </head>

    <body> 
        <div class="course-editor-header">
            <%@include file="/components/headerEditCourse.jspf" %>
        </div>
        <c:if test="${title != null}"> 
            <div class="back">
                <h2 class="btn_back" onclick="window.history.back()"><i class="fa-solid fa-arrow-left-long"></i> Back</h2>
            </div>
            <div class="container_certificate"> 
                <div class="center_space_certificate">
                    <div class="container_element">
                        <div class="element_head"> 
                            <h2 class="certificate_name">${userName}</h2>
                        </div>
                        <div class="element_footer">
                            <div class="left_space_certificate_title"></div>
                            <div class="center_space_certificate_title">
                                <div class="container_certificate_title">
                                    <h1 class="certificate_title">${title}</h1>
                                    <h1 class="certificate_title_space"></h1>
                                </div>
                            </div>
                            <div class="right_space_certificate_title"></div>
                        </div>
                    </div>
                </div> 
            </div>
        </c:if>
        <c:if test="${title == null}">
            <div class="course_list_not_found">
                <div class="not_found-item">
                    <div class="not_found_item-head">
                        <h1 class="not_found_item_head-title">
                            <span class="not_found_item_head-first">4</span><span class="not_found_item_head-second">0</span><span class="not_found_item_head-third">4</span></h1>
                    </div>
                    <div class="not_found_item_body">
                        <h1 class="not_found_item_body-title">Oops... You have not completed this course</h1>
                        <p class="not_found_item_body-desc">
                            <span>Sorry, can't found any certificate, you must enroll some course before and complete it</span> <br/>
                            <span>Make sure you've taken the course </span>
                        </p>
                    </div>
                    <div class="not_found-btn">
                        <a class="not_found_btn-link" onclick="window.history.back()">Back</a>
                    </div>
                </div>
            </div>
        </c:if> 
    </body>
</html>
