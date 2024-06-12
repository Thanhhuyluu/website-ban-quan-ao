	// Xử lí modal-delete
   const open_modal = document.getElementById('open-modal-btn')
   const modal_alert = document.querySelector('.modal-alert')
   const icon_close = document.querySelector('.icon-close')
   const btnDeleteStaffs = document.querySelectorAll('.staff-delete-btn')
   // const alert_footer_btn = document.querySelectorAll('.alert__footer-btn')
   const modal_alert_inner = document.querySelector('.modal-alert__inner')
   const btnDeleteItem = document.getElementById('delete-item-modal');
   const btnExitDelete = document.getElementById('exit-delete-modal');

	// Xử lí xoá
	var staffId;
	const formDeleteStaff = document.forms['form-staff-delete'];

   function show(){
       modal_alert.classList.toggle('show')
   }
  /*  icon_close.addEventListener('click',show) */

    btnDeleteStaffs.forEach(element => {
        element.addEventListener('click',() => {
        show();
        staffId = element.getAttribute("data-bs-id");
        console.log(staffId);
        });
    });
   btnDeleteItem.addEventListener('click', ()=>{
	   formDeleteStaff.action = '/Online_Shop/admin-staff-delete?id=' + staffId ;
       formDeleteStaff.submit();
       console.log('xoa');
   });
   btnExitDelete.addEventListener('click', ()=>{
       show();
       console.log('exit');
   });
   icon_close.addEventListener('click', show);