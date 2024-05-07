package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO implements DAOInterface<Product> {

	private CategoryDAO categoryDAO;
	private BrandDAO brandDAO;
	private SupplierDAO supplierDAO;
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	@Override
	public int insert(Product t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "INSERT INTO `product`(`category_id`, `brand_id`, `supplier_id`, `title`, `price`, `discount`, `img`, `description`, `created_at`, `updated_at`, `deleted`, `gender`, `likes`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getCategory().getId());
			pst.setInt(2, t.getBrand().getId());
			pst.setInt(3, t.getSupplier().getId());
			pst.setString(4, t.getTitle());
			pst.setInt(5, t.getPrice());
			pst.setInt(6, t.getDiscount());
			pst.setString(7, t.getImg());
			pst.setString(8, t.getDescription());
			pst.setDate(9, t.getCreatedAt());
			pst.setDate(10, t.getUpdatedAt());
			pst.setBoolean(11, t.isDeleted());
			pst.setInt(12, t.getGender());
			pst.setInt(13, t.getLikes());
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
	public int update(Product t) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "UPDATE `product` SET `category_id`= ? ,`brand_id`= ? ,`supplier_id`= ? ,`title`= ? ,`price`= ? ,`discount`= ? ,`img`= ? ,`description`= ? ,`created_at`= ? ,`updated_at`= ? ,`deleted`= ? ,`gender`= ?, `likes` = ?  WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, t.getCategory().getId());
			pst.setInt(2, t.getBrand().getId());
			pst.setInt(3, t.getSupplier().getId());
			pst.setString(4, t.getTitle());
			pst.setInt(5, t.getPrice());
			pst.setInt(6, t.getDiscount());
			pst.setString(7, t.getImg());
			pst.setString(8, t.getDescription());
			pst.setDate(9, t.getCreatedAt());
			pst.setDate(10, t.getUpdatedAt());
			pst.setBoolean(11, t.isDeleted());
			pst.setInt(12, t.getGender());
			pst.setInt(13, t.getLikes());
			pst.setInt(14, t.getId());
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
	public int delete(Product product) {
		int ketqua = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `product` "
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, product.getId());
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
	public List<Product> selectAll() {
		List<Product> ketqua = new ArrayList<Product>();
		categoryDAO = new CategoryDAO();
		brandDAO = new BrandDAO();
		supplierDAO = new SupplierDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product` ";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				int brandId = rs.getInt("brand_id");
				int supplierId = rs.getInt("supplier_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				String img = rs.getString("img");
				String description = rs.getString("description");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				boolean deleted = rs.getBoolean("deleted");
				int gender = rs.getInt("gender");
				int likes  = rs.getInt("likes");
				Product p = new Product(id, categoryDAO.selectById(categoryId), brandDAO.selectById(brandId), supplierDAO.selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				ketqua.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	@Override
	public Product selectById(int id) {
		Product ketqua = null;
		categoryDAO = new CategoryDAO();
		brandDAO = new BrandDAO();
		supplierDAO = new SupplierDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product` WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int Id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				int brandId = rs.getInt("brand_id");
				int supplierId = rs.getInt("supplier_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				String img = rs.getString("img");
				String description = rs.getString("description");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				boolean deleted = rs.getBoolean("deleted");
				int gender = rs.getInt("gender");
				int likes = rs.getInt("likes");
				ketqua =  new Product(Id, categoryDAO.selectById(categoryId), brandDAO.selectById(brandId), supplierDAO.selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<Product> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Product> getListByPage(List<Product> list, int start, int end){
		ArrayList<Product> arr = new ArrayList<Product>();
		for(int i = start; i< end; i++) {
			arr.add(list.get(i));
		}
		return arr;
	}
	
	public List<Product> selectByCateId(int[] arr) {
		List<Product> ketqua = new ArrayList<Product>();
		categoryDAO = new CategoryDAO();
		brandDAO = new BrandDAO();
		supplierDAO = new SupplierDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT p.id, p.category_id, p.brand_id, p.supplier_id, p.title, p.price, p.discount, p.img, p.description, p.created_at, p.updated_at, p.deleted, p.gender, p.likes FROM product p INNER JOIN category on p.category_id = category.id WHERE 1=1";
			
			
			if(arr != null) {
				
			
				sql += " and p.category_id in (";
				for(int i = 0; i< arr.length; i++) {
					sql += arr[i] + ",";
				}
				if(sql.endsWith(",")) {
					sql = sql.substring(0,sql.length()-1);
				}
				sql+=");";
			}
			
			System.out.println(sql);
			
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				int brandId = rs.getInt("brand_id");
				int supplierId = rs.getInt("supplier_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				String img = rs.getString("img");
				String description = rs.getString("description");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				boolean deleted = rs.getBoolean("deleted");
				int gender = rs.getInt("gender");
				int likes  = rs.getInt("likes");
				Product p = new Product(id, categoryDAO.selectById(categoryId), brandDAO.selectById(brandId), supplierDAO.selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				ketqua.add(p);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}
	
	
	
	
	public List<Product> searchByName(String key) {
		List<Product> ketqua = new ArrayList<Product>();
		categoryDAO = new CategoryDAO();
		brandDAO = new BrandDAO();
		supplierDAO = new SupplierDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product` where title like ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1,"%" + key + "%");
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				int brandId = rs.getInt("brand_id");
				int supplierId = rs.getInt("supplier_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				String img = rs.getString("img");
				String description = rs.getString("description");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				boolean deleted = rs.getBoolean("deleted");
				int gender = rs.getInt("gender");
				int likes  = rs.getInt("likes");
				Product p = new Product(id, categoryDAO.selectById(categoryId), brandDAO.selectById(brandId), supplierDAO.selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				ketqua.add(p);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}

	public List<Product> selectRelatedProductsByBrand(int bId, int pId) {
		List<Product> ketqua = new ArrayList<Product>();
		categoryDAO = new CategoryDAO();
		brandDAO = new BrandDAO();
		supplierDAO = new SupplierDAO();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product` where brand_id = ? AND id != ? LIMIT 16 ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,bId);
			pst.setInt(2, pId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				int brandId = rs.getInt("brand_id");
				int supplierId = rs.getInt("supplier_id");
				String title = rs.getString("title");
				int price = rs.getInt("price");
				int discount = rs.getInt("discount");
				String img = rs.getString("img");
				String description = rs.getString("description");
				Date createdAt = rs.getDate("created_at");
				Date updatedAt = rs.getDate("updated_at");
				boolean deleted = rs.getBoolean("deleted");
				int gender = rs.getInt("gender");
				int likes  = rs.getInt("likes");
				Product p = new Product(id, categoryDAO.selectById(categoryId), brandDAO.selectById(brandId), supplierDAO.selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				ketqua.add(p);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ketqua;
	}
	

}
