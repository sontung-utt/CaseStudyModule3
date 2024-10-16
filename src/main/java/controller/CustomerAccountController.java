package controller;

import model.Account;
import model.CustomerAccount;
import service.CustomerAccountService;
import service.IService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerAccountController", value = "/accCustomers")
public class CustomerAccountController extends HttpServlet {
    IService<CustomerAccount> customerAccountIService = new CustomerAccountService();
    CustomerAccountService customerAccountService = new CustomerAccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "register":
                showFormRegister(req, resp);
                break;
            case "add":
                showFormAddInfo(req, resp);
                break;
        }
    }

    public void showFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormAddInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/add.jsp");
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
        CustomerAccount account = new CustomerAccount(username,password);
        if (customerAccountService.existAccountName(username)){
            req.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
            showFormRegister(req, resp);
            return;
        }
        if (!customerAccountService.checkPassword(password,rePassword)){
            req.setAttribute("errorMessage", "Mật khẩu và Nhập lại mật khẩu phải trùng khớp!");
            showFormRegister(req, resp);
            return;
        }
        customerAccountIService.add(account);
        resp.sendRedirect("/loginUser");
    }
}
