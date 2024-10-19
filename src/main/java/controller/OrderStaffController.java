package controller;

import model.Order;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderStaffController", value = "/orderStaff")
public class OrderStaffController extends HttpServlet {
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            if("orderDetail".equals(action)){
                showOrderDetail(req, resp);
            } else {
                showOrder(req, resp);
            }
        } else {
            resp.sendRedirect("/home?action=staff");
        }
    }

    public void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderService.getAll();
        req.setAttribute("orderList", orderList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/order/order.jsp");
        dispatcher.forward(req, resp);
    }

    public void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
