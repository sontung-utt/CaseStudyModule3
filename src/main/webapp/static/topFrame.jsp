<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/16/2024
  Time: 10:46 AM
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
    <link rel="stylesheet" href="<c:url value='/css/product.css?v=1.0'/>">
    <link rel="stylesheet" href="<c:url value='/css/topFrame.css?v=1.0'/>">
    <title>Thông tin sản phẩm</title>
</head>
<body>
<div class="top-frame">
    <a href="">
        <div class="account">
            <i class="fa-solid fa-user"></i>
            <p>${username}</p>
        </div>
    </a>
    <a href="http://localhost:8080/logout">
        <div class="logout">
            <i class="fa-solid fa-right-from-bracket"></i>
            <p onclick="return confirm('Bạn có chắc chắn muốn đăng xuất không?')";>Đăng xuất</p>
        </div>
    </a>

</div>
</body>

<c:url value="/js/changeColor.js" var="myScriptPath" />
<script src="${myScriptPath}"></script>
