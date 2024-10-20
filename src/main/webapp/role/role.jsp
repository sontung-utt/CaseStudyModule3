<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/15/2024
  Time: 7:19 PM
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
    <link rel="stylesheet" href="<c:url value='/css/role.css?v=1.0'/>">
    <title>Document</title>
</head>
<body>

<div class="container">
    <%@ include file="/static/leftFrame.jsp" %>
    <div class="main-info">
        <%@ include file="/static/topFrame.jsp" %>
        <div class="info">

            <div class="form-role">
                <div class="search">
                    <div class="add-button">
                        <a href="http://localhost:8080/role?action=add">
                            <i class="fa-solid fa-plus"></i>
                            <p>Thêm mới</p>
                        </a>
                    </div>

                </div>
                <div class="list-role">

                    <table border="1">
                        <tr>
                            <th>STT</th>
                            <th>Mã chức năng</th>
                            <th>Tên chức năng</th>
                            <th>Hành động</th>
                        </tr>
                        <c:if test="${not empty errorMessage}">
                            <div class="error-message">
                                <p>${errorMessage}</p>
                            </div>
                        </c:if>
                        <c:forEach var="item" items="${roleList}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>
                                    <a href="http://localhost:8080/role?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                                    <a href="http://localhost:8080/role?action=delete&id=${item.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa chức năng này không?')";><button class="btn btn-remove"><i class="fa-solid fa-trash-can"></i></button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="form-account">
                <div class="search">
                    <div class="select-input">
                        <h4>Chọn chức năng</h4>
                        <form action="role" method="get">
                            <input type="hidden" name="action" value="role">
                            <select name="idRole" id="idRole" onchange="this.form.submit()">
                                <c:forEach var="item" items="${roleList}">
                                    <option value="${item.id}" ${param.idRole == item.id ? 'selected' : ''}>
                                            ${item.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="list-account">
                    <table border="1">
                        <tr>
                            <th>STT</th>
                            <th>Mã tài khoản</th>
                            <th>Tên tài khoản</th>
                            <th>Ngày tạo</th>
                            <th>Ngày sửa</th>
                        </tr>
                        <c:forEach var="item" items = "${accounts}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${item.id}</td>
                                <td>${item.username}</td>
                                <td>${item.created_at}</td>
                                <td>${item.modified_at}</td>
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

