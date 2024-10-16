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
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "AccountController", value = "/accounts")
public class AccountController extends HttpServlet {
    IService<Account> accountIService = new AccountService();
    AccountService accountService = new AccountService();
    IService<Role> roleIService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "register":
                showFormRegister(req, resp);
                break;
            case "account":
                showAccount(req, resp);
                break;
            case "add":
                showFormAdd(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "delete":
                deleteAccount(req, resp);
                break;
        }
    }

    public void showAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accountList = accountIService.getAll();
        req.setAttribute("accountList",accountList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/account/account.jsp");
        dispatcher.forward(req,resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/account/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        List<Role> roleList = roleIService.getAll();
        Account account = accountIService.findById(idEdit);
        req.setAttribute("account",account);
        req.setAttribute("roleList",roleList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/account/edit.jsp");
        dispatcher.forward(req,resp);
    }

    public void showFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
        dispatcher.forward(req, resp);
    }

    public void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        accountIService.delete(idDelete);
        resp.sendRedirect("/accounts?action=account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "register":
                register(req, resp);
                break;
            case "add":
                addAccount(req, resp);
                break;
            case "edit":
                editAccount(req,resp);
                break;
        }

    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter(("rePassword"));
        Account account = new Account(username,password);
        if (accountService.existAccountName(username)){
            req.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
            showFormRegister(req, resp);
            return;
        }
        if (!accountService.checkPassword(password,rePassword)){
            req.setAttribute("errorMessage", "Mật khẩu và Nhập lại mật khẩu phải trùng khớp!");
            showFormRegister(req, resp);
            return;
        }
        accountService.add(account);
        resp.sendRedirect("/login");
    }

    public void addAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(accountService.existAccountName(username)){
            req.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
            showFormAdd(req, resp);
            return;
        }
        Account account = new Account(username,password);
        accountIService.add(account);
        resp.sendRedirect("/accounts?action=account");
    }

    public void editAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String idRoleParam = req.getParameter("idRole");
        int idRole;
        if (idRoleParam == null || idRoleParam.isEmpty()){
            idRole = roleIService.getAll().get(0).getId();
        } else {
            idRole = Integer.parseInt(idRoleParam);
        }
        Account existAccount = accountIService.findById(id);
        if(!existAccount.getUsername().equals(username)){
            if(accountService.existAccountName(username)){
                req.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
                showFormEdit(req, resp);
                return;
            }
        }
        Account account = new Account(id,username,password,idRole);
        accountIService.update(id,account);
        resp.sendRedirect("/accounts?action=account");
    }
}
