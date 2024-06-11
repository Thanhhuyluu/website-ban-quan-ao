<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<tbody id="product-list">
    <c:forEach var="lProductItem" items="${lProductItems}">
    <c:set var="product" value="${lProductItem.product}" />
    <c:set var="productDetails" value="${lProductItem.productDetails}" />
    <c:set var="images" value="${lProductItem.images}" />
    <c:set var="colorsOfProduct" value="${lProductItem.colorsOfProduct}" />
    <c:set var="sizesOfProduct" value="${lProductItem.sizesOfProduct}" />
    <c:set var="countProductDetail" value="${lProductItem.countProductDetail}" />
    <tr class="product-item__row">
        <td><c:out value="${product.title}" /></td>
        <td><c:out value="${product.price}" /> VNĐ</td>
        <td><img style="height: 50px; width: 50px;" src="<c:url value='/imgs/${product.img}'/>" alt=""></td>
        <c:choose>
            <c:when test="${product.gender == 1}"><td>Nam</td></c:when>
            <c:when test="${product.gender == 2}"><td>Nữ</td></c:when>
            <c:otherwise><td>Cả nam và nữ</td></c:otherwise>
        </c:choose>
        <td><c:out value="${product.createdAt}" /></td>
        <td>
            <a class="active-link" href="/Online_Shop/admin-product-edit?id=<c:out value='${product.id}' />">
                <i class="fa-solid fa-pen-to-square" style="text-align: center;" title="Chỉnh sửa thông tin sản phẩm "></i>
            </a>
            <a class="active-link product-delete-btn" data-bs-id="${product.id}">
                <i class="fa-solid fa-trash-can" style="text-align: center;" title="Xoá sản phẩm "></i>
            </a>
            <a class="active-link btn-detail-product" id="btn-detail-product">
                <i class="fa-solid fa-magnifying-glass" style="text-align: center;" title="Xem chi tiết sản phẩm "></i>
            </a>
        </td>
        <td id="modal_detail_product" class="modal modal-detail-product">
            <div class="modal__content" id="product-details">
                <div class="modal__header">
                    <i class="fa-solid fa-xmark icon-close"></i>
                    <div class="product-detail__img" style="background-image: url(<c:url value='/imgs/${product.img}'/>);"></div>
                    <div class="product-detail__title">${product.title}</div>
                    <div class="product-detail__category">
                        Loại áo:
                        <c:choose>
                            <c:when test="${product.gender == 1}">Nam</c:when>
                            <c:when test="${product.gender == 2}">Nữ</c:when>
                            <c:otherwise>Cả nam và nữ</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="modal__main">
                    <div class="select-size-color">
                        <div class="select-item__size">
                            <label class="select-size_title" for="select-size">Size:</label>
                            <select name="select-size" id="select-size">
                                <c:forEach var="size" items="${sizesOfProduct}">
                                    <option value="${size}">${size}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="select-item__color">
                            <label for="colorPicker" class="color__title">Màu: </label>
                            <div class="square colorPicker"></div>
                            <div class="color-options colorOptions">
                                <c:forEach var="color" items="${colorsOfProduct}">
                                    <div class="color-option" style="background-color: #${color};"></div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="detail-product-count-fav">
                        <div class="detail-product-status-count">Trong kho còn ${countProductDetail} sản phẩm</div>
                        <div class="detail-product-status-fav">Luợt thích: ${product.likes}</div>
                    </div>
                    <div class="detail-product-descript">
                        <div class="descript-title">Mô tả</div>
                        <div class="descript-content">${product.description}</div>
                    </div>
                </div>
                <div class="modal__footer">
                    <div class="modal__footer-btn-wrapper">
                        <a href="/Online_Shop/admin-productDetail-add?id=<c:out value='${product.id}'/>" class="add-productDetail-btn">Thêm sản phẩm</a>
                        <button class="close">Thoát</button>
                    </div>
                </div>
            </div>
        </td>
    </tr>
</c:forEach>
</tbody>

<script src="<c:url value='/template/admin/assets/scripts/productList.js' />"></script>
