<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@include file="/common/taglib.jsp"%>
    <!-- Sale & Revenue Start -->
   <div class="container-body">
                <div class="column-3-2">
                    
                    <ul class="chart-group">
                        <li class="chart-item">
                            <div class="chart-item__name">Ten bieu do</div>
                            <div class="chart">
                                <canvas id="barchart-profit"  ></canvas>

                            </div>
                        </li>
                        <li class="chart-item">
                            <div class="chart-item__name">Ten bieu do</div>
                            <div class="chart">
                                <canvas id="barchart-orders"  ></canvas>

                            </div>
                        </li>
                    </ul>
                </div>
                <div class="column-3-1">
                    <ul class="cart-list">
                        <li class="cart-item">
                            <img src="<c:url value='/template/admin/assets/imgs/profit.png' />" alt="" class="cart-item__img">
                            <span class="cart-item__total">1.679.000 VNĐ</span>
                            <span class="cart-item__title">Tien lai/ngay</span>
                        </li>
                        <li class="cart-item">
                            <img src="<c:url value='/template/admin/assets/imgs/money_current.png' />" alt="" class="cart-item__img">
                            <span class="cart-item__total">1.679.000 VNĐ</span>
                            <span class="cart-item__title">So tien hien co</span>
                        </li>
                        <li class="cart-item">
                            <img src="<c:url value='/template/admin/assets/imgs/growth.png' />" alt="" class="cart-item__img">
                            <span class="cart-item__total">100%</span>
                            <span class="cart-item__title">Tang truong thang truoc</span>
                        </li>
                        <li class="cart-item">
                            <img src="<c:url value='/template/admin/assets/imgs/order.png' />" alt="" class="cart-item__img">
                            <span class="cart-item__total">1000 đơn</span>
                            <span class="cart-item__title">Don hang/ngay</span>
                        </li>
                    </ul>
                </div>
                
            </div>