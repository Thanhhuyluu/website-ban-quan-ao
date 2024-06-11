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
<title>Trang chủ - High</title>

</head>
<body>
	<c:set var="gender" value="${requestScope.gender }" />
	<c:set var="genderString" value="gender=${requestScope.gender}&" />
	<c:set var="saleoff" value="${requestScope.saleoff }" />
	<c:set var="saleoffString" value="saleoff=${requestScope.saleoff}&" />
	<c:set var="searchKey" value="${requestScope.searchKey }" />
	<c:set var="searchKeyString" value="searchKey=${requestScope.searchKey}&" />
	<c:set var="likedProduct" value="${requestScope.likedProduct}" />
	<div class="grid">
		<div class="grid__row">
			<div class="grid__column-3">
				<div class="app__sidebar-category">
					<div class="sidebar-category-nav">
						<ul class="sidebar-category-nav-list">
							<li class="sidebar-category-nav-item"><a href="trang-chu"
								class="sidebar-category-nav-link ${requestScope.gender == null? "active":"" }" ">TẤT
									CẢ</a></li>
							<li class="sidebar-category-nav-item"><a
								href="trang-chu?gender=1"
								class="sidebar-category-nav-link ${requestScope.gender == 1? "active":"" }" >NAM</a>
							</li>
							<li class="sidebar-category-nav-item"><a
								href="trang-chu?gender=2"
								class="sidebar-category-nav-link ${requestScope.gender == 2? "active":"" }">NỮ</a>
							</li>
						</ul>

					</div>
					<div class="sidebar-category-menu">

						<div class="sidebar-category-item">
							<div class="sidebar-category-heading">
								ÁO<i class="sidebar-category-icon fa-solid fa-chevron-down"></i>
							</div>
							<div class="sidbar-category-sub-item-wrapper">
								<c:forEach items="${requestScope.upperList}" var="ct">

									<div class="sidebar-category-sub-item ">
										<a href="trang-chu?${gender==null?"
											":genderString }cateId=${ ct.getId() }
											" class="sidebar-category-sub-link">${ct.getName()} </a>
									</div>
								</c:forEach>
							</div>

						</div>
						<div class="sidebar-category-item">
							<div class="sidebar-category-heading">
								QUẦN<i class="sidebar-category-icon fa-solid fa-chevron-down"></i>
							</div>
							<div class="sidbar-category-sub-item-wrapper">
								<c:forEach items="${requestScope.lowerList }" var="ct">

									<div class="sidebar-category-sub-item ">
										<a href="trang-chu?${gender==null?"
											":genderString }cateId=${ ct.getId() }
											" class="sidebar-category-sub-link ">${ct.getName() }</a>
									</div>

								</c:forEach>

							</div>
						</div>
						<div class="sidebar-category-item">
							<div class="sidebar-category-heading">PHỤ KIỆN</div>
						</div>

					</div>
				</div>
			</div>
			<div class="grid__column-9">
				<div class="home-slider-container">
					<i
						class="home-slider-btn home-slider-btn-left fa-solid fa-angle-left"></i>
					<div class="home-img-slider">
						<div class="home-img-slider-wrap">
							<img src="<c:url value='/template/web/assets/imgs/slide.jpg'/>"
								alt="" class="home-img-slider__item">
						</div>
						<div class="home-img-slider-wrap">
							<img src="<c:url value='/template/web/assets/imgs/slide2.jpg'/>"
								alt="" class="home-img-slider__item">
						</div>
						<div class="home-img-slider-wrap">
							<img src="<c:url value='/template/web/assets/imgs/slide.jpg'/>"
								alt="" class="home-img-slider__item">
						</div>
					</div>
					<i
						class="home-slider-btn home-slider-btn-right fa-solid fa-angle-right"></i>
				</div>
				<div class="home-product">

					<div class="grid__row">

						<c:forEach items="${requestScope.list }" var="p">

							<div class="grid__column-3-3">
								<!-- product item -->
								<div class="product-item">
									<div class="product-item__img"
										style="background-image: url('./imgs/${p.getImg()}')">
										<div class="product-item-action">
											<a href="chi-tiet-san-pham?proId=${p.getId() }"
												class="product-item-buy__btn-link">
												<div class="product-item-buy_btn">Mua ngay</div>
											</a>

											<div id="product-item-like-${p.getId() }" onclick="likeProduct(${p.getId()})"
											
											<c:set var="isProductInList" value="false" />
    
										    <c:forEach var="id" items="${likedProduct}">
										        <c:if test="${id == p.getId()}">
										            <c:set var="isProductInList" value="true" />
										        </c:if>
										    </c:forEach>
											
												class="product-item-like__btn  ${isProductInList == true ?"product-item-like__btn--liked":""  } ">
												<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
											</div>
										</div>
									</div>
									<h4 class="product-item__name">${p.getTitle()}</h4>
									<span class="product-item__price-old">${ p.getPrice()}
										vnđ</span>
									<c:set var="price" value="${p.getPrice()}" />
									<c:set var="discount" value="${p.getDiscount()}" />

									<c:set var="discountedPrice"
										value="${price - (price * discount / 100)}" />

									<span class="product-item__price-current">${fn:replace(discountedPrice, '.0', '')}vnđ</span>

									<form action="chi-tiet-san-pham" class="sendProductIdForm">
										<input id="idSender" type="hidden" value="" name="proId">
									</form>
									<!-- xử lý gửi Id -->
									
								</div>

							</div>

						</c:forEach>

					</div>
				</div>

				<h3 class="no-product-infor">Không có sản phẩm nào</h3>
				<ul class="pagination home-product__pagination">

					<c:set var="page" value="${requestScope.page }" />


					<li class="pagination-item "><a
						href="trang-chu?${searchKey==null? "": searchKeyString }${saleoff==null?"":saleoffString }${requestScope.gender== null ? "":genderString}page=${ (page == 1 ? 1 : (page - 1)) }
						" class="pagination-item__link"> <i
							class="pagination-item__icon fa-solid fa-chevron-left"></i>
					</a></li>

					<c:forEach begin="${1}" end="${requestScope.pageNum }" var="i">
						<li class="pagination-item ${i == page ? "pagination-item--active" : "" }">

							<a href="trang-chu?${searchKey==null? "": searchKeyString }${saleoff==null?"
							":saleoffString }${requestScope.gender== null ? "" :
							genderString}page=${ i} " class="pagination-item__link ">${i }</a>
						</li>

					</c:forEach>


					<li class="pagination-item"><a
						href="trang-chu?${searchKey==null? "": searchKeyString }${saleoff==null?"
						":saleoffString }${requestScope.gender== null ? "" :
						genderString}page=${ (page == pageNum ? pageNum : (page + 1)) }
						" class="pagination-item__link"> <i
							class="pagination-item__icon fa-solid fa-chevron-right"></i>
					</a></li>
				</ul>
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
				        var likedProduct = document.getElementById('product-item-like-'+Id);
				        likedProduct.classList.toggle('product-item-like__btn--liked');
				        
				        location.reload();
				    
				    }
				    
				    	
					});
			
			}
			
				var container = document
						.querySelector(".home-product .grid__row");
				console.log(container);
				var elements = container.querySelector(".grid__column-3-3");
				var navigation = document
						.querySelectorAll(".pagination-item__link");
				var noProduct = document.querySelector(".no-product-infor");
				console.log(noProduct);
				if (!elements) {
					navigation.forEach(function(element) {
						element.remove();
					});

				} else {
					console.log("co product");

					noProduct.style.display = "none";
				}
			</script>
		</div>
	</div>



</body>

</html>