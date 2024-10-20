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
    <link rel="stylesheet" href="<c:url value='/css/vieworder.css?v=1.0'/>">

    <title>Quản lý đơn hàng</title>
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
                    <th colspan="6">LỊCH SỬ ĐẶT HÀNG</th>
                </tr>
                <tr>
                    <th>STT</th>
                    <th>Mã đơn hàng</th>
                    <th>Ngày đặt hàng</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Chi tiết đơn hàng</th>
                </tr>

                <c:forEach var="item" items="${orderList}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${item.id}</td>
                        <td>${item.time}</td>
                        <td>${item.total}</td>
                        <td>${item.status}</td>
                        <td class="tdbtn">
                            <a href="http://localhost:8080/orderCustomer?action=viewDetail&id=${item.id}"><button class="btn btn-remove"><i class="fa-solid fa-eye"></i></button></a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
    </div>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
            <p>${errorMessage}</p>
        </div>
    </c:if>

</div>
</body>
</html>
