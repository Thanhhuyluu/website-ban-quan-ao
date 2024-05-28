<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="app">
		<div class="app__container">
		<div class="main__payment-area">
                <div class="grid">
                	<c:set var="cart" value="${requestScope.cart}" />
                    <form method="post" action="hoan-tat-dat-hang" id="payment-form"  class="grid__row">
                       		<input name="action" value="hoan-tat-dat-hang" type="hidden">

                            <div class="grid__column-6-6">
                                <h1 class="payment-page__heading">Thông tin giao hàng</h1>
                            	<c:if test="${not empty USER }">
                            		<div class="payment-page__infor">
                                    <div class="payment-page__infor-input-group">
                                        <input name="fullname" id="payment-page__name-input" type="text" class="payment-page__infor-input" placeholder="HỌ TÊN" value="${USER.getFullname()}">
                                    	<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="phoneNumber" id="payment-page__phonenumber-input" type="text" class="payment-page__infor-input" placeholder="Số điện thoại" value="${USER.getPhoneNumber() }" >
                                    	<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="email" id="payment-page__email-input" type="text" class="payment-page__infor-input" placeholder="Email" value="${USER.getEmail() }">
                                    	<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="address" id="payment-page__address-input" type="text" class="payment-page__infor-input" placeholder="Địa chỉ">
                                   		<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="province" id="payment-page__province-input" type="text" class="payment-page__infor-input" placeholder="Tỉnh / Thành phố">
                                   		<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__input-group-wrapper">
                                    	<div  class="payment-page__infor-input-group">
                                        <input name="district"  id="payment-page__district-input"  type="text" class="payment-page__infor-input" placeholder="Quận / Huyện">
                                        <span class="form-message"></span>
                                        
	                                    </div>
	                                    <div class="payment-page__infor-input-group">
	                                        <input name="wards" id="payment-page__wards-input" type="text" class="payment-page__infor-input" placeholder="Phường / Xã">
	                                        <span class="form-message"></span>
	                                        
	                                    </div>
                                    
                                    </div>
                                    
                                    <div class="payment-page__infor-input-group">
                                        <input name="note" type="text" class="payment-page__infor-input" placeholder="Lưu ý">
										<span class="form-alert active"></span>                                        
                                    </div>
                                    
                                </div>
                            		<h1 class="payment-page__heading">Phương thức thanh toán</h1>
                                   	<div class="payment-by">
                                   		
                                   		<div class="payment-page__radio-input-group">
	                                        <input checked class="payment__radio-input" type="radio" id="" name="paymentMethod" value="cod">
	                                       
	                                        <span  class="radio-input-infor"> Thanh toán khi nhận hàng </span>
	                                        <i class="radio-input-infor-icon fa-regular fa-money-bill-1"></i>
	                                        
	                                    </div>
	                                    <div class="payment-page__radio-input-group">
	                                        <input class="payment__radio-input" type="radio" id="" name="paymentMethod" value="vnpay">
	                                        
	                                        <span class="radio-input-infor"> Thanh toán bằng VNPay</span>
	                                    	<i class="radio-input-infor-icon fa-regular fa-credit-card"></i>
	                                    </div>
                                   	</div>
                            	</c:if>
                            	<c:if test="${empty USER }">
                            		<div class="payment-page__infor">
                                    <div class="payment-page__infor-input-group">
                                        <input  name="fullname" id="payment-page__name-input" type="text" class="payment-page__infor-input" placeholder="HỌ TÊN">
                                    	<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="phoneNumber" id="payment-page__phonenumber-input" type="text" class="payment-page__infor-input" placeholder="Số điện thoại">
                                    	<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="email"  id="payment-page__email-input" type="text" class="payment-page__infor-input" placeholder="Email">
                                    	<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="address"  id="payment-page__address-input" type="text" class="payment-page__infor-input" placeholder="Địa chỉ">
                                   		<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__infor-input-group">
                                        <input name="province" id="payment-page__province-input" type="text" class="payment-page__infor-input" placeholder="Tỉnh / Thành phố">
                                   		<span class="form-message"></span>
                                    </div>
                                    <div class="payment-page__input-group-wrapper">
                                    	<div class="payment-page__infor-input-group">
                                        <input name="district"  id="payment-page__district-input"  type="text" class="payment-page__infor-input" placeholder="Quận / Huyện">
                                        <span class="form-message"></span>
                                        
	                                    </div>
	                                    <div class="payment-page__infor-input-group">
	                                        <input name="wards" id="payment-page__wards-input" type="text" class="payment-page__infor-input" placeholder="Phường / Xã">
	                                        <span class="form-message"></span>
	                                        
	                                    </div>
                                    
                                    </div>
                                    
                                    <div class="payment-page__infor-input-group">
                                        <input name="note" type="text" class="payment-page__infor-input" placeholder="Lưu ý">
										<span class="form-alert active"></span>                                        
                                    </div>
                                    
                                   
                                </div>
                            
                            	
                            	</c:if>
                            </div>
                            <div class="grid__column-3-3">
                               <div class="payment-page__payment-infor">

                                    <h1 class="payment-page__payment-infor-heading">Đơn hàng</h1>
                                    
                                    <div class="payment-page__product-list">
                                        
    									<c:forEach items="${cart.getItems() }" var="cartItem">
    										<c:set var="product" value="${cartItem.getProduct() }" />
											<c:set var="productDetail" value="${cartItem.getProductDetail() }"/>
    										<div class="payment-page__product-item">
                                            <div class="payment-page__row">
    
                                                <h3 class="payment-page__product-item-title">${cartItem.getProduct().getTitle() }</h3>
    
                                                <p class="payment-page__product-item-price">${product.getPrice() } vnđ</p>
                                                
                                            </div>
                                            <div class="payment-page__row">
    
                                                <p class="payment-page__product-item-size">Size: ${productDetail.getSize() }</p>
    
                                                <p class="payment-page__product-item-quantity">x ${ cartItem.getQuantity()}</p>
                                                
                                            </div>
                                        </div>
                                        
    									</c:forEach>
                                       

                                    </div>
                                    <div class="payment-page__price">
                                        <div class="payment-page__total-price-item">
                                            <div class="payment-page__row">
                                                <h3 class="payment-page-bold">
                                                    Đơn hàng
                                                </h3>
                                                <h3 class="payment-page-bold">
                                                    ${cart.getTotalMoney() } vnđ
                                                </h3>
                                            </div>
                                        </div>
                                        
                                        <div class="payment-page__total-price-item">
                                            <div class="payment-page__row">
                                                <h3 class="payment-page-bold">
                                                    Giảm
                                                </h3>
                                                <h3 class="payment-page-normal">
                                                    ${cart.getSaleoffMoney() } vnđ
                                                </h3>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="payment-page__total-price">
                                        <div class="payment-page__row">
                                            <h3 class="payment-page__total-price-heading">Tổng cộng</h3>
                                            <h3 class="payment-page__total-price-value">${cart.getAfterSaleoffMoney() } vnđ</h3>
                                        </div>
                                    </div>
                                    <button type="submit" class="payment-page__btn-submit">Hoàn tất đặt hàng</button>
                               </div>
                            </div>

                    </form>
    
    
                </div>
            </div>
		
		</div>
	
	</div>
</body>
</html>