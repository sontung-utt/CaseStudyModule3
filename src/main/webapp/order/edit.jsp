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
        <link rel="stylesheet" href="<c:url value='/css/addProduct.css?v=1.0'/>">
        <title>Form sửa đơn hàng</title>
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
            <form action="http://localhost:8080/orderStaff" method="post">
                <table border="1">
                    <tr>
                        <th colspan="2">SỬA THÔNG TIN ĐƠN HÀNG</th>
                    </tr>
                    <tr>
                        <td class="info">
                            <label for="id">Mã đơn hàng</label>
                        </td>
                        <td><input type="text" name="id" id="id" value="${order.id}" readonly></td>
                    </tr>
                    <tr>
                        <td class="info">
                            <label for="nameCustomer">Tên khách hàng</label>
                        </td>
                        <td><input type="text" name="nameCustomer" id="nameCustomer" value="${order.nameCustomer}" readonly></td>
                    </tr>
                    <tr>
                        <td class="info">
                            <label for="total">Tổng tiền</label>
                        </td>
                        <td><input type="text" name="total" id="total" value="${order.total}" readonly></td>
                    </tr>
                    <tr>
                        <td class="info">
                            <label for="time">Ngày đặt</label>
                        </td>
                        <td><input type="text" name="time" id="time" value="${order.time}" readonly></td>
                    </tr>
                    <tr>
                        <td class="info">
                            <label for="status">Trạng thái</label>
                        </td>
                        <td>
                            <select id="status" name="status">
                                <c:forEach var="item" items="${statusList}">
                                    <option value="${item.name()}" <c:if test="${item.displayName==order.status}">selected</c:if>>
                                            ${item.displayName}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
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
