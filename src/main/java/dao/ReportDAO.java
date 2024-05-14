package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.MyItem;

public class ReportDAO  {

	public List<MyItem> reportReceipt(Date date, int limit) {
        List<MyItem> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDays(date, i);
            MyItem myItem = new MyItem();
            myItem.setTime(covertD2S(d));
            myItem.setValue(countItemByDate(d));
            list.add(myItem);
        }
        return list;
    }
	
	private int countItemByDate(Date date) {
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
	        
	        System.out.println(resultSet);

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
}
