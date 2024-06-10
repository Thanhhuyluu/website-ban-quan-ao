	const likedItemCountInputWrapper = document.querySelectorAll('.liked__product-item-count-container');
	likedItemCountInputWrapper.forEach(element=>{
		const likedItemCountInput = element.querySelector('.liked__product-item-count-input');
    const likedItemDecreaseBtn = element.querySelector('.liked__product-item-count-btn-dec');
    const likedItemIncreaseBtn = element.querySelector('.liked__product-item-count-btn-inc');
    
    const likedItemCurrentQuantity = element.querySelector('.liked__product-item-current-quantity').value;
    window.addEventListener('DOMContentLoaded', () => {
		
       if(parseInt(likedItemCurrentQuantity)> 0){
		   
        	likedItemCountInput.value = 1;
	   }else {
		   likedItemCountInput.value = 0;
	   }
    });
    // Thêm sự kiện khi nhấn nút "-"
    likedItemDecreaseBtn.addEventListener('click', () => {
        let value = parseInt(likedItemCountInput.value);
        if (value > 1) {
            likedItemCountInput.value = value - 1;
        }
    });

    // Thêm sự kiện khi nhấn nút "+"
    likedItemIncreaseBtn.addEventListener('click', () => {
        let value = parseInt(likedItemCountInput.value);
        if (value < parseInt(likedItemCurrentQuantity)) {
            likedItemCountInput.value = value + 1;
        }
    });

    // Kiểm tra giá trị input khi người dùng nhập trực tiếp
    likedItemIncreaseBtn.addEventListener('input', () => {
        let value = parseInt(cartItemCountInput.value);
        if (isNaN(value) || value < 1) {
            cartItemCountInput.value = 1;
        } else if (value > parseInt(cartItemCurrentQuantity)) {
            cartItemCountInput.value = parseInt(cartItemCurrentQuantity);
        }
    });
	})