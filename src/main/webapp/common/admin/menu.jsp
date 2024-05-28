	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
	<!-- Sidebar Start -->
		
	  <div class="side-bar">
            <div class="side-bar__header">
                <a href="<c:url value = "admin-home"/>">
                <img class="side-bar__header-img" src="<c:url value='/template/admin/assets/imgs/high-logo-D022924D47-seeklogo.com.png' />" alt="">
                </a> 
            </div>
			<c:set var="url" value="${pageContext.request.requestURL}" />
			<c:set var="desiredPart" value="${fn:substringAfter(url, '/Online_Shop/')}" />
            <ul class="side-bar__list-item">
                <li class="side-bar__item <c:if test="${desiredPart == 'admin-home' }">
  					  active
				</c:if> ">
                    <a href="<c:url value = "admin-home"/>" class="side-bar__item-link"> 
                        <img src="<c:url value='/template/admin/assets/imgs/home.png' />" alt="">
                        <span class="item-title">Thống kê</span>
                    </a>
                </li>
                <li class="side-bar__item <c:if test="${desiredPart == 'admin-order' }">
  					  active
				</c:if>" >
                    <a href="<c:url value = "admin-order"/>" class="side-bar__item-link"> 
                        <img src="<c:url value='/template/admin/assets/imgs/invoice.png' />" alt="">
                        <span class="item-title">Đơn hàng</span>
                    </a>
                </li>
                <li class="side-bar__item <c:if test="${desiredPart == 'admin-customer' }">
  					  active
				</c:if> " >
                    <a href="<c:url value = "admin-customer"/>" class="side-bar__item-link"> 
                        <img src="<c:url value='/template/admin/assets/imgs/cus.png' />" alt="">
                        <span class="item-title">Khách hàng</span>
                    </a>
                </li>
                <li class="side-bar__item <c:if test="${desiredPart == 'admin-product' }">
  					  active
				</c:if>">
                    <a href="<c:url value = "admin-product"/>" class="side-bar__item-link"> 
                        <img src="<c:url value='/template/admin/assets/imgs/product.png' />" alt="">
                        <span class="item-title">Sản phẩm</span>
                    </a>
                </li>
                <!-- <c:url value = "/admin-category"/> -->
                <li class="side-bar__item  <c:if test="${desiredPart == 'admin-category' }">
  					  active
				</c:if>" >
                    <a href="<c:url value = "admin-category"/>" class="side-bar__item-link"> 
                        <img src="<c:url value='/template/admin/assets/imgs/classify.png' />" alt="">
                        <span class="item-title">Phân loại</span>
                    </a>
                </li>
                <li class="side-bar__item <c:if test="${desiredPart == 'admin-staff' }">
  					  active
				</c:if> " >
                    <a href="<c:url value = "admin-staff"/>" class="side-bar__item-link"> 
                        <img src="<c:url value='/template/admin/assets/imgs/staff.png' />" alt="">
                        <span class="item-title">Nhân viên</span>
                    </a>
                </li>
                <li class="side-bar__item <c:if test="${desiredPart == 'admin-refund-request' }">
  					  active
				</c:if> ">
                    <a href="<c:url value = "admin-refund-request-list"/>" class="side-bar__item-link"> 
                        <i class="fa-solid fa-code-pull-request" style="font-size: 1.6rem; margin-right: 12px; margin-left: 3px;"></i>
                        <span class="item-title">Request</span>
                        <span class="side-bar__item-link-request-count">20</span>
                    </a>
                </li>
            </ul>
        </div>
        
        
	<!-- Sidebar End -->