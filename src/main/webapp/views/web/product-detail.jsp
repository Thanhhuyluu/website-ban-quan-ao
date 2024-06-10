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

<c:set var="product" value="${requestScope.product }"/>
<title>${product.getTitle() }</title>
</head>
<body>
	<c:set var="productDetail" value="${requestScope.productDetail }" />
	<c:set var="formattedPrice" value="${requestScope.formattedPrice }" />
	<c:set var="relatedProducts" value="${requestScope.relatedProducts }" />
	<c:set var="availableSizes" value="${requestScope.availableSizes }" />
	<c:set var="imgList" value="${requestScope.imgList }"  />
	<c:set var="colorList" value="${requestScope.colorList }"/>
	<c:set var="selectedColor" value="${requestScope.selectedColor }"  />
	<c:set var="selectedSize" value="${requestScope.selectedSize }"  />
	<c:set var="productCurrentQuantity" value="${requestScope.productCurrentQuantity }"  />
	<c:set var="colorMap" value="${requestScope.colorMap }"/>
	<c:set var="likedProduct" value="${requestScope.likedProduct}" />
	<div class="grid">
		<div class="app__heading">
			<ul class="app__heading-list">
				<li class="app__heading-item">Áo</li>
				<li class="app__heading-separate"></li>
				<li class="app__heading-item">Áo thun Lenin hoạ tiết bắt mắt</li>
			</ul>
		</div>
		<div class="app__body">
			<div class="grid__row">
				<div class="grid__column-7">
					<div class="product-item-img"
						style="background-image: url(<c:url value='./imgs/${product.getImg() }'/>);"></div>

					<div class="product-item-imgs-container">
						<div class="product-item-imgs-btn-wrap">
							<i
								class="product-item-imgs-btn product-item-imgs-btn-left fa-solid fa-chevron-left"></i>
						</div>
						<div class="product-item-imgs-list">
						
						
							<div class="product-item-imgs-item"
								style="background-image: url(<c:url value="./imgs/${product.getImg() }"/>);"></div>
							
							<c:forEach items="${imgList}" var="img">
							
							
							
							<div class="product-item-imgs-item"
								style="background-image: url(<c:url value="./imgs/${img.getImg() }"/>);"></div>
							
							
							</c:forEach>
						
						</div>

						<div class="product-item-imgs-btn-wrap">
							<i
								class="product-item-imgs-btn product-item-imgs-btn-right fa-solid fa-chevron-right"></i>
						</div>

					</div>
				</div>
				
				<div id = "san-pham-form" class="grid__column-5">
					
					<h4 class="product__heading-title">${product.getTitle() }</h4>
					<h6 class="product__detail">
						Mã sản phẩm: <strong>AV00207</strong>
					</h6>
					<h5 class="product__price">${formattedPrice} vnđ</h5>
					<div class="product__divider"></div>
					<div class="product__color">
					
						 <c:if test="${ colorMap != null}">
							
							<c:forEach items="${colorMap }" var="entry" >
							
								<c:set var="color" value="${entry.key}" />
    							<c:set var="isAvailable" value="${entry.value}" />
    							
    							<input  value="${color}" type="button" class="product__color-item  ${color == selectedColor? "selected-color":"" } ${isAvailable == true ?"" : "not-available" }" style="background-color:#${color}; color : #${color }; ${isAvailable == true ?"" : "cursor:not-allowed; "} " />
    							
							</c:forEach>
						
						</c:if> 
					
						<!--
						
						FORM GỬI MÀU ĐÃ CHỌN
						
						  -->
						
						<form id="colorSenderForm" action="chi-tiet-san-pham" method="get">
							<input id="selectedColor" name="selectedColor" type="hidden" value=""/>
							<input id="selectedSize" name="selectedSize" type="hidden" value=""/>
							<input value="${product.getId() }" name="proId" type="hidden"/>
						</form>
						
						
                        
					</div>
					<div class="product__divider"></div>

					<div class="product__select">
						<div class="product__size">
							<h5 class="product__size-title">SIZE</h5>
							<div class="product__size-btn product__select-btn">
								<span class="product__size-current">${selectedSize }</span> <i
									class="product__size-icon fa-solid fa-chevron-down"></i>
								<div class="product__select-container">
									<div class="product__select-row">
									
									
									
									
									<c:forEach items = "${availableSizes }" var="size">
									
										<div class="product__select__column-3">
											<input type="button" class="product__size-item-btn ${size == selectedSize? "selected-size":"" }" value="${size}" >
										</div>
										
									</c:forEach>
									
									
									
									</div>
								</div>
							</div>

						</div>
						<div class="product__count">
							<h5 class="product__count-title">SỐ LƯỢNG</h5>
							<div class="product-count-container">

								<div class="product-count-btn product-count-btn-dec">-</div>
								<input type="text" class="product-count-input"value"1">
								<div class="product-count-btn product-count-btn-inc">+</div>

							</div>
							<span class="product-current-quantity-wrapper">Hiện còn <span
								class="product-current-quantity">${productCurrentQuantity }</span> sản phẩm trong kho
							</span>
						</div>

					</div>
					<div action="gio-hang" class="product__select"></div>
					<form id="add-to-cart-form" class="product__add-cart">
						<button  class="product__add-cart-btn" onclick="addToCart()" style="${productCurrentQuantity == 0 ? "pointer-events: none; background-color: #555050; color:#a1a1a1; ":""}">THÊM VÀO GIỎ HÀNG</button>
						<input id="add-cart-action" type="hidden" name="action" value="add"/>
						<input id="productDetail-id" type="hidden" name="productDetailId" value="${productDetail.getId() }"/>
						<input id="buy-quantity" type="hidden" name="buyQuantity" value=""/>
						
						<c:set var="isProductInList" value="false" />
   						 	<c:forEach var="id" items="${likedProduct}">
								<c:if test="${id == product.getId()}">
									<c:set var="isProductInList" value="true" />
								</c:if>
							</c:forEach>
										    
						<div id="product-item-like__btn-${product.getId() }" onclick="likeProduct(${product.getId()},'main')" class="product__cart-icon-heart ${isProductInList == true ?"active-hearted":""  }">
							
							<i class="icon-heart-full fa-solid fa-heart"></i>
						</div>
					</form>
					<div class="product__payment"><a href="thanh-toan?action=thanh-toan" class="product__payment-link" style="${productCurrentQuantity == 0 ? "pointer-events: none; background-color: #555050; color:#a1a1a1;cursor: not-allowed; ":""}">THANH TOÁN</a></div>
					<div class="panel-group">
						<div class="panel-item ">
							<div class="panel-item__heading js-panel-item__heading-infor ">
								THÔNG TIN SẢN PHẨM <i
									class="product__size-icon fa-solid fa-chevron-down"></i>
							</div>
							<div class="product__divider "></div>
							<div class="panel-item__sub js-panel-item__sub ">
								
							
								<div class="panel-item__sub-text">Giới tính: 
								
									<c:if test="${product.getGender() == 1 }">Nam</c:if>
									<c:if test="${product.getGender() == 2 }">Nữ</c:if>
									<c:if test="${product.getGender() == 3 }">Nam/Nữ</c:if>
									
								</div>
								<div class="panel-item__sub-text">Mô tả: ${product.getDescription() }</div>
								
								<div class="panel-item__sub-img"
									style="background-image: url(<c:url value = "/template/web/assets/imgs/chon-size.png"/>);"></div>
							</div>
						</div>
						<div class="panel-item">
							<div class="panel-item__heading js-panel-exchange">
								QUY ĐỔI SẢN PHẨM <i
									class="product__size-icon fa-solid fa-chevron-down"></i>
							</div>
							<div class="product__divider"></div>
							<div class="panel-item__sub js-exchange-sub ">
								<h6 class="">
									<ul>
										<li class="panel-text">Chỉ đổi hàng 1 lần duy nhất, mong
											bạn cân nhắc kĩ trước khi quyết định.</li>
										<li class="panel-text">Thời hạn đổi sản phẩm khi mua trực
											tiếp tại cửa hàng là 07 ngày, kể từ ngày mua. Đổi sản phẩm
											khi mua online là 14 ngày, kể từ ngày nhận hàng.</li>
										<li class="panel-text">Sản phẩm đổi phải kèm hóa đơn. Bắt
											buộc phải còn nguyên tem, hộp, nhãn mác.</li>
										<li class="panel-text">Sản phẩm đổi không có dấu hiệu đã
											qua sử dụng, không giặt tẩy, bám bẩn, biến dạng.</li>
										<li class="panel-text">High chỉ ưu tiên hỗ trợ đổi
											size. Trong trường hợp sản phẩm hết size cần đổi, bạn có thể
											đổi sang 01 sản phẩm khác: - Nếu sản phẩm muốn đổi ngang giá
											trị hoặc có giá trị cao hơn, bạn sẽ cần bù khoảng chênh lệch
											tại thời điểm đổi (nếu có). - Nếu bạn mong muốn đổi sản phẩm
											có giá trị</li>
										<li class="panel-text">Trong trường hợp sản phẩm - size
											bạn muốn đổi không còn hàng trong hệ thống. Vui lòng chọn sản
											phẩm khác.</li>
										<li class="panel-text">Không hoàn trả bằng tiền mặt dù
											bất cứ trong trường hợp nào. Mong bạn thông cảm.</li>
									</ul>
								</h6>
							</div>
						</div>
						
					</div>
				</div>
				</div>
		</div>


	</div>

	<div class="container-later">
		<div class="product__divider divider-full"></div>
		<div class="container__footer">
			<div class="grid">
				<div class="container__footer-heading">SẢN PHẨM LIÊN QUAN</div>
				<div class="grid__row">
					<!-- <div class="grid__column-2"></div> -->
					<div class="container__footer-parents">
						<div class="container__footer-slider">
							<div class="container__footer-list-item">
							
							<c:forEach items="${relatedProducts }" var="pro">
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(<c:url value="./imgs/${pro.getImg()}"/>);">
											<div class="product-item-action">
												<a href="chi-tiet-san-pham?proId=${pro.getId() }" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<c:set var="isProductInList" value="false" />
    
											    <c:forEach var="id" items="${likedProduct}">
											        <c:if test="${id == pro.getId()}">
											            <c:set var="isProductInList" value="true" />
											        </c:if>
											    </c:forEach>
												<div id="product-item-like__btn-${pro.getId() }" onclick="likeProduct(${pro.getId()},'relate')"
													class="product-item-like__btn  ${isProductInList == true ?"product-item-like__btn--liked":""  }">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
												</div>
												
											</div>
										</div>
										<h4 class="product-item__name">${pro.getTitle() }</h4>
										<c:set var="price" value="${pro.getPrice()}" />
										<c:set var="discount" value="${pro.getDiscount()}" />
										<span class="product-item__price-old">${price } vnđ</span>
										<span
											class="product-item__price-current">${price - (price * discount / 100)}vnđ</span>
									</div>
								</div>
								
							</c:forEach>
							
							
							</div>
							<div class="container__footer-icon-list">
								<div
									class="container__footer-icon-item container__footer-icon-left">
									<i class="fa-solid fa-chevron-left"></i>
								</div>
								<div
									class="container__footer-icon-item container__footer-icon-right">
									<i class="fa-solid fa-chevron-right"></i>
								</div>
							</div>
						</div>
					</div>


					<!-- <div class="grid__column-2"></div>  -->


				</div>
			</div>
		</div>
	</div>

	<script>
	/* XỬ LÝ CHỨC NĂNG CHỌN XEM HÌNH ẢNH */
	    document.addEventListener("DOMContentLoaded", function() {
	        var imgsItems = document.querySelectorAll(".product-item-imgs-item");
	
	        imgsItems.forEach(function(item) {
	            item.addEventListener("click", function() {
	                var imageUrl = item.style.backgroundImage;
	                document.querySelector(".product-item-img").style.backgroundImage = imageUrl;
	            });
	        });
	    });
	    
	    
	  /* XỬ LÝ GỬI MÀU ĐƯỢC CHỌN VỀ SERVLET ĐỂ LẤY SIZE HIỆN CÓ CỦA MÀU ĐÓ */
	  
	  
   		var colorButtons = document.querySelectorAll('.product__color-item');
    	var selectedColorInput = document.getElementById('selectedColor');

    	var sizeButtons = document.querySelectorAll('.product__size-item-btn');
    	var selectedSizeInput = document.getElementById('selectedSize');
    	var selectedColorButton = document.querySelector('.product__color-item.selected-color');
    	var selectedSizeButton = document.querySelector('.product__size-item-btn.selected-size');
    	colorButtons.forEach(function(button) {
    		
            button.addEventListener('click', function() {
                if(!this.classList.contains('not-available')){
                	selectedColorInput.value = this.value; 
                    selectedSizeInput.value = selectedSizeButton.value;
                    
                    console.log(selectedColorInput.value)
                    document.getElementById('colorSenderForm').submit(); 
                }
            });
        });
    	
    	

    	
    	sizeButtons.forEach(function(button) {
    		
            button.addEventListener('click', function() {
            	selectedSizeInput.value = this.value; 
            	selectedColorInput.value = selectedColorButton.value;
                document.getElementById('colorSenderForm').submit(); 
            });
        });  
    	
    	const addToCartForm = document.getElementById('add-to-cart-form');
    	const currentQuatityToBuy = document.querySelector('.product-count-input');
    	function addToCart(){
    		var addToCartQuantityInput = document.getElementById('buy-quantity');
    		addToCartQuantityInput.value= currentQuatityToBuy.value;
    		addToCartForm.action = "gio-hang";
    		addToCartForm.submit();
    	}
    	
    	
    	function likeProduct(Id,flat){
			$.ajax({
			    type: "POST",
			    url: "/Online_Shop/like-product",
			    data: {
			    	productId: Id
			    	
			    },
			    
			    success: function(result) {
			        var likedProduct = document.getElementById('product-item-like__btn-'+Id);
			        if(flat == 'main')
			        	{
			        		likedProduct.classList.toggle('active-hearted')
			        	}
			        else if(flat=='relate'){
			        	likedProduct.classList.toggle('product-item-like__btn--liked');
			        }
			        location.reload();
					}
				});
		
		}
    	
    	
    	
    	</script>
</html>