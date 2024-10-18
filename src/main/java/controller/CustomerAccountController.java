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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CustomerAccountController", value = "/accCustomers")
public class CustomerAccountController extends HttpServlet {
    IService<CustomerAccount> customerAccountIService = new CustomerAccountService();
    CustomerAccountService customerAccountService = new CustomerAccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equals(action)){
            showFormRegister(req, resp);
        }
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerUserName") != null){
            if("view".equals(action)){
                showAccount(req, resp);
            } else if ("edit".equals(action)){
                showFormEdit(req, resp);
            }
        } else {
            resp.sendRedirect("/home");
        }
    }

    public void showAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        if (idLogin != -1) {
            CustomerAccount customerAccount = customerAccountIService.findById(idLogin);
            req.setAttribute("idLogin",idLogin);
            req.setAttribute("customerAccount",customerAccount);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/customerAccount/view.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/loginUser");
        }

    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        if (idLogin != -1) {
            CustomerAccount customerAccount = customerAccountIService.findById(idLogin);
            req.setAttribute("customerAccount",customerAccount);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/customerAccount/edit.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/loginUser");
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
            case "edit":
                editAccount(req, resp);
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
        if (!customerAccountService.        checkPassword(password,rePassword)){
            req.setAttribute("errorMessage", "Mật khẩu và Nhập lại mật khẩu phải trùng khớp!");
            showFormRegister(req, resp);
            return;
        }
        customerAccountIService.add(account);
        resp.sendRedirect("/loginUser");
    }

    public void editAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String rePassword = req.getParameter(("rePassword"));
        CustomerAccount existAccount = customerAccountIService.findById(id);
        if (!existAccount.getPassword().equals(password)){
            req.setAttribute("errorMessage", "Mật khẩu cũ không đúng. Yêu cầu nhập lại!");
            showFormEdit(req, resp);
            return;
        }
        if (!existAccount.getUsername().equals(username)){
            if (customerAccountService.existAccountName(username)){
                req.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
                showFormEdit(req, resp);
                return;
            }
        }

        if (!customerAccountService.checkPassword(newPassword,rePassword)){
            req.setAttribute("errorMessage", "Mật khẩu mới và Nhập lại mật khẩu phải trùng khớp!");
            showFormEdit(req, resp);
            return;
        }
        CustomerAccount account = new CustomerAccount(username,newPassword);
        customerAccountIService.update(id,account);
        resp.sendRedirect("/view");
    }
}
