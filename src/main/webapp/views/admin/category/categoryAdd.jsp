<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-fluid pt-4 px-4"> 
<div class="col-sm-12 col-xl-6">
    <div class="bg-secondary rounded h-100 p-4">
        <h6 class="mb-4">Thêm mới loại hàng</h6>            
		<form action="admin-category-insert" method="POST">
            <div class="mb-3">
                <label for="name" class="form-label">Tên Loại hàng</label>
                <input type="text" class="form-control" id="name" name="name"/>
            </div>
            <div class="mb-3">
                <select class="form-select" name="type">
                    <option value="1" >Áo</option>
                    <option value="2" >Quần</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Thêm</button>
        </form>
    </div>
</div>
</div>
