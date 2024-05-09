<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
	<c:set var="gender" value = "${requestScope.gender }"/>
    <c:set var="genderString" value = "gender=${requestScope.gender}&"/>
    <c:set var="cart" value = "${requestScope.cart }"/>
<header class="header">
			
            <div class="header__navbar-cover">
                <div class="grid">
                    <nav class="header__navbar">
                        <ul class="header__navbar-list">
                           
                            <c:if test="${not empty USER }">
                            
                            	<li class="header__navbar-item--saparate header__navbar-item header__navbar-item--strong">
                                <a id="" class="header__navbar-item--link">
                                    <i class="fa-solid fa-user header__navbar-icon"></i>
                                    Welcome, ${USER.getFullname()} 
                                </a>
                            	</li>
                            	<li class="header__navbar-item--saparate header__navbar-item header__navbar-item--strong">
                                <a id="" class="header__navbar-item--link" href="thoat?action=logout">
                                    Thoát
                                </a>
                            	</li>
                            	<c:if test="${USER.getRole()==1 }">
                            		<li class="header__navbar-item--saparate header__navbar-item header__navbar-item--strong">
	                                <a id="" class="header__navbar-item--link" href="admin-home">
	                                    Trở về trang quản lý
	                                </a>
	                            	</li>
                            	</c:if>
                            	
                            </c:if>
                            <c:if test="${empty USER }">
                            
                             	<li class="header__navbar-item--saparate header__navbar-item header__navbar-item--strong">
                                <a id="login__btn" class="header__navbar-item--link">
                                    <i class="fa-solid fa-user header__navbar-icon"></i>
                                    Đăng nhập
                                </a>
                            	</li>
                            	
                            </c:if>
                            
                            <li class="header__navbar-item header__navbar-item--strong">
                                <a href="gio-hang?action=show" class="header__navbar-item--link">
                                    <i class="fa-solid fa-cart-shopping header__navbar-icon"></i>
                                    Giỏ hàng
                                    <c:if test="${cart != null }">(${cart.getCartSize() })</c:if>
                                </a>
                                
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="header-with-search">
                <a href="trang-chu" class="header-logo">
                    <img class="header-logo-img" src="<c:url value = 'template/web/assets/imgs/high-logo-D022924D47-seeklogo.com.png'/>" alt="">
                </a>
                <div class="header-categories">
                    <ul class="header-cate-list">
                        <li class="header-cate-item">
                            <a href="trang-chu" class="header-cate-link">SẢN PHẨM
                                <i class="header-cate-icon fa-solid fa-chevron-down"></i>
                            </a>
                            <div class="header-cate-dropbox">
                                <div class="grid">
                                    <div class="grid__row">
                                        <div class="grid__column-3">
                                            <a href="trang-chu?gender=1" class="header-cate-dropbox-with-img">
                                                <div class="cate-dropbox-img" style="background-image: url('<c:url value='template/web/assets/imgs/nam.jpg'/>');">
                                                    <div class="cate-dropbox-img-blur"></div>
                                                </div>
                                                
                                                <h2 class="cate-dropbox-heading">Nam</h2>
                                            </a>
                                        </div>
                                        <div class="grid__column-3">
                                            <a href="trang-chu?gender=2" class="header-cate-dropbox-with-img">
                                                <div class="cate-dropbox-img" style="background-image: url('<c:url value='template/web/assets/imgs/nu.jpg'/>');">
                                                    <div class="cate-dropbox-img-blur"></div>
                                                </div>
                                                
                                                <h2 class="cate-dropbox-heading">Nữ</h2>
                                            </a>
                                        </div>
                                        <div class="grid__column-3">
                                            <a href="#" class="header-cate-dropbox-with-img">
                                                <div class="cate-dropbox-img" style="background-image: url('<c:url value='template/web/assets/imgs/phukien.jpg'/>');">
                                                    <div class="cate-dropbox-img-blur"></div>
                                                </div>
                                                
                                                <h2 class="cate-dropbox-heading">Phụ kiện</h2>
                                            </a>
                                        </div>
                                        <div class="grid__column-3">
                                            <a href="#" class="header-cate-dropbox-with-img">
                                                <div class="cate-dropbox-img" style="background-image: url('<c:url value='template/web/assets/imgs/saleoff.jpg'/>');">
                                                    <div class="cate-dropbox-img-blur"></div>
                                                </div>
                                                
                                                <h2 class="cate-dropbox-heading">SaleOff</h2>
                                            </a>
                                        </div>
                                        
                                        
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="header-cate-item">
                            <a href="trang-chu?gender=1" class="header-cate-link">NAM
                                <i class="header-cate-icon fa-solid fa-chevron-down"></i>
                            </a>
                            <div class="header-cate-dropbox">
                                <div class="grid">
                                    <div class="grid__row">
                                        <div class="grid__column-3">
                                            <ul class="header-cate-dropbox-list">
                                                <h3 class="header-cate-dropbox-heading">Nổi bật</h3>
                                                <li class="header-cate-dropbox-item">
                                                    <a href="trang-chu" class="header-cate-dropbox-link">Best Seller</a>
                                                </li>
                                                <li class="header-cate-dropbox-item">
                                                    <a href="trang-chu?saleoff=1&gender=1" class="header-cate-dropbox-link">Sale Off</a>
                                                </li>
                                                
                                            </ul>
                                        </div>
                                        <div class="grid__column-3">
                                            
                                        </div>
                                        <div class="grid__column-3">
                                            <ul class="header-cate-dropbox-list">
                                                <h3 class="header-cate-dropbox-heading">Nửa trên</h3>
                                                
                                                
                                                <c:forEach items = "${requestScope.upperList }" var = "ct">
                                                	<li class="header-cate-dropbox-item">
                                                    	<a href="#" class="header-cate-dropbox-link">${ct.getName() }</a>
                                                	</li>
                                                </c:forEach>
                                                
                                            </ul>
                                        </div>
                                        <div class="grid__column-3">
                                            <ul class="header-cate-dropbox-list">
                                                <h3 class="header-cate-dropbox-heading">Nửa dưới</h3>
                                                
                                                
                                                <c:forEach items = "${requestScope.lowerList }" var = "ct">
                                                	<li class="header-cate-dropbox-item">
                                                    	<a href="#" class="header-cate-dropbox-link">${ct.getName() }</a>
                                                	</li>
                                                </c:forEach>
                                                
                                                
                                                
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="header-cate-item">
                            <a href="trang-chu?gender=2" class="header-cate-link">NỮ
                                <i class="header-cate-icon fa-solid fa-chevron-down"></i>
                            </a>
                            <div class="header-cate-dropbox">
                                <div class="grid">
                                    <div class="grid__row">
                                        <div class="grid__column-3">
                                            <ul class="header-cate-dropbox-list">
                                                <h3 class="header-cate-dropbox-heading">Nổi bật</h3>
                                                <li class="header-cate-dropbox-item">
                                                    <a href="trang-chu" class="header-cate-dropbox-link">Best Seller</a>
                                                </li>
                                                <li class="header-cate-dropbox-item">
                                                    <a href="trang-chu?saleoff=1&gender=2" class="header-cate-dropbox-link">Sale Off</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="grid__column-3">
                                            
                                        </div>
                                        <div class="grid__column-3">
                                            <ul class="header-cate-dropbox-list">
                                                <h3 class="header-cate-dropbox-heading">Áo</h3>
                                                
                                                <c:forEach items = "${requestScope.upperList }" var = "ct">
                                                	<li class="header-cate-dropbox-item">
                                                    	<a href="#" class="header-cate-dropbox-link">${ct.getName() }</a>
                                                	</li>
                                                </c:forEach>
                                                
                                            </ul>
                                        </div>
                                        <div class="grid__column-3">
                                            <ul class="header-cate-dropbox-list">
                                                <h3 class="header-cate-dropbox-heading">Quần</h3>
                                                
                                                
                                                <c:forEach items = "${requestScope.lowerList }" var = "ct">
                                                	<li class="header-cate-dropbox-item">
                                                    	<a href="#" class="header-cate-dropbox-link">${ct.getName() }</a>
                                                	</li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="header-cate-item">
                            <a href="trang-chu?saleoff=1" class="header-cate-link">SALEOFF
                               
                            </a>
                        </li>
                    </ul>
                    
                </div>
                <form action="trang-chu" method="get" id="header-seach" class="header-search">
                    <div class="header-search-input-wrap">
                        <input id="header-search-input" name="searchKey" type="text" class="header-search-input" placeholder="Tìm kiếm" value="${ requestScope.searchKey }">
                        
                            
                        
                        
                    </div>
                   	<c:if test="${requestScope.gender !=null }">
                   		<input type="hidden" name="gender" value="${gender }"/>
                   	</c:if>
                    <button onclick="submitForm()" class="header-search-btn"><i class="header-search-btn-icon fa-solid fa-magnifying-glass"></i></button>
                	
                </form>
                <script>
				    
				</script>
            </div>
            <div class="header-slider">
                <div class="header-slider-inner">
                    <div class="header-slider-btn header-slider-btn-left">
                        <i class="header-slider-icon fa-solid fa-chevron-left"></i>
                    </div>
                    <div class="header-slider-list-wrap">
                        <div class="header-slider-list">
                            <div class="header-slider-item"><h4 class="header-slider-item-content">Hoàn trả 100% trước 10 ngày</h4></div>
                            <div class="header-slider-item"><h4 class="header-slider-item-content">Hoàn trả 100% trước 9 ngày</h4></div>
                            <div class="header-slider-item"><h4 class="header-slider-item-content">Hoàn trả 100% trước 8 ngày</h4></div>
                            <div class="header-slider-item"><h4 class="header-slider-item-content">Hoàn trả 100% trước 7 ngày</h4></div>
                        </div>
                    </div>
                    
                    <div class="header-slider-btn header-slider-btn-right">
                        <i class="header-slider-icon fa-solid fa-chevron-right"></i>
                    </div>
                </div>
            </div>
            
        </header>
        