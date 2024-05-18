package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class CategoryDAO implements DAOInterface<Category> {

	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}
	@Override
	public int insert(Category t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `category`(`name`,`type`)"
					+ " VALUES (?, ?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setInt(2,t.getType());
			pst.executeUpdate();
			System.out.println("Số lệnh đã thêm: " + result);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Category t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `category` SET `name`= ? , `type` = ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setInt(2, t.getType());
			pst.setInt(3, t.getId());
			pst.executeUpdate();
			System.out.println("Số lệnh đã thêm: " + result);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Category category) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `category`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, category.getId());
			pst.executeUpdate();
			
			System.out.println("Số lệnh đã thêm: " + result);
			System.out.println("Lệnh đã thực thi là: " + sql);
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
		
	@Override
	public List<Category> selectAll() {
		List<Category> result = new ArrayList<Category>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `category`";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int type = rs.getInt("type");
				Category cate = new Category(id,name, type);
				result.add(cate);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Category selectById(int id) {
		Category result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `category` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				int type = rs.getInt("type");
				result = new Category(Id,name,type);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<Category> selectByType(int type) {                  //áo : 1   quần : 2   Không phải áo quần : 3
		List<Category> result = new ArrayList<Category>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `category` WHERE `type` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,type);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				int Type = rs.getInt("type");
				Category ct = new Category(Id,name,Type);
				result.add(ct);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	

	@Override
	public ArrayList<Category> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
