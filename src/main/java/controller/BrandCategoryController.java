package controller;

import model.BrandCategory;
import service.BrandCategoryService;
import service.IService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BrandCategoryController", value = "/brand_category")
public class BrandCategoryController extends HttpServlet {
    private final IService<BrandCategory> brandCategoryIService = new BrandCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "home":
                showAll(req, resp);
                break;
        }
    }

    public void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BrandCategory> list = brandCategoryIService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/brand_category/brand_category.jsp");
        req.setAttribute("list",list);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
