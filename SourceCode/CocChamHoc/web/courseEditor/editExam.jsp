<%-- 
    Document   : editLesson
    Created on : July 5, 2023, 6:07:15 PM
    Author     : Phuoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="courseId" value="${empty param.courseId ? -1 : param.courseId}"></c:set>
<c:set var="chapterId" value="${empty param.chapterId ? -1 : param.chapterId}"></c:set>
<c:set var="lessonNumber" value="${empty param.lessonNumber ? -1 : param.lessonNumber}"></c:set>
<c:set var="examId" value="${empty param.examId ? -1 : param.lessonNumber}"></c:set>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Edit Exam</title>
        <%@include file="/components/headCommon.jspf" %>
    </head>
    <body>
        <div class="course-editor-frame">
            <div class="course-editor-header">
                <%@include file="/components/headerEditCourse.jspf" %>
            </div>
            <div id="course-editor-nav" class="course-editor-nav">
                <%@include file="/components/editCourseNavbar.jspf" %>
            </div>
            <div class="course-editor-title-bar">
                <button onclick="collapseEvent(this)" data-target="course-editor-nav"><i class="fa-solid fa-bars"></i></button>
                <h1 class="editor-default-title">
                    Edit Exam
                </h1>
            </div>
            <main class="course-editor-main">
                <form method="post">                    
                    <div >
                        <label style="width: 100px" for="exam-name">Name</label>
                        <input type="text" id="exam-name" name="ExamName" value="${exam.examName}" required/>
                        <label  style="width: 100px" for="exam-duration">Duration</label>
                        <input type="text" id="exam-duration" name="ExamDuration" value="${exam.durationInSecond}"  required/>

                        <div style="border:#ccc 1px solid; padding:10px">

                            <ul>
                                <c:forEach var="question" items="${questions}" varStatus="questionStatus">
                                    <li style="border:#ccc 1px solid; padding:10px; margin: 10px">
                                        <label for="exam-question">Question: ${questionStatus.count}</label>
                                        
                                        <div class="question">
                                            <input type="text" id="exam-question" name="ExamQuestionDetail${questionStatus.index}" value="${question.questionDetail}"  required/>
                                            <a class="btn-delete" href=""><i class="gg-trash"></i></a>
                                        </div>

                                        <input type="hidden" name="QuestionId${questionStatus.index}" value="${question.questionId}"/>
                                        <a href="/admin/create-choice?courseId=${param.courseId}&examId=${param.examId}&questionId=${question.questionId}">Add new Answer</a>
                                        <div style="margin: 10px">
                                            <ul style="border: 10px #000">
                                                <c:forEach var="choice" items="${choiceMap.get(question)}" varStatus="choiceStatus">
                                                    <li style="margin: 10px">
                                                        <label for="choice-description">${choiceStatus.count}</label>
                                                        <input type="text" id="choice-description" name="QuestionChoiceDetail${questionStatus.index}-${choiceStatus.index}" value="${choice.description}"  required/>
                                                        <input type="checkbox" name="IsTrueAnswer${questionStatus.index}-${choiceStatus.index}" ${choice.isTrueAnswer ? "checked":""} value="true"/>
                                                        <label>True answer</label>
                                                        <input type="hidden" name="ChoiceId${questionStatus.index}-${choiceStatus.index}" value="${choice.choiceId}"/>
                                                    </li> 

                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </li>    
                                </c:forEach>
                            </ul>

                            <a  href="/admin/create-question?courseId=${param.courseId}&examId=${param.examId}">Add new Question</a>
                        </div>
                    </div>


                    <div class="action-container">
                        <input type="submit" name="action" value="Save" class="btn-save"/>
                        <input type="submit" name="action" value="Delete" class="btn-del"/>

                    </div>
                    <div>

                    </div>
                </form>
            </main>
        </div>
        <style>
            .question{
                display: flex;
                align-items: center;
            }
            .btn-delete{
                margin: 10px
            }
            body {
                background: #F4F6FC;
            }

            .course-editor-main {
                padding: 2rem;
            }
            input[type=text], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type=number], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

        </style>
        <script src="/assets/js/base.js"></script>
        <link href='https://unpkg.com/css.gg@2.0.0/icons/css/trash.css' rel='stylesheet'>
    </body>
</html>