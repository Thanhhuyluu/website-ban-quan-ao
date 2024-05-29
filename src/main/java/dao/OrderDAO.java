package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderDAO implements DAOInterface<Order>{

	private UserDAO userDAO;
	public static OrderDAO getInstance() {
		return new OrderDAO();
	}
	@Override
	public int insert(Order t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `order`(`user_id`, `fullname`, `email`, `phone_number`, `address`, `note`, `order_date`, `status`) VALUES ( ?,?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getUser().getId());
			pst.setString(2, t.getFullname());
			pst.setString(3, t.getEmail());
			pst.setString(4, t.getPhoneNum());
			pst.setString(5, t.getAddress());
			pst.setString(6, t.getNote()); 
			pst.setDate(7, t.getOrderDate());
			pst.setInt(8, t.getStatus());
			 
			
			
			result = pst.executeUpdate();
			System.out.println("Số lệnh đã thêm: " + result);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Order t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `order` SET `user_id`= ?,`fullname`= ?,`email`= ?,`phone_number`= ?,`address`= ?,`note`= ?,`order_date`= ?,`status`= ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getUser().getId());
			pst.setString(2, t.getFullname());
			pst.setString(3, t.getEmail());
			pst.setString(4, t.getPhoneNum());
			pst.setString(5, t.getAddress());
			pst.setString(6, t.getNote());
			pst.setDate(7, t.getOrderDate());
			pst.setInt(8, t.getStatus());
			pst.setInt(9, t.getId());
			result = pst.executeUpdate();
			System.out.println("Số lệnh đã thêm: " + result);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Order order) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `order`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, order.getId());
			result = pst.executeUpdate();
			
			System.out.println("Số lệnh đã thêm: " + result);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Order> selectAll() {
		List<Order> result = new ArrayList<Order>();
		userDAO = new UserDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order` ";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String note = rs.getString("note");
				Date orderDate = rs.getDate("order_date");
				int status = rs.getInt("status");
				Order o = new Order(id, userDAO.selectById(userId), fullname, email, phoneNumber, address, note, orderDate, status);
				result.add(o);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Order selectById(int id) {
		Order result = null;
		userDAO = new UserDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String note = rs.getString("note");
				Date orderDate = rs.getDate("order_date");
				int status = rs.getInt("status");
				result = new Order(Id, userDAO.selectById(userId), fullname, email, phoneNumber, address, note, orderDate, status);	
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Order> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Order selectLastestOrderOfUser(int s) {
		Order result = null;
		userDAO = new UserDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order` WHERE `user_id` = ? "
					+ "ORDER BY id DESC, order_date DESC "
					+ "LIMIT 1;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,s);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String note = rs.getString("note");
				Date orderDate = rs.getDate("order_date");
				int status = rs.getInt("status");
				result = new Order(Id, userDAO.selectById(userId), fullname, email, phoneNumber, address, note, orderDate, status);	
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> selectByUserId(int uId) {
		List<Order> result = new ArrayList<Order>();
		userDAO = new UserDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order` WHERE `user_id` = ? ORDER BY `id` DESC, `order_date` DESC ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, uId);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String note = rs.getString("note");
				Date orderDate = rs.getDate("order_date");
				int status = rs.getInt("status");
				Order o = new Order(id, userDAO.selectById(userId), fullname, email, phoneNumber, address, note, orderDate, status);
				result.add(o);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Order> selectByOrderDate(Date date) {
		List<Order> result = new ArrayList<Order>();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    Connection c = JDBCUtil.getConnection();
	    try {
	        // Tạo truy vấn SQL
	        String sql = "SELECT * FROM `order` WHERE `order_date` = ?";
	        preparedStatement = c.prepareStatement(sql);     
	        preparedStatement.setDate(1, date);
	        // Thực thi truy vấn
	        rs = preparedStatement.executeQuery();
	        // Lấy kết quả
	        if (rs.next()) {
	        	int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String note = rs.getString("note");
				Date orderDate = rs.getDate("order_date");
				int status = rs.getInt("status");
				Order o = new Order(id, UserDAO.getInstance().selectById(userId), fullname, email, phoneNumber, address, note, orderDate, status);
				result.add(o);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        // Đóng ResultSet, PreparedStatement không cần kiểm tra null vì chúng không thể null
	        if (rs != null) {
	            try {
	                rs.close();
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

	    return result;
	}
}
