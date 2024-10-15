<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/16/2024
  Time: 3:36 AM
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
    <link rel="stylesheet" href="<c:url value='/css/account.css?v=1.0'/>">
    <title>Thông tin sản phẩm</title>
</head>
<body>
<div class="container">
    <div class="left-frame">
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
                        <div class="list-item"></div>
                        <div class="list-item"></div>
                        <div class="list-item"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main-info">
        <div class="top-frame">
            <div class="account"></div>
        </div>
        <div class="search">
            <div class="add-button">
                <a href="http://localhost:8080/accounts?action=add">
                    <i class="fa-solid fa-plus"></i>
                    <p>Thêm mới</p>
                </a>
            </div>
            <div class="sort-area">

            </div>
            <div class="search-input">
                <div class="icon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <div class="input">
                    <input type="text" id="search-input" placeholder="Tìm kiếm">
                </div>
            </div>
        </div>
        <div class="info-product">
            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Mã tài khoản</th>
                    <th>Tên tài khoản</th>
                    <th>Chức năng</th>
                    <th>Mật khẩu</th>
                    <th>Ngày tạo</th>
                    <th>Ngày sửa</th>
                    <th>Hành động</th>
                </tr>
                <c:forEach var="item" items="${accountList}" varStatus="loop">
                    <tr>
                        <td class="small">${loop.index + 1}</td>
                        <td class="medium">${item.id}</td>
                        <td class="medium">${item.name}</td>
                        <td class="medium">${item.roleName}</td>
                        <td class="medium">${item.password}</td>
                        <td class="large">${item.created_at}</td>
                        <td class="large">${item.modified_at}</td>
                        <td class="medium">
                            <a href="http://localhost:8080/accounts?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                            <a href="http://localhost:8080/accounts?action=delete&id=${item.id}"><button class="btn btn-remove"><i class="fa-solid fa-trash-can"></i></button></a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>
</body>
</html>
