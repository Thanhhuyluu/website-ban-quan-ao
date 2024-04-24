package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Supplier;

public class SupplierDAO implements DAOInterface<Supplier>{
	
	public static SupplierDAO getInstance() {
		return new SupplierDAO();
	}
	@Override
	public int insert(Supplier t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `supplier`(`name`, `email`, `phone_num`, `address`) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setString(2, t.getEmail());
			pst.setString(3, t.getPhoneNum());
			pst.setString(4, t.getAddress());
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
	public int update(Supplier t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `supplier` "
					+ "SET `name`= ?,`email`=?,`phone_num`=?,`address`= ? WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setString(2, t.getEmail());
			pst.setString(3, t.getPhoneNum());
			pst.setString(4, t.getAddress());
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
			String sql ="DELETE FROM `supplier`"
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
	public List<Supplier> selectAll() {
		List<Supplier> ketqua = new ArrayList<Supplier>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `supplier` ";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_num");
				String address= rs.getString("address");
				Supplier sup = new Supplier(id, name, email, phoneNum, address);
				ketqua.add(sup);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	@Override
	public Supplier selectById(int id) {
		Supplier ketqua = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `supplier` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phoneNum = rs.getString("phone_num");
				String address= rs.getString("address");
				ketqua = new Supplier(Id, name, email, phoneNum, address);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<Supplier> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
