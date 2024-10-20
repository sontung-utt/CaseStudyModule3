    <%--
      Created by IntelliJ IDEA.
      User: Admin
      Date: 10/13/2024
      Time: 7:55 AM
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
        <link rel="stylesheet" href="<c:url value='/css/brand.css?v=1.0'/>">
        <title>Document</title>
    </head>
    <body>

    <div class="container">
        <%@ include file="/static/leftFrame.jsp" %>
        <div class="main-info">
            <%@ include file="/static/topFrame.jsp" %>
            <div class="info">

                <div class="form-brand">
                    <div class="search">
                        <div class="add-button">
                            <a href="http://localhost:8080/brands?action=add">
                                <i class="fa-solid fa-plus"></i>
                                <p>Thêm mới</p>
                            </a>
                        </div>
                    </div>
                    <div class="list-brand">
                        <table border="1">
                            <tr>
                                <th>STT</th>
                                <th>Mã thương hiệu</th>
                                <th>Tên thương hiệu</th>
                                <th>Hình ảnh</th>
                                <th>Hành động</th>
                            </tr>
                            <c:if test="${not empty errorMessage}">
                                <div class="error-message">
                                    <p>${errorMessage}</p>
                                </div>
                            </c:if>
                            <c:forEach var="item" items="${brandList}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td><img src="${item.image}" alt=""></td>
                                    <td>
                                        <a href="http://localhost:8080/brands?action=edit&id=${item.id}"><button class="btn btn-edit"><i class="fa-solid fa-pen-to-square"></i></button></a>
                                        <a href="http://localhost:8080/brands?action=delete&id=${item.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa thương hiệu này không?')";><button class="btn btn-remove"><i class="fa-solid fa-trash-can"></i></button></a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </div>
                <div class="form-category">
                    <div class="search">
                        <div class="select-input">
                            <h4>Chọn thương hiệu</h4>
                            <form action="brands" method="get">
                                <input type="hidden" name="action" value="brand">
                                <select name="idBrand" id="idBrand" onchange="this.form.submit()">
                                    <c:forEach var="item" items="${brandList}">
                                        <option value="${item.id}" ${param.idBrand == item.id ? 'selected' : ''}>
                                                ${item.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                    </div>
                    <div class="list-category">
                        <table border="1">
                            <tr>
                                <th>STT</th>
                                <th>Mã loại sản phẩm</th>
                                <th>Tên loại sản phẩm</th>
                                <th>Hình ảnh</th>
                            </tr>
                            <c:forEach var="item" items="${categories}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td><img src="${item.image}" alt=""></td>
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
