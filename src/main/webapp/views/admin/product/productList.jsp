<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@include file="/common/taglib.jsp"%>
    <div class="container-fluid pt-4 px-4">
      <div class="bg-secondary text-center rounded p-4">
        <div class="d-flex align-items-center justify-content-between mb-4">
          <h6 class="mb-0">Product</h6>
          <a class="btn btn-sm btn-primary" href="/Online_Shop/admin-product-new">Add</a>
        </div>
        <div class="table-responsive">
          <table class="table text-start align-middle table-bordered table-hover mb-0">
            <thead>
              <tr class="text-white">
                <th scope="col">Title</th>
                <th scope="col">Price</th>
                <th scope="col">Image</th>
                <th scope="col">Decription</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="product" items="${products}">
                <tr>
                  <td><c:out value="${product.title}" /></td>
                  <td><c:out value="${product.price}" /></td>
                  <td><img style="height: 30px; width: 30px;" src="<c:url value='/template/web/assets/imgs/${product.img}'/>" alt=""></td>					
                  <td><c:out value="${product.description}" /></td>
            
                  <td>
                    <a class="btn btn-sm btn-primary" href="/Online_Shop/admin-product-edit?id=<c:out value='${product.id}' />">Edit</a>
                    <a class="btn btn-sm btn-primary" href="/Online_Shop/admin-product-delete?id=<c:out value='${product.id}' />">Delete</a>
                  </td> 
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>