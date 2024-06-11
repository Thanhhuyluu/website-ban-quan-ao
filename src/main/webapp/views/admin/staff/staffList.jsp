<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
  <div class="table-classify">
      <div class="classify__header">
          <h6 class="classify__header-title">
              Danh sách nhân viên
          </h6>
           <form id="searchForm" action="admin-staff" method="GET">
		        <div class="header__search">
		            <div class="header__search-icon">
		            	<i class="fa-solid fa-magnifying-glass"></i>
		            </div>
		            <input type="text" name="txtSearch" class="header__search-input"
		                placeholder="Tìm kiếm tên nhân viên  " value="${txtSearch}">
		        </div>
		    </form>
          <div class="btn-add">
              <a class="btn-add-link" href=" <c:url value = "admin-staff-new"/>" >
                  <i class="fa-solid fa-plus" style="text-align: center;" title="Thêm nhân viên mới "></i>
              </a>
          </div>
      </div>
       <span> ${message}</span>
      <div class="classify__body">
          <table class="table">
              <thead>
                  <tr>
                    <th>Họ và tên </th>
                    <th>Email</th>
                    <th>Số điện thoại </th>
                    <th>Địa chỉ </th>
                    <th>Trạng thái </th>
                    <th>Thao tác </th>
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
		                           	<i class="fa-solid fa-unlock" style="text-align: center;" title="Tài khoản chưa bị khoá " ></i>
		                        </a>
                            </c:when>    
                            <c:otherwise>
                               <a class="active-link" href="/Online_Shop/admin-staff-band?id=<c:out value='${staff.id}' />" >
		                           	<i class="fa-solid fa-lock" style="text-align: center;" title="Tài khoản đã bị khoá" ></i>
		                        </a>
                            </c:otherwise>    
                        </c:choose>  
                    </td>
                    <td>
                        <a class="active-link" href="/Online_Shop/admin-staff-edit?id=<c:out value='${staff.id}' />" >
                            <i class="fa-solid fa-pen-to-square" style="text-align: center;" title="Cập nhật thông tin nhân viên  "></i>
                        </a>
                        <a class="active-link staff-delete-btn" id="open-modal-btn" data-bs-id="${staff.id}" >
						    <i class="fa-solid fa-trash-can" style="text-align: center;" title="Xoá nhân viên "></i> 
						</a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
          </table>
          <ul class="pagination home-product__pagination">
			<li class="pagination-item "><a
				href="admin-staff?txtSearch=${txtSearch==null? "": txtSearch}&index=${tag == 1?tag : (tag-1)}" class="pagination-item__link"> <i
					class="pagination-item__icon fa-solid fa-chevron-left"></i>
			</a></li>
			<c:forEach begin="1" end="${endPage}" var="i">
				<li class="pagination-item ${tag == i?"pagination-item--active":""}">

					<a class="pagination-item__link" href="admin-staff?txtSearch=${txtSearch==null? "": txtSearch}&index=${i}" >${i}</a>
				</li>
			</c:forEach>
			<li class="pagination-item"><a
				href="admin-staff?txtSearch=${txtSearch==null? "": txtSearch}&index=${tag == endPage?tag : (tag+1)}" class="pagination-item__link"> <i
					class="pagination-item__icon fa-solid fa-chevron-right"></i>
			</a></li>
		</ul>
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
           	<button class="alert__footer-btn " id="delete-item-modal" >Có</button>
            <button class="alert__footer-btn " id="exit-delete-modal" >Thoát</button>
        </div>
    </div>
</div>
    
<form name="form-staff-delete" method="POST"></form>
 

<script src="<c:url value='/template/admin/assets/scripts/search.js' />"></script>
<script src="<c:url value='/template/admin/assets/scripts/staffList.js' />"></script>

