<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
  <div class="table-classify">
      <div class="classify__header">
          <h6 class="classify__header-title">
              <c:out value="${product.title}" />
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
                    <th>Kích thước </th>
	                <th>Số lượng</th>
	                <th>Màu sắc </th>
                  </tr>
              </thead>
              <tbody>
                <c:forEach var="productDetail" items="${productDetails}">
                  <tr>
                  		<td><c:out value="${productDetail.id}" /></td>
                    	<td><c:out value="${productDetail.size}" /></td>
                  		<td><c:out value="${productDetail.quantity}" /> </td>
                        <td><c:out value="${productDetail.color}" /> </td>
                  </tr>
                </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
</div>   
