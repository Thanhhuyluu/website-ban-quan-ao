<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Thông tin cá nhân - High</title>
  </head>
  <body>
    <div class="main__infor-area">
      <div class="grid">
        <h1 class="payment-page__heading">Thông tin cá nhân</h1>
        <form id="infor-form" method="post" class="personal-infor-page__infor">
          <div class="personal-infor-page__infor-input-group">
            <input
              name="fullname"
              id="payment-page__name-input"
              type="text"
              class="personal-infor-page__infor-input"
              placeholder="HỌ TÊN"
              value="${USER.getFullname()}"
            />
            <span class="form-message"></span>
          </div>
          <div class="personal-infor-page__infor-input-group">
            <input
              readonly
              name="phoneNumber"
              style="color: #b49595; cursor: not-allowed"
              id="payment-page__phonenumber-input"
              type="text"
              class="personal-infor-page__infor-input"
              placeholder="Số điện thoại"
              value="${USER.getPhoneNumber() }"
            />
            <span class="form-message"></span>
          </div>
          <div class="personal-infor-page__infor-input-group">
            <input
              readonly
              style="color: #b49595; cursor: not-allowed"
              name="email"
              id="payment-page__email-input"
              type="text"
              class="personal-infor-page__infor-input"
              placeholder="Email"
              value="${USER.getEmail() }"
            />
            <span class="form-message"></span>
          </div>
          <div class="personal-infor-page__infor-input-group">
            <input
              name="password"
              id="infor-page__password-input"
              type="password"
              class="personal-infor-page__infor-input"
              placeholder="Password"
              value="${requestScope.error_password == null ? USER.getPassword():  requestScope.error_password}"
            />
            <span id="password-error" class="form-message">${requestScope.error }</span>
          </div>
          <div class="personal-infor-page__infor-input-group">
            <input
              name="address"
              id="infor-page__address-input"
              type="text"
              class="personal-infor-page__infor-input"
              placeholder="Địa chỉ"
              value="${ADDRESS.getAddress() }"
            />
            <span class="form-message"></span>
          </div>
          <div class="personal-infor-page__infor-input-group">
            <input
              name="province"
              id="infor-page__province-input"
              type="text"
              class="personal-infor-page__infor-input"
              placeholder="Tỉnh / Thành phố"
              value="${ADDRESS.getProvince()  }"
            />
            <span class="form-message"></span>
          </div>
          <div class="personal-infor-page__input-group-wrapper">
            <div class="personal-infor-page__infor-input-group">
              <input
                name="district"
                id="infor-page__district-input"
                type="text"
                class="personal-infor-page__infor-input"
                placeholder="Quận / Huyện"
                value="${ADDRESS.getDistrict()}"
              />
              <span class="form-message"></span>
            </div>
            <div class="personal-infor-page__infor-input-group">
              <input
                name="wards"
                id="infor-page__wards-input"
                type="text"
                class="personal-infor-page__infor-input"
                placeholder="Phường / Xã"
                value="${ADDRESS.getWards()}"
              />
              <span class="form-message"></span>
            </div>
          </div>
          <div class="personal-infor-nav-link-wrapper">
            <div class="personal-infor-nav-link">
              <a href="trang-chu" class="return-to-shop-link"
                >TIẾP TỤC MUA HÀNG</a
              >
            </div>
            <div class="personal-infor-nav-link">
              <div onclick='changePersonalInfor("${USER.getFullname()}",
              "${USER.getPhoneNumber() }", "${USER.getEmail() }")' class="return-to-shop-link">LƯU THAY ĐỔI
            </div>
          </div>
        </div>
        <input name="action"  type="hidden" value="change">
      </form>
      
     </div>
	  <script type="text/javascript">
		function changePersonalInfor(name, phoneNum, email) {
			
			/* address_raw = $('#infor-page__address-input').val().trim();
			province_raw = $('#infor-page__province-input').val().trim();
			district_raw = $('#infor-page__district-input').val().trim();
			wards_raw = $('#infor-page__wards-input').val().trim();
			password_raw = $('#infor-page__password-input').val().trim();
			$.ajax({
				type: "POST",
				url: "/Online_Shop/thong-tin-ca-nhan?action=change",
				
				data: {
					fullname: name,
					phoneNumber: phoneNum,
					email: email,
					password: password_raw,
					address: address_raw,
					province: province_raw,
					district: district_raw,
					wards: wards_raw
				},
				success: function(result) {
					// You can handle the result here if needed
					
					var error_raw = $('#error-collector').val();
					console.log(error_raw);
					if(error_raw != null && error_raw!=""){
						$('#password-error').text("This password has been used");
					}
					
					
					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					// Handle any errors that may occur during the request
					console.error("Request failed: " + textStatus + ", " + errorThrown);
				}
			}); */
			$('#infor-form').action = '/thong-tin-ca-nhan?action=change';
			$('#infor-form').submit();
		}
		
		
		
	</script>
    </div>
  </body>
</html>
