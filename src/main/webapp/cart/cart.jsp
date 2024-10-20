<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/12/2024
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/bd7b2915a7.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/cart.css?v=1.0'/>">

    <title>Quản lý giỏ hàng</title>
</head>
<body>
<div class="container">

        <div class="header-form">

            <div class="item-header">
                <a href="http://localhost:8080/view">
                    <i class="fa-solid fa-house"></i>
                    <p>Trang chủ</p>
                </a>
            </div>

        </div>

        <div class="info-cart">

            <table border="1">
                <tr>
                    <th colspan="6">THÔNG TIN GIỎ HÀNG</th>
                </tr>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Ngày thêm vào giỏ hàng</th>
                    <th>Cập nhật giỏ hàng</th>
                </tr>
                <c:forEach var="item" items="${cartDetailList}" varStatus="loop">

                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${item.nameProduct}</td>
                        <td>${item.quantity}</td>
                        <td>${item.price}</td>
                        <td>${item.created_at}</td>
                        <td class="btn">
                            <a href="http://localhost:8080/cart?action=add&idProduct=${item.idProduct}"><button><i class="fa-solid fa-cart-arrow-down"></i></button></a>
                            <a href="http://localhost:8080/cart?action=delete&id=${item.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng không?')";><button><i class="fa-solid fa-trash"></i></button></a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="6" class="thbtn">
                        <a href="http://localhost:8080/orderCustomer">ĐẶT HÀNG</a>
                    </th>
                </tr>
            </table>

    </div>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <p><%= request.getAttribute("errorMessage") %></p>
    </div>
    <% } %>
</div>
</body>
</html>
