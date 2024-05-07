const side_bar_items = document.querySelectorAll('.side-bar__item')

side_bar_items.forEach(sidebarItem =>{
    sidebarItem.addEventListener('click', function(e){
        side_bar_items.forEach(item =>{
            item.classList.remove('active')
        })
        sidebarItem.classList.add('active');
    });
});

const ctx = document.getElementById('barchart-profit');

new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ['20/4/2024', '21/4/2024', '22/4/2024', '23/4/2024', '24/4/2024', '25/4/2024'],
    datasets: [{
      label: 'Tien lai/ngay',
      data: [200, 400, 1500, 800, 700, 300],
      borderWidth: 1,
      backgroundColor: "rgb(83, 104, 110)",
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});

const cty = document.getElementById('barchart-orders');

new Chart(cty, {
  type: 'bar',
  data: {
    labels: ['20/4/2024', '21/4/2024', '22/4/2024', '23/4/2024', '24/4/2024', '25/4/2024'],
    datasets: [{
      label: 'Tien lai/ngay',
      data: [200, 400, 1500, 800, 700, 300],
      borderWidth: 1,
      backgroundColor: "rgba(235, 22, 22,1)"
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
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
	console.log('oke');
})