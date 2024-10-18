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
    <link rel="stylesheet" href="<c:url value='/css/addCustomer.css?v=1.0'/>">

    <title>Form thêm mới</title>
</head>
<body>
<div class="container">

    <div class="form">
        <div class="header-form">

            <div class="item-header">
                <a href="http://localhost:8080/view">
                    <i class="fa-solid fa-house"></i>
                    <p>Trang chủ</p>
                </a>
            </div>

        </div>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
        <% } %>
        <form action="http://localhost:8080/view?action=edit" method="post">
            <table border="1">
                <tr>
                    <th colspan="2">SỬA THÔNG TIN CÁ NHÂN</th>
                </tr>
                <tr>
                    <td class="info">
                        <label for="username">Tên tài khoản</label>
                    </td>
                    <td><input type="text" name="username" id="username" readonly value="${customerAccount.username}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="id">Mã khách hàng</label>
                    </td>
                    <td><input type="text" name="id" id="id" readonly value="${customer.id}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="name">Tên khách hàng</label>
                    </td>
                    <td><input type="text" name="name" id="name" value="${customer.name}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="age">Tuổi</label>
                    </td>
                    <td><input type="text" name="age" id="age" value="${customer.age}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="gender">Giới tính</label>
                    </td>
                    <td><input type="text" name="gender" id="gender" value="${customer.gender}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="address">Địa chỉ</label>
                    </td>
                    <td><input type="text" name="address" id="address" value="${customer.address}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="phone">Số điện thoại</label>
                    </td>
                    <td><input type="text" name="phone" id="phone" value="${customer.phone}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="email">Email</label>
                    </td>
                    <td><input type="text" name="email" id="email" value="${customer.email}"></td>
                </tr>
                <tr>
                    <td colspan="2" class="tdbtn">
                        <a href=""><button>Sửa thông tin</button></a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
