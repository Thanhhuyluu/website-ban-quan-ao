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
						<th>Ngày yêu cầu</th>
						<th>Tên khách hàng</th>
						<th>Đơn hàng</th>
						<th>Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lProductItem" items="${lProductItems}">
						
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
										<div class="product-detail__category">Loại áo: Nam</div>
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
													<div class="color-option" onclick="changeColor('#${color}')"
														style="background-color: #${color};"></div>
													</c:forEach>
												</div>
											</div>
										</div>
										<div class="detail-product-count-fav">
											<div class="detail-product-status-count">Trong kho còn ${countProductDetail} sản
												phẩm</div>
											<div class="detail-product-status-fav">Luợt thích: 17</div>
										</div>
										<div class="detail-product-descript">
											<div class="descript-title">Mô tả</div>
											<div class="descript-content">Áo này thuộc cho dòng thời trang cao cấp, hạng sang mà sang đàng</div>
										</div>

									</div>
									<div class="modal__footer">
										<div class="modal__footer-btn-wrapper">
											<button class="add-productDetail-btn">Thêm sản phẩm </button>
											<button class="close">Thoát</button>
										</div>
										
									</div>
								</div>

							</td>

						</tr>

					</c:forEach>
					
				</tbody>
				
			</table>
			
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
    
<!-- <form name="form-product-delete" method="POST"></form> -->
 


<script>

</script>

<script>
	
	
</script>