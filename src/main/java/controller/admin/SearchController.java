package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;
import model.ProductItem;
import service.ProductManager;

@WebServlet(urlPatterns = { "/admin-searchProduct" })
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtSearch = req.getParameter("txt");
		List<Product> productList = ProductDAO.getInstance().searchByName(txtSearch);
		List<Product> products = new ArrayList<>();
		for (Product product : productList) {
			if (!product.isDeleted()) {
				products.add(product);
			}
		}
		List<ProductItem> lProductItems = ProductManager.getInstance().products2ProductItems(products);

		// Xác định PrintWriter từ HttpServletResponse
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<div class=\"classify__body\">\n"
					+ "				<table class=\"table\">\n"
					+ "					<thead>\n"
					+ "						<tr>\n"
					+ "							<th>Tên</th>\n"
					+ "							<th>Giá</th>\n"
					+ "							<th>Ảnh</th>\n"
					+ "							<th>Giới tính</th>\n"
					+ "							<th>Ngày tạo</th>\n"
					+ "							<th>Thao tác</th>\n"
					+ "							<th></th>\n"
					+ "						</tr>\n"
					+ "					</thead>\n"
					+ "	\n"
					+ "					<tbody>");
			for (ProductItem lProductItem : lProductItems) {
				Product product = lProductItem.getProduct();
				List<String> sizesOfProduct = lProductItem.getSizesOfProduct();
				List<String> colorsOfProduct = lProductItem.getColorsOfProduct();
				int countProductDetail = lProductItem.getCountProductDetail();

				out.println("<tr class=\"product-item__row\">");
				out.println("<td>" + product.getTitle() + "</td>");
				out.println("<td>" + product.getPrice() + " VNĐ</td>");
				out.println("<td><img style=\"height: 50px; width: 50px;\" src=\"" + req.getContextPath() + "/imgs/"
						+ product.getImg() + "\" alt=\"\"></td>");
				out.println(
						"<td>" + (product.getGender() == 1 ? "Nam" : (product.getGender() == 2 ? "Nữ" : "Cả nam và nữ"))
								+ "</td>");
				out.println("<td>" + product.getCreatedAt() + "</td>");
				out.println("<td>");
				out.println("<a class=\"active-link\" href=\"/Online_Shop/admin-product-edit?id=" + product.getId()
						+ "\">");
				out.println(
						"<i class=\"fa-solid fa-pen-to-square\" style=\"text-align: center;\" title=\"Chỉnh sửa thông tin sản phẩm \"></i>");
				out.println("</a>");
				out.println("<a class=\"active-link product-delete-btn\" data-bs-id=\"" + product.getId() + "\">");
				out.println(
						"<i class=\"fa-solid fa-trash-can\" style=\"text-align: center;\" title=\"Xoá sản phẩm \"></i>");
				out.println("</a>");
				out.println("<a class=\"active-link btn-detail-product\" id=\"btn-detail-product\">");
				out.println(
						"<i class=\"fa-solid fa-magnifying-glass\" style=\"text-align: center;\" title=\"Xem chi tiết sản phẩm \"></i>");
				out.println("</a>");
				out.println("</td>");
				out.println("<td id=\"modal_detail_product\" class=\"modal modal-detail-product\">");
				out.println("<div class=\"modal__content\" id=\"product-details\">");
				out.println("<div class=\"modal__header\">");
				out.println("<i class=\"fa-solid fa-xmark icon-close\"></i>");
				out.println("<div class=\"product-detail__img\" style=\"background-image: url(" + req.getContextPath()
						+ "/imgs/" + product.getImg() + ")\"></div>");
				out.println("<div class=\"product-detail__title\">" + product.getTitle() + "</div>");
				out.println("<div class=\"product-detail__category\">Loại áo: "
						+ (product.getGender() == 1 ? "Nam" : (product.getGender() == 2 ? "Nữ" : "Cả nam và nữ"))
						+ "</div>");
				out.println("</div>");
				out.println("<div class=\"modal__main\">");
				out.println("<div class=\"select-size-color\">");
				out.println("<div class=\"select-item__size\">");
				out.println("<label class=\"select-size_title\" for=\"select-size\">Size:</label>");
				out.println("<select name=\"select-size\" id=\"select-size\">");
				for (String size : sizesOfProduct) {
					out.println("<option value=\"" + size + "\">" + size + "</option>");
				}
				out.println("</select>");
				out.println("</div>");
				out.println("<div class=\"select-item__color\">");
				out.println("<label for=\"colorPicker\" class=\"color__title\">Màu: </label>");
				out.println("<div class=\"square colorPicker\"></div>");
				out.println("<div class=\"color-options colorOptions\">");
				for (String color : colorsOfProduct) {
					out.println("<div class=\"color-option\" style=\"background-color: #" + color + "\"></div>");
				}
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"detail-product-count-fav\">");
				out.println("<div class=\"detail-product-status-count\">Trong kho còn " + countProductDetail
						+ " sản phẩm</div>");
				out.println("<div class=\"detail-product-status-fav\">Luợt thích: " + product.getLikes() + "</div>");
				out.println("</div>");
				out.println("<div class=\"detail-product-descript\">");
				out.println("<div class=\"descript-title\">Mô tả</div>");
				out.println("<div class=\"descript-content\">" + product.getDescription() + "</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"modal__footer\">");
				out.println("<div class=\"modal__footer-btn-wrapper\">");
				out.println("<a href=\"/Online_Shop/admin-productDetail-add?id=" + product.getId()
						+ "\" class=\"add-productDetail-btn\">Thêm sản phẩm</a>");
				out.println("<button class=\"close\">Thoát</button>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</tbody>\n"
					+ "				</table>\n"
					+ "			</div>");
			out.println("<div class=\"modal-alert\">\n"
					+ "			<div class=\"modal-alert__inner\">\n"
					+ "				<div class=\"alert__header\">\n"
					+ "					<div class=\"alert__header-title\">Cảnh báo</div>\n"
					+ "					<i class=\"fa-solid fa-xmark icon-close\" id=\"icon-close\"></i>\n"
					+ "				</div>\n"
					+ "				<div class=\"alert__content\">\n"
					+ "					<p class=\"alert__content-text\">Bạn có chắc chắn muốn xoá không ?</p>\n"
					+ "				</div>\n"
					+ "				<div class=\"alert__footer\">\n"
					+ "					<button class=\"alert__footer-btn \" id=\"delete-item-modal\">Có</button>\n"
					+ "					<button class=\"alert__footer-btn \" id=\"exit-delete-modal\">Thoát</button>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "		</div>\n"
					+ "		\n"
					+ "		<form name=\"form-product-delete\" method=\"POST\"></form>");
			out.close();
		} catch (Exception e) {

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
