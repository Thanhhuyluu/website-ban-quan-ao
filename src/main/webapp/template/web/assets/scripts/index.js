



const listItem = document.querySelector('.header-slider-list')
const items = document.querySelectorAll('.header-slider-item');
const btnLeft  = document.querySelector('.header-slider-btn-left')
const btnRight  = document.querySelector('.header-slider-btn-right')
const length = items.length
let current = 0;
const handleChangeSlider =()=>{
    if(current==length-1) {
        current= 0
        let width = items[0].offsetWidth
        listItem.style.transform = `translateX(0px)`
       }else{
    
           current++;
           let width = items[0].offsetWidth
           listItem.style.transform = `translateX(${width  *-1* current}px)`
       }
}
let handleEventChangeSlide = setInterval(handleChangeSlider, 4000);

btnRight.addEventListener('click',()=>{
    clearInterval(handleEventChangeSlide)
    handleChangeSlider()
    handleEventChangeSlide = setInterval(handleChangeSlider, 4000);
})

btnLeft.addEventListener('click',()=>{
    clearInterval(handleEventChangeSlide)
    if(current==0) {
        current = length-1
        let width = items[0].offsetWidth
        listItem.style.transform = `translateX(${width * -1 * current}px)`
        }else{

            current--
            let width = items[0].offsetWidth
            listItem.style.transform = `translateX(${width  *-1* current}px)`
    }
    handleEventChangeSlide = setInterval(handleChangeSlider, 4000);
})





// side bar toggle


const sidebarItem = document.querySelectorAll('.sidebar-category-heading')
const sidebarSubItem= document.querySelector('.sidebar-category-sub-item')


sidebarItem.forEach(element => {
    element.addEventListener('click',()=>{
        element.classList.toggle('unshow')
    })
});









// home slider


const homeSlider = document.querySelector('.home-img-slider')
const homeSliderImgs = document.querySelectorAll('.home-img-slider-wrap');
 const homeSliderBtnLeft  = document.querySelector('.home-slider-btn-left')
 const homeSliderBtnRight  = document.querySelector('.home-slider-btn-right')
const length2 = homeSliderImgs.length
let current2 = 0;
const handleChangeSlider2 =()=>{
    if(current2==length2-1) {
        current2= 0
        let width = homeSliderImgs[0].offsetWidth
        homeSlider.style.transform = `translateX(0px)`
       }else{
    
           current2++;
           let width = homeSliderImgs[0].offsetWidth
           homeSlider.style.transform = `translateX(${width  *-1* current2}px)`
       }
}
let handleEventChangeSlide2 = setInterval(handleChangeSlider2, 4000);

homeSliderBtnRight.addEventListener('click',()=>{
    clearInterval(handleEventChangeSlide2)
    handleChangeSlider2()
    handleEventChangeSlide2 = setInterval(handleChangeSlider2, 4000);
})

homeSliderBtnLeft.addEventListener('click',()=>{
    clearInterval(handleEventChangeSlide2)
    if(current2==0) {
        current2 = length2-1
        let width = homeSliderImgs[0].offsetWidth
        homeSlider.style.transform = `translateX(${width * -1 * current2}px)`
        }else{

            current2--
            let width = homeSliderImgs[0].offsetWidth
            homeSlider.style.transform = `translateX(${width  *-1* current2}px)`
    }
    handleEventChangeSlide2 = setInterval(handleChangeSlider2, 4000);
})




//authform
