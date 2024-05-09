<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@include file="/common/taglib.jsp"%>
    <div class="container-fluid pt-4 px-4">
      <div class="bg-secondary text-center rounded p-4">
        <div class="d-flex align-items-center justify-content-between mb-4">
          <h6 class="mb-0">Category</h6>
          <a href="">Show All</a>
        </div>
        <div class="table-responsive">
          <table class="table text-start align-middle table-bordered table-hover mb-0">
            <thead>
              <tr class="text-white">
                <th scope="col">
                  <input class="form-check-input" type="checkbox">
                </th>
       			<th scope="col">Fullname</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
                <th scope="col">Address</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="customer" items="${customers}">
                <tr>
                  <td>
                    <input class="form-check-input" type="checkbox">
                  </td>
                  <td>${customer.fullname}</td>
                  <td>${customer.email}</td>
                  <td>${customer.phoneNumber}</td>
                  <td>${customer.address}</td>
                  <td>
                    <a class="btn btn-sm btn-primary" href="">Detail</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>