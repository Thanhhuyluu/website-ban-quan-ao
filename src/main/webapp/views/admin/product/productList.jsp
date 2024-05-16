<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
	<div class="table-classify">
		<div class="classify__header">
			<h6 class="classify__header-title">Products</h6>
			<div class="btn-add">
				<a class="btn-add-link" href=" <c:url value = " admin-product-new" />"> <i class="fa-solid fa-plus"></i>
				</a>
			</div>
		</div>
		<div class="classify__body">
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Price</th>
						<th>Image</th>
						<th>Gender</th>
						<th>Created At</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lProductItem" items="${lProductItems}">
						<c:set var="product" value="${lProductItem.product}" />
						<c:set var="productDetails" value="${lProductItem.productDetails}" />
						<c:set var="images" value="${lProductItem.images}" />
						<c:set var="colorsOfProduct" value="${lProductItem.colorsOfProduct}" />
						<c:set var="sizesOfProduct" value="${lProductItem.sizesOfProduct}" />
						<c:set var="countProductDetail" value="${lProductItem.countProductDetail}" />
						<tr class="product-item__row">
							<td>
								<c:out value="${product.id}" />
							</td>
							<td>
								<c:out value="${product.title}" />
							</td>
							<td>
								<c:out value="${product.price}" /> VNĐ</td>
							<td><img style="height: 30px; width: 30px;"
									src="<c:url value='/template/web/assets/imgs/${product.img}'/>" alt=""></td>
							<c:choose>
								<c:when test="${product.gender == 1}">
									<td>Nam</td>
								</c:when>
								<c:otherwise>
									<td>Nữ</td>
								</c:otherwise>
							</c:choose>
							<td>
								<c:out value="${product.createdAt}" />
							</td>
							<td><a class="active-link"
									href="/Online_Shop/admin-product-edit?id=<c:out value='${product.id}' />">
									<i class="fa-solid fa-pen-to-square"></i>
								</a> <a class="active-link"
									href="/Online_Shop/admin-product-delete?id=<c:out value='${product.id}' />">
									<i class="fa-solid fa-trash-can"></i>
								</a> <a class="active-link btn-detail-product" id="btn-detail-product"> <i
										class="fa-solid fa-magnifying-glass"></i>
								</a></td>

							<td id="modal_detail_product" class="modal modal-detail-product">
								<div class="modal__content" id="product-details">
									<div class="modal__header">
										<i class="fa-solid fa-xmark icon-close"></i>
										<div class="product-detail__img" style="background-image: url(<c:url value='/template/web/assets/imgs/${product.img}'/>);" > </div>
										<div class="product-detail__title">${product.title}</div>
									</div>
									<div class="modal__main">
										<div class="select-size-color">
											<div class="select-item__size">
												<label class="select-size_title" for="select-size">Size:
												</label> 
												<select name="select-size" id="select-size">
												<c:forEach var="size" items="${sizesOfProduct}">
													<option value="${size}">${size}</option>
												</c:forEach>
												</select>
											</div>
											<div class="select-item__color">
												<label for="colorPicker" class="color__title">Màu: </label>
												<div id="colorPicker" class="square" onclick="toggleColorOptions()">
												</div>
												<div id="colorOptions" class="color-options">
													<c:forEach var="color" items="${colorsOfProduct}">
													<div class="color-option" onclick="changeColor('red')"
														style="background-color: #${color};"></div>
													</c:forEach>
												</div>
											</div>
										</div>

									</div>
									<div class="modal__footer">
										<div class="product-detail__count">Trong kho còn ${countProductDetail} sản
											phẩm</div>
										<button class="close">Thoát</button>
									</div>
								</div>

							</td>

						</tr>

					</c:forEach>
					
				</tbody>
				
			</table>
			<%--  <ul class="pagination order-page__pagination">
				<c:set var="page" value="${requestScope.page }" />
				<li class="pagination-item ">
					<a href="don-hang?action=xem-tat-ca&page=${ (page == 1 ? 1 : (page - 1)) }
							" class="order-page__pagination-item__link"> <i class="pagination-item__icon fa-solid fa-chevron-left"></i>
					</a>
				</li>
				<c:forEach begin="${1}" end="${requestScope.pageNum }" var="i">
					<li class="pagination-item ${i == page ? " pagination-item--active" : "" }">
						<a href="don-hang?action=xem-tat-ca&page=${ i}
									" class="order-page__pagination-item__link ">${i }</a>
					</li>
				</c:forEach>
				<li class="pagination-item"><a href="don-hang?action=xem-tat-ca&page=${ (page == pageNum ? pageNum : (page + 1)) }
							" class="order-page__pagination-item__link"> <i class="pagination-item__icon fa-solid fa-chevron-right"></i>
					</a>
				</li>
			</ul>  --%>
		</div>
	</div>

</div>

<script>
	//  ĐỔ SIZE RA
	function selectSize(size) {
		var button = document.querySelector(".select-size__title");
		button.textContent = size;
		toggleSizeDropdown();
	}

	function toggleColorOptions() {
		var colorOptions = document.getElementById("colorOptions");
		if (colorOptions.style.display === "none") {
			colorOptions.style.display = "block";
		} else {
			colorOptions.style.display = "none";
		}
	}
	//          SET MAU BAN DAU
	window.onload = function () {
		var firstColorOption = document.querySelector(".color-option"); // Lấy color-option đầu tiên
		var firstColor = window.getComputedStyle(firstColorOption).getPropertyValue(
			"background-color"); // Lấy màu nền của color-option đầu tiên
		changeColor(firstColor); // Áp dụng màu của color-option đầu tiên vào ô màu chính
	};

	function changeColor(color) {
		var colorPicker = document.getElementById("colorPicker");
		colorPicker.style.backgroundColor = color;
		var colorOptions = document.getElementById("colorOptions");
		colorOptions.style.display = "none";
	}



	const btn_show_detail = document.querySelectorAll('.btn-detail-product');



	btn_show_detail.forEach(button => {
		button.addEventListener('click', function () {
			const tableCell = button.parentElement;
			const tableRow = button.parentElement.parentElement;
			const modal_product_detail = tableRow.querySelector('td#modal_detail_product.modal.modal-detail-product');
			// const modal_product_detail = document.getElementById('product-details');
			// console.log(modal_product_detail);		
			modal_product_detail.classList.toggle('modal--active')
			const icon_close = modal_product_detail.querySelector('.icon-close');
			const btn_close = modal_product_detail.querySelector('.close');
			icon_close.addEventListener('click', () => {
				modal_product_detail.classList.remove("modal--active");
				// console.log('Turn off');	
			})
			btn_close.addEventListener('click', () => {	
				modal_product_detail.classList.remove("modal--active");
				// modal_product_detail.classList.toggle('modal--active')
				// console.log('Turn off');
			})

			
		});
	});
	



	// MODAL
	// const btnModals = document.querySelectorAll("[data-trigger = 'modal']")

	// function onActiveOrNot(btnTargetModal, modal, open) {
	// 	if (!modal.classList.contains("modal--active") && open) {
	// 		modal.classList.add("modal--active")
	// 	} else {
	// 		modal.classList.remove("modal--active");
	// 	}
	// 	btnTargetModal.setAttribute("aria-open", open);
	// }

	// btnModals.forEach(btnModal => {
	// 	// lấy ra thuộc tính modal 

	// 	const modal = document.querySelector(btnModal.getAttribute("data-target")); 
	// 	//const modal = btnModal.closest("modal");
	// 	// lấy trạng thái của modal
	// 	const open = btnModal.getAttribute("aria-open") === "true" ? true : false;
	// 	//set cho aria-opne laij trang thai ban day
	// 	onActiveOrNot(btnModal, modal, open);

	// 	btnModal.addEventListener('click', () => {
	// 		onActiveOrNot(btnModal, modal, !open);
	// 	});

	// 	// const formReloadProductList = document.forms['form-reload-productList'];
	// 	// Tắt modal thong qua nut close
	// 	document.querySelector('.modal__footer .close').addEventListener('click', () => {
	// 		onActiveOrNot(btnModal, modal, open);
	// 		//formReloadProductList.action = 'admin-product';
	// 		// formReloadProductList.submit();
	// 	});
	// 	document.querySelector('.modal__header .icon-close').addEventListener('click', () => {
	// 		onActiveOrNot(btnModal, modal, open);
	// 		//formReloadProductList.action = 'admin-product';
	// 		//formReloadProductList.submit();
	// 	});

	// });
</script>