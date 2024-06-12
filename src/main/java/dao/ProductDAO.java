package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Product;

public class ProductDAO implements DAOInterface<Product> {
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	
	@Override
	public int insert(Product t) {
		int result = 0;
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
	public int update(Product t) {
		int result = 0;
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
	public int delete(Product product) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql ="DELETE FROM `product` "
					+ " WHERE `id` = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, product.getId());
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
	public List<Product> selectAll() {
		List<Product> result = new ArrayList<Product>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product` ";
			PreparedStatement pst = c.prepareStatement(sql);
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
				Product p = new Product(id,CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int getCountTotal() {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select count(*) from product where product.deleted = 0";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Product> pagingAcount(int index) {
		List<Product> result = new ArrayList<Product>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select * from product where deleted = 0 limit ?, 8;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1,(index-1)*5);
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
				Product p = new Product(id,CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Product> selectAllAvailabilities() {
		List<Product> result = new ArrayList<Product>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM `product` WHERE `deleted` = 0";
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
				Product p = new Product(id,CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Product selectById(int id) {
		Product result = null;
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
				result =  new Product(Id,CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
	
	public List<Product> searchByName(String productName) {
	    List<Product> result = new ArrayList<>();
	    Connection c = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    try {
	        c = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM `product` WHERE title LIKE ?";
	        pst = c.prepareStatement(sql);
	        pst.setString(1, "%" + productName + "%"); // Tìm kiếm sản phẩm có tên chứa productName
	        rs = pst.executeQuery();
	        while (rs.next()) {
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
	            int likes = rs.getInt("likes");
	            Product p = new Product(id, CategoryDAO.getInstance().selectById(categoryId),
	                    BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId),
	                    title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
	            result.add(p);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pst != null) {
	            try {
	                pst.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        JDBCUtil.closeConnection(c);
	    }
	    return result;
	}

	public List<Product> searchByKey(List<Product> li, String key){
		List<Product> result = new ArrayList<Product>();
		for(Product product : li) {
			if(product.getTitle().toLowerCase().contains(key.toLowerCase())) {
				result.add(product);
			}
		}
		return result;
	}
	
	public List<Product> selectByCateId(int[] arr) {
		List<Product> result = new ArrayList<Product>();
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
				Product p = new Product(id, CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	

	public List<Product> selectRelatedProductsByBrand(int bId, int pId) {
		List<Product> result = new ArrayList<Product>();
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
				Product p = new Product(id, CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Product> selectDistinctCate() {
		List<Product> result = new ArrayList<Product>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT *\n"
					+ "FROM `product`\n"
					+ "WHERE id IN (\n"
					+ "    SELECT MIN(id)\n"
					+ "    FROM `product`\n"
					+ "    GROUP BY category_id\n"
					+ ")";
			PreparedStatement pst = c.prepareStatement(sql);
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
				Product p = new Product(id,CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Product> selectDistinctBrand() {
		List<Product> result = new ArrayList<Product>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT *\n"
					+ "FROM product\n"
					+ "WHERE id IN (\n"
					+ "    SELECT MIN(id)\n"
					+ "    FROM product\n"
					+ "    GROUP BY brand_id\n"
					+ ")";
			PreparedStatement pst = c.prepareStatement(sql);
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
				Product p = new Product(id, CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Product> selectDistinctSupplier() {
		List<Product> result = new ArrayList<Product>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT *\n"
					+ "FROM product\n"
					+ "WHERE id IN (\n"
					+ "    SELECT MIN(id)\n"
					+ "    FROM product\n"
					+ "    GROUP BY supplier_id\n"
					+ ")";
			PreparedStatement pst = c.prepareStatement(sql);
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
				Product p = new Product(id, CategoryDAO.getInstance().selectById(categoryId), BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
				
				result.add(p);
				
			}
			JDBCUtil.closeConnection(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	

}