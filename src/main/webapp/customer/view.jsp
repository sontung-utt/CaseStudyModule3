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
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/view.css?v=1.0'/>">
    <link rel="stylesheet" href="<c:url value='/css/list-product.css?v=1.0'/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <script src="https://kit.fontawesome.com/bd7b2915a7.js" crossorigin="anonymous"></script>
</head>

<body>
<div class="container">
    <div class="slide-bar">
        <div class="item">
            <div class="icon">
                <i class="fa-solid fa-arrow-right-arrow-left"></i>
            </div>
            <div class="content">
                <h3>Thu cũ</h3>
                <p>Giá ngon </p>
                <h3>- Lên đời </h3>
                <p>Tiết kiệm</p>
            </div>
        </div>
        <div class="item">
            <div class="icon">
                <i class="fa-solid fa-truck-fast"></i>
            </div>
            <div class="content">
                <h3>Giao nhanh - Miễn phí</h3>
                <p>cho đơn từ</p>
                <h4>300K</h4>
            </div>
        </div>
        <div class="item">
            <div class="icon">
                <i class="fa-solid fa-medal"></i>
            </div>
            <div class="content">
                <p>Sản phẩm</p>
                <h3>Chính hãng - Xuất VAT</h3>
                <p>đầy đủ</p>
            </div>
        </div>
        <div class="prev">
            <i class="fa-solid fa-chevron-left"></i>
        </div>
        <div class="next">
            <i class="fa-solid fa-chevron-right"></i>
        </div>
    </div>
    <div class="header">
        <div class="left-header">
            <div class="logo">
                <img src="image/logo.png" alt="">
            </div>
            <div class="category">
                <div class="icon">
                    <i class="fa-solid fa-list"></i>
                </div>
                <div class="item">
                    <p>Danh mục</p>
                </div>
            </div>
            <div class="province">
                <div class="icon">
                    <i class="fa-solid fa-location-dot"></i>
                </div>
                <div class="item">
                    <p>Thành phố của bạn</p>
                    <div class="icon-arrow">
                        <i class="fa-solid fa-chevron-down"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="mid-header">
            <div class="search" id="search">
                <div class="icon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <div class="input">
                    <input type="text" id="search-input" placeholder="Bạn cần tìm gì?">
                    <div class="icon-x">
                        <i class="fa-solid fa-x"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="right-header">
            <div class="phone">
                <div class="icon">
                    <i class="fa-solid fa-phone"></i>
                </div>
                <div class="item">
                    <p>Gọi mua hàng</p>
                    <p>0988.485.463</p>
                </div>
            </div>
            <div class="order">
                <div class="icon">
                    <i class="fa-solid fa-truck-fast"></i>
                </div>
                <div class="item">
                    <p>Tra cứu đơn hàng</p>
                </div>
            </div>
            <div class="cart">
                <a href="http://localhost:8080/cart?action=cart">
                    <div class="icon">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <div class="quantity-cart">${numProduct}</div>
                    </div>
                    <div class="item">
                        <p>Giỏ hàng</p>
                    </div>
                </a>
            </div>
            <div class="info">
                <a href="http://localhost:8080/view?action=add">
                    <div class="icon">
                        <i class="fa-solid fa-user-pen"></i>
                    </div>
                    <div class="item">
                        <p>Sửa thông tin cá nhân</p>
                    </div>
                </a>

            </div>
            <div class="register">
                <div class="icon">
                    <i class="fa-solid fa-user-large"></i>
                </div>
                <div class="item">
                    <a href="http://localhost:8080/accCustomers?action=view"><p>${customerUserName}</p></a>
                    <a href="http://localhost:8080/logout?action=customer"><p>Đăng xuất</p></a>
                </div>
            </div>

        </div>
    </div>
    <div class="filter-back-up" id="filterSection">
        <div class="filter-category">
            <div class="item">
                <i class="fa-solid fa-filter"></i>
                <h4>Bộ lọc của bạn</h4>
            </div>
            <div class="cate">
                <i class="fa-solid fa-list"></i>
                <h4>Loại sản phẩm</h4>
                <div class="icon-down">
                    <i class="fa-solid fa-caret-down"></i>
                </div>
            </div>
            <div class="price">
                <i class="fa-solid fa-money-bill"></i>
                <h4>Giá sản phẩm</h4>
                <div class="icon-down">
                    <i class="fa-solid fa-caret-down"></i>
                </div>
            </div>
            <div class="other">
                <i class="fa-solid fa-circle-info"></i>
                <h4>Khác</h4>
                <div class="icon-down">
                    <i class="fa-solid fa-caret-down"></i>
                </div>
            </div>
        </div>
        <div class="filter-sort">
            <div class="item">
                <i class="fa-solid fa-sort"></i>
                <h4>Sắp xếp theo</h4>
            </div>
            <div class="item">
                <button>Mới nhất</button>
            </div>
            <div class="item">
                <button>Nổi bật</button>
            </div>
            <div class="item">
                <button>Bán chạy</button>
            </div>
            <div class="item">
                <button>Giá tăng dần</button>
            </div>
            <div class="item">
                <button>Giá giảm dần</button>
            </div>
        </div>
    </div>
    <div class="info-product">
        <div class="search-category">
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-mobile-screen-button"></i>
                </div>
                <div class="content">
                    <a href="" class="multiple-link">
                        <h5>Điện thoại,&nbsp;</h5>
                    </a>
                    <a href="" class="multiple-link">
                        <h5>Máy tính bảng</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-laptop"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Laptop</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-headphones"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Âm thanh</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-camera-retro"></i>
                </div>
                <div class="content">
                    <a href="" class="multiple-link">
                        <h5>Đồng hồ,&nbsp;</h5>
                    </a>
                    <a href="" class="multiple-link">
                        <h5>Camera</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-house-signal"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Đồ gia dụng</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-keyboard"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Phụ kiện</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-desktop"></i>
                </div>
                <div class="content">
                    <a href="" class="multiple-link">
                        <h5>PC,&nbsp;</h5>
                    </a>
                    <a href="" class="multiple-link">
                        <h5>Màn hình,&nbsp;</h5>
                    </a>
                    <a href="" class="multiple-link">
                        <h5>Máy in</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-tv"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Tivi</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-money-bill-transfer"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Thu cũ đổi mới</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-hand-holding-dollar"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Hàng cũ</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-bullhorn"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Khuyến mãi</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="item">
                <div class="icon">
                    <i class="fa-solid fa-square-rss"></i>
                </div>
                <div class="content">
                    <a href="" class="single-link">
                        <h5>Tin công nghệ</h5>
                    </a>
                </div>
                <div class="arrow-icon">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
        </div>
        <div class="slide-bar-product">
            <div class="slide-container">
                <div class="slide-image">
                    <a href="" class="slide" data-name="Điện thoại 1">
                        <img src="image/slide-image-1.jpg" alt="image1">
                    </a>
                    <a href="" class="slide" data-name="Điện thoại 2">
                        <img src="image/slide-image-2.jpg" alt="image2">
                    </a>
                    <a href="" class="slide" data-name="Điện thoại 3">
                        <img src="image/slide-image-3.jpg" alt="image3">
                    </a>
                    <a href="" class="slide" data-name="Điện thoại 4">
                        <img src="image/slide-image-4.jpg" alt="image4">
                    </a>
                    <a href="" class="slide" data-name="Điện thoại 5">
                        <img src="image/slide-image-5.jpg" alt="image5">
                    </a>
                </div>
                <div class="prev-image">
                    <i class="fa-solid fa-chevron-left"></i>
                </div>
                <div class="next-image">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>
            </div>
            <div class="name-product">
                <div class="item-name">
                    <h5>Iphone 16 Series</h5>
                    <p>Giá ưu đãi</p>
                </div>
                <div class="item-name">
                    <h5>Huawei Watch Series</h5>
                    <p>Giảm mạnh 50%</p>
                </div>
                <div class="item-name">
                    <h5>Samsung Galaxy M72</h5>
                    <p>Hỗ trợ đổi trả</p>
                </div>
                <div class="item-name">
                    <h5>Samsung Galaxy M31</h5>
                    <p>Miễn phí giao hàng</p>
                </div>
                <div class="item-name">
                    <h5>Samsung Galaxy S10+</h5>
                    <p>Ưu đãi lớn</p>
                    <p></p>
                </div>
            </div>
        </div>
        <div class="hot-product">
            <div class="item">
                <a href="" class="hot-item">
                    <img src="image/hot-product.jpg" alt="">
                </a>
            </div>
            <div class="item">
                <a href="" class="hot-item">
                    <img src="image/hot-product-2.jpg" alt="">
                </a>
            </div>
            <div class="item">
                <a href="" class="hot-item">
                    <img src="image/hot-product-3.jpg" alt="">
                </a>
            </div>
        </div>
    </div>
    <div class="filter">
        <div class="filter-category">
            <div class="item">
                <i class="fa-solid fa-filter"></i>
                <h4>Bộ lọc của bạn</h4>
            </div>
            <div class="cate">
                <i class="fa-solid fa-list"></i>
                <h4>Loại sản phẩm</h4>
                <div class="icon-down">
                    <i class="fa-solid fa-caret-down"></i>
                </div>
            </div>
            <div class="price">
                <i class="fa-solid fa-money-bill"></i>
                <h4>Giá sản phẩm</h4>
                <div class="icon-down">
                    <i class="fa-solid fa-caret-down"></i>
                </div>
            </div>
            <div class="other">
                <i class="fa-solid fa-circle-info"></i>
                <h4>Khác</h4>
                <div class="icon-down">
                    <i class="fa-solid fa-caret-down"></i>
                </div>
            </div>
        </div>
        <div class="filter-sort">
            <div class="item">
                <i class="fa-solid fa-sort"></i>
                <h4>Sắp xếp theo</h4>
            </div>
            <div class="item">
                <button>Mới nhất</button>
            </div>
            <div class="item">
                <button>Nổi bật</button>
            </div>
            <div class="item">
                <button>Bán chạy</button>
            </div>
            <div class="item">
                <button>Giá tăng dần</button>
            </div>
            <div class="item">
                <button>Giá giảm dần</button>
            </div>
        </div>
    </div>
    <div class="list-product">
        <c:forEach var="product" items="${productList}">
            <div class="item-product">
                <div class="image-product">
                    <img src="${product.image}" alt="">
                </div>
                <div class="name-product">
                    <h3>${product.name}</h3>
                </div>
                <div class="price-product">
                    <h3>$</h3>
                    <h3>${product.price}VND</h3>
                </div>

                <div class="cart-plus">
                    <div class="description">
                        <p>${product.description}</p>
                    </div>
                    <div class="cart-i">
                        <a href="http://localhost:8080/cart?action=add&idProduct=${product.id}"><i class="fa-solid fa-cart-plus"></i></a>
                    </div>
                </div>

            </div>
        </c:forEach>


    </div>
    <div class="footer"></div>
</div>
</body>
<c:url value="/js/slide_image.js" var="myScriptPath" />
<script src="${myScriptPath}"></script>
<c:url value="/js/appear_filter.js" var="myScriptPath" />
<script src="${myScriptPath}"></script>
</html>