package controller;

import model.Account;
import model.CustomerAccount;
import model.Role;
import service.AccountService;
import service.CustomerAccountService;
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
    IService<CustomerAccount> customerAccountIService = new CustomerAccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            switch (action) {
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
                case "customer":
                    showCustomerAccount(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/home?action=staff");
        }
    }

    public void showAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Account> accountList = accountIService.getAll();
        if (accountList.isEmpty()){
            req.setAttribute("errorMessage", "Hiện tại không có tài khoản nào.");
            req.setAttribute("accountList", accountList);
        } else {
            req.setAttribute("accountList", accountList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/account/account.jsp");
        dispatcher.forward(req,resp);
    }

    public void showCustomerAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerAccount> list = customerAccountIService.getAll();
        if (list.isEmpty()){
            req.setAttribute("errorMessage", "Hiện tại không có tài khoản nào.");
            req.setAttribute("list", list);
        } else {
            req.setAttribute("list", list);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/account/customer.jsp");
        dispatcher.forward(req, resp);
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

    public void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        accountIService.delete(idDelete);
        resp.sendRedirect("/accounts?action=account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addAccount(req, resp);
                break;
            case "edit":
                editAccount(req,resp);
                break;
        }

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
