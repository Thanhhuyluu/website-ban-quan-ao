package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.MyItem;
import model.Order;
import model.OrderDetail;

public class RevenueDAO  {

	public List<MyItem> reportReceipt(Date date, int limit) {
        List<MyItem> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDays(date, i);
            MyItem myItem = new MyItem();
            myItem.setTime(covertD2S(d));
            myItem.setValue(revenueOfDate(d));
            list.add(myItem);
        }
        return list;
    }
	
	
	
	public int revenueOfLastMonth(Date currentDate) {
			int revenue = 0; 
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			    
			  // Lấy ngày đầu tiên của tháng hiện tại
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			    
			    // Giảm đi một ngày để có được ngày cuối cùng của tháng trước
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Date lastDayOfLastMonth = calendar.getTime();
			
			// Doanh thu của tháng trước
			List<MyItem> lastMonthReceipts = reportReceipt(lastDayOfLastMonth, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			for (MyItem myItem : lastMonthReceipts) {
				revenue += myItem.getValue();
			}

		return revenue; 
	}
	
	public int revenueOfCurrentMonth(Date currentDate) {
	    int revenue = 0; 
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(currentDate);
	    
	    // Lấy ngày đầu tiên của tháng hiện tại
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    Date firstDayOfCurrentMonth = calendar.getTime();
	    
	    // Doanh thu của tháng hiện tại
	    List<MyItem> currentMonthReceipts = reportReceipt(firstDayOfCurrentMonth, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    
	    for (MyItem myItem : currentMonthReceipts) {
	        revenue += myItem.getValue();
	    }
	    return revenue; 
	}
	
	public int countOrderByDate(Date date) {
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    int count = 0;
	    Connection c = JDBCUtil.getConnection();
	    try {
	    	
	        // Tạo truy vấn SQL
	        String sql = "SELECT count(*) FROM `order` WHERE `order_date` = ?";
	        preparedStatement = c.prepareStatement(sql);
	        
	        preparedStatement.setDate(1, new java.sql.Date(date.getTime()));

	        // Thực thi truy vấn
	        resultSet = preparedStatement.executeQuery();
	       
	        // Lấy kết quả
	        if (resultSet.next()) {
	            count = resultSet.getInt(1);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        // Đóng ResultSet, PreparedStatement không cần kiểm tra null vì chúng không thể null
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        if ( c != null) {
	        	try {
	        		JDBCUtil.closeConnection(c);
				} catch (Exception e) {
					// TODO: handle exception
				}
	        }
	    }

	    return count;
	}
	
	

	private int revenueOfDate(Date date) {
		int revenueOfDate = 0; 
		List<Order> orders = OrderDAO.getInstance().selectByOrderDate(new java.sql.Date(date.getTime()));
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		for (Order order : orders) {
			orderDetails = OrderDetailDAO.getInstance().selectByOrderId(order);
		}
		for (OrderDetail orderDetail : orderDetails) {
			revenueOfDate += orderDetail.getPrice();
		}
		return revenueOfDate;
	}
	
	
    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date subDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        return df.format(date);
    }
    
    public String formatRevenue(int revenueRaw) {
		String revenue = null; 
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		revenue = decimalFormat.format(revenueRaw);
		return revenue; 
	}
}
