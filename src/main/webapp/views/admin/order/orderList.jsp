<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
  <div class="table-classify">
      <div class="classify__header">
          <h6 class="classify__header-title">
              Orders
          </h6>
          <div class="btn-add">
              <a class="btn-add-link" href=" <c:url value = "admin-product-new"/>" >
                  <i class="fa-solid fa-plus"></i>
              </a>
          </div>
      </div>
      <div class="classify__body">
          <table class="table">
              <thead>
                  <tr>
                   	<th>ID</th>
                    <th>Tên người nhận </th>
	                <th>Số điện thoại </th>
	                <th>Địa chỉ </th>
	                <th>Ngày đặt hàng </th>
	                <th>Trạng thái đơn </th>
	                <th>Thao tác   </th>
                  </tr>
              </thead>
              <tbody>
                <c:forEach var="order" items="${orders}">
                  <tr>
                  		<td><c:out value="${order.id}" /></td>
                    	<td><c:out value="${order.fullname}" /></td>
                  		<td><c:out value="${order.phoneNum}" /> </td>
                  		<td><c:out value="${order.address}" /> </td>
	  					<td><c:out value="${order.orderDate}" /> </td>
	                  	 <c:choose>
                            <c:when test="${order.status == 0}">
                                <td>Đang chuẩn bị hàng </td>
                            </c:when>    
                            <c:when test="${order.status == 1}">
                                <td>Đang giao hàng </td>
                            </c:when>    
                            <c:when test="${order.status == 2}">
                                <td>Giao hàng thành công </td>
                            </c:when>    
                            <c:otherwise>
                                <td>Đã huỷ đơn hàng </td>
                            </c:otherwise>    
                        </c:choose>       
                         <td>
	                     <a class="active-link" href="/Online_Shop/admin-order-deleteSoft?id=<c:out value='${order.id}' />" >
	                            <i class="fa-regular fa-calendar-xmark"></i>
	                        </a>
	                          <a class="active-link" href="/Online_Shop/admin-orderDetail?id=<c:out value='${order.id}' />">
	                            <i class="fa-solid fa-magnifying-glass"></i>
	                        </a>
	                  </td>   	
                  </tr>
                </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
</div>   
