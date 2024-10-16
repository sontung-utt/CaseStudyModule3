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
    <title>Thông tin khách hàng</title>
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
        <%@ include file="/static/topFrame.jsp" %>
        <div class="search">
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
                    <th>Mã khách hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Tuổi</th>
                    <th>Giới tính</th>
                    <th>Địa chỉ</th>
                    <th>Điện thoại</th>
                    <th>Email</th>
                </tr>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">
                        <p>${errorMessage}</p>
                    </div>
                </c:if>
                <c:forEach var="item" items="${customerList}" varStatus="loop">
                    <tr>
                        <td class="small">${loop.index + 1}</td>
                        <td class="medium">${item.id}</td>
                        <td class="medium">${item.name}</td>
                        <td class="medium">${item.age}</td>
                        <td class="medium">${item.gender}</td>
                        <td class="large">${item.address}</td>
                        <td class="medium">${item.phone}</td>
                        <td class="large">${item.email}</td>
                    </tr>
                </c:forEach>


            </table>
        </div>
    </div>
</div>
</body>
</html>