<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/15/2024
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <script src="https://kit.fontawesome.com/bd7b2915a7.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value='/css/home.css?v=1.0'/>">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="logo">
            <img src="image/logo.png" alt="">
        </div>
        <div class="user">
            <a href="http://localhost:8080/login">
                <div class="login">
                    <div class="icon">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="item">
                        <p>Đăng nhập hệ thống</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
    <div class="form">
        <h1>XIN VUI LÒNG ĐĂNG NHẬP!</h1>
        <h2>NẾU CHƯA CÓ TÀI KHOẢN YÊU CẦU LIÊN HỆ ADMIN ĐỂ ĐƯỢC CUNG CẤP</h2>
        <div class="select">
            <a href="http://localhost:8080/login"><button>ĐĂNG NHẬP</button></a>
        </div>
    </div>
</div>
</body>
</html>
