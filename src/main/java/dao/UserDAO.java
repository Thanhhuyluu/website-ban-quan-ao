package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;

public class UserDAO implements DAOInterface<User>{
	private static UserDAO userDao;
	public static UserDAO getInstance() {
		if(userDao == null) {
			userDao = new UserDAO();
		}
		return  userDao;
	}
	
	@Override
	
	public int insert(User t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `user`"
					+ "(`fullname`, `email`, `phone_number`, `address`, `password`, `created_at`, `updated_at`, `status`, `role`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getFullname());
			pst.setString(2, t.getEmail());
			pst.setString(3, t.getPhoneNumber());
			pst.setString(4, t.getAddress());
			pst.setString(5, t.getPassword());
			pst.setDate(6, t.getCreatedAt());
			pst.setDate(7, t.getUpdatedAt());
			pst.setInt(8, t.getStatus());
			pst.setInt(9, t.getRole());
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
	public int update(User t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `user` SET \r\n"
					+ "`fullname`= ? ,"
					+ "`email`= ?,"
					+ "`phone_number`= ?,"
					+ "`address`= ?,"
					+ "`password`= ?,"
					+ "`created_at`= ?,"
					+ "`updated_at`= ?,"
					+ "`status`= ?,"
					+ "`role`= ? "
					+ "WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getFullname());
			pst.setString(2, t.getEmail());
			pst.setString(3, t.getPhoneNumber());
			pst.setString(4, t.getAddress());
			pst.setString(5, t.getPassword());
			pst.setDate(6, t.getCreatedAt());
			pst.setDate(7, t.getUpdatedAt());
			pst.setInt(8, t.getStatus());
			pst.setInt(9, t.getRole());
			pst.setInt(10, t.getId());
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
	public int delete(User user) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `user`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, user.getId());
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
	public List<User> selectAll() {
		 
		List<User> result = new ArrayList<User>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `user`";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_number");
				String address = rs.getString("address");
				String password = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				int status = rs.getInt("status");
				int role = rs.getInt("role");
				User u = new User(id,fullname,email,phoneNum,address,password,createdAt,updatedAt,status,role);
				result.add(u);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int getCountTotalStaff() {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select count(*) from user where user.status = 0 and user.role = 2";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt(1);
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int getCountTotalCustomer() {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select count(*) from user where user.status = 0 and user.role = 0";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt(1);
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<User> pagingAcountStaff(int index) {
		List<User> result = new ArrayList<User>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select * from user where status = 0 and role = 2 limit ?, 5;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,(index-1)*5);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_number");
				String address = rs.getString("address");
				String password = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				int status = rs.getInt("status");
				int Role = rs.getInt("role");
				result.add(new User(Id, fullname, email, phoneNum, address, password, createdAt, updatedAt, status, Role));	
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<User> pagingAcountCustomer(int index) {
		List<User> result = new ArrayList<User>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select * from user where status = 0 and role = 0 limit ?, 5;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,(index-1)*5);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_number");
				String address = rs.getString("address");
				String password = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				int status = rs.getInt("status");
				int Role = rs.getInt("role");
				result.add(new User(Id, fullname, email, phoneNum, address, password, createdAt, updatedAt, status, Role));	
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public User selectById(int id) {
		User result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `user` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_number");
				String address = rs.getString("address");
				String password = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				int status = rs.getInt("status");
				int role = rs.getInt("role");
				result = new User(Id,fullname,email,phoneNum,address,password,createdAt,updatedAt,status,role);		
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User seletUserByPasswordAndStatusAndPhoneNumberOrEmail(String pass, String emailOrPhoneNum, int stat) {
		User result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `user` WHERE `password` = ? and `status` = ? and (`email` = ? or `phone_number` = ?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pass);
			pst.setInt(2, stat);
			pst.setString(3, emailOrPhoneNum);
			pst.setString(4, emailOrPhoneNum);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_number");
				String address = rs.getString("address");
				String password = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				int status = rs.getInt("status");
				int role = rs.getInt("role");
				result = new User(Id,fullname,email,phoneNum,address,password,createdAt,updatedAt,status,role);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<User> selecteByRole(int role){
		List<User> result = new ArrayList<User>();
		
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `user` WHERE `role` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, role);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_number");
				String address = rs.getString("address");
				String password = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				int status = rs.getInt("status");
				int Role = rs.getInt("role");
				result.add(new User(Id, fullname, email, phoneNum, address, password, createdAt, updatedAt, status, Role));
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public ArrayList<User> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		UserDAO x = new UserDAO();
		List<User> list = x.selecteByRole(1);
		for (User user : list) {
			System.out.println(user.getEmail());
		}
	}
}
