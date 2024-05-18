package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Image;

public class ImageDAO implements DAOInterface<Image>{
	private static ImageDAO imgDao;
	private ProductDAO productDAO;
	public static ImageDAO getInstance() {
		if(imgDao== null) {
			imgDao = new ImageDAO();
		}
		return imgDao;
	}
	

	@Override
	public int insert(Image t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `image`(`product_id`,`img`) "
					+ "VALUES (?, ?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProduct().getId());
			pst.setString(2, t.getImg());
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
	public int update(Image t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `image` SET `product_id`= ? ,`img`=? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProduct().getId());
			pst.setString(2, t.getImg());
			pst.setInt(3, t.getId());
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
	public int delete(Image img) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `image`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, img.getId());
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
	public List<Image> selectAll() {
		List<Image> result = new ArrayList<Image>();
		productDAO = new ProductDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `image`";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String img = rs.getString("img");
				Image i = new Image(id, productDAO.selectById(productId), img);
				result.add(i);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Image selectById(int id) {
		Image result = null;
		productDAO = new ProductDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `image` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String img = rs.getString("img");
				result = new Image(Id, productDAO.selectById(productId), img);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Image> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Image> selectByProductId(int pId) {
		List<Image> result = new ArrayList<Image>();
		productDAO = new ProductDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `image` WHERE `product_id` = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, pId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String img = rs.getString("img");
				Image i = new Image(id, productDAO.selectById(productId), img);
				result.add(i);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
