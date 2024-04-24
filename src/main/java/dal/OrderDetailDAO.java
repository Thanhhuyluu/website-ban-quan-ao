package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderDetail;

public class OrderDetailDAO implements DAOInterface<OrderDetail> {

	public static OrderDetailDAO getInstance() {
		return new OrderDetailDAO();
	}

	@Override
	public int insert(OrderDetail t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `order_details`(`order_id`, `product_details_id`, `price`, `num`) VALUES ( ? , ? , ? , ?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getOrderId());
			pst.setInt(2, t.getProductDetailId());
			pst.setInt(3, t.getPrice());
			pst.setInt(4, t.getNum());
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
	public int update(OrderDetail t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `order_details` SET `order_id`= ? ,`product_details_id`= ? ,`price`= ? ,`num`= ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getOrderId());
			pst.setInt(2, t.getProductDetailId());
			pst.setInt(3, t.getPrice());
			pst.setInt(4, t.getNum());
			pst.setInt(5, t.getId());
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
			String sql ="DELETE FROM `order_details`"
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
	public List<OrderDetail> selectAll() {
		List<OrderDetail> ketqua = new ArrayList<OrderDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order_details` ";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int productDetailId = rs.getInt("product_details_id");
				int price = rs.getInt("price");
				int num = rs.getInt("num");
				OrderDetail od = new OrderDetail(id, orderId, productDetailId, price, num);
				ketqua.add(od);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	@Override
	public OrderDetail selectById(int id) {
		OrderDetail ketqua = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `supplier` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int productDetailId = rs.getInt("product_details_id");
				int price = rs.getInt("price");
				int num = rs.getInt("num");
				ketqua = new OrderDetail(Id, orderId, productDetailId, price, num);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<OrderDetail> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
