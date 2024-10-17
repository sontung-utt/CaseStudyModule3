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
        <div class="error-message" style="color: red;">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
        <% } %>
        <form action="http://localhost:8080/accCustomers?action=view">
            <table border="1">
                <tr>
                    <th colspan="2">THÔNG TIN TÀI KHOẢN</th>
                </tr>
                <tr>
                    <td class="info">
                        <label for="id">Mã tài khoản</label>
                    </td>
                    <td><input type="text" name="id" id="id" readonly value="${customerAccount.id}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="username">Tên tài khoản</label>
                    </td>
                    <td><input type="text" name="username" id="username" readonly value="${customerAccount.username}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="password">Mật khẩu</label>
                    </td>
                    <td><input type="text" name="password" id="password" readonly value="${customerAccount.password}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="name">Ngày tạo</label>
                    </td>
                    <td><input type="text" name="name" id="name" readonly value="${customerAccount.created_at}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="age">Ngày sửa</label>
                    </td>
                    <td><input type="text" name="age" id="age" readonly value="${customerAccount.modified_at}"></td>
                </tr>

                    <td colspan="2" class="tdbtn">
                        <a href="http://localhost:8080/accCustomers?action=edit&id=${customerAccount.id}"><button type="button">Sửa thông tin</button></a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
