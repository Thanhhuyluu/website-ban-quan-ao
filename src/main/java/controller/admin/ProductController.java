package controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SupplierDAO;
import model.Brand;
import model.Category;
import model.Product;
import model.Supplier;

@WebServlet(urlPatterns = {"/admin-product", "/admin-product-new", "/admin-product-insert", "/admin-product-delete", "/admin-product-edit", "/admin-product-update" })
public class ProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	

	
	 private ProductDAO productDAO = new ProductDAO();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub

			String action = req.getServletPath();
			req.setCharacterEncoding("UTF-8");
			System.out.println(action);
	        try {
	            switch (action) {
	            case "/admin-product-new":
	                showNewForm(req, resp);
	                break;
	            case "/admin-product-insert":
	                insertProduct(req, resp);
	                break;
	            case "/admin-product-delete":
	                deleteProduct(req, resp);
	                break;
	            case "/admin-product-edit":
	                showEditForm(req, resp);
	                break;
	            case "/admin-product-update":
	                updateProduct(req, resp);
	                break;
	            default:
	                listProduct(req, resp);
	                break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, resp);
		}
		
		private void listProduct(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        List<Product> products = productDAO.selectAll();    
	        request.setAttribute("products", products);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productList.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	CategoryDAO categoryDAO = new CategoryDAO(); 
	    	BrandDAO brandDAO = new BrandDAO(); 
	    	SupplierDAO supplierDAO = new SupplierDAO(); 
	    	List<Category> categories = categoryDAO.selectAll();
	    	List<Brand> brands = brandDAO.selectAll();
	    	List<Supplier> suppliers = supplierDAO.selectAll();
	    	request.setAttribute("categories", categories);
	    	request.setAttribute("brands", brands);
	    	request.setAttribute("suppliers", suppliers);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productAdd.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        CategoryDAO categoryDAO = new CategoryDAO(); 
	    	BrandDAO brandDAO = new BrandDAO(); 
	    	SupplierDAO supplierDAO = new SupplierDAO(); 
	    	Product existingProduct = productDAO.selectById(id);
	    	List<Category> categories = categoryDAO.selectAll();
	    	List<Brand> brands = brandDAO.selectAll();
	    	List<Supplier> suppliers = supplierDAO.selectAll();
	        request.setAttribute("categories", categories);
	    	request.setAttribute("brands", brands);
	    	request.setAttribute("suppliers", suppliers);
	        request.setAttribute("product", existingProduct);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productEdit.jsp");
	        dispatcher.forward(request, response);
	 
	    }
	 
	    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	    	CategoryDAO categoryDAO = new CategoryDAO();
	    	BrandDAO brandDAO = new BrandDAO();
	    	SupplierDAO supplierDAO = new SupplierDAO();
	        String title = request.getParameter("title");
	        int category_id = Integer.parseInt(request.getParameter("category_id"));
	        int brand_id = Integer.parseInt(request.getParameter("brand_id"));
	        int supplier_id = Integer.parseInt(request.getParameter("supplier_id"));
	        int price = Integer.parseInt(request.getParameter("price"));
	        int discount = Integer.parseInt(request.getParameter("discount"));
	        String img = request.getParameter("img");
	        int gender = Integer.parseInt(request.getParameter("gender"));
	        String description = request.getParameter("description");
	        Date createdAt = new Date(System.currentTimeMillis());
	        Date updatedAt = null;
	        int likes = 0; 
	        boolean deleted = false;
	        
	        Product newProduct = new Product(categoryDAO.selectById(category_id), brandDAO.selectById(brand_id), supplierDAO.selectById(supplier_id), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
	        productDAO.insert(newProduct);
	        response.sendRedirect("admin-product");
	    }
	 
	    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    CategoryDAO categoryDAO = new CategoryDAO();
	    	BrandDAO brandDAO = new BrandDAO();
	    	SupplierDAO supplierDAO = new SupplierDAO();
	    	int id = Integer.parseInt(request.getParameter("id"));
	        String title = request.getParameter("title");
	        int category_id = Integer.parseInt(request.getParameter("category_id"));
	        int brand_id = Integer.parseInt(request.getParameter("brand_id"));
	        int supplier_id = Integer.parseInt(request.getParameter("supplier_id"));
	        int price = Integer.parseInt(request.getParameter("price"));
	        int discount = Integer.parseInt(request.getParameter("discount"));
	        String img = request.getParameter("img");
	        int gender = Integer.parseInt(request.getParameter("gender"));
	        String description = request.getParameter("description");
	        Date createdAt = Date.valueOf(request.getParameter("createdAt"));
	        Date updatedAt = new Date(System.currentTimeMillis());
	        int likes = Integer.parseInt(request.getParameter("likes"));
	        boolean deleted = false;
	        
	        Product updatedProduct = new Product(id, categoryDAO.selectById(category_id), brandDAO.selectById(brand_id), supplierDAO.selectById(supplier_id), title, price, discount, img, description, createdAt, updatedAt, deleted, gender, likes);
	        productDAO.update(updatedProduct);    
			response.sendRedirect("admin-product");
		
	    }
	 
	    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        
	        Product product = productDAO.selectById(id);
	        productDAO.delete(product);
	        response.sendRedirect("admin-product");
	 
	    }
}
