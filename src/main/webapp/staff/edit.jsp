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
    <link rel="stylesheet" href="<c:url value='/css/addStaff.css?v=1.0'/>">
    <title>Form sửa</title>
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
                <i class="fa-solid fa-circle-user"></i>
                <p>Quản lý tài khoản</p>
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
        <form action="http://localhost:8080/staff?action=edit" method="post">
            <table border="1">
                <tr>
                    <th colspan="2">SỬA THÔNG TIN NHÂN SỰ</th>
                </tr>
                <tr>
                    <td class="info">
                        <label for="id">Mã nhân sự</label>
                    </td>
                    <td><input type="text" name="id" id="id" readonly value="${staff.id}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="name">Tên nhân sự</label>
                    </td>
                    <td><input type="text" name="name" id="name" required value="${staff.name}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="age">Tuổi</label>
                    </td>
                    <td><input type="text" name="age" id="age" required value="${staff.age}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="gender">Giới tính</label>
                    </td>
                    <td><input type="text" name="gender" id="gender" required value="${staff.gender}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="address">Địa chỉ</label>
                    </td>
                    <td><input type="text" name="address" id="address" value="${staff.address}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="phone">Số điện thoại</label>
                    </td>
                    <td><input type="text" name="phone" id="phone" required value="${staff.phone}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="email">Email</label>
                    </td>
                    <td><input type="text" name="email" id="email" required value="${staff.email}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="image">Hình ảnh</label>
                    </td>
                    <td><input type="text" name="image" id="image" value="${staff.image}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="userId">Mã tài khoản</label>
                    </td>
                    <td><input type="text" name="userId" id="userId" value="${staff.userId}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="salary">Lương</label>
                    </td>
                    <td><input type="text" name="salary" id="salary" value="${staff.salary}"></td>
                </tr>
                <tr>
                    <td class="info">
                        <label for="idDepartment">Phòng ban</label>
                    </td>

                    <td>
                        <select id="idDepartment" name="idDepartment">
                            <c:forEach var="item" items="${departmentList}">
                                <option value="${item.id}" <c:if test="${item.id == staff.idDepartment}">selected</c:if>>
                                        ${item.name}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>


                <tr>
                    <td colspan="2" class="tdbtn">
                        <button>Sửa thông tin</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
