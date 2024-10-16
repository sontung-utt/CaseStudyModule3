<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/15/2024
  Time: 5:46 PM
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
    <link rel="stylesheet" href="<c:url value='/css/department.css?v=1.0'/>">
    <title>Document</title>
</head>
<body>

<div class="container">
    <%@ include file="/static/leftFrame.jsp" %>
    <div class="main-info">
        <%@ include file="/static/topFrame.jsp" %>
        <div class="info">

            <div class="form-department">
                <div class="search">
                    <div class="add-button">
                        <a href="http://localhost:8080/departments?action=add">
                            <i class="fa-solid fa-plus"></i>
                            <p>Thêm mới</p>
                        </a>
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
                <div class="list-department">

                    <table border="1">
                        <tr>
                            <th>STT</th>
                            <th>Mã phòng ban</th>
                            <th>Tên phòng ban</th>
                            <th>Hệ số lương</th>
                            <th>Hành động</th>
                        </tr>
                        <c:if test="${not empty errorMessage}">
                            <div class="error-message">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <c:forEach var="item" items="${departmentList}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>${item.salaryCoefficient}</td>
                                <td>
                                    <a href="http://localhost:8080/departments?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                                    <a href="http://localhost:8080/departments?action=delete&id=${item.id}"><button class="btn btn-remove"><i class="fa-solid fa-trash-can"></i></button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="form-staff">
                <div class="search">
                    <div class="select-input">
                        <h4>Chọn phòng ban</h4>
                        <form action="departments" method="get">
                            <input type="hidden" name="action" value="department">
                            <select name="idDepartment" id="idDepartment" onchange="this.form.submit()">
                                <c:forEach var="item" items="${departmentList}">
                                    <option value="${item.id}" ${param.idDepartment == item.id ? 'selected' : ''}>
                                            ${item.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </form>

                    </div>
                </div>
                <div class="list-staff">
                    <table border="1">
                        <tr>
                            <th>STT</th>
                            <th>Mã nhân sự</th>
                            <th>Tên nhân sự</th>
                            <th>Hình ảnh</th>
                            <th>Số điện thoại</th>
                            <th>Email</th>
                        </tr>
                        <c:forEach var="item" items="${staffs}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td><img src="${item.image}" alt=""></td>
                                <td>${item.phone}</td>
                                <td>${item.email}</td>
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
