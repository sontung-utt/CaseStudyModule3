<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/16/2024
  Time: 12:02 AM
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
    <link rel="stylesheet" href="<c:url value='/css/staff.css?v=1.0'/>">
    <title>Thông tin nhân sự</title>
</head>
<body>
<div class="container">
    <%@ include file="/static/leftFrame.jsp" %>
    <div class="main-info">
        <%@ include file="/static/topFrame.jsp" %>
        <div class="search">
            <div class="add-button">
                <a href="http://localhost:8080/staff?action=add">
                    <i class="fa-solid fa-plus"></i>
                    <p>Thêm mới</p>
                </a>
            </div>
            <div class="sort-area">

            </div>
            <div class="search-input">
                <form action="http://localhost:8080/staff" method="get">
                    <input type="hidden" name="action" value="staff">
                    <div class="icon">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <div class="input">
                        <input type="text" id="name" name="name" placeholder="Tìm kiếm" value="${param.name}" onchange="this.form.submit()">
                    </div>
                </form>
            </div>
        </div>
        <div class="info-product">
            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Mã nhân sự</th>
                    <th>Tên nhân sự</th>
                    <th>Tên tài khoản</th>
                    <th>Giới tính</th>
                    <th>Hình ảnh</th>
                    <th>Điện thoại</th>
                    <th>Email</th>
                    <th>Lương</th>
                    <th>Phòng ban</th>
                    <th>Hành động</th>
                </tr>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">
                        <p>${errorMessage}</p>
                    </div>
                </c:if>
                <c:forEach var="item" items="${staffList}" varStatus="loop">
                    <tr>
                        <td class="small">${loop.index + 1}</td>
                        <td class="medium">${item.id}</td>
                        <td class="medium">${item.name}</td>
                        <td class="medium">${item.username}</td>
                        <td class="medium">${item.gender}</td>
                        <td class="medium"><img src="${item.image}" alt=""></td>
                        <td class="medium">${item.phone}</td>
                        <td class="large">${item.email}</td>
                        <td class="medium">${item.salary}</td>
                        <td class="medium">${item.nameDepartment}</td>
                        <td class="medium">
                            <a href="http://localhost:8080/staff?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                            <a href="http://localhost:8080/staff?action=delete&id=${item.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa nhân sự này không?')";><button class="btn btn-remove"><i class="fa-solid fa-trash-can"></i></button></a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>
</body>
</html>
