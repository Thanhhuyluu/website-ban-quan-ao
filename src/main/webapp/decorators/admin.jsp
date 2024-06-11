<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><dec:title default="Admin" /></title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/template/web/assets/imgs/high-logo.ico"/>">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/base.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/main.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/modalDelete.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/detailProduct.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/category.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/formadditem.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/orderDetail.css' />">
	<link rel="stylesheet" href="<c:url value='/template/admin/assets/css/addNewProduct.css' />">
    <link rel="stylesheet" href="<c:url value="/template/web/assets/fonts/fontawesome-free-6.5.2-web/css/all.min.css"/>" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" rel="stylesheet">
    
    <!-- icon -->
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <!-- chart -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
 	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   
</head>
<body>
    <div class="app">
   		<!-- menu -->
      	  <%@include file="/common/admin/menu.jsp" %>
        <div class="container">
        
        	<!-- header -->
          	<%@include file="/common/admin/header.jsp" %>
           <!-- main -->
       		<dec:body/>
            <!-- footer -->
           <%@include file="/common/admin/footer.jsp" %>  
        </div>
    </div>
	
    <script src="<c:url value='/template/admin/assets/scripts/index.js' />"></script>
    <script src="<c:url value='/template/admin/assets/scripts/validateForm.js' />"></script>
    	
    
   <%--  <script src="<c:url value='/template/admin/assets/scripts/chart.js' />"></script>  --%>
 
    
</body>
</html>