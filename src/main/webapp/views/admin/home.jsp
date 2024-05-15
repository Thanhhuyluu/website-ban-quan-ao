<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
    <!-- Sale & Revenue Start -->
   <div class="container-body">
      <div class="column-3-2">
          
          <ul class="chart-group">
              <li class="chart-item">
                  <div class="chart-item__name">Doanh thu tuần qua </div>
                  <div class="chart">
                      <canvas id="barchart-profit"  ></canvas>
                       <script>
                         		var barChartData = {
                             	labels: [<c:forEach var="item" items="${receipts}">'${item.time}',</c:forEach>],
                              datasets: [{
                                  label: 'Doanh thu/ngay',
                                  data: [<c:forEach var="item" items="${receipts}">${item.value},</c:forEach>],
                                  borderWidth: 1,
                                  backgroundColor: "rgb(83, 104, 110)",
                              }]
                          };
                      
                          const ctx1 = document.getElementById('barchart-profit');
                          new Chart(ctx1, {
                              type: 'bar',
                              data: barChartData,
                              options: {
                                  scales: {
                                      y: {
                                          beginAtZero: true
                                      }
                                  }
                              }
                          });
                      </script> 

                  </div>
              </li>
          </ul>
      </div>
      <div class="column-3-1">
          <ul class="cart-list">
              <li class="cart-item">
                  <img src="<c:url value='/template/admin/assets/imgs/profit.png' />" alt="" class="cart-item__img">
                  <span class="cart-item__total">${revenueOfDay} VNĐ</span>
                  <span class="cart-item__title">Daonh thu hôm nay</span>
              </li>
              <li class="cart-item">
                  <img src="<c:url value='/template/admin/assets/imgs/money_current.png' />" alt=""
                      class="cart-item__img">
                  <span class="cart-item__total">${revenueOfLastMonth} VNĐ</span>
                  <span class="cart-item__title">Doanh thu tháng trước </span>
              </li>
              <li class="cart-item">
                  <img src="<c:url value='/template/admin/assets/imgs/growth.png' />" alt="" class="cart-item__img">
                  <span class="cart-item__total">${revenueGrowth}%</span>
                  <span class="cart-item__title">Tăng trưởng tháng trước </span>
              </li>
              <li class="cart-item">
                  <img src="<c:url value='/template/admin/assets/imgs/order.png' />" alt="" class="cart-item__img">
                  <span class="cart-item__total">${orderOfDate} đơn</span>
                  <span class="cart-item__title">Đơn hàng/ngày </span>
              </li>
          </ul>
      </div>

      </div>