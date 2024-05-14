<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/base.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/main.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/product_page.css'/>"
	type="text/css">

<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/cart_page.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/payment_page.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/order_page.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/order_detail_page.css'/>"
	type="text/css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value="/template/web/assets/fonts/fontawesome-free-6.5.2-web/css/all.min.css"/>"
	type="text/css">
<title><dec:title default="Trang chủ" /></title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/template/web/assets/imgs/high-logo.ico"/>">
</head>
<body>


	<div class="app">
		<%@ include file="/common/web/header.jsp"%>

		<div class="app__container">
			<dec:body />
		</div>

		<%@ include file="/common/web/footer.jsp"%>
		<!-- modal layout -->
		<div
			class="modal ${ requestScope.alert == null && requestScope.isExistAccount== null && requestScope.isInvalidEmail== null && requestScope.isInvalidPhoneNumber== null && requestScope.isInvalidPassword== null? "hide-modal":"" }" >
			<div class="modal__overlay"></div>
			<div class="modal__body">
				<div class="modal__inner">


					<form action="<c:url value="dang-ki?action=register"/>"
						method="post"
						class="auth-form ${requestScope.isExistAccount== null && requestScope.isInvalidEmail== null && requestScope.isInvalidPhoneNumber== null && requestScope.sInvalidPassword== null?"
						" :"active"  } " id="auth-form-regist">
						<div class="auth-form__container">
							<div class="auth-form__header">
								<h2 class="auth-form__heading">Đăng kí</h2>
								<div class="auth-form__form">
									<div class="auth-form__group-wrapper">
										<div class="auth-form__group invalid">
											<input name="registerName" id="auth-form-regist__name"
												type="text" class="auth-form__input"
												placeholder="Nhập tên của bạn"> <span
												class="form-message"></span>
										</div>
										<div class="auth-form__group">
											<input name="registerEmail" id="auth-form-regist__email"
												type="text" class="auth-form__input" placeholder="Email">
											<span class="form-message">${requestScope.isInvalidEmail }</span>
										</div>
									</div>
									<div class="auth-form__group">
										<input name="registerPhoneNumber"
											id="auth-form-regist__phoneNumber" type="text"
											class="auth-form__input" placeholder="Nhập số điện thoại">
										<span class="form-message">${requestScope.isInvalidPhoneNumber }</span>
									</div>
									<div class="auth-form__group">
										<input name="registerPassword" type="password"
											id="auth-form-regist__password" type="text"
											class="auth-form__input" placeholder="Nhập mật khẩu">
										<span class="form-message">${requestScope.isInvalidPassword }</span>
									</div>
									<div class="auth-form__group">
										<input name="registerConfPassword" type="password"
											id="auth-form-regist__confpassword" class="auth-form__input"
											placeholder="Nhập lại mật khẩu"> <span
											class="form-message"></span>
									</div>
								</div>

								<div class="auth-form__switch">
									<a href="" class="auth-form__switch-link">Đăng nhập</a>
								</div>
								<span class="form-alert ${requestScope.isExistAccount == null?"": "active" }">${requestScope.isExistAccount  }</span>
								<div class="auth-form__group">
									<button type="submit" class="btn">Đăng kí</button>
								</div>
								<input type="hidden" value="login" id="action" name="action" />
							</div>
						</div>
					</form>

					<form action="<c:url value="dang-nhap?action=login"/>"
						method="post"
						class="auth-form   ${requestScope.isExistAccount== null && requestScope.isInvalidEmail== null && requestScope.isInvalidPhoneNumber== null && requestScope.sInvalidPassword== null ? "
						active":"" }" id="auth-form-login">
						<div class="auth-form__container">
							<div class="auth-form__header">
								<h2 class="auth-form__heading">Đăng nhập</h2>
								<div class="auth-form__form">

									<div class="auth-form__group">
										<input value="${ userEmailOrPhone}"
											name="emailOrPhoneNumberLogin" id="auth-form-login__email"
											type="text" class="auth-form__input"
											placeholder="Nhập email hoặc số điện thoại"> <span
											class="form-message"></span>
									</div>
									<div class="auth-form__group">
										<input value="${userPassword }" name="passwordLogin"
											type="password" id="auth-form-login__password"
											class="auth-form__input" placeholder="Nhập mật khẩu">

										<span class="form-message"></span>
									</div>
								</div>

								<span class="form-alert ${requestScope.alert == null?"": "active" }">${requestScope.alert  }</span>
								<div class="form-check">
									<input name="remember" value="1" type="checkbox" /> <span
										class="remember-label">Remember me</span>
								</div>
								<div class="auth-form__switch">
									<a href="" class="auth-form__switch-link">Đăng kí</a>
								</div>

								<div class="auth-form__group">
									<button type="submit" class="btn">Đăng nhập</button>
								</div>
								<input type="hidden" value="login" id="action" name="action" />
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
	<div
		class="order-cancel-modal ${ requestScope.orderCanceledAlert == null ? "hide-modal":""}">
		<div class="order-cancel-modal__overlay"></div>
		<div class="modal__body">
			<div class="modal__inner">
				<form action="don-hang" id="order-cancel-id-sender" class="order-cancel-confirm">
					<input type="hidden" name="action" value="huy"/>
					<input type="hidden" name="orderId" id="order_id_input" value=""/>
					<h3 class="order-cancel-confirm__heading">Bạn đã chắc muốn xóa
						đơn hàng này chứ</h3>
					<div class="order-cancel-confirm__btn-wrapper">
						<a class="order-cancel-confirm__btn order-cancel-out">Thoát</a> 
						<button
							type="submit"
							class="order-cancel-confirm__btn order-cancel-in">Tôi chắc chắn</button>
					</div>
				</form>

			</div>
		</div>
	</div>



	<script src="<c:url value='/template/web/assets/scripts/index.js'/>"></script>
	<script
		src="<c:url value='/template/web/assets/scripts/FormValidate.js'/>"></script>
	<script
		src="<c:url value='/template/web/assets/scripts/product_page.js'/>"></script>
	<script
		src="<c:url value='/template/web/assets/scripts/cart_page.js'/>"></script>

	<script>
        Validator({
            form: '#auth-form-login',
            rules: [
                Validator.isRequired('#auth-form-login__email'),
                Validator.isEmail('#auth-form-login__email'),
                Validator.isRequired('#auth-form-login__password'),
                Validator.minLength('#auth-form-login__password', 6),
                
            ], 
            
        });
        Validator({
            form: '#auth-form-regist',
            rules: [
                Validator.isRequired('#auth-form-regist__email'),
                Validator.isEmail('#auth-form-regist__email'),
				
                Validator.isRequired('#auth-form-regist__name', 'Vui lòng nhập họ tên của bạn'),
                
                Validator.isRequired('#auth-form-regist__phoneNumber'),
                Validator.isPhoneNumber('#auth-form-regist__phoneNumber'),

                Validator.isRequired('#auth-form-regist__password'),
                Validator.minLength('#auth-form-regist__password',6),

                Validator.isRequired('#auth-form-regist__confpassword'),
                Validator.isConfirmed('#auth-form-regist__confpassword', function(){
                    return document.querySelector('#auth-form-regist__password').value;
                }, 'Mật khẩu nhập lại không chính xác'),


            ]
        });
        Validator({
            form: '#payment-form',
            rules: [
                Validator.isRequired('#payment-page__name-input'),
                Validator.isRequired('#payment-page__phonenumber-input'),
                Validator.isPhoneNumber('#payment-page__phonenumber-input'),
                Validator.isRequired('#payment-page__email-input'),
                Validator.isRequired('#payment-page__address-input'),
                Validator.isRequired('#payment-page__province-input'),
                Validator.isRequired('#payment-page__district-input'),
                Validator.isRequired('#payment-page__wards-input'),
                Validator.isEmail('#payment-page__email-input'),
               // Validator.isRequired('#auth-form-login__password'),
               // Validator.minLength('#auth-form-login__password', 6),
                
            ], 
            
        });

        const switchLinks = document.querySelectorAll('.auth-form__switch-link');
                    switchLinks.forEach(link => {
                        link.addEventListener('click', function(e) {
                            e.preventDefault();
                            const forms = document.querySelectorAll('.auth-form');
                            forms.forEach(form => form.classList.toggle('active'));
                        });
                    });




                 // modal


                    const loginBtn = document.querySelector('#login__btn');
                    const overLay = document.querySelector('.modal__overlay');
            		
                    // Định nghĩa hàm trước khi sử dụng
                    function toggleForm(){
                        var modal = document.querySelector('.modal');
                        
                      
                        	modal.classList.toggle('hide-modal');
                        
                        
                        
                    }
            		
					if(loginBtn!= null){
						
	                    loginBtn.onclick = toggleForm;
	                    overLay.onclick = toggleForm;
						
					}
					
					const orderCancelOut = document.querySelector('.order-cancel-out')
					
					function toggleOrderCancel(){
		            			var orderModal = document.querySelector('.order-cancel-modal');
		                        
		                        	orderModal.classList.toggle('hide-modal');
		                       
		            		}
					if(orderCancelOut!=null){
		
		                orderCancelOut.onclick= toggleOrderCancel;
		
		                const orderCancelOverLay = document.querySelector('.order-cancel-modal__overlay');
		                orderCancelOverLay.onclick = toggleOrderCancel;
		
		           	}
					
					
					function handleOrderCancleClick(orderId){
						var orderModal = document.querySelector('.order-cancel-modal');
                        
                    	orderModal.classList.remove('hide-modal');
                    	var orderCancelIn = document.getElementById('order_id_input');
                    	console.log(orderCancelIn);
                    	orderCancelIn.value=orderId;
                    	
						
					}
                   	

    </script>
</body>


</html>