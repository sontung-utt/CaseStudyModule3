<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/16/2024
  Time: 5:33 PM
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
    <link rel="stylesheet" href="<c:url value='/css/customer.css?v=1.0'/>">
    <title>Thông tin đơn hàng</title>
</head>
<body>
<div class="container">
    <%@ include file="/static/leftFrame.jsp" %>
    <div class="main-info">
        <%@ include file="/static/topFrame.jsp" %>
        <div class="search">
            <div class="sort-area">

            </div>
            <div class="search-input">
                <form action="http://localhost:8080/orderStaff" method="get">
                    <input type="hidden" name="action" value="order">
                    <div class="icon">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <div class="input">
                        <input type="text" id="nameCustomer" name="nameCustomer" placeholder="Tìm kiếm" value="${param.nameCustomer}" onchange="this.form.submit()">
                    </div>
                </form>
            </div>
        </div>
        <div class="info-product">
            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Mã đơn hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Ngày đặt hàng</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">
                        <p>${errorMessage}</p>
                    </div>
                </c:if>
                <c:forEach var="item" items="${orderList}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${item.id}</td>
                        <td>${item.nameCustomer}</td>
                        <td>${item.time}</td>
                        <td>${item.total}</td>
                        <td>${item.status}</td>
                        <td class="medium">
                            <a href="http://localhost:8080/orderStaff?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                            <a href="http://localhost:8080/orderStaff?action=orderDetail&id=${item.id}"><button class="btn btn-remove"><i class="fa-solid fa-eye"></i></button></a>
                        </td>

                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>
</body>
</html>