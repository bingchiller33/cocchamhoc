<%-- 
    Document   : exam
    Created on : Jun 22, 2023, 2:07:33 PM
    Author     : Viet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="utils.TimeUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${exam.examName}</title>
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/learnVideo.css"/>
        <link rel="stylesheet" href="/assets/css/exam.css"/>
    </head>
    <body>
        <div class="course-editor-frame">
            <div class="course-editor-header">
                <%@include file="/components/headerEditCourse.jspf" %>
            </div>
            <div id="course-editor-nav" class="course-editor-nav">
                <%@include file="/components/learnCourseNavbar.jspf" %>
            </div>
            <div class="course-editor-title-bar">
                <button onclick="collapseEvent(this)" data-target="course-editor-nav"><i class="fa-solid fa-bars"></i></button>
                <h1>Exam</h1>
            </div>
            <main class="course-editor-main">
                <c:if test="${toCert!=null}">
                    <div class="gotoCert">
                        <h2>Congratulations! You have passed all exams in this course</h2>
                        <div class="link-special">
                        <a href="${toCert}">Go To Your Certificate</a>
                        </div>
                    </div>
                    <hr>
                </c:if>
                <div class="EXAM-INF">
                    <div>
                        <h2 class="editor-default-title">
                            ${exam.examName}
                        </h2>
                        <p>Time Limit: <span>${TimeUtils.toMins(TimeUtils.timeToInt(exam.getDuration().toString()))} mins</span></p>
                    </div>
                    <div>
                        <div class="link">
                            <a href="gotoAttempt?examId=${exam.examID}&state=1">
                                <c:if test="${examPapers == null}">
                                    Attempt Exam 
                                </c:if>
                                <c:if test="${examPapers != null}">
                                    Re-Attempt Exam 
                                </c:if>    
                            </a>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="EXAM-ATTEMPT">
                    <div>
                        <c:if test="${bestAttempt != null}">
                            <c:set var="mark" value="${Math.ceil((bestAttempt.score/questionCount)*100)}"></c:set>
                            <c:if test="${mark>=80}">
                                <i class="fa-regular fa-circle-check" style="background-color:#55ff00; color: white; border-radius: 50%"></i>
                            </c:if>
                        </c:if>
                        <h3>Receive grade</h3>
                        <p>To Pass 80% or higher</p>
                    </div>
                    <div class="attempt">
                        <div>
                            Your grade
                            <c:if test="${bestAttempt == null}">
                                <p>_</p>
                            </c:if>
                            <c:if test="${bestAttempt != null}">
                                <c:if test="${mark >= 80}">
                                    <p style="color: #55ff00">${mark}%</p>
                                </c:if>
                                <c:if test="${mark < 80}">
                                    <p style="color: #ff8080">${mark}%</p>
                                </c:if>
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${examPapers != null}">
                                <div class="link">
                                    <a href="gotoAttempt?attemptId=${bestAttempt.paperID}&state=2"">
                                        View Feedback
                                    </a>
                                </div>
                                <p class="small">We keep your highest score</p>
                            </c:if>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="EXAM-PREV">
                    <c:if test="${examPapers != null}">
                        <h2>Previous Attempt</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th class="cel-1">Attempt</th>
                                    <th class="cel-2">State</th>
                                    <th class="cel-3">Grade</th>
                                    <th class="cel-4">Marks out of ${questionCount}</th>
                                    <th class="cel-5">Review</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${examPapers}" var="paper" varStatus="paperID">
                                    <tr>
                                        <td>${paperID.index+1}</td>
                                        <td>
                                            <p>${paper.state == 2 ? "Finshed":"In Progress"}</p>
                                            <p>Submitted ${paper.timeEnd}</p>
                                        </td>
                                        <td>${Math.ceil((paper.score/questionCount)*100)}%</td>
                                        <td>
                                            <p>${paper.score}/${questionCount}</p>
                                        </td>
                                        <td>
                                            <a href="gotoAttempt?attemptId=${paper.paperID}&state=2">Review</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </main>
        </div>
        <script src="/assets/js/base.js"></script>
    </body>
</html>
