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
<title>High</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/template/web/assets/imgs/high-log.ico"/>">
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
								<c:set var="cart" value="${requestScope.cart }" />
								<c:set var="tt" value="0" />
								<c:forEach items="${cart.getItems() }" var="cartItem">
									<c:set var="product" value="${cartItem.getProduct() }" />
									<c:set var="productDetail" value="${cartItem.getProductDetail() }"/>									<li class="cart__item">
										<div class="cart__item-product-infor">
											<div class="cart__item-product-img"
												style="background-image: url(<c:url value="/template/web/assets/imgs/${product.getImg() }"/>);"></div>
											<div class="cart__item-product-des">
												<h2 class="cart__item-product-title">${ cartItem.getProduct().getTitle() }</h2>
												<c:if test="${product.getDiscount()!=0 }">
													<span class="cart__item-product-old-price">${cartItem.getProduct().getPrice() }
														vnđ</span>
												</c:if>
												<c:if test="${product.getDiscount()==0 }">
													<span class="cart__item-product-old-price"></span>
												</c:if>
												<div class="cart-item__color">
													<c:set value="${cartItem.getColorMap()}" var="colorMap" />
													<c:if test="${ colorMap != null}">

														<c:forEach items="${colorMap }" var="entry">

															<c:set var="color" value="${entry.key}" />
															<c:set var="isAvailable" value="${entry.value}" />
															<c:set var="selectedColor" value="${cartItem.getProductDetail().getColor() }"/>
															<a href="gio-hang?action=process&productDetailId=${productDetail.getId()}&selectedSize=${productDetail.getSize() }&selectedColor=${color }&quantity=${cartItem.getQuantity() }" class="cart-item__color-item  ${color == selectedColor? "
																selected-color":"" } ${isAvailable==
																true ?"" : "not-available" }" style="background-color:#${color}; color : #${color }; ${isAvailable == true ?" " : "cursor:not-allowed; "} ">
															
															</a>			
														</c:forEach>

													</c:if>
												</div>

												<div class="cart__item-product-selection">
													<div class="cart-item__size">
														<h5 class="cart-item__size-title">SIZE</h5>
														<div class="cart-item__size-btn cart-item__select-btn">
															<span class="cart-item__size-current">${ cartItem.getProductDetail().getSize() }</span>
															<i class="cart-item__size-icon fa-solid fa-chevron-down"></i>
															<div class="cart-item__select-container">
																<div class="cart-item__select-row">
																	<c:set value="${cartItem.getSizeMap() }" var="sizeMap" />
																	<c:if test="${sizeMap != null}">
																		<c:forEach items="${sizeMap }" var="entry">
																			<c:set var="size" value="${entry.key}" />
														    				<c:set var="isAvailable" value="${entry.value}" />
																			<div class="cart-item__select__column-3">
																				<a href="gio-hang?action=process&productDetailId=${productDetail.getId()}&selectedSize=${size }&selectedColor=${productDetail.getColor() }&quantity=${cartItem.getQuantity() }" class="cart-item__size-item-btn-link ${size == productDetail.getSize()?"selected-size":""} ${ isAvailable == true ?"" : "not-available"}" >
																				${size }
																				</a>
														    						
																			</div>
																		</c:forEach>
																		
																	
																	</c:if>
																	
																	


																</div>
															</div>
														</div>

													</div>
													<div class="cart-item__count">
														<h5 class="cart-item__count-title">SỐ LƯỢNG</h5>
														<div class="cart-item-count-container">

															<div class="cart-item-count-btn cart-item-count-btn-dec"><a class="cart-item-count-btn-link" href="gio-hang?action=quantity_change&num=-1&productDetailId=${productDetail.getId() }">-</a></div>
															<input type="text" class="cart-item-count-input"
																value="${cartItem.getQuantity() }">
															<div class="cart-item-count-btn cart-item-count-btn-inc"><a class="cart-item-count-btn-link" href="gio-hang?action=quantity_change&num=1&productDetailId=${productDetail.getId() }">+</a></div>
															
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="cart__item-product-action">

											<c:set var="price" value="${product.getPrice()}" />
											<c:set var="discount" value="${product.getDiscount()}" />
											<h1 class="cart__item-product-main-price">${price - (price * discount / 100)}
												vnđ</h1>
											<span class="cart__item-product-quantity-status">Còn hàng</span>

											<div class="cart__item-product-like-btn liked">
												<i class="filled-heart fa-solid fa-heart"></i> <i
													class="empty-heart fa-regular fa-heart"></i>
											</div>
											<a href="gio-hang?action=delete&deleteCartItemId=${productDetail.getId() }" class="cart__item-product-remove-btn">

												<i class="fa-solid fa-trash"></i>
											</a>

											
										</div>
										
										
										
									</li>
									
									<c:if test="${ cartItem.getQuantity() > productDetail.getQuantity()}">
										<div class="cart-item-error">
												<h4 class="cart-item-error-text">Sản phẩm trên chỉ còn ${productDetail.getQuantity() }. Vui lòng cập nhận lại số lượng</h4>
										</div>
									</c:if>	
								</c:forEach>
										

				

							</ul>
							
							<c:if test="${!cart.isEmpty( ) }">
								<div class="cart-nav-link-wrapper">
									<div class="cart-nav-link">
										<a href="gio-hang?action=delete-all" class="return-to-shop-link">XÓA HẾT</a>
									</div>
									<div class="cart-nav-link">
										<a href="trang-chu" class="return-to-shop-link">TIẾP TỤC MUA HÀNG</a>
									</div>
								</div>
							</c:if>
	
						</div>
						<c:if test="${!cart.isEmpty()}">
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
								<div class="payment-submit-button">Tiếp tục thanh toán</div>
							</div>
						</div>
						</c:if>
					</div>
					
					<c:if test="${cart.isEmpty()}">
								<h1 class="cart-heading">
									GIỎ HÀNG CỦA BẠN
								</h1>
								<h4 class="empty-cart-message">Bạn đang không có sản phẩm nào trong giỏ hàng!</h4>
								<div class="cart-nav-link">
									<a href="trang-chu" class="return-to-shop-link">TIẾP TỤC MUA HÀNG</a>
								</div>
					</c:if>

				</div>
			</div>

		</div>


	</div>


</body>
<script>
			
		

</script>
</html>