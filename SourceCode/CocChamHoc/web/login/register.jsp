<%-- 
    Document   : register
    Created on : May 26, 2023, 6:47:30 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="../assets/css/logreg.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>

    <body>
        <div class="container">
            <div class="btn-back_home">
                <p>
                    <i class="fa-solid fa-house-chimney"></i>
                    <a class="back_home-detail" href="../home/home.jsp">Homepage</a>
                </p>
            </div>
            <div class="container-form">
                <div class="left-part_space"></div>
                <div class="left_part">
                    <div class="left-heading">
                        <span>One Step Closer To your dream</span>
                    </div>
                    <div class="left-des">
                        <span>A free E-Learning service that is ready to help you become an expert</span>
                    </div>
                </div>
                <div class="right_part">
                    <form action="../register" method="POST" id="registration-form">
                        <div class="right-head">
                            <h3 class="right-title">Register</h3>
                            <p class="right-desc">Fill your info</p>
                        </div>
                        <div class="form-group"> 
                            <input id="fullname" name="fullname" type="text" placeholder="Your Name" class="form-control" required="required">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="email" name="email" type="text" placeholder="Email" class="form-control" required="required">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input type="password" id="password" name="password" class="form-control" placeholde="Password" required="required">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="password-confirm" name="password-confirm" type="password" placeholder="Re-enter your password" class="form-control" required="required">
                            <span id="msg"class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" id="accept-eula" required>
                            <label for="accept-eula">I accept the End User License Agreement (EULA)</label>
                        </div>
                        <button class="form-submit">Register</button>
                        <div class="logreg-link">
                            <span>Already have an account? <a href="./login.jsp">Login</a></span>
                        </div>
                    </form>
                </div>
                <div class="right-part_space"></div>
            </div>
        </div>
        <script>
            function passwordChecking() {
                var password = document.getElementById("password").value;
                var repassword = document.getElementById("password-confirm").value;

                if (password !== repassword) {
                    document.getElementById("msg").innerHTML = "Passwords do not match!";
                    return false;
                } else {
                    document.getElementById("msg").innerHTML = "";
                    return true;
                }
            }

            document.getElementById("registration-form").addEventListener("submit", function (event) {
                if (!passwordChecking()) {
                    event.preventDefault(); // Prevent form submission
                }
            });

            document.getElementById("accept-eula").addEventListener("change", function () {
                var registerButton = document.querySelector("#registration-form button[type='submit']");
                registerButton.disabled = !this.checked;
            });



        </script>  
    </body>
</html>
