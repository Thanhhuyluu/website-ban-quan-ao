<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
  <div class="table-classify">
      <div class="classify__header">
          <h6 class="classify__header-title">
              Danh sách khách hàng
          </h6>
      </div>
      <div class="classify__body">
          <table class="table">
              <thead>
                  <tr>
                    <th>Họ và tên </th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Trạng thái</th>
                  </tr>
              </thead>
              <tbody>
                <c:forEach var="customer" items="${customers}">
                  <tr>
                    <td>${customer.fullname}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phoneNumber}</td>
                    <td>${customer.address}</td>
                    <td>
                   		<c:choose>
                            <c:when test="${customer.status == 0}">
                                 <a class="active-link" href="/Online_Shop/admin-customer-delete?id=<c:out value='${customer.id}' />" >
		                           	<i class="fa-solid fa-unlock"  style="text-align: center;" title="Tài khoản chưa bị khoá" ></i>
		                        </a>
                            </c:when>    
                            <c:otherwise>
                               <a class="active-link" href="/Online_Shop/admin-customer-delete?id=<c:out value='${customer.id}' />" >
		                           	<i class="fa-solid fa-lock"  style="text-align: center;" title="Tài khoản đã bị khoá" ></i>
		                        </a>
                            </c:otherwise>    
                        </c:choose>  
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
</div>   
