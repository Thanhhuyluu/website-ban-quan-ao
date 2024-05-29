<%@page import="Config.VNPayConfig"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        
    </head>
    <body>
        <div class="app">
        	<div class="app__container">
        		 <div class="grid">
                <div class="vnpay-return__container" style="width: 100%;display: flex; justify-content: center;align-items: center; flex-direction: column;">
                    <h1 class="infor-message-tag">
                        THÔNG TIN GIAO DỊCH
                    </h1>
                    <c:set var="paymentResult" value="${requestScope.paymentResult }"/>
                     <c:set var="transactionNo" value="${requestScope.transactionNo }"/>
                    	<h4 class="infor-message-text">${paymentResult }</h4>
                    	<h4 class="infor-message-text">Mã giao dịch: ${transactionNo }</h4>
                   
                    <div class="return-to-shop-link-wrapper">
                        <a href="trang-chu" class="return-to-shop-link">TIẾP TỤC MUA HÀNG</a>
                    </div>
                    
                </div>
            </div>
        	</div>
        </div>
    </body>
</html>
