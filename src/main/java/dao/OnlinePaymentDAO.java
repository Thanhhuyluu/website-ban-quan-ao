package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.OnlinePayment;

public class OnlinePaymentDAO implements DAOInterface<OnlinePayment>{
	private static OnlinePaymentDAO instance;
	public static OnlinePaymentDAO getInstance() {
		if(instance == null) {
			instance = new OnlinePaymentDAO();
		}
		return instance;
	}
	@Override
	public int insert(OnlinePayment t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `online_payment`(`order_id`, `amount`, `transaction_no`, `transaction_date`, `created_by`, `refunded`) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getOrder().getId());
			pst.setInt(2, t.getAmount());
			pst.setString(3, t.getTransactionNo());
			pst.setTimestamp(4, java.sql.Timestamp.valueOf(t.getTransactionDate()));
			pst.setString(5, t.getCreatedBy());
			pst.setBoolean(6, t.isRefunded());
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
	public int update(OnlinePayment t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `online_payment` SET `order_id`= ? ,`amount`= ? ,`transaction_no`= ? ,`transaction_date`= ? ,`created_by`= ? ,`refunded`= ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getOrder().getId());
			pst.setInt(2, t.getAmount());
			pst.setString(3, t.getTransactionNo());
			pst.setTimestamp(4, java.sql.Timestamp.valueOf(t.getTransactionDate()));
			pst.setString(5, t.getCreatedBy());
			pst.setBoolean(6, t.isRefunded());
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
	public int delete(OnlinePayment t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `online_payment` "
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getId());
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
	public List<OnlinePayment> selectAll() {
		List<OnlinePayment> ketqua = new ArrayList<OnlinePayment>();
		OrderDAO orderDao = OrderDAO.getInstance();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `online_payment` ";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int amount = rs.getInt("amount");
				String transactionNo = rs.getString("transaction_no");
				Timestamp transactionDate_raw = rs.getTimestamp("transaction_date");
				String createdBy = rs.getString("created_by");
				boolean refunded = rs.getBoolean("refunded");
				OnlinePayment onlPayment = new OnlinePayment(id, orderDao.selectById(orderId),amount,transactionNo,transactionDate_raw.toLocalDateTime(),createdBy,refunded);
				
				ketqua.add(onlPayment);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	@Override
	public OnlinePayment selectById(int id) {
		OnlinePayment ketqua = null;

		OrderDAO orderDao = OrderDAO.getInstance();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `online_payment` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int amount = rs.getInt("amount");
				String transactionNo = rs.getString("transaction_no");
				Timestamp transactionDate_raw = rs.getTimestamp("transaction_date");
				String createdBy = rs.getString("created_by");
				boolean refunded = rs.getBoolean("refunded");
				
				ketqua  = new OnlinePayment(Id, orderDao.selectById(orderId),amount,transactionNo,transactionDate_raw.toLocalDateTime(),createdBy,refunded);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<OnlinePayment> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
