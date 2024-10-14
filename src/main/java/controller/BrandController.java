package controller;

import model.Brand;
import model.Category;
import service.BrandService;
import service.CategoryService;
import service.IService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BrandController", value = "/brands")
public class BrandController extends HttpServlet {
    private final IService<Brand> brandIService = new BrandService();
    private final CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "brand":
                showBrand(req,resp);
                break;
            case "add":
                showFormAdd(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "delete":
                deleteBrand(req, resp);
                break;
        }
    }

    public void showBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brandList = brandIService.getAll();
        String idBrandParam = req.getParameter("idBrand");
        int idBrand = idBrandParam !=null ? Integer.parseInt(idBrandParam) : brandList.get(0).getId();
        List<Category> categories = categoryService.getCategoryByBrand(idBrand);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/brand.jsp");
        req.setAttribute("brandList", brandList);
        req.setAttribute("categories", categories);
        dispatcher.forward(req, resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/addBrand.jsp");
        dispatcher.forward(req,resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Brand brand = brandIService.findById(idEdit);
        req.setAttribute("brand", brand);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/editBrand.jsp");
        dispatcher.forward(req,resp);
    }

    public void deleteBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        brandIService.delete(idDelete);
        resp.sendRedirect("/brands?action=brand");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addBrand(req, resp);
                break;
            case "edit":
                editBrand(req, resp);
                break;
        }
    }

    public void addBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        Brand newBrand = new Brand(name,image);
        brandIService.add(newBrand);
        resp.sendRedirect("/brands?action=brand");
    }

    public void editBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        Brand newBrand = new Brand(id,name,image);
        brandIService.update(id,newBrand);
        resp.sendRedirect("/brands?action=brand");
    }
}
