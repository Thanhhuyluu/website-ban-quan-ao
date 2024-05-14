document.addEventListener("DOMContentLoaded", function() {
    const modal = document.querySelector(".modal-detail-product");
    const productDetailsContainer = document.getElementById("product-details");

    // Sử dụng event delegation để lắng nghe sự kiện click trên các thẻ a
    document.addEventListener("click", function(event) {
        if (event.target && event.target.classList.contains("active-link")) {
            event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a

            // Lấy giá trị id của sản phẩm từ thuộc tính data-product-id của thẻ a
            const productId = event.target.getAttribute("data-product-id");

            // Sử dụng AJAX để gửi yêu cầu đến Controller với productId
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Nhận phản hồi từ Controller và cập nhật nội dung của modal
                        productDetailsContainer.innerHTML = xhr.responseText;
                        modal.classList.add("show"); // Hiển thị modal
                    } else {
                        console.error("Error: " + xhr.status);
                    }
                }
            };
            xhr.open("POST", "/Online_Shop/admin-productDetail?id=" + productId, true);
            xhr.send();
        }
    });

    // Xử lý đóng modal khi nhấp vào nút đóng
/*     const closeModalButton = modal.querySelector(".icon-close");
    closeModalButton.addEventListener("click", function() {
        modal.classList.remove("show"); // Ẩn modal
    }); */
});

	//  ĐỔ SIZE RA
	function selectSize(size) {
		var button = document.querySelector(".select-size__title");
		button.textContent = size;
		toggleSizeDropdown();
	}

	function toggleColorOptions() {
		var colorOptions = document.getElementById("colorOptions");
		if (colorOptions.style.display === "none") {
			colorOptions.style.display = "block";
		} else {
			colorOptions.style.display = "none";
		}
	}
	//          SET MAU BAN DAU
	window.onload = function () {
		var firstColorOption = document.querySelector(".color-option"); // Lấy color-option đầu tiên
		var firstColor = window.getComputedStyle(firstColorOption).getPropertyValue(
		"background-color"); // Lấy màu nền của color-option đầu tiên
		changeColor(firstColor); // Áp dụng màu của color-option đầu tiên vào ô màu chính
	};

	function changeColor(color) {
		var colorPicker = document.getElementById("colorPicker");
		colorPicker.style.backgroundColor = color;
		var colorOptions = document.getElementById("colorOptions");
		colorOptions.style.display = "none";
	}



	// const btn_show_detail = document.querySelector('.btn-detail-product')
	// const icon_close = document.querySelector('.icon-close');
	// const  btn_close = document.querySelector('.close');
	// const modal = document.querySelector('.modal-detail-product')
	// btn_show_detail.addEventListener('click', ()=>{
	//   modal.classList.toggle('show')
	// })
	// icon_close.addEventListener('click', ()=>{
	//   modal.classList.toggle('show')
	// })
	// btn_close.addEventListener('click', ()=>{
	//   modal.classList.toggle('show')
	// })

	// MODAL
	const btnModals = document.querySelectorAll("[data-trigger = 'modal']")

	function onActiveOrNot(btnTargetModal, modal, open) {
		if (!modal.classList.contains("modal--active") && open) {
			modal.classList.add("modal--active")
		} else {
			modal.classList.remove("modal--active");
		}
		btnTargetModal.setAttribute("aria-open", open);
	}

	btnModals.forEach(btnModal => {
		// lấy ra thuộc tính modal 
		const modal = document.querySelector(btnModal.getAttribute("data-target"));
		// lấy trạng thái của modal
		const open = btnModal.getAttribute("aria-open") === "true" ? true : false;
		//set cho aria-opne laij trang thai ban day
		onActiveOrNot(btnModal, modal, open);

		btnModal.addEventListener('click', () => {
			onActiveOrNot(btnModal, modal, !open);
		});

		// const formReloadProductList = document.forms['form-reload-productList'];
		// Tắt modal thong qua nut close
		document.querySelector('.modal__footer .close').addEventListener('click', () => {
			onActiveOrNot(btnModal, modal, open);
			//formReloadProductList.action = 'admin-product';
			// formReloadProductList.submit();
		});
		document.querySelector('.modal__header .icon-close').addEventListener('click', () => {
			onActiveOrNot(btnModal, modal, open);
			//formReloadProductList.action = 'admin-product';
			//formReloadProductList.submit();
		}); 

	});