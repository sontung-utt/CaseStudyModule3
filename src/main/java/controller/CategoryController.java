package controller;

import model.Brand;
import model.BrandCategory;
import model.Category;
import service.BrandCategoryService;
import service.BrandService;
import service.CategoryService;
import service.IService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="CategoryController", value = "/categories")
public class CategoryController extends HttpServlet {
    private final IService<Category> categoryIService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            switch (action) {
                case "category":
                    showCategory(req, resp);
                    break;
                case "add":
                    showFormAdd(req, resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                case "delete":
                    deleteCategory(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/home?action=staff");
        }
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/addCategory.jsp");
        dispatcher.forward(req, resp);
    }

    public void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryIService.getAll();
        String idCategoryParam = req.getParameter("idCategory");
        int idCategory = idCategoryParam != null ? Integer.parseInt(idCategoryParam) : categoryList.get(0).getId();
        List<Brand> brands = brandService.getBrandByCategory(idCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/category.jsp");
        req.setAttribute("categoryList",categoryList);
        req.setAttribute("brands",brands);
        dispatcher.forward(req, resp);
    }

    public void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        categoryIService.delete(idDelete);
        resp.sendRedirect("/categories?action=category");
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Category category = categoryIService.findById(idEdit);
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/editCategory.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addCategory(req, resp);
                break;
            case "edit":
                editCategory(req, resp);
                break;
        }
    }

    public void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        if (categoryService.existCategoryName(name)){
            req.setAttribute("errorMessage", "Loại sản phẩm đã tồn tại!");
            showFormAdd(req, resp);
            return;
        }
        Category newCategory = new Category(name, image);
        categoryIService.add(newCategory);
        resp.sendRedirect("/categories?action=category");
    }

    public void editCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        Category existCategory = categoryIService.findById(id);
        if (!existCategory.getName().equals(name)) {
            if (categoryService.existCategoryName(name)){
                req.setAttribute("errorMessage", "Loại sản phẩm đã tồn tại!");
                showFormEdit(req, resp);
                return;
            }
        }
        Category newCategory = new Category(id,name,image);
        categoryIService.update(id,newCategory);
        resp.sendRedirect("/categories?action=category");
    }
}
