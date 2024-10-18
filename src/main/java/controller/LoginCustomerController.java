package controller;

import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginCustomerController", value = "/loginUser")
public class LoginCustomerController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("info".equals(action)) {
            showLoginUser(req, resp);
        } else {
            showFormLogin(req, resp);
        }
        showFormLogin(req, resp);
    }

    public void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/loginUser.jsp");
        dispatcher.forward(req, resp);
    }

    public void showLoginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String customerUserName = (String) session.getAttribute("customerUserName");
        if (customerUserName != null){
            req.setAttribute("customerUserName",customerUserName);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/view.jsp");
            dispatcher.forward(req,resp);
        } else {
            resp.sendRedirect("/loginUser");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginCustomer(req, resp);
    }

    public void loginCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter(("password"));
        int idLogin = userService.getCustomerId(username, password);
        if (userService.checkCustomer(username,password)){
            HttpSession session = req.getSession();
            session.invalidate();
            session = req.getSession(true);
            session.setAttribute("customerUserName", username);
            session.setAttribute("idLogin", idLogin);
            resp.sendRedirect("/view");
        } else {
            req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
            showFormLogin(req, resp);
        }
    }
}
