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

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("info".equals(action)) {
            showLoginUser(req, resp);
        } else {
            showFormLogin(req, resp);
        }
    }

    public void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login.jsp");
        dispatcher.forward(req, resp);
    }

    public void showLoginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null){
            req.setAttribute("username",username);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/static/topFrame.jsp");
            dispatcher.forward(req,resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter(("password"));
        if (userService.checkUser(username,password)){
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("/products?action=product");
        } else {
            req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng!");
            showFormLogin(req, resp);
        }
    }
}
