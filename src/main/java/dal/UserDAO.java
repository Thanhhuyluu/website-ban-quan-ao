package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
		int ketqua = 0;
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
	public int update(User t) {
		int ketqua = 0;
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
	public int delete(int id) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `user`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
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
	public List<User> selectAll() {
		 
		List<User> ketqua = new ArrayList<User>();
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
				ketqua.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	@Override
	public User selectById(int id) {
		User ketqua = null;
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
				ketqua = new User(Id,fullname,email,phoneNum,address,password,createdAt,updatedAt,status,role);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	public User seletUserByPasswordAndStatusAndPhoneNumberOrEmail(String pass, String emailOrPhoneNum, int stat) {
		User ketqua = null;
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
				ketqua = new User(Id,fullname,email,phoneNum,address,password,createdAt,updatedAt,status,role);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	@Override
	public ArrayList<User> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
