package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.ImageDAO;
import dal.ProductDAO;
import dal.ProductDetailDAO;
import model.Image;
import model.Product;
import model.ProductDetail;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet(urlPatterns = {"/chi-tiet-san-pham"})
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proId =  request.getParameter("proId");
		if(proId!= null) {
			int pId = Integer.parseInt(proId);
			Product product = ProductDAO.getInstance().selectById(pId);
			List<ProductDetail> list = null;
			list = ProductDetailDAO.getInstance().selectByProductId(pId);	
			List<Image> proImgList = ImageDAO.getInstance().selectByProductId(pId);
			request.setAttribute("productDetail", list);
			request.setAttribute("product", product);
			request.setAttribute("imgList", proImgList);
			request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
			
		}
		
		
		
		request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
