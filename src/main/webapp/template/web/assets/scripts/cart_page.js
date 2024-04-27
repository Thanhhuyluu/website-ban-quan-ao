const productSizeBtns  = document.querySelectorAll('.product__size-btn');



productSizeBtns.forEach(element => {
    element.addEventListener('click',()=>{
        var sizeContainer = element.querySelector('.product__select-container');
        sizeContainer.classList.toggle('active');
    })

    
});