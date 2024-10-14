<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/14/2024
  Time: 12:09 AM
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
    <link rel="stylesheet" href="<c:url value='/css/brandcategory.css?v=1.0'/>">
    <title>Document</title>
</head>
<body>
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
        <div class="info">
            <div class="form">
                <div class="search">
                    <div class="add-button">
                        <a href="http://localhost:8080/brand_category?action=add">
                            <i class="fa-solid fa-plus"></i>
                            <p>Thêm mới</p>
                        </a>
                    </div>

                </div>
                <div class="list">
                    <table border="1">
                        <tr>
                            <th>STT</th>
                            <th>Mã loại</th>
                            <th>Mã loại sản phẩm</th>
                            <th>Tên loại sản phẩm</th>
                            <th>Mã thương hiệu</th>
                            <th>Tên thương hiệu</th>
                            <th>Hành động</th>
                        </tr>
                        <c:forEach var="item" items="${list}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${item.id}</td>
                                <td>${item.category.id}</td>
                                <td>${item.category.name}</td>
                                <td>${item.brand.id}</td>
                                <td>${item.brand.name}</td>
                                <td>
                                    <a href="http://localhost:8080/brand_category?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                                    <a href="http://localhost:8080/brand_category?action=delete&id=${item.id}"><button class="btn btn-remove"><i class="fa-solid fa-trash-can"></i></button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>