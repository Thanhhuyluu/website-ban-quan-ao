package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet(urlPatterns = {"/admin-product", "/admin-product-new", "/admin-product-insert", "/admin-product-delete", "/admin-product-edit", "/admin-product-update" })
public class ProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	

	
	 private ProductDAO productDAO = new ProductDAO();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub

//			ProductDAO productDAO = new ProductDAO();
//			CategoryDAO categoryDAO = new CategoryDAO();
//			List<Product> products = productDAO.selectAll();
//			List<Category> categories = new ArrayList<Category>();
//			Category category = new Category(); 
//			for (Product product : products) {
//				category = categoryDAO.selectById(product.getCategoryId());
//				categories.add(category);
//			}
//			req.setAttribute("products", products);
//			req.setAttribute("categories", categories);
//			RequestDispatcher rq = req.getRequestDispatcher("/views/admin/product/productList.jsp");
//			rq.forward(req, resp);
			String action = req.getServletPath();
			System.out.println(action);
	        try {
	            switch (action) {
	            case "/admin-product-new":
	                showNewForm(req, resp);
	                break;
	            case "/admin-product-insert":
//	                insertProduct(req, resp);
	                break;
	            case "/admin-product-delete":
	                deleteProduct(req, resp);
	                break;
	            case "/admin-product-edit":
	                showEditForm(req, resp);
	                break;
	            case "/admin-product-update":
//	                updateProduct(req, resp);
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
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productAdd.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Product existingProduct = productDAO.selectById(id);
	        request.setAttribute("product", existingProduct);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productEdit.jsp");
	        dispatcher.forward(request, response);
	 
	    }
	 
//	    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
//	            throws SQLException, IOException {
//	        String name = request.getParameter("name");
//	        int type = Integer.parseInt(request.getParameter("type"));
//	        
//	        Product newCategory = new Product(name, type);
//	        categoryDAO.insert(newCategory);
//	        response.sendRedirect("admin-category");
//	    }
	 
//	    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
//	            throws SQLException, IOException {
//	        int id = Integer.parseInt(request.getParameter("id"));
//	        String name = request.getParameter("name");
//	        int type = Integer.parseInt(request.getParameter("type"));
//	        Category category = new Category(id, name, type);
//	        categoryDAO.update(category);
//	        response.sendRedirect("admin-category");
//	    }
	 
	    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	 
	        Product product = new Product();
	        product.setId(id);
	        productDAO.delete(product);
	        response.sendRedirect("admin-category");
	 
	    }
}
