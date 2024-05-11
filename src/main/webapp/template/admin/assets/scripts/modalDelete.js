const open_modal = document.getElementById('open-modal-btn')
const modal_alert = document.querySelector('.modal-alert')
const icon_close = document.querySelector('.icon-close')
const alert_footer_btn = document.querySelectorAll('.alert__footer-btn')
const modal_alert_inner = document.querySelector('.modal-alert__inner')
function show(){
    modal_alert.classList.toggle('show')
    console.log(open_modal);
}
 icon_close.addEventListener('click',show)

open_modal.addEventListener('click',show )
// btn_close.addEventListener('click', show)
alert_footer_btn.forEach(element => {
    element.addEventListener('click', show)
});
 icon_close.addEventListener('click', show)
