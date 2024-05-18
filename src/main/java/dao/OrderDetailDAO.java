package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductDetail;

public class OrderDetailDAO implements DAOInterface<OrderDetail> {
	private static OrderDetailDAO orderDetailDao;
	public static OrderDetailDAO getInstance() {
		if(orderDetailDao == null) {
			orderDetailDao = new OrderDetailDAO();
		}
		return orderDetailDao;
	}
	
	@Override
	public int insert(OrderDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `order_details`(`order_id`, `product_details_id`, `price`, `quantity`) VALUES ( ? , ? , ? , ?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getOrder().getId());
			pst.setInt(2, t.getProductDetail().getId());
			pst.setInt(3, t.getPrice());
			pst.setInt(4, t.getQuantity());
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
	public int update(OrderDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `order_details` SET `order_id`= ? ,`product_details_id`= ? ,`price`= ? ,`quantity`= ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getOrder().getId());
			pst.setInt(2, t.getProductDetail().getId());
			pst.setInt(3, t.getPrice());
			pst.setInt(4, t.getQuantity());
			pst.setInt(5, t.getId());
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
	public int delete(OrderDetail orderDetail) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `order_details`"
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, orderDetail.getId());
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
	public List<OrderDetail> selectAll() {
		List<OrderDetail> result = new ArrayList<OrderDetail>();
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
				int quantity = rs.getInt("quantity");
				OrderDetail od = new OrderDetail(id, OrderDAO.getInstance().selectById(orderId), ProductDetailDAO.getInstance().selectById(productDetailId), price, quantity);
				result.add(od);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<OrderDetail> selectByOrderId(Order order) {
		List<OrderDetail> result = new ArrayList<OrderDetail>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order_details` WHERE `order_id` = ?";
			pst = c.prepareStatement(sql);
			pst.setInt(1, order.getId());
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int productDetailId = rs.getInt("product_details_id");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				OrderDetail od = new OrderDetail(id, OrderDAO.getInstance().selectById(orderId), ProductDetailDAO.getInstance().selectById(productDetailId), price, quantity);
				result.add(od);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if( pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(c != null ) {
				JDBCUtil.closeConnection(c);
			}
		}
		return result;
	}
	public List<OrderDetail> selectByProductDetailId(ProductDetail productDetail) {
		List<OrderDetail> result = new ArrayList<OrderDetail>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order_details` WHERE `product_details_id` = ?";
			pst = c.prepareStatement(sql);
			pst.setInt(1, productDetail.getId());
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int productDetailId = rs.getInt("product_details_id");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				OrderDetail od = new OrderDetail(id, OrderDAO.getInstance().selectById(orderId), ProductDetailDAO.getInstance().selectById(productDetailId), price, quantity);
				result.add(od);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if( pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(c != null ) {
				JDBCUtil.closeConnection(c);
			}
		}
		return result;
	}
	
	public int getPriceByOrderId(Order order) {
		int result = 0; 
		List<Integer> prices = new ArrayList<Integer>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			c = JDBCUtil.getConnection();
			String sql = "SELECT price FROM `order_details` WHERE `order_id` = ?";
			pst = c.prepareStatement(sql);
			pst.setInt(1, order.getId());
			rs = pst.executeQuery();
			while(rs.next()) {
				int price = rs.getInt("price");
				prices.add(price);
			}
			for (Integer integer : prices) {
				result += integer;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if( pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(c != null ) {
				JDBCUtil.closeConnection(c);
			}
		}
		return result;
	}
	@Override
	public OrderDetail selectById(int id) {
		OrderDetail result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `order_details` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int productDetailId = rs.getInt("product_details_id");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				result = new OrderDetail(Id, OrderDAO.getInstance().selectById(orderId), ProductDetailDAO.getInstance().selectById(productDetailId), price, quantity);
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<OrderDetail> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
