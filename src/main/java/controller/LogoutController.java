package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("customer".equals(action)){
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate(); // Xóa session
            }
            resp.sendRedirect("/loginUser");
        } else {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate(); // Xóa session
            }
            resp.sendRedirect("/login");
        }
    }
}
