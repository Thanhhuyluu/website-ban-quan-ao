<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container-body">
    <div class="table-classify">
        <div class="classify__header">
            <h6 class="classify__header-title">
                Phân loại
            </h6>
            <div class="btn-add">
                <a class="btn-add-link" href=" <c:url value = "admin-category-new"/>" >
                    <i class="fa-solid fa-plus" style="text-align: center;" title="Thêm loại sản phẩm mới "></i>
                </a>
            </div>
        </div>
        <div class="classify__body">
            <table class="table">
                <thead>
                    <tr>
                        <th>Tên</th>
                        <th>Loại</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="category" items="${categories}">
                    <tr>
                        <td><c:out value="${category.name}" /></td>
                        <c:choose>
                            <c:when test="${category.type == 1}">
                                <td>Áo</td>
                            </c:when>    
                            <c:otherwise>
                                <td>Quần</td>
                            </c:otherwise>    
                        </c:choose>      
                        <td>
                            <a class="active-link" href="/Online_Shop/admin-category-edit?id=<c:out value='${category.id}' />" >
                                <i class="fa-solid fa-pen-to-square" style="text-align: center;" title="Chỉnh sửa loại sản phẩm "></i>
                            </a>
                            
                        </td>
                    </tr>
                    </c:forEach>
                    
                </tbody>
            </table>
        </div>
    </div>
</div>   