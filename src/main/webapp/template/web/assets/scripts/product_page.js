


// Panel heading
const panel_heading_infor = document.querySelector(".js-panel-item__heading-infor")
const panel_sub_infor = document.querySelector(".js-panel-item__sub")
// panel_heading_infor.addEventListener("click", () => {
//     panel_sub_infor.style.display = panel_sub_infor.style.display === 'none' ? 'block' : 'none';})

 const panel_exchange = document.querySelector(".js-panel-exchange")
const panel_exchange_sub = document.querySelector(".js-exchange-sub")

panel_exchange.addEventListener("click", () => {
    if(panel_exchange_sub.style.display !==  'block'){
        panel_exchange_sub.style.display = 'block'
        panel_exchange.style.color = '#546b73'
    }
    else{
        panel_exchange_sub.style.display = 'none'
        panel_exchange.style.color = '#000'
    }
})    
/*
const panel_guarantee = document.querySelector(".js-panel-guarantee")
const panel_guarantee_sub = document.querySelector(".js-guarantee-sub")
panel_guarantee.addEventListener('click', () => {
    // panel_guarantee_sub.style.display = panel_guarantee_sub.style.display === 'none' ? 'block' : 'none';
    if(panel_guarantee_sub.style.display !==  'block'){
        panel_guarantee_sub.style.display = 'block'
        panel_guarantee.style.color = '#546b73'
    }
    else{
        panel_guarantee_sub.style.display = 'none'
        panel_guarantee.style.color = '#000'
    }

})*/

panel_heading_infor.addEventListener("click", () => {
    if(panel_sub_infor.style.display !==  'block'){
        panel_sub_infor.style.display = 'block'
        panel_heading_infor.style.color = '#546b73'
    }
    else{
        panel_sub_infor.style.display = 'none'
        panel_heading_infor.style.color = '#000'
    }

})  

    // panel_heading_infor.addEventListener("click", () => {
    //     if (panel_sub_infor.classList.contains("hidden")) {
    //         panel_heading_infor.style.color = '#546b73'
    //         panel_sub_infor.classList.remove("hidden")
    //         panel_sub_infor.classList.add("open")
    //      }
    //      else if(panel_sub_infor.classList.contains("open")){
    //         panel_heading_infor.style.color = '#000'
    //         panel_sub_infor.classList.remove("open")
    //         panel_sub_infor.classList.add("hidden")
    //      }
    // });    

    //heart active 
    const heart_empty = document.querySelector('.icon-heart-empty')
    const heart_full = document.querySelector('.icon-heart-full')
    heart_empty.addEventListener('click', () => {
        if(heart_empty.classList.contains("active-hearted")){
            heart_empty.classList.remove("active-hearted")
            heart_full.classList.add("active-hearted")
        }
        else if(heart_full.classList.contains("active-hearted")){
            heart_full.classList.remove("active-hearted")
            heart_empty.classList.add("active-hearted")
        }
    })

    heart_full.addEventListener('click', () => {
        if(heart_full.classList.contains("active-hearted")){
            heart_full.classList.remove("active-hearted")
            heart_empty.classList.add("active-hearted")
        }
    })
    



    // container footer
    const listItemSliderContainer = document.querySelector('.container__footer-list-item')
    // console.log(listItemSliderContainer)
    const item_container_footer = Array.from(document.getElementsByClassName('product-item')) 
    const item = document.querySelector('.product-item')
    const icon_left = document.querySelector('.container__footer-icon-left')
    const icon_right = document.querySelector('.container__footer-icon-right')
    let lengthItem = item_container_footer.length
    // console.log(length)
    let currentItem = 0
    const handleChange = ()=> {
        if( currentItem == lengthItem -4){
            currentItem = 0
            //let widthItem = item.clientWidth
            listItemSliderContainer.style.transform = `translateX( 0px)`
        }
        else{
            currentItem +=4
            //let widthItem = item.clientWidth
            listItemSliderContainer.style.transform = `translateX(${300 * currentItem* -1  }px)`
        }
    }
    let handleEvent = setInterval(handleChange, 3000)
    clearInterval(handleEvent)
    icon_right.addEventListener('click', ()=>{
        handleChange()
    })
    icon_left.addEventListener('click',() => {
        if( currentItem == 0){
            currentItem = lengthItem -4
           // let widthItem = item.clientWidth
            listItemSliderContainer.style.transform = `translateX(${300 * currentItem* -1  }px)`
        }
        else{
            currentItem -= 4
           // let widthItem = item.clientWidth
            listItemSliderContainer.style.transform = `translateX(${300 * currentItem* -1  }px)`
        }
    })

    // const select_size = document.querySelectorAll(".product__item-size")
    // let length_item_size = select_size.length

    // const size_1 = document.querySelector('.size-1')
    // size_1.addEventListener('click',()=>{
    //     document.querySelector('.select-size').classList.remove('select-size')
    //     size_1.classList.add('select-size')
    //     size_1.classList.remove('hover')

    //     // size_1.classList.hover = `none`
    // } )
    // const size_2 = document.querySelector('.size-2')
    // size_2.addEventListener('click',()=>{
    //     document.querySelector('.select-size').classList.remove('select-size')
    //     size_2.classList.add('select-size')
    // } )
    // const size_3 = document.querySelector('.size-3')
    
    // size_3.addEventListener('click',()=>{
    //     document.querySelector('.select-size').classList.remove('select-size')
    //     size_3.classList.add('select-size')
    // } )






    //sử lý size button click
    const productSelectBtn = document.querySelector('.product__select-btn');
    
    const productSelectionContainer = document.querySelector('.product__select-container');
    const currentSize = document.querySelector('.product__size-current');

    productSelectBtn.onclick = function(){
        productSelectionContainer.classList.toggle('active');
    }




	
    var sizeButtons = productSelectionContainer.querySelectorAll('.product__size-item-btn')
    
    //console.log(sizeButtons)
    var buttons = document.querySelectorAll('.product__size-item-btn');


    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
       
            document.querySelector('.product__size-current').textContent = this.value;

     
            buttons.forEach(function(btn) {
                btn.classList.remove('selected-size');
            });

            this.classList.add('selected-size');
        });
    }); 

    //xử lý nút màu sắc


   









    //xử li script phần quantity product

    const inputElement = document.querySelector('.product-count-input');
    const decreaseBtn = document.querySelector('.product-count-btn-dec');
    const increaseBtn = document.querySelector('.product-count-btn-inc');
    const currentQuantity = document.querySelector('.product-current-quantity').textContent;
    
    window.addEventListener('DOMContentLoaded', () => {
       if(parseInt(currentQuantity)> 0){
		   
        inputElement.value = 1;
	   }else {
		   inputElement.value = 0;
	   }
    });
    // Thêm sự kiện khi nhấn nút "-"
    decreaseBtn.addEventListener('click', () => {
        let value = parseInt(inputElement.value);
        if (value > 1) {
            inputElement.value = value - 1;
        }
    });

    // Thêm sự kiện khi nhấn nút "+"
    increaseBtn.addEventListener('click', () => {
        let value = parseInt(inputElement.value);
        if (value < parseInt(currentQuantity)) {
            inputElement.value = value + 1;
        }
    });

    // Kiểm tra giá trị input khi người dùng nhập trực tiếp
    inputElement.addEventListener('input', () => {
        let value = parseInt(inputElement.value);
        if (isNaN(value) || value < 1) {
            inputElement.value = 1;
        } else if (value > parseInt(currentQuantity)) {
            inputElement.value = parseInt(currentQuantity);
        }
    });





       


    

    

const  productItemImgsWraper= document.querySelector('.product-item-imgs-container')

const  productItemImgsContainer= document.querySelector('.product-item-imgs-list')
 const productItemImgsList = document.querySelectorAll('.product-item-imgs-item');
 const productItemImgsBtnLeft = document.querySelector('.product-item-imgs-btn-left')
  const productItemImgsBtnRight  = document.querySelector('.product-item-imgs-btn-right')
 const productListLength = productItemImgsList.length
 const maxCurrentCount = parseInt(productListLength%4 == 0 ?( productListLength/4 ): (productListLength/4 +1))

 let currentProductImgItem = 0;
const handleChangeProductImgsList =()=>{
    if(currentProductImgItem!=maxCurrentCount-1) {
        currentProductImgItem++;
        let width = productItemImgsWraper.offsetWidth
        productItemImgsContainer.style.transform = `translateX(${width  *-1* currentProductImgItem}px)`
    }
}


productItemImgsBtnRight.addEventListener('click',()=>{

    handleChangeProductImgsList()
})

productItemImgsBtnLeft.addEventListener('click',()=>{
    
    if(currentProductImgItem==0) {
        productItemImgsContainer.style.transform = `translateX(0}px)`
    }else{

            currentProductImgItem--;
            let width = productItemImgsWraper.offsetWidth;
            productItemImgsContainer.style.transform = `translateX(${width  *-1* currentProductImgItem}px)`
    }
})
