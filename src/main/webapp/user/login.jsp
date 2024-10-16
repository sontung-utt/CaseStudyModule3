<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/15/2024
  Time: 11:04 AM
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
    <link rel="stylesheet" href="<c:url value='/css/login_register.css?v=1.0'/>">
    <title>Đăng nhập</title>
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
            <a href="http://localhost:8080/accounts?action=register">
                <div class="register">
                    <div class="icon">
                        <i class="fa-solid fa-user-plus"></i>
                    </div>
                    <div class="item">
                        <p>Liên hệ quản trị viên</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
    <div class="form">
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
        <% } %>
        <form action="http://localhost:8080/login" method="post">
            <table border="1">
                <tr>
                    <th colspan="2">ĐĂNG NHẬP HỆ THỐNG</th>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-solid fa-user"></i>
                        <label for="username">Tên đăng nhập</label>
                    </td>
                    <td><input type="text" name="username" id="username" required></td>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-solid fa-key"></i>
                        <label for="password">Mật khẩu</label>
                    </td>
                    <td><input type="password" name="password" id="password" required></td>
                </tr>

                <tr>
                    <td colspan="2" class="tdbtn">
                        <button type="submit">ĐĂNG NHẬP</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
