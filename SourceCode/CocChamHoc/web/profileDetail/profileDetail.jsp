<%-- 
    Document   : profileDetail
    Created on : Jun 22, 2023, 8:22:00 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <%@include file="/components/headCommon.jspf" %>
    </head>
    <body>
        <div class="course-editor-frame">
            <div class="course-editor-header">
                <%@include file="/components/headerEditCourse.jspf" %>
            </div>
            <div id="course-editor-nav" class="course-editor-nav">
                <%@include file="/components/editProfileNavbar.jspf" %>
            </div>
            <div class="course-editor-title-bar">
                <button onclick="collapseEvent(this)" data-target="course-editor-nav"><i class="fa-solid fa-bars"></i></button>
                <h1 class="editor-default-title">
                    User Profile
                </h1>
            </div>
            <main class="course-editor-main">
                <div id="profileEditSection">
                    <%@include file="/components/profileEdit.jspf" %>
                </div>
                <div id="loginDetailsEditSection" style="display: none;">
                    <%@include file="/components/loginDetailsEdit.jspf" %>
                </div>
                <div id="certificate" style="display: none;">
                    <%@include file="/components/certificateList.jsp" %>
                </div>
            </main>
        </div>
        <style>
            body {
                background: #F4F6FC;
            }

            .course-editor-main {
                padding: 2rem;
            }

            .action-container {
            }

            .align-right {
                float: right;
            }

            .small-input {
                border: none;
                border-bottom: 1px solid darkblue;
                background-color: #f4f6fc;
            }
            .btn-save {
                display: none;
            }
            .edit-icon{
                display: inline-block;
                cursor: pointer;
                font-size: 24px;
                padding-left: 50px;
            }

            .profileDetails-form profile-form {
                width: 60%;
            }

            .form-header {
                text-align: center;
            }
        </style>
        <script src="/assets/js/base.js"></script>
    </body>
</html>
