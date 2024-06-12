<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@include file="/common/taglib.jsp" %>

		<div class="container-body">
			<div class="table-classify">
				<div class="classify__header">
					<h6 class="classify__header-title">Danh sách sản phẩm</h6>
					 <form id="searchForm" action="admin-product" method="GET">
				        <div class="header__search">
				            <div class="header__search-icon">
				            	<i class="fa-solid fa-magnifying-glass"></i>
				            </div>
				            <input type="text" name="txtSearch" class="header__search-input"
				                placeholder="Tìm kiếm tên sản phẩm " value="${txtSearch}">
				        </div>
				    </form>
					<div class="btn-add">
						<a class="btn-add-link" href="admin-product-new"> <i class="fa-solid fa-plus"
								style="text-align: center;" title="Thêm sản phẩm mới "></i>
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

						<tbody id="product-list">
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
										<c:out value="${product.price}" /> VNĐ
									</td>
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
											<i class="fa-solid fa-pen-to-square" style="text-align: center;"
												title="Chỉnh sửa thông tin sản phẩm "></i>
										</a> <a class="active-link product-delete-btn" data-bs-id="${product.id}"> <i
												class="fa-solid fa-trash-can" style="text-align: center;"
												title="Xoá sản phẩm "></i>
										</a> <a class="active-link btn-detail-product" id="btn-detail-product"> <i
												class="fa-solid fa-magnifying-glass" style="text-align: center;"
												title="Xem chi tiết sản phẩm "></i>
										</a></td>
									<td id="modal_detail_product" class="modal modal-detail-product">
										<div class="modal__content" id="product-details">
											<div class="modal__header">
												<i class="fa-solid fa-xmark icon-close"></i>
												<div class="product-detail__img"
													style="background-image: url(<c:url value='/imgs/${product.img}'/>);">
												</div>
												<div class="product-detail__title">${product.title}</div>
												<div class="product-detail__category">
													Loại áo:
													<c:choose>
														<c:when test="${product.gender == 1}">Nam</c:when>
														<c:when test="${product.gender == 2}">Nữ</c:when>
														<c:otherwise>Cả nam và nữ</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="modal__main">
												<div class="select-size-color">
													<div class="select-item__size">
														<label class="select-size_title" for="select-size">Size:</label>
														<select name="select-size" id="select-size">
															<c:forEach var="size" items="${sizesOfProduct}">
																<option value="${size}">${size}</option>
															</c:forEach>
														</select>
													</div>
													<div class="select-item__color">
														<label for="colorPicker" class="color__title">Màu: </label>
														<div class="square colorPicker"></div>
														<div class="color-options colorOptions">
															<c:forEach var="color" items="${colorsOfProduct}">
																<div class="color-option"
																	style="background-color: #${color}"></div>
															</c:forEach>
														</div>
													</div>
												</div>
												<div class="detail-product-count-fav">
													<div class="detail-product-status-count">Trong kho còn
														${countProductDetail} sản phẩm</div>
													<div class="detail-product-status-fav">Luợt thích:
														${product.likes}</div>
												</div>
												<div class="detail-product-descript">
													<div class="descript-title">Mô tả</div>
													<div class="descript-content">${product.description}</div>
												</div>
											</div>
											<div class="modal__footer">
												<div class="modal__footer-btn-wrapper">
													<a href="/Online_Shop/admin-productDetail-add?id=<c:out value='${product.id}'/>"
														class="add-productDetail-btn">Thêm sản phẩm</a>
													<button class="close">Thoát</button>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<ul class="pagination home-product__pagination">
						<li class="pagination-item "><a
							href="admin-product?txtSearch=${txtSearch==null? "": txtSearch}&index=${tag == 1?tag : (tag-1)}" class="pagination-item__link"> <i
								class="pagination-item__icon fa-solid fa-chevron-left"></i>
						</a></li>
						<c:forEach begin="1" end="${endPage}" var="i">
							<li class="pagination-item ${tag == i?"pagination-item--active":""}">
		
								<a class="pagination-item__link" href="admin-product?txtSearch=${txtSearch==null? "": txtSearch}&index=${i}" >${i}</a>
							</li>
						</c:forEach>
						
						<li class="pagination-item"><a
							href="admin-product?txtSearch=${txtSearch==null? "": txtSearch}&index=${tag == endPage?tag : (tag+1)}" class="pagination-item__link"> <i
								class="pagination-item__icon fa-solid fa-chevron-right"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</div>

	
		<div class="modal-alert" id="product-main">
			<div class="modal-alert__inner">
				<div class="alert__header">
					<div class="alert__header-title">Cảnh báo</div>
					<i class="fa-solid fa-xmark icon-close" id="icon-close"></i>
				</div>
				<div class="alert__content">
					<p class="alert__content-text">Bạn có chắc chắn muốn xoá không ?</p>
				</div>
				<div class="alert__footer">
					<button class="alert__footer-btn " id="delete-item-modal">Có</button>
					<button class="alert__footer-btn " id="exit-delete-modal">Thoát</button>
				</div>
			</div>
		</div>

		<form name="form-product-delete" method="POST"></form>
		
<script src="<c:url value='/template/admin/assets/scripts/search.js' />"></script>
<script src="<c:url value='/template/admin/assets/scripts/productList.js' />"></script>	