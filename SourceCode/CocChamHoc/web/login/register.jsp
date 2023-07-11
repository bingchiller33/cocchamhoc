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
        <title>Register</title>
        <link rel="stylesheet" href="../assets/css/logreg.css">
        <link rel="stylesheet" href="../assets/css/register.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>

    <body>
        <div class="container">
            <div class="btn-back_home">
                <p>
                    <a class="back_home-detail" href="/"><i class="fa fa-arrow-left" aria-hidden="true"></i> Homepage</a>
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
                    <form action="../register" method="POST" id="form-1">
                        <div class="right-head">
                            <h3 class="right-title">Register</h3>
                            <p class="right-desc">Create your account now! It's free and only took a minute!</p>
                        </div>
                        <div class="form-group"> 
                            <input id="fullname" name="fullname" type="text" placeholder="Your Name" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="email" name="email" type="text" placeholder="Email" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input type="password" id="password" name="password" class="form-control" placeholder="Password" >
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="password-confirm" name="password-confirm" type="password" placeholder="Re-enter your password" class="form-control" >
                            <span id="msg"class="form-message"></span>
                        </div>
                        <span class="error_msg">${Email_DUP}</span>
                        <div class="eulabox">
                            <input type="checkbox" id="accept-eula" name="checkbox" required>
                            <label for="accept-eula">I agree to COC <a href="../home">Terms of use</a></label>
                        </div>
                        <button class="form-submit">Register</button>
                        <div class="logreg-link">
                            <span>Already have an account? <a href="login">Login</a></span>
                        </div>
                    </form>
                </div>
                <div class="right-part_space"></div>
            </div>
        </div>
        <script src="../assets/js/register.js"></script>
        <script>
            Validator({
                form: "#form-1",
                formGroupSelector: ".form-group",
                querySelector: ".form-message",
                rules: [
                    Validator.isRequired("#fullname"),
                    Validator.isEmail("#email"),
                    Validator.isRequired("#email"),
                    Validator.isMinlength("#password", 8),
                    Validator.isMaxlength("#password", 32),
                    Validator.isRequired("#password-confirm"),
                    Validator.isConfirmed("#password-confirm", function () {
                        return document.querySelector("#form-1 #password").value;
                    }, "Password doesn't match!")
                ]
            });
        </script>  
    </body>
</html>
