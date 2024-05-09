<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container-body">
    <div class="form">
        <h6 class="form-title">Thêm sản phẩm mới</h6>
        <form action="admin-product-insert" method="POST">
            <div class="form-child mb-16">
                <label for="title" class="form-label">Tên sản phẩm </label>
                <input type="text" name="title" id="title" class="form-control">
            </div> 
            
            <div class="form-child mb-16">
                <label for="category_id" class="form-label">Tên loại hàng</label>
                <select name="category_id" id="category_id" class="form-control form-control-select">
                	<c:forEach var="product" items="${productDistinctCates}"> 
                    	<option class="form-control-option" value="<c:out value='${product.category.id}' />"><c:out value='${product.category.name}' /></option>
                    </c:forEach>
                </select>
                <div class="icon-select">
                    <i class="fa-solid fa-chevron-down"></i>
               </div>
            </div>
             <div class="form-child mb-16">
                <label for="brand_id" class="form-label">Thương hiệu </label>
                <select name="brand_id" id="brand_id" class="form-control form-control-select">
                	<c:forEach var="product" items="${productDistinctBrands}"> 
                    	<option class="form-control-option" value="<c:out value='${product.brand.id}' />"><c:out value='${product.brand.name}' /></option>
                    </c:forEach>
                </select>
                <div class="icon-select">
                    <i class="fa-solid fa-chevron-down"></i>
               </div>
            </div>
             <div class="form-child mb-16">
                <label for="supplier_id" class="form-label">Nhà cung cấp </label>
                <select name="supplier_id" id="supplier_id" class="form-control form-control-select">
                	<c:forEach var="product" items="${productDistinctSuppliers}"> 
                    	<option class="form-control-option" value="<c:out value='${product.supplier.id}' />"><c:out value='${product.supplier.name}' /></option>
                    </c:forEach>
                </select>
                <div class="icon-select">
                    <i class="fa-solid fa-chevron-down"></i>
               </div>
            </div>
            <div class="form-child mb-16">
                <label for="gender" class="form-label">Giới tính</label>
                <select name="gender" id="gender" class="form-control form-control-select">
                    <option class="form-control-option" value="1">Nam</option>
                    <option class="form-control-option" value="2">Nữ</option>
                    <option class="form-control-option" value="3">Cả nam và nữ </option>
                </select>
                <div class="icon-select">
                    <i class="fa-solid fa-chevron-down"></i>
                </div>
            </div>
           <div class="form-child mb-16">
                <label for="price" class="form-label">Giá sản phẩm </label>
                <input type="number" name="price" id="price" class="form-control">
            </div> 
            
             <div class="form-child mb-16">
                <label for="discount" class="form-label">Giảm giá sản phẩm </label>
                <input type="number" name="discount" id="discount" class="form-control">
            </div> 
            <div class="form-child mb-16">
                <label for="img" class="form-label">Ảnh sản phẩm</label>
                <input type="file" name="img" class="fomrFile" accept="image/*">
            </div>
             <div class="form-child mb-16">
                 <label for="description" class="form-label">Mô tả sản phẩm</label>
                 <textarea name="description" id="description" class="form-textarea"></textarea>
             </div>
             
            <button type="submit" class="btn-submit">Thêm mới</button>    
        </form>
    </div>
</div>
