<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>High</title>
</head>
<body>
	<c:set var="productDetail" value="${requestScope.productDetail }"  />
	<c:set var="product" value="${requestScope.product }"  />
	<c:set var="imgList" value="${requestScope.imgList }"  />
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
						style="background-image: url(<c:url value='/template/web/assets/imgs/${product.getImg() }'/>);"></div>

					<div class="product-item-imgs-container">
						<div class="product-item-imgs-btn-wrap">
							<i
								class="product-item-imgs-btn product-item-imgs-btn-left fa-solid fa-chevron-left"></i>
						</div>
						<div class="product-item-imgs-list">
						
						
							<div class="product-item-imgs-item"
								style="background-image: url(<c:url value="/template/web/assets//imgs/${product.getImg() }"/>);"></div>
							
							<c:forEach items="${imgList}" var="img">
							
							
							
							<div class="product-item-imgs-item"
								style="background-image: url(<c:url value="/template/web/assets//imgs/${img.getImg() }"/>);"></div>
							
							
							</c:forEach>
						
						</div>

						<div class="product-item-imgs-btn-wrap">
							<i
								class="product-item-imgs-btn product-item-imgs-btn-right fa-solid fa-chevron-right"></i>
						</div>

					</div>
				</div>
				<form class="grid__column-5">
					<h4 class="product__heading-title">${product.getTitle() }</h4>
					<h6 class="product__detail">
						Mã sản phẩm: <strong>AV00207</strong>
					</h6>
					<h5 class="product__price">${product.getPrice() }</h5>
					<div class="product__divider"></div>
					<div class="product__color">
						<div class="product__color-item product__color-1"></div>
						<div class="product__color-item product__color-2"></div>
						<div class="product__color-item product__color-3"></div>
					</div>
					<div class="product__divider"></div>

					<div class="product__select">
						<div class="product__size">
							<h5 class="product__size-title">SIZE</h5>
							<div class="product__size-btn product__select-btn">
								<span class="product__size-current">M</span> <i
									class="product__size-icon fa-solid fa-chevron-down"></i>
								<div class="product__select-container">
									<div class="product__select-row">
									<c:forEach items = "${productDetail }" var="detail">
									
										<div class="product__select__column-3">
											<input type="button" class="product__size-item-btn" value="${detail.getSize() }">
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
								class="product-current-quantity">3</span> sản phẩm trong kho
							</span>
						</div>

					</div>
					<div class="product__select"></div>
					<div class="product__add-cart">
						<button class="product__add-cart-btn">THÊM VÀO GIỎ HÀNG</button>
						<div class="product__cart-icon-heart">
							<i class="icon-heart-empty fa-solid fa-heart active-hearted"></i>
							<i class="icon-heart-full fa-solid fa-heart"></i>
						</div>
					</div>
					<div class="product__payment">THANH TOÁN</div>
					<div class="panel-group">
						<div class="panel-item ">
							<div class="panel-item__heading js-panel-item__heading-infor ">
								THÔNG TIN SẢN PHẨM <i
									class="product__size-icon fa-solid fa-chevron-down"></i>
							</div>
							<div class="product__divider "></div>
							<div class="panel-item__sub js-panel-item__sub ">
								<div class="panel-item__sub-text">Gender: Unisex</div>
								<div class="panel-item__sub-text">Size run: 35 – 46</div>
								<div class="panel-item__sub-text">Upper: Canvas</div>
								<div class="panel-item__sub-text">Outsole: Rubber</div>
								<div class="panel-item__sub-img"
									style="background-image: url(/assets/imgs/Ananas_SizeChart.jpg);"></div>
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
										<li class="panel-text">Ananas chỉ ưu tiên hỗ trợ đổi
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
						<div class="panel-item">
							<div class="panel-item__heading js-panel-guarantee">
								BẢO HÀNH THẾ NÀO <i
									class="product__size-icon fa-solid fa-chevron-down"></i>
							</div>
							<div class="product__divider"></div>
							<div class="panel-item__sub js-guarantee-sub">
								<h6>
									<p class="guarantee-text">Mỗi đôi giày Ananas trước khi
										xuất xưởng đều trải qua nhiều khâu kiểm tra. Tuy vậy, trong
										quá trình sử dụng, nếu nhận thấy các lỗi: gãy đế, hở đế, đứt
										chỉ may,...trong thời gian 6 tháng từ ngày mua hàng, mong bạn
										sớm gửi sản phẩm về Ananas nhằm giúp chúng tôi có cơ hội phục
										vụ bạn tốt hơn. Vui lòng gửi sản phẩm về bất kỳ cửa hàng
										Ananas nào, hoặc gửi đến trung tâm bảo hành Ananas ngay trong
										trung tâm TP.HCM trong giờ hành chính:</p>
									<p class="guarantee-text">
										Địa chỉ: 5C Tân Cảng, P.25, Q.Bình Thạnh , TP. Hồ Chí Minh. <br>
										Hotline: 028 2211 0067
									</p>
								</h6>
							</div>
						</div>
					</div>
				</form>
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
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>
								<div class="grid__column-3">
									<div class="product-item">
										<div class="product-item__img"
											style="background-image: url(./assets/imgs/lemia-00072a-8a2rOV_800x800.jpg);">
											<div class="product-item-action">
												<a href="" class="product-item-buy__btn-link">
													<div class="product-item-buy__btn">Mua ngay</div>
												</a>
												<div
													class="product-item-like__btn product-item-like__btn--liked">
													<i class="product-item-like-icon-fill fa-solid fa-heart"></i>
													<i class="product-item-like-icon-empty fa-regular fa-heart"></i>
												</div>
											</div>
										</div>
										<h4 class="product-item__name">Áo lenin họa tiết bắt mắt
											nhất đất nước Việt Nam và trên toàn thế giới kể cả ngoài hành
											tinh cũng không sao sánh bằng được</h4>
										<span class="product-item__price-old">255.000vnđ</span> <span
											class="product-item__price-current">100.000vnđ</span>
									</div>
								</div>



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
	    document.addEventListener("DOMContentLoaded", function() {
	        var imgsItems = document.querySelectorAll(".product-item-imgs-item");
	
	        imgsItems.forEach(function(item) {
	            item.addEventListener("click", function() {
	                var imageUrl = item.style.backgroundImage;
	                document.querySelector(".product-item-img").style.backgroundImage = imageUrl;
	            });
	        });
	    });
	</script>
</body>
</html>