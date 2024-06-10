package controller.admin;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ImageDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import dao.SupplierDAO;
import model.Brand;
import model.Category;
import model.Image;
import model.Product;
import model.ProductDetail;
import model.ProductItem;
import model.Supplier;
import service.ProductManager;

@WebServlet(urlPatterns = { "/admin-product", "/admin-product-new", "/admin-product-insert", "/admin-product-delete",
		"/admin-product-edit", "/admin-product-update", "/admin-productDetail-add", "/admin-productDetail-insert", "/admin-productDetail-back" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO = new ProductDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		String action = req.getServletPath();
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
			case "/admin-productDetail-add":
				showNewProductDetail(req, resp);
				break;
			case "/admin-productDetail-back":
				backProductDetail(req, resp);
				break;
			case "/admin-productDetail-insert":
				insertProductDetail(req, resp);
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
		req.setCharacterEncoding("UTF-8");

		doGet(req, resp);
	}

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		List<Product> productRaws = productDAO.selectAll();
		List<Product> products = new ArrayList<Product>();
		for (Product product : productRaws) {
			if(!product.isDeleted()) {
				products.add(product);
			}
		}
		List<ProductItem> lProductItems = ProductManager.getInstance().products2ProductItems(products);
		request.setAttribute("lProductItems", lProductItems);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

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
	
	private void showNewProductDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int productId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("productId", productId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/product/productDetailAdd.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

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
			throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("Form is not multipart, cannot upload file.");
			return;
		}

		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			File file1 = new File("C:\\Users\\ADMIN\\git\\new_repository\\Online_Shop\\src\\main\\webapp\\");
			diskFileItemFactory.setRepository(file1);
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> fileItems = fileUpload.parseRequest(request);

			String title = null;
			String category_id = null;
			String brand_id = null;
			String supplier_id = null;
			String price = null;
			String discount = null;
			String gender = null;
			String description = null;
			String img = null;

			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					// Process regular form field (input type="text|radio|checkbox|etc", select,
					// etc).
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();

					switch (fieldName) {
					case "title":
						title = fieldValue;
						break;
					case "category_id":
						category_id = fieldValue;
						break;
					case "brand_id":
						brand_id = fieldValue;
						break;
					case "supplier_id":
						supplier_id = fieldValue;
						break;
					case "price":
						price = fieldValue;
						break;
					case "discount":
						discount = fieldValue;
						break;
					case "gender":
						gender = fieldValue;
						break;
					case "description":
						description = fieldValue;
						break;
					}
				} else {
					// Process form file field (input type="file").
					if ("img".equals(item.getFieldName())) {
						img = item.getName();
						File file = new File(
								"C:\\Users\\ADMIN\\git\\new_repository\\Online_Shop\\src\\main\\webapp\\imgs\\"
										+ img);
						item.write(file);

						try {
				            // Đặt tên project cần tìm
				            String projectName = "Online_Shop";

				            // Lấy tất cả các project trong workspace
				            IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

				            // Tìm project theo tên
				            IProject targetProject = null;
				            for (IProject project : projects) {
				                if (project.getName().equals(projectName)) {
				                    targetProject = project;
				                    break;
				                }
				            }

				            // Kiểm tra và làm việc với project được tìm thấy
				            if (targetProject != null) {
				                System.out.println("Found project: " + targetProject.getName());

				                // Ví dụ: Refresh project được tìm thấy
				                targetProject.refreshLocal(IResource.DEPTH_INFINITE, null);
				                System.out.println("Project " + targetProject.getName() + " refreshed successfully.");
				            } else {
				                System.out.println("Project with name '" + projectName + "' not found.");
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
					}
				}
			}
			int categoryId = Integer.parseInt(category_id);
			int brandId = Integer.parseInt(brand_id);
			int supplierId = Integer.parseInt(supplier_id);
			int priceValue = Integer.parseInt(price);
			int discountValue = Integer.parseInt(discount);
			int genderValue = Integer.parseInt(gender);
			Date createdAt = new Date(System.currentTimeMillis());
			Date updatedAt = null;
			int likes = 0;
			boolean deleted = false;

			Product newProduct = new Product(CategoryDAO.getInstance().selectById(categoryId),
					BrandDAO.getInstance().selectById(brandId), SupplierDAO.getInstance().selectById(supplierId), title,
					priceValue, discountValue, img, description, createdAt, updatedAt, deleted, genderValue, likes);

			String message = null; 
			if (ProductDAO.getInstance().insert(newProduct) > 0){
				message = "Thêm sản phẩm thành công!!"; 
			}else {
				message = "Thêm sản phẩm thất !!";
			}
			request.setAttribute("message", message);
			response.sendRedirect("admin-product");

		} catch (FileUploadException e) {
			System.out.println("File upload error!");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Number format error: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Thiếu tham số: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi khác: " + e.getMessage());
		}
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

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

		Product updatedProduct = new Product(id, categoryDAO.selectById(category_id), brandDAO.selectById(brand_id),
				supplierDAO.selectById(supplier_id), title, price, discount, img, description, createdAt, updatedAt,
				deleted, gender, likes);
		productDAO.update(updatedProduct);
		response.sendRedirect("admin-product");

	}
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));

		Product product = productDAO.selectById(id);
		if (!product.isDeleted()) {
			product.setDeleted(true);
		}
		productDAO.update(product);
		response.sendRedirect("admin-product");

	}
	private void insertProductDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("Form is not multipart, cannot upload file.");
			return;
		}

		try {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			File file1 = new File("C:\\Users\\ADMIN\\git\\new_repository\\Online_Shop\\src\\main\\webapp\\");
			diskFileItemFactory.setRepository(file1);
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> fileItems = fileUpload.parseRequest(request);

			String productId = null;
			String size = null;
			String quantity = null;
			String color = null;
			List<String> imagePaths = new ArrayList<>();


			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					// Process regular form field (input type="text|radio|checkbox|etc", select,
					// etc).
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();

					switch (fieldName) {
					case "productId":
						productId = fieldValue;
						break;
					case "size":
						size = fieldValue;
						break;
					case "quantity":
						quantity = fieldValue;
						break;
					case "color":
						color = fieldValue.substring(1);
						break;	
					}
				} else {
					// Process form file field (input type="file").
					if ("img".equals(item.getFieldName())) {
                        String img = item.getName();
                        String filePath = "C:\\Users\\ADMIN\\git\\new_repository\\Online_Shop\\src\\main\\webapp\\imgs";
                        File file = new File(filePath + img);
                        item.write(file);
                        imagePaths.add(img);  // Lưu tên file vào danh sách
                        try {
				            // Đặt tên project cần tìm
				            String projectName = "Online_Shop";

				            // Lấy tất cả các project trong workspace
				            IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

				            // Tìm project theo tên
				            IProject targetProject = null;
				            for (IProject project : projects) {
				                if (project.getName().equals(projectName)) {
				                    targetProject = project;
				                    break;
				                }
				            }

				            // Kiểm tra và làm việc với project được tìm thấy
				            if (targetProject != null) {
				                System.out.println("Found project: " + targetProject.getName());

				                // Ví dụ: Refresh project được tìm thấy
				                targetProject.refreshLocal(IResource.DEPTH_INFINITE, null);
				                System.out.println("Project " + targetProject.getName() + " refreshed successfully.");
				            } else {
				                System.out.println("Project with name '" + projectName + "' not found.");
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
                    }
				}
			}
			int productIdValue = Integer.parseInt(productId);
			String sizeValue = size;
			int quantityValue = Integer.parseInt(quantity);
			String colorValue = color;
			Date createdAt = new Date(System.currentTimeMillis());
			int rs1 = 0,rs2 = 0; 
			ProductDetail productDetail = ProductDetailDAO.getInstance().selectByColorAndSize(colorValue, sizeValue, productIdValue);
			
			if(productDetail == null) {
				rs1 = ProductDetailDAO.getInstance().insert(new ProductDetail(ProductDAO.getInstance().selectById(productIdValue), sizeValue, quantityValue, createdAt, colorValue)); 
			}else {
				int qtt = productDetail.getQuantity(); 
				qtt += quantityValue;
				productDetail.setQuantity(qtt);
				rs1 = ProductDetailDAO.getInstance().update(productDetail);
			}
			List<Image> imgs = new ArrayList<Image>();
			for (String img : imagePaths) {
				Image image = ImageDAO.getInstance().selectByImgAndProductId(img, productIdValue);
				if(image == null) {
					imgs.add(new Image(ProductDAO.getInstance().selectById(productIdValue), img));
				}
			}
			if (imgs.isEmpty()) {
				System.out.println("Anh da ton tai!!");
			}else {
				rs2 = ImageDAO.getInstance().insert(imgs);
			}
			
			String message = null; 
			
			if ( rs1 > 0 ){
				message = "Thêm sản phẩm thành công!!"; 
			}
			else {
				message = "Thêm sản phẩm thất bại!!";
			}
			System.out.println(message);
			request.setAttribute("message", message);
			response.sendRedirect("admin-product");
			
		} catch (FileUploadException e) {
			System.out.println("File upload error!");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Number format error: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Thiếu tham số: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi khác: " + e.getMessage());
		}
		
	}
	private void backProductDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		response.sendRedirect("admin-product");
	}
}
