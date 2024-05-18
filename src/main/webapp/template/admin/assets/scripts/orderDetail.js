// const btnShowOrderDetail = document.querySelectorAll('.btn-show-modal-order-detail')
// const modalOrderDetailIcon = document.querySelector('.modal__header-icon-close');
// const modal = document.querySelector('.modal');
// const modalOrderDetail = document.querySelector('.modal-order-detail');
// const orderDetailBtn = document.querySelector('.order-detail-btn');

// function showOrderDetail() {
//     modal.classList.toggle('show')

// }
// // btnShowOrderDetail.addEventListener('click', showOrderDetail);

// btnShowOrderDetail.forEach(element => {
//     element.addEventListener('click', showOrderDetail)
// });
// modalOrderDetailIcon.addEventListener('click', showOrderDetail);
// orderDetailBtn.addEventListener('click', showOrderDetail);

// // modal.addEventListener("click", showOrderDetail )
// modalOrderDetail.addEventListener("click", function (event) {
//     event.stopPropagation()
// });



// const btn_show_detail = document.querySelectorAll('.btn-show-modal-order-detail');
// 	btn_show_detail.forEach(button => {
// 		button.addEventListener('click', function () {
// 			const tableCell = button.parentElement;
// 			const tableRow = button.parentElement.parentElement;
// 			const modal_product_detail = tableRow.querySelector('td#modal_detail_order.modal.modal-detail-order');
// 			modal_product_detail.classList.toggle('show')
// 			const icon_close = modal_product_detail.querySelector('.modal__header-icon-close');
// 			const btn_close = modal_product_detail.querySelector('.order-detail-btn');
// 			icon_close.addEventListener('click', () => {
// 				modal_product_detail.classList.remove("show");
// 				console.log('Turn off');	
// 			})
// 			btn_close.addEventListener('click', () => {	
// 				modal_product_detail.classList.remove("show");
// 				// modal_product_detail.classList.toggle('modal--active')
// 				console.log('Turn off'); 
// 			})
// 			document.addEventListener('keydown', function(event) {
// 				// Kiểm tra nếu phím nhấn là phím Esc (Escape)
// 				if (event.key === 'Escape' || event.keyCode === 27) {
// 					modal_product_detail.classList.remove("show");
// 					// Thực hiện các hành động bạn muốn ở đây
// 				}
// 			});

			
// 		});
// 	});
// const btn_show_detail = document.querySelectorAll('.btn-show-modal-order-detail');

// btn_show_detail.forEach(button => {
//     button.addEventListener('click', function () {
//         const tableRow = button.closest('tr');
//         const modal_product_detail = tableRow.querySelector('#modal_detail_order');
        
//         modal_product_detail.classList.toggle('show');

//         const icon_close = modal_product_detail.querySelector('.modal__header-icon-close');
//         const btn_close = modal_product_detail.querySelector('.order-detail-btn');

//         icon_close.addEventListener('click', () => {
//             modal_product_detail.classList.remove('show');
//         });

//         btn_close.addEventListener('click', () => {
//             modal_product_detail.classList.remove('show');
//         });

//         document.addEventListener('keydown', function(event) {
//             if (event.key === 'Escape' || event.keyCode === 27) {
//                 modal_product_detail.classList.remove('show');
//             }
//         });
//     });
// });
document.addEventListener('DOMContentLoaded', () => {
    const btn_show_detail = document.querySelectorAll('.btn-show-modal-order-detail');

    btn_show_detail.forEach(button => {
        button.addEventListener('click', function () {
            const tableRow = button.closest('tr');
            const modal_product_detail = tableRow.querySelector('.modal.modal-detail-order');
            
            modal_product_detail.classList.toggle('show');

            const icon_close = modal_product_detail.querySelector('.modal__header-icon-close');
            const btn_close = modal_product_detail.querySelector('.order-detail-btn');

            icon_close.addEventListener('click', () => {
                modal_product_detail.classList.remove('show');
            });

            btn_close.addEventListener('click', () => {
                modal_product_detail.classList.remove('show');
            });

            document.addEventListener('keydown', function(event) {
                if (event.key === 'Escape' || event.keyCode === 27) {
                    modal_product_detail.classList.remove('show');
                }
            });
        });
    });
});