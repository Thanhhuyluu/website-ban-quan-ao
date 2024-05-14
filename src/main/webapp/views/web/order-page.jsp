<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<div class="app">
		<c:set var="userOrderList" value="${requestScope.userOrderList }" />
		<div class="app__container">
			<div class="grid">
				<div class="grid__row">
				
					<div class="order__main-container">
					<c:if test="${userOrderList.size()>0 }">
						<h1 class="order__main-heading">ĐƠN HÀNG CỦA BẠN</h1>
						<ul class="order-list">
							<c:forEach items="${userOrderList }" var="order">
								<li class="order-item">
									<div class="order-item__des">
										<div class="order-item__date">
											Đã đặt hàng ngày:
											<p class="order-item__date-text">${order.getOrderDate() }</p>
										</div>
										<div class="order-item__infor">
											<p class="order-item__address">Địa chỉ:
												${order.getAddress() }</p>
											<p class="order-item__phonenumber">SĐT:
												${order.getPhoneNum() }</p>
										</div>
									</div>
									<div class="order-item__button-wrapper">
									
										<c:if test="${order.getStatus() == 0 }">
										<a onclick="handleOrderCancleClick(${order.getId()})" class="order-item__button order-item__button-cancel">Hủy đơn hàng</a>
										</c:if>
										
										 <a href="chi-tiet-don-hang?orderId=${order.getId() }" class="order-item__button">Xem chi tiết</a>
									</div>
									<h2 class="order-item__status">
										<c:if test="${order.getStatus() == 0 }">Chờ xác nhận</c:if>
										<c:if test="${order.getStatus() == 1 }">Đang vận chuyển</c:if>
										<c:if test="${order.getStatus() == 2 }">Đơn hàng đã được giao</c:if>
										<c:if test="${order.getStatus() == 3 }">Đã hủy</c:if>
										
									
									</h2>
								</li>
								
							</c:forEach>
							
							<ul class="pagination order-page__pagination">

								<c:set var="page" value="${requestScope.page }" />


								<li class="pagination-item ">
									<a
									href="don-hang?action=xem-tat-ca&page=${ (page == 1 ? 1 : (page - 1)) }
									" class="order-page__pagination-item__link"> <i
										class="pagination-item__icon fa-solid fa-chevron-left"></i>
									</a>
								</li>

								 <c:forEach begin="${1}" end="${requestScope.pageNum }" var="i"> 
										<li class="pagination-item ${i == page ? "pagination-item--active" : "" }">
		
											<a href="don-hang?action=xem-tat-ca&page=${ i}
											" class="order-page__pagination-item__link ">${i }</a>
										</li>

								 </c:forEach> 


								<li class="pagination-item"><a
									href="don-hang?action=xem-tat-ca&page=${ (page == pageNum ? pageNum : (page + 1)) }
									" class="order-page__pagination-item__link"> <i
										class="pagination-item__icon fa-solid fa-chevron-right"></i>
								</a></li>
							</ul>
						</ul>
						</c:if>
						<c:if test="${userOrderList.size()<=0}">
								<h1 class="order-page-heading">
									Đơn hàng của bạn
								</h1>
								<h4 class="empty-order-page-message">Bạn đang không có sản phẩm nào trong giỏ hàng!</h4>
								<div class="order-page-nav-link">
									<a href="trang-chu" class="return-to-shop-link">TIẾP TỤC MUA HÀNG</a>
								</div>
						</c:if>
					</div>
				
				
					
				</div>
			</div>
		</div>
	</div>
</body>

</html>