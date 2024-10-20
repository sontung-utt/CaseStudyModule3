package controller;

import model.Order;
import model.OrderDetail;
import model.Status;
import service.OrderDetailService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "OrderStaffController", value = "/orderStaff")
public class OrderStaffController extends HttpServlet {
    OrderService orderService = new OrderService();
    OrderDetailService orderDetailService = new OrderDetailService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            String action = req.getParameter("action");
            if("orderDetail".equals(action)){
                showOrderDetail(req, resp);
            } else if("edit".equals(action)){
                showFormEdit(req, resp);
            } else if("order".equals(action)){
                showOrder(req, resp);
            }
        } else {
            resp.sendRedirect("/home?action=staff");
        }
    }

    public void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList;
        String nameCustomer = req.getParameter("nameCustomer");
        if (nameCustomer!=null && !nameCustomer.trim().isEmpty()){
            orderList = orderService.searchOrderList(nameCustomer);
        } else {
            orderList = orderService.getAll();
        }
        if (orderList.isEmpty()){
            req.setAttribute("errorMessage", "Không có đơn hàng nào.");
            req.setAttribute("orderList", orderList);
        } else {
            req.setAttribute("orderList", orderList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/order/order.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOrder = Integer.parseInt(req.getParameter("id"));
        Order order = orderService.findById(idOrder);
        List<Status> statusList = Arrays.asList(Status.values());
        req.setAttribute("order",order);
        req.setAttribute("statusList",statusList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/order/edit.jsp");
        dispatcher.forward(req, resp);
    }

    public void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOrder = Integer.parseInt(req.getParameter("id"));
        double total = orderService.getTotalById(idOrder);
        List<OrderDetail> list = orderDetailService.listOrderDetailById(idOrder);
        req.setAttribute("list",list);
        req.setAttribute("idOrder",idOrder);
        req.setAttribute("total",total);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/order/viewDetail.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        editOrder(req, resp);
    }

    public void editOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String time = req.getParameter("time");
        double total = Double.parseDouble(req.getParameter("total"));
        String status = req.getParameter("status");
        Order order = new Order(id,time,total,status);
        orderService.update(id,order);
        resp.sendRedirect("/orderStaff?action=order");
    }
}
