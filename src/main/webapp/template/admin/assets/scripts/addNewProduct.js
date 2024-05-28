

const btnAdd = document.querySelector('.btn-add-product.add');
const btnCancel = document.querySelector('.btn-add-product.cancel');
//  ĐỔ SIZE RA
function selectSize(size) {
    var button = document.querySelector(".new-product__select-size__title");
    button.textContent = size;
    toggleSizeDropdown();   
  }

  function toggleColorOptions() {
    var colorOptions = document.getElementById("new-product__colorOptions");
    if (colorOptions.style.display === "none" || colorOptions.style.display === "") {
      colorOptions.style.display = "block";
    } else {
      colorOptions.style.display = "none";
    }
  }
  //          SET MAU BAN DAU
  window.onload = function() {
    var firstColorOption = document.querySelector(".new-product__color-option"); // Lấy color-option đầu tiên
    var firstColor = window.getComputedStyle(firstColorOption).getPropertyValue("background-color"); // Lấy màu nền của color-option đầu tiên
    var firstHexColor = rgbToHex(firstColor);
    changeColor(firstHexColor); // Áp dụng màu của color-option đầu tiên vào ô màu chính
  };

  function changeColor(color) {
    var colorPicker = document.getElementById("new-product__colorPicker");
    colorPicker.style.backgroundColor = color;

     // Cập nhật giá trị của trường ẩn
     var selectedColorInput = document.getElementById("selectedColor");
     selectedColorInput.value = color;

    var colorOptions = document.getElementById("new-product__colorOptions");
    colorOptions.style.display = "none";
  }

            // Tăng giảm kich thước
const new_product_count_input = document.querySelector('.new-product__count-input');
const count_btn_dec = document.querySelector('.count-btn-dec');
const count_btn_inc = document.querySelector('.count-btn-inc');

count_btn_dec.addEventListener('click', () =>{
  let value = parseInt(new_product_count_input.value);
  if( value > 1) {
    new_product_count_input.value = value - 1;
  }
});

count_btn_inc.addEventListener('click', ()=>{
  let value = parseInt(new_product_count_input.value);
  new_product_count_input.value = value + 1;
});

// validation nhap so luong 
const error_message = document.querySelector('.new-product__count-error-message');
const regex = /^\d+$/; // Chỉ cho phép nhập số nguyên
new_product_count_input.addEventListener('blur', ()=>{
  const value = new_product_count_input.value.trim();
  if(value === '') {
    error_message.textContent = "Vui lòng không để trống trường này.";
    btnAdd.disabled = true;
  }
  else if(!(regex.test(value)) || parseInt(value) < 1){
    error_message.textContent = "Vui lòng nhập số nguyên lớn hơn 0";
    btnAdd.disabled = true;
  }
  else {
    error_message.textContent = '';
    btnAdd.disabled = false;
    
  }
}); 
function rgbToHex(rgb) {
    var rgbValues = rgb.match(/\d+/g).map(Number);
    var hex = "#" + rgbValues.map(function(value) {
        return ("0" + value.toString(16)).slice(-2);
    }).join("");
    return hex;
}

//    CHONJ ANH

const inputImg = document.getElementById('input-img');
const displayImgs = document.getElementById('display-imgs');
let imgList = [];
inputImg.addEventListener('change', function(){
  const files = this.files;
  for(let i = 0; i< files.length; i++) {
    imgList.push(files[i]);
  }
  for(const file of files) {
    const reader = new FileReader();
    reader.onload = function(event) {
      // tao ra boc anh
      const imgGroup = document.createElement('div');
      imgGroup.classList.add('img-group');
      // tao ra anh 
      const img = document.createElement('img');
      
      img.src = event.target.result;
      img.id =`img-${file.name}`;
      // tao ra icon xoa
      const iconDelete = document.createElement('span');
      iconDelete.classList.add('icon-delete');
      iconDelete.innerHTML = '<i class="fa-solid fa-xmark"></i>';

      imgGroup.appendChild(img);
      imgGroup.appendChild(iconDelete);
      displayImgs.appendChild(imgGroup);
    }
    reader.readAsDataURL(file);
  }

});

displayImgs.addEventListener('click', function(event){
  if(event.target.closest('.icon-delete')){
    const imgGroup = event.target.closest('.img-group');
    // lay ra duoc img
    const img = imgGroup.querySelector('img');
    // laay ra cai id
    const id = img.id;
    //tu do lay dc cai file name
    const filename = id.replace('img-','');
    // dem xoa trong list
    imgList = imgList.filter(file => filename !== file.name);
    imgGroup.parentNode.removeChild(imgGroup);
  }
  // imgList.forEach(file => {
  //   console.log(file.name );
  // });
});






