<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
    <div class="form">
        <h6 class="form-title">Thêm nhân viên mới</h6>
        <form action="admin-staff-insert" method="POST">
            <div class="form-child mb-16">
                <label for="fullname" class="form-label">Họ và tên</label>
                <input type="text" name="fullname" id="fullname" class="form-control">
            </div> 
            <div class="form-child mb-16">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" id="email" class="form-control">
            </div>
            <div class="form-child mb-16">
                <label for="phoneNumber" class="form-label">Số điện thoại</label>
                <input type="tel" name="phoneNumber" id="phoneNumber" class="form-control">
            </div>
            <div class="form-child mb-16">
                <label for="address" class="form-label">Địa chỉ</label>
                <input type="text" name="address" id="address" class="form-control">
            </div>
            <button type="submit" class="btn-submit">Thêm mới</button>    
        </form>
    </div>
</div>
