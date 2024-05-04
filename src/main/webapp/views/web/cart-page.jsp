<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>cart page</title>
</head>
<body>
	<c:set var="cart" value="${requestScope.cart}" />


    <div class="app">
        
        <div class="app__container">
            <div class="main__cart">
                <div class="grid">
                    <div class="grid__row">
                        <div class="grid__column-6-6">
                            <ul class="cart__list">
                                <li class="cart__item">
                                    <div class="cart__item-product-infor">
                                        <div class="cart__item-product-img" style="background-image: url(<c:url value="/template/web/assets/imgs/leninn-tan-basic-tanktop.jpg"/>);"></div>
                                        <div class="cart__item-product-des">
                                            <h2 class="cart__item-product-title">Áo thun leninn họa tiết bắt mắt</h2>
                                            <span class="cart__item-product-old-price">250.000 vnđ</span>
                                            <div class="cart-item__color">
                                            	<input  value="" type="button" class="cart-item__color-item"/>
                                            	<input  value="" type="button" class="cart-item__color-item"/>
                                            	<input  value="" type="button" class="cart-item__color-item"/>
                                            </div>

                                            <div class="cart__item-product-selection">
                                                <div class="cart-item__size">
                                                    <h5 class="cart-item__size-title">SIZE</h5>
                                                    <div class="cart-item__size-btn cart-item__select-btn">
                                                        <span class="cart-item__size-current">M</span>
                                                        <i class="cart-item__size-icon fa-solid fa-chevron-down"></i>
                                                        <div class="cart-item__select-container">
                                                            <div class="cart-item__select-row">
                                                                <div class="cart-item__select__column-3">
                                                                    <input type="button" class="cart-item__size-item-btn selected-size" value="M">
                                                                </div>
                                                                <div class="cart-item__select__column-3">
                                                                    <input type="button" class="cart-item__size-item-btn" value="B">
                                                                </div>
                                                                <div class="cart-item__select__column-3">
                                                                    <input type="button" class="cart-item__size-item-btn" value="a">
                                                                </div>
                                                                <div class="cart-item__select__column-3">
                                                                    <input type="button" class="cart-item__size-item-btn" value="d">
                                                                </div>
                                                                <div class="cart-item__select__column-3">
                                                                    <input type="button" class="cart-item__size-item-btn" value="e">
                                                                </div>
                                                                <div class="product__select__column-3">
                                                                    <input type="button" class="cart-item__size-item-btn" value="M">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <div class="cart-item__count">
                                                    <h5 class="cart-item__count-title">SỐ LƯỢNG</h5>
                                                    <div class="cart-item-count-container">
            
                                                        <div class="cart-item-count-btn cart-item-count-btn-dec">-</div>
                                                        <input type="text" class="cart-item-count-input" value"1">
                                                        <div class="cart-item-count-btn cart-item-count-btn-inc">+</div>
                                                        <input id="cart-item-current-quantity" type="hidden" value="3"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cart__item-product-action">
                                        <h1 class="cart__item-product-main-price">250.000 vnđ</h1>
                                        <span class="cart__item-product-quantity-status">Còn hàng</span>
                                        <div class="cart__item-product-like-btn liked">
                                            <i class="filled-heart fa-solid fa-heart"></i>
                                            <i class="empty-heart fa-regular fa-heart"></i>
                                        </div>
                                        <div class="cart__item-product-remove-btn">

                                            <i class="fa-solid fa-trash"></i>
                                        </div>
                                    </div>
                                </li>
                               
                                
                                
                                
                            </ul>
                        </div>
                        <div class="grid__column-3-3">
                            <div class="payment__container">
                                <h1 class="payment-heading">Đơn hàng</h1>
                                <div class="payment-item-infor-row">
                                    <h3 class="payment-item-heading">Đơn hàng</h3>
                                    <h3 class="payment-item-des">620.000 vnđ</h3>
                                </div>
                                <div class="payment-item-infor-row">
                                    <h3 class="payment-item-heading">Giảm</h3>
                                    <h3 class="payment-item-des">0 vnđ</h3>
                                </div>
                                <div class="payment-item-infor-row">
                                    <h3 class="payment-item-heading">Tạm tính</h3>
                                    <h3 class="payment-item-des">620.000 vnđ</h3>
                                </div>
                                <div class="payment-submit-button">
                                    Tiếp tục thanh toán
                                </div>
                            </div>
                        </div>
                    </div>
    
    
                </div>
            </div>
            
        </div>
        

    </div>

</body>
</html>