<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
  <div class="table-classify">
      <div class="classify__header">
          <h6 class="classify__header-title">
              Danh sách nhân viên
          </h6>
          <div class="btn-add">
              <a class="btn-add-link" href=" <c:url value = "admin-staff-new"/>" >
                  <i class="fa-solid fa-plus"></i>
              </a>
          </div>
      </div>
      <div class="classify__body">
          <table class="table">
              <thead>
                  <tr>
                    <th>Fullname</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>Action</th>
                  </tr>
              </thead>
              <tbody>
                <c:forEach var="staff" items="${staffs}">
                  <tr>
                    <td>${staff.fullname}</td>
                    <td>${staff.email}</td>
                    <td>${staff.phoneNumber}</td>
                    <td>${staff.address}</td>
                    <td>
                   		<c:choose>
                            <c:when test="${staff.status == 0}">
                                 <a class="active-link" href="/Online_Shop/admin-staff-band?id=<c:out value='${staff.id}' />" >
		                           	<i class="fa-solid fa-unlock"></i>
		                        </a>
                            </c:when>    
                            <c:otherwise>
                               <a class="active-link" href="/Online_Shop/admin-staff-band?id=<c:out value='${staff.id}' />" >
		                           	<i class="fa-solid fa-lock"></i>
		                        </a>
                            </c:otherwise>    
                        </c:choose>  
                    </td>
                    <td>
                        <a class="active-link" href="/Online_Shop/admin-staff-edit?id=<c:out value='${staff.id}' />" >
                            <i class="fa-solid fa-pen-to-square"></i>
                        </a>
                        <a  class="active-link" href="/Online_Shop/admin-staff-delete?id=<c:out value='${staff.id}' />">
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
<div class="modal-alert">
        <div class="modal-alert__inner">
            <div class="alert__header">
                <div class="alert__header-title">Cảnh báo</div>
                <i class="fa-solid fa-xmark icon-close"></i>
            </div>
            <div class="alert__content">
                <p class="alert__content-text">Bạn có chắc chắn muốn xoá không ?</p>
            </div>
            <div class="alert__footer">
               	<button class="alert__footer-btn btn-submit" type="button">Có</button>
                <button class="alert__footer-btn btn-close">Thoát</button>
            </div>
        </div>
    </div>
    <script src="<c:url value='/template/admin/assets/scripts/modalDelete.js' />"></script>
