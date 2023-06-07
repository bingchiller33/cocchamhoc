<%-- 
    Document   : Denied
    Created on : Jun 2, 2023, 2:12:16 AM
    Author     : Viet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200&display=swap" rel="stylesheet">
        <style>
            *{
                padding: 0;
                margin: 0;
                font-family: 'Poppins', sans-serif;
            }
            .denied-container{
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .content {
                text-align: center;
            }
            .content h1{
                font-size: 100px;
                font-weight: 900;
                color: #FCD980;
            }
            .content p {
                font-size: 38px;
                font-weight: 600;
                color: #232536;
            }
            .content a {
                padding: 15px 50px;
                text-decoration: none;
                color: #282938;
                background-color: #FCD980;
                font-weight: 600;
                border-radius: 6px;
            }
        </style>
    </head>
    <body>
        <div class="denied-container">
            <div class="content">
                <h1>ACCESS DENIED</h1>
                <p>You Don't Have Permission To View This Page</p>
                <br>
                <a href="/">Homepage</a>
            </div>
        </div>
    </body>
</html>
