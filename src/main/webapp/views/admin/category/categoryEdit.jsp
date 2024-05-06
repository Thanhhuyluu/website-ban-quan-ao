<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
    <div class="form ">
        <h6 class="form-title">Cập nhật loại hàng</h6>
        <form action="admin-category-update" method="POST">
            <input type="hidden" name="id" value="<c:out value='${category.id}' />" />         
            <div class="form-child mb-16">
                <label for="name" class="form-label">Tên Loại hàng</label>
                <input type="text" name="name" id="name" class="form-control" value="<c:out value='${category.name}' />">
            </div>
            <div class="form-child mb-16">
                <label for="type" class="form-label">Loại hàng</label>
                <select name="type" id="type" class="form-control form-control-select">
                    <option class="form-control-option" value="1" <c:if test="${category.type == 1}"> selected</c:if> >Áo</option>
                    <option class="form-control-option" value="2" <c:if test="${category.type == 2}"> selected</c:if> >Quần</option>
                </select>
                <div class="icon-select">
                    <i class="fa-solid fa-chevron-down"></i>
                </div>
            </div>
            <button type="submit" class="btn-submit">Lưu lại</button>    
        </form>
    </div>
</div>

