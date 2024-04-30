<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>	
    <meta charset="utf-8">
    <title><dec:title default="Trang chủ" /></title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="<c:url value='/template/admin/img/favicon.ico' />" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<c:url value='/template/admin/lib/owlcarousel/assets/owl.carousel.min.css' />" rel="stylesheet">
    <link href="<c:url value='/template/admin/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css' />" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<c:url value='/template/admin/css/bootstrap.min.css' />" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<c:url value='/template/admin/css/style.css' />" rel="stylesheet">
</head>

<body>
    <div class="container-fluid position-relative d-flex p-0">
        <!-- Spinner Start -->
        <%@include file="/common/admin/spinner.jsp" %>
        <!-- Spinner End -->
        <!-- Sidebar Start -->
        <%@include file="/common/admin/menu.jsp" %>
        <!-- Sidebar End -->
        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
          	<%@include file="/common/admin/header.jsp" %>
            <!-- Navbar End -->
           
           	<dec:body/>
          
            <!-- Footer Start -->
           	<%@include file="/common/admin/footer.jsp" %>
            <!-- Footer End -->
        </div>
        <!-- Content End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<c:url value='/template/admin/lib/chart/chart.min.js' />"></script>
    <script src="<c:url value='/template/admin/lib/easing/easing.min.js' />"></script>
    <script src="<c:url value='/template/admin/lib/waypoints/waypoints.min.js' />"></script>
 	<script src="<c:url value='/template/admin/lib/owlcarousel/owl.carousel.min.js' />"></script>
    <script src="<c:url value='/template/admin/lib/tempusdominus/js/moment.min.js' />"></script>
    <script src="<c:url value='/template/admin/lib/tempusdominus/js/moment-timezone.min.js' />"></script>
   	<script src="<c:url value='/template/admin/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js' />"></script>

    <!-- Template Javascript -->
    <script src="<c:url value='/template/admin/js/main.js' />"></script>
</body>

</html>