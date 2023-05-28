<%-- 
    Document   : login
    Created on : May 26, 2023, 6:46:57 PM
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
                <div class="left_part"></div>
                <div class="right_part">
                    <form action="#">
                        <div class="right-head">
                            <h3 class="right-title">Register</h3>
                            <p class="right-desc">Fill your info</p>
                        </div>
                        <div class="form-group"> 
                            <input id="email" name="email" type="text" placeholder="Email" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="password" name="password" type="text" placeholder="Password" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-remember"> 
                            <input id="remember-account" name="remember-account" type="checkbox" checked placeholder="a" class="check-remember">
                            <span class="remember-detail"> Remember this account</span>
                        </div>
                        <button class="form-submit">Login</button>
                        <div class="logreg-link">
                            <span>Don't have an account? <a href="./register.jsp">Register now</a></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
