<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/16/2024
  Time: 9:35 PM
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
    <link rel="stylesheet" href="<c:url value='/css/leftFrame.css?v=1.0'/>">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="left-main">
        <div class="logo">
            <div class="logo-img">
                <img src="image/logo.png" alt="">
            </div>
            <div class="user">
                <i class="fa-solid fa-user-group"></i>
                <p>Nhân viên</p>
            </div>
        </div>
        <div class="manage">
            <div class="icon">
                <div class="item">
                    <i class="fa-solid fa-circle-user"></i>
                </div>
                <div class="item">
                    <i class="fa-solid fa-house"></i>
                </div>
                <div class="item">
                    <i class="fa-solid fa-box-open"></i>
                </div>
                <div class="item">
                    <i class="fa-solid fa-users"></i>
                </div>
                <div class="item">
                    <i class="fa-solid fa-cart-shopping"></i>
                </div>
                <div class="item"></div>
            </div>
            <div class="info">
                <div class="name">
                    <div class="type-name"></div>
                    <div class="icon-name">
                        <i class="fa-solid fa-bars"></i>
                    </div>
                </div>
                <div class="list">
                    <div class="list-item">
                        <a href="http://localhost:8080/products?action=product"><i class="fa-solid fa-boxes-packing"></i>Quản lý sản phẩm</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/categories?action=category"><i class="fa-solid fa-box-open"></i>Quản lý loại sản phẩm</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/brands?action=brand"><i class="fa-solid fa-rectangle-list"></i>Quản lý thương hiệu</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/brand_category?action=home"><i class="fa-solid fa-layer-group"></i>Quản lý nhóm sản phẩm</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/accounts?action=account"><i class="fa-solid fa-user-group"></i>Quản lý tài khoản nhân sự</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/accounts?action=customer"><i class="fa-solid fa-circle-user"></i>Quản lý tài khoản khách hàng</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/staff?action=staff"><i class="fa-solid fa-user-tie"></i>Quản lý nhân sự</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/customers"><i class="fa-solid fa-users"></i>Quản lý khách hàng</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/departments?action=department"><i class="fa-solid fa-building-user"></i>Quản lý phòng ban</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/role?action=role"><i class="fa-solid fa-user-shield"></i></i>Quản lý chức năng</a>
                    </div>
                    <div class="list-item">
                        <a href="http://localhost:8080/orderStaff"><i class="fa-solid fa-cart-shopping"></i></i></i>Quản lý đơn hàng</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>