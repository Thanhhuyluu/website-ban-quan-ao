<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-header">
	<div class="sidebar-toggler">
		<i class="fa fa-bars"></i>
	</div>
	<div class="header__right">
		<div class="header__right-notify">
			<a class="get-back-to-shop-link" href="trang-chu">Cửa hàng của tôi</a>
		</div>
		<div class="header__right-notify">
			<i class="notify-icon fa-solid fa-bell"></i>
		</div>
		
		<div class="header__right-user">
			<img
				src="<c:url value='/template/admin/assets/imgs/high-logo-D022924D47-seeklogo.com.png' />"
				alt="" class="header__right-user-img"> <span
				class="header__right-user-name">Chu shop high</span> <i
				class="icon-down fa-solid fa-angle-down"></i>
			<ul class="user-menu">
				<li class="user-item"><a href="#" class="user-item-link">Thông
						tin cá nhân</a></li>
				<li class="user-item"><a href="#" class="user-item-link">Cài
						đặt</a></li>
				<li class="user-item"><a href="thoat?action=logout"
					class="user-item-link">Đăng xuất</a></li>
			</ul>
		</div>
	</div>
</div>