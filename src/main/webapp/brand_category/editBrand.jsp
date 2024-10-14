<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/13/2024
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/editForm.css?v=1.0'/>">
    <script src="https://kit.fontawesome.com/bd7b2915a7.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="header-form">

        <div class="item-header">
            <a href="">
                <i class="fa-solid fa-box-open"></i>
                <p>Quản lý sản phẩm</p>
            </a>
        </div>
        <div class="item-header">
            <a href="http://localhost:8080/categories?action=category">
                <i class="fa-solid fa-box-archive"></i>
                <p>Quản lý loại sản phẩm</p>
            </a>
        </div>
        <div class="item-header">
            <a href="http://localhost:8080/brands?action=brand">
                <i class="fa-solid fa-tag"></i>
                <p>Quản lý thương hiệu</p>
            </a>
        </div>
        <div class="item-header">
            <a href="http://localhost:8080/products?action=product">
                <i class="fa-solid fa-house"></i>
                <p>Quay lại trang chủ</p>
            </a>
        </div>
        <div class="item-header">
            <a href="">
                <i class="fa-solid fa-users"></i>
                <p>Quản lý khách hàng</p>
            </a>
        </div>
        <div class="item-header">
            <a href="">
                <i class="fa-solid fa-circle-user"></i>
                <p>Quản lý tài khoản</p>
            </a>
        </div>
        <div class="item-header">
            <a href="">
                <i class="fa-solid fa-cart-shopping"></i>
                <p>Quản lý đơn hàng</p>
            </a>
        </div>
    </div>
    <div class="form">
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
        <% } %>
        <form action="http://localhost:8080/brands?action=edit" method="post">
            <table border="1">
                <tr>
                    <th colspan="2">SỬA THÔNG TIN THƯƠNG HIỆU</th>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-solid fa-list-ol"></i>
                        <label for="name">Mã thương hiệu</label>
                    </td>
                    <td><input type="text" name="id" id="id" readonly value="${brand.id}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-solid fa-gamepad"></i>
                        <label for="name">Tên thương hiệu</label>
                    </td>
                    <td><input type="text" name="name" id="name" value="${brand.name}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-regular fa-image"></i>
                        <label for="image">Hình ảnh</label>
                    </td>
                    <td><input type="text" name="image" id="image" value="${brand.image}"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <img src="${brand.image}" alt="">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="tdbtn">
                        <a href="">
                            <button>Sửa thông tin</button>
                        </a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
