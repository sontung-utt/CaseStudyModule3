package controller;

import model.Department;
import model.Staff;
import service.DepartmentService;
import service.IService;
import service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentController", value = "/departments")
public class DepartmentController extends HttpServlet {
    IService<Department> departmentIService = new DepartmentService();
    DepartmentService departmentService = new DepartmentService();
    StaffService staffService = new StaffService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "department":
                showDepartment(req, resp);
                break;
            case "add":
                showFormAdd(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "delete":
                deleteDepartment(req, resp);
                break;
        }
    }

    public void showDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departmentList = departmentIService.getAll();
        if (departmentList.isEmpty()) {
            req.setAttribute("errorMessage", "Hiện tại không có phòng ban nào.");
            req.setAttribute("departmentList", departmentList);
        } else {
            String idDepartmentParam = req.getParameter("idDepartment");
            int idDepartment = idDepartmentParam != null ? Integer.parseInt(idDepartmentParam) : departmentList.get(0).getId();
            List<Staff> staffs = staffService.listStaffByDepartment(idDepartment);
            req.setAttribute("staffs",staffs);
            req.setAttribute("departmentList",departmentList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/department/department.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/department/add.jsp");
        dispatcher.forward(req, resp);
    }

    public void deleteDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        departmentIService.delete(idDelete);
        resp.sendRedirect("/departments?action=department");
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Department department = departmentIService.findById(idEdit);
        req.setAttribute("department",department);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/department/edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addDepartment(req, resp);
                break;
            case "edit":
                editDepartment(req, resp);
                break;
        }
    }

    public void addDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double salaryCoefficient = Double.parseDouble(req.getParameter("salaryCoefficient"));
        if (departmentService.existDepartment(name)){
            req.setAttribute("errorMessage", "Phòng ban đã tồn tại!");
            showFormEdit(req, resp);
            return;
        }
        Department department = new Department(name,salaryCoefficient);
        departmentIService.add(department);
        resp.sendRedirect("/departments?action=department");
    }

    public void editDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double salaryCoefficient = Double.parseDouble(req.getParameter("salaryCoefficient"));
        Department existDepartment = departmentIService.findById(id);
        if(!existDepartment.getName().equals(name)){
            if(departmentService.existDepartment(name)){
                req.setAttribute("errorMessage", "Phòng ban đã tồn tại!");
                showFormEdit(req, resp);
                return;
            }
        }
        Department department = new Department(id,name,salaryCoefficient);
        departmentIService.update(id,department);
        resp.sendRedirect("/departments?action=department");
    }
}
