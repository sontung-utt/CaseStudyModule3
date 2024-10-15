package controller;

import model.Account;
import service.AccountService;
import service.IService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AccountController", value = "/accounts")
public class AccountController extends HttpServlet {
    IService<Account> accountIService = new AccountService();
    AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "register":
                showFormRegister(req, resp);
                break;
        }
    }

    public void showFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "register":
                register(req, resp);
                break;
        }

    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter(("rePassword"));
        Account account = new Account(username,password);
        if (accountService.existAccount(username)){
            req.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
            showFormRegister(req, resp);
            return;
        }
        if (!accountService.checkPassword(password,rePassword)){
            req.setAttribute("errorMessage", "Mật khẩu và Nhập lại mật khẩu phải trùng khớp!");
            showFormRegister(req, resp);
            return;
        }
        accountService.register(account);
        resp.sendRedirect("/login");
    }
}
