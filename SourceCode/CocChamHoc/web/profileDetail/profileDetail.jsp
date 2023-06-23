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
                    User Profile  <i class="fa-solid fa-pen-to-square edit-icon" onclick="enableEditing()"></i>
                </h1>
            </div>
            <main class="course-editor-main">
                <%@include file="/components/profileEdit.jspf" %>
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

            .field-list {
                width: 60%;
                margin-right: 0px;
            }
        </style>
        <script src="/assets/js/base.js"></script>
    </body>
</html>
