<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
  <div class="table-classify">
      <div class="classify__header">
          <h6 class="classify__header-title">
              Products
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
                    <th>Title</th>
	                <th>Price</th>
	                <th>Image</th>
	                <th>Gender</th>
	                <th>Created At</th>
	                <th>Actions</th>
                  </tr>
              </thead>
              <tbody>
                <c:forEach var="product" items="${products}">
                  <tr>
                  		<td><c:out value="${product.id}" /></td>
                    	<td><c:out value="${product.title}" /></td>
                  		<td><c:out value="${product.price}" /> VNĐ</td>
	                  	<td><img style="height: 30px; width: 30px;" src="<c:url value='/template/web/assets/imgs/${product.img}'/>" alt=""></td>	
	                  	 <c:choose>
                            <c:when test="${product.gender == 1}">
                                <td>Nam</td>
                            </c:when>    
                            <c:otherwise>
                                <td>Nữ</td>
                            </c:otherwise>    
                        </c:choose> 		
	                  	<td><c:out value="${product.createdAt}" /></td>
	                  <td>
	                     <a class="active-link" href="/Online_Shop/admin-product-edit?id=<c:out value='${product.id}' />" >
	                            <i class="fa-solid fa-pen-to-square"></i>
	                        </a>
	                        <a class="active-link" href="/Online_Shop/admin-product-delete?id=<c:out value='${product.id}' />">
	                            <i class="fa-solid fa-trash-can"></i> 
	                        </a>
	                  </td> 
                  </tr>
                </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
</div>   
