<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/14/2024
  Time: 12:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/addBrandCategory.css'/>">
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
            <a href="http://localhost:8080/products?action=product">
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
        <form action="" method="post">
            <table border="1">
                <tr>
                    <th colspan="2">THÊM MỚI LOẠI SẢN PHẨM - THƯƠNG HIỆU</th>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-solid fa-box-archive"></i>
                        <label for="idCategoryBrand">Loại sản phẩm</label>
                    </td>
                    <td>
                        <select id="idCategoryBrand" name="idCategoryBrand">
                            <option value="Java">Java</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="info">
                        <i class="fa-solid fa-box-archive"></i>
                        <label for="idBrandCategory">Thương hiệu</label>
                    </td>
                    <td>
                        <select id="idBrandCategory" name="idBrandCategory">
                            <option value="Java">Java</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" class="tdbtn">
                        <a href=""><button>Thêm mới</button></a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
