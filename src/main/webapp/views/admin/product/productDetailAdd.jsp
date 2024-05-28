<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

  <div class="content">
        <div class="new-product">
            <div class="new-product__header ">
                <h2 class="new-product-title">Thêm sản phẩm</h2>
            </div>
            <form action="admin-productDetail-insert" method="POST">
                <input type="hidden" name="productId" value="${productId}">
                <div class="new-product__body">
                    <div class="new-product__select-item__size">
                        <label class="new-product__select-size_title" for="new-product__select-size">Size: </label>
                        <select name="size" id="new-product__select-size">
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                            <option value="XXL">XXL</option>
                        </select>
                    </div>
    
                    <div class="new-product__select-item__color">
                        <label for="colorPicker__colorPicker" class="new-product__color__title">Màu: </label>
                        <div id="new-product__colorPicker" class="new-product__square" onclick="toggleColorOptions()">
                        </div>
                        <div id="new-product__colorOptions" class="new-product__color-options">
                            <div class="new-product__color-option" onclick="changeColor('#ff0000')" style="background-color: #ff0000;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#00ff00')" style="background-color: #00ff00;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#0000ff')" style="background-color: #0000ff;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#ffff00')" style="background-color: #ffff00;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#ffa500')" style="background-color: #ffa500;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#800080')" style="background-color: #800080;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#ffc0cb')" style="background-color: #ffc0cb;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#a52a2a')" style="background-color: #a52a2a;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#808080')" style="background-color: #808080;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#000000')" style="background-color: #000000;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#ffffff')" style="background-color: #ffffff;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#87ceeb')" style="background-color: #87ceeb;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#00ffff')" style="background-color: #00ffff;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#4b0082')" style="background-color: #4b0082;"></div>
					        <div class="new-product__color-option" onclick="changeColor('#ffffe0')" style="background-color: #ffffe0;"></div>
                            <input type="hidden" name="color" id="selectedColor">
                        </div>
    
                    </div>
                    
                    <div class="new-product__count">
                        <div class="new-product__count-title">Số Lượng: </div>
                        <div class="new-product__count-container">
                            <div class="new-product__count-btn count-btn-dec">-</div>
                            <input type="text" class="new-product__count-input" name="quantity" value="1">
                            <div class="new-product__count-btn count-btn-inc">+</div>
                        </div>
                        <span class="new-product__count-error-message"></span>
                    </div>
                    <div class="choose-imgs">
                        <label for="input-img" class="lable-input-img">Chọn ảnh:</label>   
                        <input type="file" accept="image/*" name="img" id="input-img" multiple>
                        <div id="display-imgs">
                            <!-- <div class="img-group">
                                <img src="https://data.webnhiepanh.com/wp-content/uploads/2020/11/21105453/phong-canh-1.jpg" alt="" class="img">
                                <span class="icon-delete">
                                    <i class="fa-solid fa-xmark"></i>
                                </span>
                            </div> -->
                        </div>
                    </div>
                    
                </div>
                <div class="new-product__footer">
                    <button class="btn-add-product cancel">Huỷ</button>
                    <button class="btn-add-product add " type="submit">Thêm</button>
                </div>
            </form>
           
        </div>

    </div>
    
    <script src="<c:url value='/template/admin/assets/scripts/addNewProduct.js' />"></script>	