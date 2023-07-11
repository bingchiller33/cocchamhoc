<%-- 
    Document   : attempt
    Created on : Jun 29, 2023, 1:31:49 PM
    Author     : Viet
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="utils.TimeUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <title>${exam.examName}</title>
        <link rel="stylesheet" href="/assets/css/attempt.css"/>
        <script>
            function updateTimer() {
                var remainingTime = document.getElementById("remainingTime").value;
                var hours = Math.floor(remainingTime / 3600);
                var minutes = Math.floor((remainingTime - hours * 3600) / 60);
                var seconds = Math.floor(remainingTime - hours * 3600 - minutes * 60);
                document.getElementById("timer").innerHTML = hours + ":" + minutes + ":" + seconds;
                remainingTime--;
                document.getElementById("remainingTime").value = remainingTime;
                if (remainingTime > 0) {
                    setTimeout(updateTimer, 900);
                } else {
                    document.getElementById("examForm").submit();
                }
            }            
        </script>
    </head>
    <body onload="updateTimer()">
        <%@include file="/components/headerExam.jspf" %>
        <input type="hidden" id="remainingTime" value="${remainingTime}" />
        <div class="content">
            <form id="examForm" action="/learn/attempt?attemptId=${attemptId}" method="post"> 
                <c:forEach items="${questions}" var="question" varStatus="questionStatus">
                    <div class="question" id="question${questionStatus.index+1}">
                        <div class="question-inf">
                            Question <h2>${questionStatus.index+1}</h2>
                        </div>
                        <div class="question-detail">
                            <div class="question-content">
                                ${question.questionDetail}
                            </div>
                            <hr>
                            <div class="choices">
                                <c:forEach items="${question.choices}" var="choice" varStatus="choiceStatus">
                                    <div>
                                        <input type="radio" name="${question.questionID}" id="${questionStatus.index+1}_${choiceStatus.index+1}" value="${choice.choiceID}">
                                        <label for="${questionStatus.index+1}_${choiceStatus.index+1}">${choice.description}</label>
                                    </div>
                                </c:forEach> 
                            </div>
                        </div>
                    </div> 
                </c:forEach>
            </form>
            <div class="question-nav">
                <h3>Exam Navigation</h3>
                Total Question: <p id="questionCount">${questionCount}</p>
                <ul>
                    <c:forEach var="i" begin="1" end="${questionCount}">
                        <li><button id="${i}">${i}</button></li>
                        </c:forEach>
                </ul>
                <input type="submit" form="examForm"value="Submit">    
            </div>
        </div>
        <script>
            var totalQuestions = document.getElementById('questionCount').textContent;
            for (var i = 1; i <= totalQuestions; i++) {
                var button = document.getElementById(i.toString());
                var questionSection = document.getElementById('question'+i);
                questionSection.addEventListener('click', handleDivClick);
                button.setAttribute('data-question', i);
                button.addEventListener('click', navigateToQuestion);
                if (i === 1)
                    button.classList.add('active');
            }
            function handleDivClick(event) {
                var clickedElement = event.target;
                if (clickedElement.tagName === 'INPUT' && clickedElement.type === 'radio') {
                    var radioValue = clickedElement.id;
                    var parts = radioValue.split("_");
                    var button = document.getElementById(parts[0].toString());
                    button.classList.add('selected');
                }
            }
            // Function to handle navigation when a button is clicked
            function navigateToQuestion(event) {
                var questionNumber = event.target.getAttribute('data-question');
                var questionSection = document.getElementById('question' + questionNumber);
                if (questionSection) {
                    questionSection.scrollIntoView({block: "center", behavior: 'smooth'});
                }
                var buttons = document.querySelectorAll('.question-nav button');
                buttons.forEach(function (button) {
                    button.classList.remove('active');
                });
                event.target.classList.add('active');
            }
        </script>
    </body>
</html>
