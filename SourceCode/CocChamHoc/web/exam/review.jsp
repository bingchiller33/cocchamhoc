<%-- 
    Document   : attempt
    Created on : Jun 29, 2023, 1:31:49 PM
    Author     : Viet
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@page import="model.Question" %>
<%@page import="model.Choice" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <title>${exam.examName}</title>
        <script>
            // Assuming you have the total number of questions stored in a variable called `totalQuestions`
            var questionList = document.getElementById('question-list');

            // Generate the navigation buttons
            for (var i = 1; i <= totalQuestions; i++) {
                var button = document.createElement('button');
                button.textContent = i;
                button.setAttribute('data-question', i);
                button.addEventListener('click', navigateToQuestion);
                questionList.appendChild(document.createElement('li').appendChild(button));
            }

            // Function to handle navigation when a button is clicked
            function navigateToQuestion(event) {
                var questionNumber = event.target.getAttribute('data-question');

                // Your logic to display the respective question goes here
                // For example, you can show/hide question sections based on the selected question number

                // Remove 'active' class from all buttons
                var buttons = document.querySelectorAll('.question-nav button');
                buttons.forEach(function (button) {
                    button.classList.remove('active');
                });

                // Add 'active' class to the clicked button
                event.target.classList.add('active');
            }

        </script>
        <link rel="stylesheet" href="/assets/css/review.css"/>
    </head>
    <body>
        <%@include file="/components/headerExam.jspf" %>
        <div class="content">
            <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            Map<Question, Integer> userAnswers = (Map<Question, Integer>) request.getAttribute("userAnswers");
            for(int i=0; i < questions.size(); i++){
                Question q = questions.get(i);
                int answer = (int)userAnswers.get(q);
            %>
            <div class="question" id="question<%=i+1%>">
                <div class="question-inf">
                    Question <h2><%=(i+1)%></h2>
                    <%
                        if(answer == -1){
                    %>
                    <p>Not answered</p>
                    <%
                    }
                    else{
                    %>
                    <p>Complete</p>
                    <%
                    }
                    %>
                </div>
                <div class="question-detail">
                    <div class="question-content">
                        <%=q.getQuestionDetail()%>
                    </div>
                    <hr>
                    <div class="choices">
                        <%
                        List<Choice> choices = q.getChoices();
                        String answerlog = "Please review the course's materials for better understanding";
                        boolean flag = false;
                        if(answer == -1)
                            answerlog = "You didn’t select an answer.";
                        for(Choice choice: choices){
                        %>
                        <%
                        if(answer == choice.getChoiceID()){
                            if(choice.isIsTrueAnswer() == true){
                                answerlog = "The answer "+choice.getDescription()+" is correct.";
                                flag = true;
                            }
                        %>
                        <div>
                            <input type="radio" checked>
                            <label>
                                <%=choice.getDescription()%>
                            </label>
                        </div>
                        <%
                        }
                        else{
                        %>  
                        <div>
                            <input type="radio">
                            <label>
                                <%=choice.getDescription()%>
                            </label>
                        </div>
                        <%
                        }
                        %>
                        <%
                        } 
                        %>
                        <%
                        if(flag == false){
                        %>
                        <div class="false-answer">
                            <i class="fa-regular fa-circle-xmark" style="color:#ff8080"></i> Incorrect
                            <p><%=answerlog%></p>
                        </div>
                        <%
                        }
                        %>
                        <%
                        if(flag == true){
                        %>
                        <div class="true-answer">
                            <i class="fa-regular fa-circle-check" style="color: #55ff00"></i> Correct
                            <p><%=answerlog%></p>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <%
            }
            %>
        <div class="question-nav">
                <h3>Exam Navigation</h3>
                Total Question: <p id="questionCount">${questionCount}</p>
                <ul>
                    <c:forEach var="i" begin="1" end="${questionCount}">
                        <li><button id="${i}">${i}</button></li>
                    </c:forEach>
                </ul>
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
            var radioButtons = document.querySelectorAll("input[type='radio']");
            for (var i = 0; i < radioButtons.length; i++) {
                radioButtons[i].disabled = true;
            }
        </script>
    </body>
</html>
