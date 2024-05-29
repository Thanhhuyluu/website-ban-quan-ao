<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
	<div class="table-classify">
		<div class="classify__header">
			<h6 class="classify__header-title">Danh sách sản phẩm </h6>
			<div class="btn-add">
				<a class="btn-add-link" href=" <c:url value = " admin-product-new" />"> <i class="fa-solid fa-plus"></i>
				</a>
			</div>
		</div>
		<div class="classify__body">
			<table class="table">
				<thead>
					<tr>
						<th>Tên</th>
						<th>Giá</th>
						<th>Ảnh</th>
						<th>Giới tính</th>
						<th>Ngày tạo</th>
						<th>Thao tác</th>
						<th></th>
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
								<c:out value="${product.title}" />
							</td>
							<td>
								<c:out value="${product.price}" /> VNĐ</td>
							<td><img style="height: 50px; width: 50px;"
									src="<c:url value='/imgs/${product.img}'/>" alt=""></td>
							<c:choose>
								<c:when test="${product.gender == 1}">
									<td>Nam</td>
								</c:when>
								<c:when test="${product.gender == 2}">
									<td>Nữ</td>
								</c:when>
								<c:otherwise>
									<td>Cả nam và nữ</td>
								</c:otherwise>
							</c:choose>
							<td>
								<c:out value="${product.createdAt}" />
							</td>
							<td><a class="active-link"
									href="/Online_Shop/admin-product-edit?id=<c:out value='${product.id}' />">
									<i class="fa-solid fa-pen-to-square"></i>
								</a> <a class="active-link product-delete-btn" data-bs-id="${product.id}">
									<i class="fa-solid fa-trash-can"></i>
								</a> <a class="active-link btn-detail-product" id="btn-detail-product"> <i
										class="fa-solid fa-magnifying-glass"></i>
								</a></td>

							<td id="modal_detail_product" class="modal modal-detail-product">
								<div class="modal__content" id="product-details">
									<div class="modal__header">
										<i class="fa-solid fa-xmark icon-close"></i>
										<div class="product-detail__img" style="background-image: url(<c:url value='/imgs/${product.img}'/>);" > </div>
										<div class="product-detail__title">${product.title}</div>
										<div class="product-detail__category">Loại áo: 
										<c:choose>
											<c:when test="${product.gender == 1}">
												Nam
											</c:when>
											<c:when test="${product.gender == 2}">
												Nữ
											</c:when>
											<c:otherwise>
												Cả nam và nữ
											</c:otherwise>
										</c:choose>
										</div>
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
												
												<div class="square colorPicker" >
												</div>
												
												<div class="color-options colorOptions" >
												<c:forEach var="color" items="${colorsOfProduct}">
													<div class="color-option" style="background-color: #${color};"></div>
												</c:forEach>
												</div>
											</div>
											
										</div>
										<div class="detail-product-count-fav">
											<div class="detail-product-status-count">Trong kho còn ${countProductDetail} sản
												phẩm</div>
											<div class="detail-product-status-fav">Luợt thích: ${product.likes}</div>
										</div>
										<div class="detail-product-descript">
											<div class="descript-title">Mô tả</div>
											<div class="descript-content">${product.description}</div>
										</div>

									</div>
									<div class="modal__footer">
										<div class="modal__footer-btn-wrapper">
										 	 <a href="/Online_Shop/admin-productDetail-add?id=<c:out value='${product.id}'/>" class="add-productDetail-btn" >Thêm sản phẩm </a>  
											<button class="close">Thoát</button>
										</div>
										
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
<div class="modal-alert">
    <div class="modal-alert__inner">
        <div class="alert__header">
            <div class="alert__header-title">Cảnh báo</div>
            <i class="fa-solid fa-xmark icon-close" id="icon-close"></i>
        </div>
        <div class="alert__content">
            <p class="alert__content-text">Bạn có chắc chắn muốn xoá không ?</p>
        </div>
        <div class="alert__footer">
           	<button class="alert__footer-btn " id="delete-item-modal" >Có</button>
            <button class="alert__footer-btn " id="exit-delete-modal" >Thoát</button>
        </div>
    </div>
</div>
    
<form name="form-product-delete" method="POST"></form>
 


<script>
	// Xử lí modal-delete
   const open_modal = document.getElementById('open-modal-btn')
   const modal_alert = document.querySelector('.modal-alert')
   const icon_close = document.getElementById('icon-close')
   const btnDeleteProducts = document.querySelectorAll('.product-delete-btn')
   // const alert_footer_btn = document.querySelectorAll('.alert__footer-btn')
   const modal_alert_inner = document.querySelector('.modal-alert__inner')
   const btnDeleteItem = document.getElementById('delete-item-modal');
   const btnExitDelete = document.getElementById('exit-delete-modal');

	// Xử lí xoá
	var productId;
	const formDeleteProduct = document.forms['form-product-delete'];

	/* open_modal.onclick = function () {
       
   } */
	
   
   function show(){
       modal_alert.classList.toggle('show')
   }
  /*  icon_close.addEventListener('click',show) */

    btnDeleteProducts.forEach(element => {
        element.addEventListener('click',() => {
        show();
        productId = element.getAttribute("data-bs-id");
        console.log(productId);
        });
    });
   
   // btn_close.addEventListener('click', show)
   // alert_footer_btn.forEach(element => {
   //     element.addEventListener('click', show)
   // });
   btnDeleteItem.addEventListener('click', ()=>{
	   formDeleteProduct.action = '/Online_Shop/admin-product-delete?id=' + productId ;
	   formDeleteProduct.submit();
       console.log('xoa');
   });
   btnExitDelete.addEventListener('click', ()=>{
       show();
       console.log('exit');
   });
   icon_close.addEventListener('click', ()=>{
       show();
       console.log('exit');
   });
</script>

<script>
	//  ĐỔ SIZE RA
	function selectSize(size) {
		var button = document.querySelector(".select-size__title");
		button.textContent = size;
		toggleSizeDropdown();
	}
	function rgbToHex(rgb) {
		// Chia chuỗi RGB thành mảng các số
		var rgbArray = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
		// Chuyển đổi các số thành hex
		function hex(x) {
			return ("0" + parseInt(x).toString(16)).slice(-2);
		}
		// Trả về mã hex
		return "#" + hex(rgbArray[1]) + hex(rgbArray[2]) + hex(rgbArray[3]);
	}
 	/* function toggleColorOptions() {
		 const colorOptions = document.querySelector(".colorOptions");
		 if (colorOptions.style.display === "none") {
			colorOptions.style.display = "block";
		} else {
			colorOptions.style.display = "none";
		} 
	}   */
	/* function changeColor(color) {
		
		
	}   */
	const btn_show_detail = document.querySelectorAll('.btn-detail-product');
	btn_show_detail.forEach(button => {
		button.addEventListener('click', function () {
			const tableCell = button.parentElement;
			const tableRow = button.parentElement.parentElement;
			const modal_product_detail = tableRow.querySelector('td#modal_detail_product.modal.modal-detail-product');
			// const modal_product_detail = document.getElementById('product-details');
			// console.log(modal_product_detail);		
			modal_product_detail.classList.toggle('show')
			const icon_close = modal_product_detail.querySelector('.icon-close');
			const btn_close = modal_product_detail.querySelector('.close');
			icon_close.addEventListener('click', () => {
				modal_product_detail.classList.remove("show");
				console.log('Turn off');	
			})
			btn_close.addEventListener('click', () => {	
				modal_product_detail.classList.remove("show");
				// modal_product_detail.classList.toggle('modal--active')
				console.log('Turn off'); 
			})
			
			const colorPicker = modal_product_detail.querySelector(".colorPicker");
			const colorOption = modal_product_detail.querySelector('.colorOptions');
			
			 
			
			const firstColorOption = modal_product_detail.querySelector('.color-option'); // Lấy color-option đầu tiên
			const firstColor = window.getComputedStyle(firstColorOption).getPropertyValue("background-color"); // Lấy màu nền của color-option đầu tiên
			console.log(rgbToHex(firstColor));
			/* changeColor(rgbToHex(firstColor)); // Áp dụng màu của color-option đầu tiên vào ô màu chính */
			
			colorPicker.style.backgroundColor = rgbToHex(firstColor);
			
			const colorOptions = modal_product_detail.querySelectorAll(".color-option");
			colorOptions.forEach(changeColor => {
				
				changeColor.addEventListener('click', function () {
					console.log("ok")
					colorPicker.style.backgroundColor = window.getComputedStyle(changeColor).getPropertyValue("background-color");
					colorOption.style.display = "none";
				})
			})
			
			colorPicker.addEventListener('click', () => {
				console.log("ok2")
				if (colorOption.style.display === "none" || colorOption.style.display === "") {
					colorOption.style.display = "block";
				} else {
					colorOption.style.display = "none";
				} 
			}) 
				
			/* const colorOptions = modal_product_detail.querySelector(".colorOptions"); */
			/* colorOptions.style.display = "none"; */
			/* changeColor(rgbToHex(firstColor)); */
			
			
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