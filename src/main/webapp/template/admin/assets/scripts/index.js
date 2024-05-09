const side_bar_items = document.querySelectorAll('.side-bar__item')

side_bar_items.forEach(sidebarItem =>{
    sidebarItem.addEventListener('click', function(e){
        side_bar_items.forEach(item =>{
            item.classList.remove('active')
        })
        sidebarItem.classList.add('active');
    });
});



// sidebar toggler
const sidebarToggler = document.querySelector('.sidebar-toggler')
const sidebar = document.querySelector('.side-bar')
const container = document.querySelector('.container')
sidebarToggler.addEventListener('click', function(){
    sidebar.classList.toggle('open')
    container.classList.toggle('open')
});

// user menu down
const header_right_user = document.querySelector('.header__right-user')
const user_menu = document.querySelector('.user-menu')
const down = document.querySelector('.icon-down')
header_right_user.addEventListener('click', function(){
  user_menu.classList.toggle('open')
  down.classList.toggle('down')
})