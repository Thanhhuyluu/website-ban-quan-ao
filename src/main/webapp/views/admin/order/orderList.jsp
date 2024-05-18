<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
	<div class="table-classify">
		<div class="classify__header">
			<h6 class="classify__header-title">Orders</h6>
		</div>
		<div class="classify__body">
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên người nhận</th>
						<th>Số điện thoại</th>
						<th>Địa chỉ</th>
						<th>Ngày đặt hàng</th>
						<th>Trạng thái đơn</th>
						<th>Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lOrderItem" items="${lOrderItems}">
						<c:set var="order" value="${lOrderItem.order}" />
						<c:set var="orderDetails" value="${lOrderItem.orderDetails}" />
						<c:set var="priceOrder" value="${lOrderItem.priceOrder}" />
						<c:set var="discountOrders" value="${lOrderItem.discountOrders}" />
						<tr>
							<td><c:out value="${order.id}" /></td>
							<td><c:out value="${order.fullname}" /></td>
							<td><c:out value="${order.phoneNum}" /></td>
							<td><c:out value="${order.address}" /></td>
							<td><c:out value="${order.orderDate}" /></td>
							<c:choose>
								<c:when test="${order.status == 0}">
									<td>Đang chuẩn bị hàng</td>
								</c:when>
								<c:when test="${order.status == 1}">
									<td>Đang giao hàng</td>
								</c:when>
								<c:when test="${order.status == 2}">
									<td>Giao hàng thành công</td>
								</c:when>
								<c:otherwise>
									<td>Đã huỷ đơn hàng</td>
								</c:otherwise>
							</c:choose>
							<td><a class="active-link"
								href="/Online_Shop/admin-order-deleteSoft?id=<c:out value='${order.id}' />">
									<i class="fa-regular fa-calendar-xmark"></i>
							</a> <a class="active-link btn-show-modal-order-detail"> <i
									class="fa-solid fa-magnifying-glass"></i>
							</a></td>
							<td class="modal modal-detail-order"
								id="modal_detail_order_<c:out value='${order.id}' />">
								<div class="modal__content modal-order-detail">
									<div class="modal__header">
										<div class="modal__header-icon-close">
											<i class="fa-solid fa-xmark"></i>
										</div>
										<h1 class="modal__header-title">ĐƠN HÀNG</h1>
										<div class="modal__header-inf">
											<div class="modal__header-left">
												<div class="modal__header-name-cus">${order.fullname}</div>
												<div class="modal__header-address">${order.address}</div>
												<div class="modal__header-phoneNum">${order.phoneNum}</div>
											</div>
											<div class="modal__header-right">
												<div class="modal__header-order-id">Số 1000${order.id}</div>
												<div class="modal__header-day-create">Ngày ${order.orderDate}</div>
											</div>
										</div>
									</div>
									<div class="modal__main">
										<table class="table-order-detail">
											<thead>
												<tr class="row-header">
													<th class="table-order-detail-name-product">Tên sản
														phẩm</th>
													<th>Số lượng</th>
													<th>Giảm giá</th>
													<th>Đơn giá</th>
													<th>Thành tiền</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="orderDetail" items="${orderDetails}" >
												<tr>
													<td>${orderDetail.productDetail.product.title}</td>
													<td class="td-count">${orderDetail.quantity}</td>
													<td>${orderDetail.productDetail.product.discount}%</td>
													<td>${orderDetail.productDetail.product.price} VNĐ </td>
													<td>${orderDetail.price} VNĐ </td>
												</tr>
											</c:forEach>	
											</tbody>
											<tfoot>
												<tr>
													<td colspan="4" class="label">Tổng cộng:</td>
													<td class="value">${priceOrder} VNĐ </td>
												</tr>
											</tfoot>
										</table>
									</div>
									<div class="modal__footer">
										<div class="order-detail-status">Trạng thái đơn hàng: 
							<c:choose>
								<c:when test="${order.status == 0}">
									Đang chuẩn bị hàng
								</c:when>
								<c:when test="${order.status == 1}">
									Đang giao hàng
								</c:when>
								<c:when test="${order.status == 2}">
									Giao hàng thành công
								</c:when>
								<c:otherwise>
									Đã huỷ đơn hàng
								</c:otherwise>
							</c:choose></div>
										<button class="order-detail-btn">Thoát</button>
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



<script
	src="<c:url value='/template/admin/assets/scripts/orderDetail.js' />"></script>
