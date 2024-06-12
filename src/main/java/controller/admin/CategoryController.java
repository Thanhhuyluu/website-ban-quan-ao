package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.UserDAO;
import model.Category;
import model.User;


@WebServlet(urlPatterns = {"/admin-category", "/admin-category-edit", "/admin-category-new", "/admin-category-insert", "/admin-category-delete", "/admin-category-update" })
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 private CategoryDAO categoryDAO = new CategoryDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		System.out.println(action);
        try {
            switch (action) {
            case "/admin-category-new":
                showNewForm(req, resp);
                break;
            case "/admin-category-insert":
                insertCategory(req, resp);
                break;
            case "/admin-category-delete":
                deleteCategory(req, resp);
                break;
            case "/admin-category-edit":
                showEditForm(req, resp);
                break;
            case "/admin-category-update":
                updateCategory(req, resp);
                break;
            default:
                listCategory(req, resp);
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
	
	private void listCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		String searchKey = request.getParameter("txtSearch");
		String indexPage = request.getParameter("index");
		if(indexPage == null) {
			indexPage = "1";
		}
		int index = Integer.parseInt(indexPage);
		int count = CategoryDAO.getInstance().getCountTotal();
		int endPage = count/5;
		if(count % 5 != 0) {
			endPage++;
		}
		
		List<Category> categories = CategoryDAO.getInstance().pagingAcount(index);
		if(searchKey != null) {
			categories = CategoryDAO.getInstance().searchByKey(categories, searchKey);
		}
		request.setAttribute("categories", categories);
		request.setAttribute("endPage", endPage);
		request.setAttribute("tag", index);
		request.setAttribute("txtSearch", searchKey);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/category/categoryList.jsp");
	    dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/category/categoryAdd.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category existingCategory = categoryDAO.selectById(id);
        request.setAttribute("category", existingCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/category/categoryEdit.jsp");
        dispatcher.forward(request, response);
 
    }
 
    private void insertCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int type = Integer.parseInt(request.getParameter("type"));
        
        Category newCategory = new Category(name, type);
        categoryDAO.insert(newCategory);
        response.sendRedirect("admin-category");
    }
 
    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int type = Integer.parseInt(request.getParameter("type"));
        Category category = new Category(id, name, type);
        categoryDAO.update(category);
        response.sendRedirect("admin-category");
    }
 
    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Category category = new Category();
        category.setId(id);
        categoryDAO.delete(category);
        response.sendRedirect("admin-category");
 
    }
}
