<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ include file="/common/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yêu thích - High</title>
</head>
<body>

<c:set var="productList" value="${requestScope.productList }" />
<div class="main_liked_product_page">
            <div class="grid">
                <c:if test="${not empty productList  }">
                <ul  class="liked__product-list">
                    <c:forEach items="${productList }" var="commonProduct">
                    			<c:set var="product" value="${commonProduct.getProduct() }" />
                    			<c:set var="productDetail" value="${commonProduct.getProductDetail() }" />
                   					 <li class="liked__product__item">
                                        <div class="liked__product__item-product-infor">
                                            <div class="liked__product__item-product-img" style="background-image: url('./imgs/${product.getImg()}')"></div>
                                            <div class="liked__product__item-product-des">
                                                <h2 class="liked__product__item-product-title">${product.getTitle() }</h2>
                                                
                                                <span class="liked__product__item-product-old-price">${product.getPrice() } VNĐ</span>
    											<div class="cart-item__color">
													<c:set value="${commonProduct.getColorMap()}" var="colorMap" />
													<c:if test="${ colorMap != null}">

														<c:forEach items="${colorMap }" var="entry">

															<c:set var="color" value="${entry.key}" />
															<c:set var="isAvailable" value="${entry.value}" />
															<c:set var="selectedColor" value="${commonProduct.getProductDetail().getColor() }"/>
															<a href="yeu-thich?action=process&productDetailId=${productDetail.getId()}&selectedSize=${productDetail.getSize() }&selectedColor=${color }" class="cart-item__color-item  ${color == selectedColor? "
																selected-color":"" } ${isAvailable==
																true ?"" : "not-available" }" style="background-color:#${color}; color : #${color }; ${isAvailable == true ?" " : "cursor:not-allowed;pointer-events: none; "} ">
															
															</a>			
														</c:forEach>

													</c:if>
												</div>
                                                <div class="liked__product__item-product-selection">
                                                    <div class="liked__product-item__size">
														<h5 class="liked__product-item__size-title">SIZE</h5>
														<div class="liked__product-item__size-btn cart-item__select-btn">
														<c:if test="${productDetail!= null }">
														
															<span class="liked__product-item__size-current">${productDetail.getSize() }</span>
														</c:if>
															<i class="liked__product-item__size-icon fa-solid fa-chevron-down"></i>
															<div class="liked__product-item__select-container">
																<div class="liked__product-item__select-row">
																	<c:set value="${commonProduct.getSizeMap() }" var="sizeMap" />
																	<c:if test="${sizeMap != null}">
																		<c:forEach items="${sizeMap }" var="entry">
																			<c:set var="size" value="${entry.key}" />
														    				<c:set var="isAvailable" value="${entry.value}" />
																			<div class="cart-item__select__column-3">
																				<a href="yeu-thich?action=process&productDetailId=${productDetail.getId()}&selectedSize=${size }&selectedColor=${productDetail.getColor() }" class="cart-item__size-item-btn-link ${size == productDetail.getSize()?"selected-size":""} ${ isAvailable == true ?"" : "not-available"}" >
																				${size }
																				</a>
														    						
																			</div>
																		</c:forEach>
																		
																	
																	</c:if>
																	
																	


																</div>
															</div>
														</div>

													</div>
                                                    <div class="liked__product-item__count">
														<h5 class="liked__product-item__count-title">SỐ LƯỢNG</h5>
														<div class="liked__product-item-count-container">

															<div class="liked__product-item-count-btn liked__product-item-count-btn-dec">-</div>
															<input id="quantity-input-${productDetail.getId()}" type="text" class="liked__product-item-count-input"
																value="">
															<div class="liked__product-item-count-btn liked__product-item-count-btn-inc">+</div>
															<c:if test="${productDetail != null }">
															<input type="hidden" class="liked__product-item-current-quantity" value="${productDetail.getQuantity() }">
															</c:if>
															<c:if test="${productDetail == null }">
															<input type="hidden" class="liked__product-item-current-quantity" value="0">
															</c:if>
														</div>
													</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="liked__product__item-product-action">
                                       		 <c:set var="price" value="${product.getPrice()}" />
												<c:set var="discount" value="${product.getDiscount()}" />
			
												<c:set var="discountedPrice"
													value="${price - (price * discount / 100)}" />
                                            <h1 class="liked__product__item-product-main-price">${fn:replace(discountedPrice, '.0', '') } VNĐ</h1>
                                            
                                           	 <c:if test="${productDetail.getQuantity() != 0 }">
                                           	 	<span class="liked__product__item-product-quantity-status">Còn hàng</span>
                                           	 </c:if>
                                             <c:if test="${productDetail.getQuantity() == 0 }">
                                           	 	<span class="liked__product__item-product-quantity-status">Hết hàng</span>
                                           	 </c:if>
                                            <div onclick="addToCart(${productDetail.getId()})" class="liked__product__item-product-add-cart-btn " style="${productDetail.getQuantity() == 0 ? "pointer-events: none; background-color:rgb(55 ,42, 42,0.8);color: #adadad;":""}">
                                                <i class="fa-solid fa-cart-plus"></i>
                                            </div>
                                            <div onclick="deleteItem(${product.getId()})" class="liked__product__item-product-remove-btn">
    
                                                <i class="fa-solid fa-trash"></i>
                                            </div>
                                        </div>
                                    </li>
                    </c:forEach>
                </ul>
           
								<div class="cart-nav-link-wrapper">
									<div class="cart-nav-link">
										<a href="yeu-thich?action=delete-all" class="return-to-shop-link">XÓA HẾT</a>
									</div>
									<div class="cart-nav-link">
										<a href="trang-chu" class="return-to-shop-link">TIẾP TỤC MUA HÀNG</a>
									</div>
								</div>
							</c:if>
                
                <c:if test="${empty productList }">
								<h1 style="margin-top: 60px;" class="cart-heading">
									DANH MỤC YÊU THÍCH
								</h1>
								<h4 class="empty-cart-message">Bạn đang không thích sản phẩm nào!</h4>
								<div class="cart-nav-link">
									<a href="trang-chu" class="return-to-shop-link">TIẾP TỤC MUA HÀNG</a>
								</div>
				</c:if>
            </div>
           </div>
           <script type="text/javascript">
           const commonProductSizeBtns  = document.querySelectorAll('.liked__product-item__size-btn');



           commonProductSizeBtns.forEach(element => {
               element.addEventListener('click',()=>{
                   var sizeContainer = element.querySelector('.liked__product-item__select-container');

                   sizeContainer.classList.toggle('active');
               })

               

           });
           

			function addToCart(id){
				var quantity = $('#quantity-input-'+id).val();
				$.ajax({
				    type: "GET",
				    url: "/Online_Shop/yeu-thich?action=add-to-cart",
				    data: {
				    	productDetailId: id,
				    	buyQuantity : quantity
				    	
				    },
				    
				    success: function(result) {
				        location.reload();
				        
				    }
					});
			
			}
			function deleteItem(id){
				
				$.ajax({
				    type: "GET",
				    url: "/Online_Shop/yeu-thich?action=delete",
				    data: {
				    	productId: id
				    },
				    
				    success: function(result) {
				        location.reload();
				        
				    }
					});
			
			}
				
           </script>
           
</body>
</html>