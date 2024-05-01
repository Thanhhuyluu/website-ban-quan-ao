package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductDetail;

public class ProductDetailDAO implements DAOInterface<ProductDetail> {
	private static ProductDetailDAO productDetailDao;

	public static ProductDetailDAO getInstance() {
		if (productDetailDao == null) {
			productDetailDao = new ProductDetailDAO();
		}
		return productDetailDao;
	}

	@Override
	public int insert(ProductDetail t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `product_details`(`product_id`, `size`, `quantity`, `created_at`, `color`)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProductId());
			pst.setString(2, t.getSize());
			pst.setInt(3, t.getQuantity());
			pst.setDate(4, t.getCreatedAt());
			pst.setString(5, t.getColor());
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
	public int update(ProductDetail t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `product_details` SET `product_id`= ? ,`size`= ? ,`quantity`= ? ,`created_at`= ? ,`color`= ?  WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProductId());
			pst.setString(2, t.getSize());
			pst.setInt(3, t.getQuantity());
			pst.setDate(4, t.getCreatedAt());
			pst.setString(5, t.getColor());
			pst.setInt(6, t.getId());
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
	public int delete(ProductDetail productDetail) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM `product_details`" + " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, productDetail.getId());
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
	public List<ProductDetail> selectAll() {
		List<ProductDetail> ketqua = new ArrayList<ProductDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details`";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				ProductDetail pd = new ProductDetail(id, productId, size, quantity, createdAt, color);
				ketqua.add(pd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ketqua;
	}

	@Override
	public ProductDetail selectById(int id) {
		ProductDetail ketqua = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				ketqua = new ProductDetail(Id, productId, size, quantity, createdAt, color);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<ProductDetail> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<ProductDetail> selectByProductId(int pId) {
		List<ProductDetail> ketqua = new ArrayList<ProductDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details` WHERE `product_id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, pId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				ProductDetail pd = new ProductDetail(id, productId, size, quantity, createdAt, color);
				ketqua.add(pd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ketqua;
	}
	public ProductDetail selectByColorAndSize(String pColor, String pSize) {
		ProductDetail ketqua = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details` WHERE `color` = ? AND `size` = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pColor);
			pst.setString(2, pSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				ketqua = new ProductDetail(Id, productId, size, quantity, createdAt, color);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	public List<String> selectDistinctSizeByColor(String pColor) {
		List<String> ketqua = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT `size` FROM `product_details` WHERE `color` = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pColor);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				String size = rs.getString("size");
				
				ketqua.add(size);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ketqua;
	}
	public List<String> selectDistinctColorBySize(String pSize) {
		List<String> ketqua = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT `color` FROM `product_details` WHERE `size` = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				String size = rs.getString("color");
				
				ketqua.add(size);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ketqua;
	}

}
