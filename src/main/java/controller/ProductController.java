package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import model.BrandCategory;
import model.Product;
import service.BrandCategoryService;
import service.BrandService;
import service.IService;
import service.ProductService;

@WebServlet(name = "ProductController", value = "/products")
public class ProductController extends HttpServlet {
    private final IService<Product> productIService = new ProductService();
    private final ProductService productService = new ProductService();
    private final IService<BrandCategory> brandCategoryIService = new BrandCategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            switch (action) {
                case "product":
                    showProduct(req,resp);
                    break;
                case "add":
                    showFormAdd(req,resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    public void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productIService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/product.jsp");
        req.setAttribute("productList", productList);
        dispatcher.forward(req, resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BrandCategory> list = brandCategoryIService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/add.jsp");
        req.setAttribute("list",list);
        dispatcher.forward(req, resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        List<BrandCategory> list = brandCategoryIService.getAll();
        Product product = productIService.findById(idEdit);
        req.setAttribute("list",list);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        dispatcher.forward(req, resp);
    }

    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        productIService.delete(idDelete);
        resp.sendRedirect("/products?action=product");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "add":
                addProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
        }
    }

    public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String image = req.getParameter("image");
        String description = req.getParameter("description");
        int idBrandCategory = Integer.parseInt(req.getParameter("idBrandCategory"));
        Product newProduct = new Product(name,price, quantity, image, description, idBrandCategory);
        productIService.add(newProduct);
        resp.sendRedirect("/products?action=product");
    }

    public void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String image = req.getParameter("image");
        String description = req.getParameter("description");
        int idBrandCategory = Integer.parseInt(req.getParameter("idBrandCategory"));
        Product newProduct = new Product(id,name,price,quantity,image,description,idBrandCategory);
        productIService.update(id,newProduct);
        resp.sendRedirect("/products?action=product");
    }
}
