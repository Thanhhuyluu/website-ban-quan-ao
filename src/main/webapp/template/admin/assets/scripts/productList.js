
// Xử lí modal-delete
const open_modal = document.getElementById("open-modal-btn");
const modal_alert = document.querySelector(".modal-alert");
const icon_close = document.getElementById("icon-close");
const btnDeleteProducts = document.querySelectorAll(".product-delete-btn");
// const alert_footer_btn = document.querySelectorAll('.alert__footer-btn')
const modal_alert_inner = document.querySelector(".modal-alert__inner");
const btnDeleteItem = document.getElementById("delete-item-modal");
const btnExitDelete = document.getElementById("exit-delete-modal");

// Xử lí xoá
var productId;
const formDeleteProduct = document.forms["form-product-delete"];

/* open_modal.onclick = function () {
       
   } */

function show() {
  modal_alert.classList.toggle("show");
}
/*  icon_close.addEventListener('click',show) */

btnDeleteProducts.forEach((element) => {
  element.addEventListener("click", () => {
    show();
    productId = element.getAttribute("data-bs-id");
    console.log(productId);
  });
});

btnDeleteItem.addEventListener("click", () => {
  formDeleteProduct.action =
    "/Online_Shop/admin-product-delete?id=" + productId;
  formDeleteProduct.submit();
  console.log("xoa");
});
btnExitDelete.addEventListener("click", () => {
  show();
  console.log("exit");
});
icon_close.addEventListener("click", () => {
  show();
  console.log("exit");
});

//  ĐỔ SIZE RA
function selectSize(size) {
  var button = document.querySelector(".select-size__title");
  button.textContent = size;
  toggleSizeDropdown();
}
function rgbToHex(rgb) {
  // Chia chuỗi RGB thành mảng các số
  var rgbArray = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
  // Chuyển đổi các số thành hex
  function hex(x) {
    return ("0" + parseInt(x).toString(16)).slice(-2);
  }
  // Trả về mã hex
  return "#" + hex(rgbArray[1]) + hex(rgbArray[2]) + hex(rgbArray[3]);
}

const btn_show_detail = document.querySelectorAll(".btn-detail-product");
btn_show_detail.forEach((button) => {
  button.addEventListener("click", function () {
    const tableCell = button.parentElement;
    const tableRow = button.parentElement.parentElement;
    const modal_product_detail = tableRow.querySelector(
      "td#modal_detail_product.modal.modal-detail-product"
    );
    // const modal_product_detail = document.getElementById('product-details');
    // console.log(modal_product_detail);
    modal_product_detail.classList.toggle("show");
    const icon_close = modal_product_detail.querySelector(".icon-close");
    const btn_close = modal_product_detail.querySelector(".close");
    icon_close.addEventListener("click", () => {
      modal_product_detail.classList.remove("show");
      console.log("Turn off");
    });
    btn_close.addEventListener("click", () => {
      modal_product_detail.classList.remove("show");
      // modal_product_detail.classList.toggle('modal--active')
      console.log("Turn off");
    });

    const colorPicker = modal_product_detail.querySelector(".colorPicker");
    const colorOption = modal_product_detail.querySelector(".colorOptions");

    const firstColorOption =
      modal_product_detail.querySelector(".color-option"); // Lấy color-option đầu tiên
    const firstColor = window
      .getComputedStyle(firstColorOption)
      .getPropertyValue("background-color"); // Lấy màu nền của color-option đầu tiên
    console.log(rgbToHex(firstColor));
    /* changeColor(rgbToHex(firstColor)); // Áp dụng màu của color-option đầu tiên vào ô màu chính */

    colorPicker.style.backgroundColor = rgbToHex(firstColor);

    const colorOptions = modal_product_detail.querySelectorAll(".color-option");
    colorOptions.forEach((changeColor) => {
      changeColor.addEventListener("click", function () {
        console.log("ok");
        colorPicker.style.backgroundColor = window
          .getComputedStyle(changeColor)
          .getPropertyValue("background-color");
        colorOption.style.display = "none";
      });
    });

    colorPicker.addEventListener("click", () => {
      console.log("ok2");
      if (
        colorOption.style.display === "none" ||
        colorOption.style.display === ""
      ) {
        colorOption.style.display = "block";
      } else {
        colorOption.style.display = "none";
      }
    });
  });
});
