<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
    <div class="container-fluid pt-4 px-4">
      <div class="bg-secondary text-center rounded p-4">
        <div class="d-flex align-items-center justify-content-between mb-4">
          <h6 class="mb-0">Category</h6>
          <a class="btn btn-sm btn-primary" href="/Online_Shop/admin-category-new">Add</a>
        </div>
        <div class="table-responsive">
          <table class="table text-start align-middle table-bordered table-hover mb-0">
            <thead>
              <tr class="text-white">
                <th scope="col">
                  <input class="form-check-input" type="checkbox">
                </th>
                <th scope="col">Name</th>
                <th scope="col">Type</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="category" items="${categories}">
                <tr>
                  <td>
                    <input class="form-check-input" type="checkbox">
                  </td>
                  <td><c:out value="${category.name}" /></td>
                  <c:choose>
					    <c:when test="${category.type == 1}">
							<td>Ao</td>
					    </c:when>    
					    <c:otherwise>
							<td>Quan</td>
					    </c:otherwise>
					</c:choose>                 
                  <td>
                    <a class="btn btn-sm btn-primary" href="/Online_Shop/admin-category-edit?id=<c:out value='${category.id}' />">Edit</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>