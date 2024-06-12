<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<c:set var="likedProduct" value="${requestScope.likedProduct}" />
	<div class="app__container">
            <div class="grid">
                <div class="order-detail__main-container">
                    <h1 class="order-detail__heading">Đơn hàng ngày 05/11/2024</h1>
                    <ul class="order-detail__list">
                        <c:set var="orderDetails" value="${requestScope.orderDetails }"/>
                        
                        <c:forEach items="${orderDetails }" var="orderDetail">
                        	<c:set var="order" value="${ orderDetail.getOrder()}" />
                        	<c:set var="productDetail" value="${orderDetail.getProductDetail() }" />
                        	
                        	<c:set var="product" value="${productDetail.getProduct()}" />
                        	<li class="order-detail__item">
                            
                            <div class="order-detail__item-product-infor">
                                <div class="order-detail__item-product-img" style="background-image: url(<c:url value="./imgs/${product.getImg() }"/>);"></div>
                                <div class="order-detail__item-product-des">
                                	
                                    <h2 class="order-detail__item-product-title">${product.getTitle() }</h2>
                                    <span class="order-detail__item-product-old-price">${product.getPrice() } vnđ</span>
                                    <div class="order-detail__item-color">
                                        

                                            
                                                <a href="" class="order-detail__item-color-item" style="background-color:#${productDetail.getColor()};">
                                                    
                                                </a>			
                                                
                                           
                                    </div>

                                    <div class="order-detail__item-product-selection">
                                        <div class="product__size">
                                            <h5 class="product__size-title">SIZE</h5>
                                            <div class="product__size-btn product__select-btn">
                                                <span class="product__size-current">${productDetail.getSize() }</span>
                                               
                                            </div>
                                            
                                        </div>
                                        <div class="product__count">
                                            <h5 class="product__count-title">SỐ LƯỢNG</h5>
                                            <div class="product-count-container">
    
                                                
                                                <input disabled type="text" class="product-count-input" value="${orderDetail.getQuantity() }">
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="order-detail__item-product-action">
                                <h1 class="order-detail__item-product-main-price">250.000 vnđ</h1>
                                <span class="order-detail__item-product-quantity-status">Còn hàng</span>
                                <c:set var="isProductInList" value="false" />
    
                                <c:forEach var="id" items="${likedProduct}">
									<c:if test="${id == product.getId()}">
										<c:set var="isProductInList" value="true" />
									</c:if>
								</c:forEach>
                                <div id="order-detail__item-product-like-btn-${product.getId() }" onclick="likeProduct(${product.getId()})" class="order-detail__item-product-like-btn  ${isProductInList== true?"liked":""}">
                                    <i class="filled-heart fa-solid fa-heart"></i>
                                    
                                </div>
                                
                            </div>
                        
                    </li>
                        </c:forEach>
                        
                    </ul>
                </div>

            </div>
        </div>
<script type="text/javascript">

           function likeProduct(Id){
				$.ajax({
				    type: "POST",
				    url: "/Online_Shop/like-product",
				    data: {
				    	productId: Id
				    	
				    },
				    
				    success: function(result) {
				        var likedProduct = document.getElementById('order-detail__item-product-like-btn-'+Id);
				        likedProduct.classList.toggle('liked');
				        
				        location.reload();
				    
				    }
				    
				    	
					});
			
			}
</script>
</body>

</html>