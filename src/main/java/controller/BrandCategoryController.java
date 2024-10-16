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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BrandCategoryController", value = "/brand_category")
public class BrandCategoryController extends HttpServlet {
    private final IService<BrandCategory> brandCategoryIService = new BrandCategoryService();
    private final BrandCategoryService brandCategoryService = new BrandCategoryService();
    private final IService<Brand> brandIService = new BrandService();
    private final IService<Category> categoryIService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            switch (action){
                case "home":
                    showAll(req, resp);
                    break;
                case "add":
                    showFormAdd(req, resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                case "delete":
                    deleteType(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    public void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BrandCategory> list = brandCategoryIService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/brand_category.jsp");
        req.setAttribute("list",list);
        dispatcher.forward(req, resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brandList = brandIService.getAll();
        List<Category> categoryList = categoryIService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/addBrandCategory.jsp");
        req.setAttribute("brandList",brandList);
        req.setAttribute("categoryList",categoryList);
        dispatcher.forward(req, resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        List<Brand> brandList = brandIService.getAll();
        List<Category> categoryList = categoryIService.getAll();
        BrandCategory brandCategory = brandCategoryIService.findById(idEdit);
        req.setAttribute("brandCategory", brandCategory);
        req.setAttribute("brandList",brandList);
        req.setAttribute("categoryList",categoryList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/editBrandCategory.jsp");
        dispatcher.forward(req, resp);

    }

    public void deleteType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        brandCategoryIService.delete(idDelete);
        resp.sendRedirect("/brand_category?action=home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addType(req, resp);
                break;
            case "edit":
                editType(req, resp);
                break;
        }
    }

    public void addType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idBrand = Integer.parseInt(req.getParameter("idBrand"));
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        if (brandCategoryService.existsBrandAndCategory(idBrand, idCategory)) {
            req.setAttribute("errorMessage", "Mã thương hiệu và mã loại sản phẩm đã tồn tại!");
            showFormAdd(req, resp);
            return;
        }
        BrandCategory brandCategory = new BrandCategory(idCategory, idBrand);
        brandCategoryIService.add(brandCategory);
        resp.sendRedirect("/brand_category?action=home");
    }

    public void editType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int idBrand = Integer.parseInt(req.getParameter("idBrand"));
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        BrandCategory existType = brandCategoryIService.findById(id);
        if(existType.getIdBrand()!=idBrand || existType.getIdCategory()!=idCategory){
            if (brandCategoryService.existsBrandAndCategory(idBrand, idCategory)) {
                req.setAttribute("errorMessage", "Mã thương hiệu và mã loại sản phẩm đã tồn tại!");
                showFormEdit(req, resp);
                return;
            }
        }
        BrandCategory brandCategory = new BrandCategory(id, idCategory, idBrand);
        brandCategoryIService.update(id,brandCategory);
        resp.sendRedirect("/brand_category?action=home");
    }
}
