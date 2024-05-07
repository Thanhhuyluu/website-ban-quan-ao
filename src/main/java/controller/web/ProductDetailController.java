package controller.web;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImageDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import model.Image;
import model.Product;
import model.ProductDetail;
import service.ProductDetailManager;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet(urlPatterns = { "/chi-tiet-san-pham" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String proId = request.getParameter("proId");
		if( request.getAttribute("proId")!= null) {
			proId = (String)request.getAttribute("proId");
			
		}
		if (proId != null) {
			int pId = Integer.parseInt(proId);
			String selectedSize = request.getParameter("selectedSize");
			String selectedColor = request.getParameter("selectedColor");

			List<ProductDetail> PDlist = ProductDetailDAO.getInstance().selectByProductId(pId);
			List<String> sizeList = ProductDetailManager.getInstance().getDistinctSize(PDlist);
			List<String> colorList = ProductDetailManager.getInstance().getDistinctColor(PDlist);
			
			if ((selectedColor == "" && colorList.size() > 0) || (selectedColor == null && colorList.size() > 0)) {
				selectedColor = colorList.get(0);
			}

			List<String> availableSizes = ProductDetailDAO.getInstance().selectDistinctSizeByColor(selectedColor,pId);
			if ((selectedSize == "" && availableSizes.size() > 0) || (selectedSize == null && availableSizes.size() > 0)) {
				selectedSize = availableSizes.get(0);
			}
			List<String> availableColors = ProductDetailDAO.getInstance().selectDistinctColorBySize(selectedSize,pId);

			Map<String, Boolean> colorMap = new HashMap<String, Boolean>();
			for (String color : colorList) {
				colorMap.put(color, availableColors.contains(color));
			}
			
			int productCurrentQuantity = ProductDetailManager.getInstance().getCurrentProductQuantity(PDlist,
					selectedColor, selectedSize);

			Product product = ProductDAO.getInstance().selectById(pId);
			ProductDetail productDetail = ProductDetailManager.getInstance().getProductDetailByProductIdAndSizeAndColor(pId, selectedSize, selectedColor, PDlist);
			double price = product.getPrice();
		    double discount = product.getDiscount();

		     
		    double discountPrice = price - (price * discount / 100);
		    DecimalFormat formatter = new DecimalFormat("#,###");
	        String formattedPrice = formatter.format(discountPrice);
			List<Image> proImgList = ImageDAO.getInstance().selectByProductId(pId);

			List<Product> relatedProducts = ProductDAO.getInstance().selectRelatedProductsByBrand(product.getBrand().getId(), product.getId());
			request.setAttribute("formattedPrice", formattedPrice);
			request.setAttribute("relatedProducts", relatedProducts);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("selectedSize", selectedSize);
			request.setAttribute("selectedColor", selectedColor);
			request.setAttribute("sizeList", sizeList);
			request.setAttribute("colorList", colorList);

			request.setAttribute("productCurrentQuantity", productCurrentQuantity);
			request.setAttribute("availableSizes", availableSizes);
			request.setAttribute("product", product);
			request.setAttribute("productDetail", productDetail);
			request.setAttribute("imgList", proImgList);
			request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);

		}

		request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
