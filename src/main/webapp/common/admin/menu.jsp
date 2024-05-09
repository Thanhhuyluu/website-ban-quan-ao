	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<!-- Sidebar Start -->
	<div class="sidebar pe-4 pb-3">
	  <nav class="navbar bg-secondary navbar-dark">
	    <a href="index.html" class="navbar-brand mx-4 mb-3">
	      <h3 class="text-primary">
	        <i class="fa fa-user-edit me-2"></i>HIGH
	      </h3>
	    </a>
	    <div class="d-flex align-items-center ms-4 mb-4"></div>
	    <div class="navbar-nav w-100">
	      <a href="index.html" class="nav-item nav-link active">
	        <i class="fa fa-tachometer-alt me-2"></i>Thống kê </a>
	      <div class="nav-item dropdown">
	        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
	        	<i class="fa fa-laptop me-2"></i>Đơn hàng </a>
	        <div class="dropdown-menu bg-transparent border-0">
	          <a href="button.html" class="dropdown-item">Buttons</a>
	          <a href="typography.html" class="dropdown-item">Typography</a>
	          <a href="element.html" class="dropdown-item">Other Elements</a>
	        </div>
	        <a href="<c:url value='/admin-customer'/>" class="nav-item nav-link">
	        	<i class="fa fa-th me-2"></i>Khách hàng</a>
        	<a href="<c:url value='/admin-product'/>" class="nav-item nav-link">
	        	<i class="fa fa-th me-2"></i>Sản phẩm</a>
        	<a href="<c:url value='/admin-category'/>" class="nav-item nav-link">
	        	<i class="fa fa-th me-2"></i>Phân loại
	        	</a>
        	<a href="<c:url value='/admin-staff'/>" class="nav-item nav-link">
	        	<i class="fa fa-th me-2"></i>Nhân viên</a>
	      </div>
	    </div>
	  </nav>
	</div>
	<!-- Sidebar End -->