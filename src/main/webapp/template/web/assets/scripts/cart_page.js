<<<<<<< HEAD
const productSizeBtns  = document.querySelectorAll('.product__size-btn');



productSizeBtns.forEach(element => {
    element.addEventListener('click',()=>{
        var sizeContainer = element.querySelector('.product__select-container');
=======
const CartItemSizeBtns  = document.querySelectorAll('.cart-item__size-btn');



CartItemSizeBtns.forEach(element => {
    element.addEventListener('click',()=>{
        var sizeContainer = element.querySelector('.cart-item__select-container');
>>>>>>> 931742ee15a3d12d6c975474353137e7e4595508
        sizeContainer.classList.toggle('active');
    })

    
<<<<<<< HEAD
});
=======
});










	const cartItemCountInput = document.querySelector('.cart-item-count-input');
    const cartItemDecreaseBtn = document.querySelector('.cart-item-count-btn-dec');
    const cartItemIncreaseBtn = document.querySelector('.cart-item-count-btn-inc');
    const cartItemCurrentQuantity = document.getElementById('cart-item-current-quantity').value;
    
    window.addEventListener('DOMContentLoaded', () => {
       if(parseInt(cartItemCurrentQuantity)> 0){
		   
        	cartItemCountInput.value = 1;
	   }else {
		   cartItemCountInput.value = 0;
	   }
    });
    // Thêm sự kiện khi nhấn nút "-"
    cartItemDecreaseBtn.addEventListener('click', () => {
        let value = parseInt(cartItemCountInput.value);
        if (value > 1) {
            cartItemCountInput.value = value - 1;
        }
    });

    // Thêm sự kiện khi nhấn nút "+"
    cartItemIncreaseBtn.addEventListener('click', () => {
        let value = parseInt(cartItemCountInput.value);
        if (value < parseInt(cartItemCurrentQuantity)) {
            cartItemCountInput.value = value + 1;
        }
    });

    // Kiểm tra giá trị input khi người dùng nhập trực tiếp
    cartItemCountInput.addEventListener('input', () => {
        let value = parseInt(cartItemCountInput.value);
        if (isNaN(value) || value < 1) {
            cartItemCountInput.value = 1;
        } else if (value > parseInt(cartItemCurrentQuantity)) {
            cartItemCountInput.value = parseInt(cartItemCurrentQuantity);
        }
    });
>>>>>>> 931742ee15a3d12d6c975474353137e7e4595508
