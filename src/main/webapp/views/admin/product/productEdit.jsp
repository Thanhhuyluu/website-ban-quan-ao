<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-fluid pt-4 px-4"> 
<div class="col-sm-12 col-xl-6">
    <div class="bg-secondary rounded h-100 p-4">
         <h6 class="mb-4">Chỉnh sửa sản phẩm</h6>            
		<form action="admin-product-update" method="POST">
        	<input type="hidden" name="id" value="<c:out value='${product.id}' />" />         
            <div class="mb-3">
                <label for="name" class="form-label">Tên sản phẩm</label>
                <input type="text" class="form-control" id="name" name="name"  value="<c:out value='${product.title}' />" />
            </div>
            <div class="mb-3">
                <label for="category_id" class="form-label">Loại sản phẩm</label>
                <select class="form-select" name="category_id">
                    <option value="1" <c:if test="${product.category_id == 1}"> selected</c:if> >Nam</option>
                    <option value="2" <c:if test="${product.category_id == 2}"> selected</c:if>>Nữ</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Giá sản phẩm</label>
                <input type="text" class="form-control" id="price" name="price"  value="<c:out value='${product.price}' />" />
            </div>
            <div class="form-floating">
                <textarea class="form-control" 
                    id="description" style="height: 150px;" name="description"></textarea>
                <label for="description">Mô tả</label>
            </div>
            <div class="mb-3">
                <select class="form-select" name="gender">
                    <option value="1" <c:if test="${product.gender == 1}"> selected</c:if> >Nam</option>
                    <option value="2" <c:if test="${product.gender == 2}"> selected</c:if>>Nữ</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Thêm</button>
        </form>
    </div>
</div>
</div>
