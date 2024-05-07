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
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `order`(`user_id`, `fullname`, `email`, `phone_number`, `address`, `note`, `order_date`, `status`) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getUser().getId());
			pst.setString(2, t.getFullname());
			pst.setString(3, t.getEmail());
			pst.setString(4, t.getPhoneNum());
			pst.setString(5, t.getAddress());
			pst.setString(6, t.getNote());
			pst.setDate(7, t.getOrderDate());
			pst.setInt(8, t.getStatus());
			pst.executeUpdate();
			System.out.println("Số lệnh đã thêm: " + ketqua);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int update(Order t) {
		int ketqua = 0;
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
			pst.executeUpdate();
			System.out.println("Số lệnh đã thêm: " + ketqua);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int delete(Order order) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `order`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, order.getId());
			pst.executeUpdate();
			
			System.out.println("Số lệnh đã thêm: " + ketqua);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public List<Order> selectAll() {
		List<Order> ketqua = new ArrayList<Order>();
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
				ketqua.add(o);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	@Override
	public Order selectById(int id) {
		Order ketqua = null;
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
				ketqua = new Order(Id, userDAO.selectById(userId), fullname, email, phoneNumber, address, note, orderDate, status);	
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<Order> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
