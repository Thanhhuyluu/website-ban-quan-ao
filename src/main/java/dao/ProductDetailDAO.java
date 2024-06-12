package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
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
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `product_details`(`product_id`, `size`, `quantity`, `created_at`, `color`)"
					+ " VALUES (?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProduct().getId());
			pst.setString(2, t.getSize());
			pst.setInt(3, t.getQuantity());
			pst.setDate(4, t.getCreatedAt());
			pst.setString(5, t.getColor());
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
	public int update(ProductDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `product_details` SET `product_id`= ? ,`size`= ? ,`quantity`= ? ,`created_at`= ? ,`color`= ?  WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProduct().getId());
			pst.setString(2, t.getSize());
			pst.setInt(3, t.getQuantity());
			pst.setDate(4, t.getCreatedAt());
			pst.setString(5, t.getColor());
			pst.setInt(6, t.getId());
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
	public int delete(ProductDetail productDetail) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM `product_details`" + " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, productDetail.getId());
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
	public List<ProductDetail> selectAll() {
		List<ProductDetail> result = new ArrayList<ProductDetail>();
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
				ProductDetail pd = new ProductDetail(id, ProductDAO.getInstance().selectById(productId), size, quantity, createdAt, color);
				result.add(pd);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ProductDetail selectById(int id) {
		ProductDetail result = null;
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

				result = new ProductDetail(Id, ProductDAO.getInstance().selectById(productId), size, quantity, createdAt, color);


			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<ProductDetail> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<ProductDetail> selectByProductId(int pId) {
		List<ProductDetail> result = new ArrayList<ProductDetail>();
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
				ProductDetail pd = new ProductDetail(id, ProductDAO.getInstance().selectById(productId), size, quantity, createdAt, color);
				result.add(pd);
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public int countProductDetail(Product product) {
		List<ProductDetail> lProductDetails = selectByProductId(product.getId());
		int result = 0;
		for (ProductDetail productDetail : lProductDetails) {
			result += productDetail.getQuantity();
		}
		return result;
	}
	public ProductDetail selectByColorAndSize(String pColor, String pSize, int proId) {
		ProductDetail result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details` WHERE `color` = ? AND `size` = ? 	AND product_id = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pColor);
			pst.setString(2, pSize);
			pst.setInt(3, proId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				result = new ProductDetail(Id, ProductDAO.getInstance().selectById(productId), size, quantity, createdAt, color);

			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ProductDetail> selectByColorAndSizeAndProductId(String pColor, String pSize, int proId) {
		List<ProductDetail> result = new ArrayList<ProductDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details` WHERE `color` = ? AND `size` = ? 	AND product_id = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pColor);
			pst.setString(2, pSize);
			pst.setInt(3, proId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				ProductDetail productDetail= new ProductDetail(Id, ProductDAO.getInstance().selectById(productId), size, quantity, createdAt, color);
				result.add(productDetail);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<String> selectDistinctSizeByColor(String pColor, int productId) {
		List<String> result = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT `size` FROM `product_details` WHERE `color` = ? AND `product_id` = ?	";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pColor);
			pst.setInt(2, productId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				String size = rs.getString("size");
				
				result.add(size);
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public List<String> selectDistinctSize(Product product) {
		List<String> result = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT `size` FROM `product_details` WHERE `product_id` = ?	";
			PreparedStatement pst = c.prepareStatement(sql);

			pst.setInt(1, product.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				String size = rs.getString("size");
				
				result.add(size);
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public List<String> selectDistinctColorBySize(String pSize, int pId) {
		List<String> result = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT `color` FROM `product_details` WHERE `size` = ? AND product_id = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, pSize);
			pst.setInt(2, pId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				String size = rs.getString("color");
				
				result.add(size);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<String> selectDistinctColor(Product product) {
		List<String> result = new ArrayList<String>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT DISTINCT `color` FROM `product_details` WHERE product_id = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, product.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				String size = rs.getString("color");
				
				result.add(size);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public ProductDetail selectOne(Product product) {
		ProductDetail result = new ProductDetail();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product_details` WHERE product_id = ? LIMIT 1";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, product.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				String size = rs.getString("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				String color = rs.getString("color");
				ProductDetail pd = new ProductDetail(id, ProductDAO.getInstance().selectById(productId), size, quantity, createdAt, color);
				result = pd;
				System.out.print(pd.toString() + "    -----");
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
