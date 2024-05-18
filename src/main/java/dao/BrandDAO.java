package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Brand;

public class BrandDAO implements DAOInterface<Brand> {

	public static BrandDAO getInstance() {
		return new BrandDAO();
	}
	@Override
	public int insert(Brand t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `brand`(`name`) "
					+ "VALUES (?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getName());
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
	public int update(Brand t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `brand` SET `name`= ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setInt(2, t.getId());
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
	public int delete(Brand brand) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `brand`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, brand.getId());
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
	public List<Brand> selectAll() {
		List<Brand> result = new ArrayList<Brand>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `brand`";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Brand b = new Brand(id,name);
				result.add(b);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Brand selectById(int id) {
		Brand result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `brand` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				result = new Brand(Id,name);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Brand> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
