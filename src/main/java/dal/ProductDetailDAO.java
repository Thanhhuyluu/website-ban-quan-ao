package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductDetail;

public class ProductDetailDAO implements DAOInterface<ProductDetail>{
	
	public static ProductDetailDAO getInstance() {
		return new ProductDetailDAO();
	}
	
	@Override
	public int insert(ProductDetail t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `product_details`(`product_id`, `size`, `quantity`, `created_at`, `color`, `img`)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProductId());
			pst.setInt(2, t.getSize());
			pst.setInt(3, t.getQuantity());
			pst.setDate(4, t.getCreatedAt());
			pst.setInt(5, t.getColor());
			pst.setString(6, t.getImg());
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
			String sql = "UPDATE `product_details` SET `product_id`= ? ,`size`= ? ,`quantity`= ? ,`created_at`= ? ,`color`= ? ,`img`=? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getProductId());
			pst.setInt(2, t.getSize());
			pst.setInt(3, t.getQuantity());
			pst.setDate(4, t.getCreatedAt());
			pst.setInt(5, t.getColor());
			pst.setString(6, t.getImg());
			pst.setInt(7, t.getId());
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
			String sql ="DELETE FROM `product_details`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, id);
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
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				int size = rs.getInt("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				int color = rs.getInt("color");
				String img = rs.getString("img");
				ProductDetail pd = new ProductDetail(id, productId, size, quantity, createdAt, color, img);
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
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int productId = rs.getInt("product_id");
				int size = rs.getInt("size");
				int quantity = rs.getInt("quantity");
				Date createdAt = rs.getDate("created_at");
				int color = rs.getInt("color");
				String img = rs.getString("img");
				ketqua = new ProductDetail(Id, productId, size, quantity, createdAt, color, img);
				
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

}
