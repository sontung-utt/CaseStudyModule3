package controller;

import model.Account;
import model.Role;
import service.AccountService;
import service.IService;
import service.RoleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleController", value = "/role")
public class RoleController extends HttpServlet {
    IService<Role> roleIService = new RoleService();
    RoleService roleService = new RoleService();
    AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            switch (action){
                case "role":
                    showRole(req, resp);
                    break;
                case "add":
                    showFormAdd(req, resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                case "delete":
                    deleteRole(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/home?action=staff");
        }

    }

    public void showRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roleList = roleIService.getAll();
        if (roleList.isEmpty()) {
            req.setAttribute("errorMessage", "Hiện tại không có chức năng nào.");
            req.setAttribute("roleList", roleList); // Still set this attribute as the JSP expects it
        } else {
            String idRoleParam = req.getParameter("idRole");
            int idRole = idRoleParam != null ? Integer.parseInt(idRoleParam) : roleList.get(0).getId();
            List<Account> accounts = accountService.getAccountByRole(idRole);
            req.setAttribute("accounts", accounts);
            req.setAttribute("roleList",roleList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/role/role.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/role/add.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormEdit (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Role role = roleIService.findById(idEdit);
        req.setAttribute("role",role);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/role/edit.jsp");
        dispatcher.forward(req,resp);
    }

    public void deleteRole (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        roleIService.delete(idDelete);
        resp.sendRedirect("/role?action=role");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "add":
                addRole(req, resp);
                break;
            case "edit":
                editRole(req, resp);
                break;
        }
    }

    public void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (roleService.existRoleName(name)){
            req.setAttribute("errorMessage", "Chức năng đã tồn tại!");
            showFormAdd(req, resp);
            return;
        }
        Role role = new Role(name);
        roleIService.add(role);
        resp.sendRedirect("/role?action=role");
    }

    public void editRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Role existRole = roleIService.findById(id);
        if (!existRole.getName().equals(name)){
            if (roleService.existRoleName(name)){
                req.setAttribute("errorMessage", "Chức năng đã tồn tại!");
                showFormEdit(req, resp);
                return;
            }
        }
        Role role = new Role(id,name);
        roleIService.update(id,role);
        resp.sendRedirect("/role?action=role");
    }
}
