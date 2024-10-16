package controller;

import model.Account;
import model.Department;
import model.Staff;
import service.AccountService;
import service.DepartmentService;
import service.IService;
import service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(name = "StaffController", value = "/staff")
public class StaffController extends HttpServlet {
    IService<Staff> staffIService = new StaffService();
    StaffService staffService = new StaffService();
    IService<Department> departmentIService = new DepartmentService();
    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            switch (action) {
                case "staff":
                    showStaff(req, resp);
                    break;
                case "add":
                    showFormAdd(req, resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                case "delete":
                    deleteStaff(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

    public void showStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff> staffList = staffIService.getAll();
        if (staffList.isEmpty()) {
            req.setAttribute("errorMessage", "Hiện tại không có nhân sự nào.");
            req.setAttribute("staffList", staffList);
        } else {
            req.setAttribute("staffList", staffList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/staff/staff.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departmentList = departmentIService.getAll();
        req.setAttribute("departmentList", departmentList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/staff/add.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Staff staff = staffService.findById(idEdit);
        List<Department> departmentList = departmentIService.getAll();
        req.setAttribute("departmentList", departmentList);
        req.setAttribute("staff", staff);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/staff/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void deleteStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        staffService.delete(idDelete);
        resp.sendRedirect("/staff?action=staff");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addStaff(req, resp);
                break;
            case "edit":
                editStaff(req, resp);
                break;
        }
    }

    public void addStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String image = req.getParameter("image");
        int userId = Integer.parseInt(req.getParameter("userId"));
        double salary = Double.parseDouble(req.getParameter("salary"));
        int idDepartment = Integer.parseInt(req.getParameter("idDepartment"));

        if (staffService.checkExistPhone(phone)) {
            req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng!");
            showFormAdd(req, resp);
            return;
        }
        if (staffService.checkExistEmail(email)) {
            req.setAttribute("errorMessage", "Email đã được sử dụng!");
            showFormAdd(req, resp);
            return;
        }
        if (!staffService.checkValidatePhone(phone)) {
            req.setAttribute("errorMessage", "Số địện thoại nhập không đúng định dạng!");
            showFormAdd(req, resp);
            return;
        }
        if (!staffService.checkValidateEmail(email)) {
            req.setAttribute("errorMessage", "Email nhập không đúng định dạng!");
            showFormAdd(req, resp);
            return;
        }
        if (staffService.checkExistUserId(userId)) {
            req.setAttribute("errorMessage", "Mã tài khoản đã được sử dụng!");
            showFormAdd(req, resp);
            return;
        }
        if (!accountService.existAccount(userId)) {
            req.setAttribute("errorMessage", "Mã tài khoản không tồn tại!");
            showFormAdd(req, resp);
            return;
        }
        Staff staff = new Staff(name, age, gender, address, phone, email, userId, image, salary, idDepartment);
        staffIService.add(staff);
        resp.sendRedirect("/staff?action=staff");
    }

    public void editStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String image = req.getParameter("image");
        int userId = Integer.parseInt(req.getParameter("userId"));
        double salary = Double.parseDouble(req.getParameter("salary"));
        int idDepartment = Integer.parseInt(req.getParameter("idDepartment"));
        Staff existStaff = staffIService.findById(id);

        if (!existStaff.getPhone().equals(phone)) {
            if (staffService.checkExistPhone(phone)) {
                req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng!");
                showFormEdit(req, resp);
                return;
            }
            if (!staffService.checkValidatePhone(phone)) {
                req.setAttribute("errorMessage", "Số điện thoại nhập không đúng định dạng!");
                showFormEdit(req, resp);
                return;
            }
        }

        if (!existStaff.getEmail().equals(email)) {
            if (staffService.checkExistEmail(email)) {
                req.setAttribute("errorMessage", "Email đã được sử dụng!");
                showFormEdit(req, resp);
                return;
            }

            if (!staffService.checkValidateEmail(email)) {
                req.setAttribute("errorMessage", "Email nhập không đúng định dạng!");
                showFormEdit(req, resp);
                return;
            }
        }

        if (existStaff.getUserId() != userId) {
            if (staffService.checkExistUserId(userId)) {
                req.setAttribute("errorMessage", "Mã tài khoản đã được sử dụng!");
                showFormEdit(req, resp);
                return;
            }
            if (!accountService.existAccount(userId)) {
                req.setAttribute("errorMessage", "Mã tài khoản không tồn tại!");
                showFormAdd(req, resp);
                return;
            }
        }
        Staff staff = new Staff(id, name, age, gender, address, phone, email, userId, image, salary, idDepartment);
        staffIService.update(id, staff);
        resp.sendRedirect("/staff?action=staff");
    }
}
