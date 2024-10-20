package controller;

import model.CartDetail;
import model.Order;
import model.OrderDetail;
import model.Product;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderCustomerController", value = "/orderCustomer")
public class OrderCustomerController extends HttpServlet {
    CustomerService customerService = new CustomerService();
    IService<Product> productIService = new ProductService();
    CartService cartService = new CartService();
    CartDetailService cartDetailService = new CartDetailService();
    OrderService orderService = new OrderService();
    IService<OrderDetail> orderDetailIService = new OrderDetailService();
    OrderDetailService orderDetailService = new OrderDetailService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerUserName") != null){
            String action = req.getParameter("action");
            if ("view".equals(action)){
                showOrder(req, resp);
            } else if ("viewDetail".equals(action)){
                showOrderDetail(req, resp);
            } else {
                showFormOrderDetail(req, resp);
            }
        }
    }

    public void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        if (idCustomer != -1){
            List<Order> orderList = orderService.getOrderListByIdCustomer(idCustomer);
            if (orderList.isEmpty()){
                req.setAttribute("errorMessage", "Bạn chưa có đơn hàng nào.");
                req.setAttribute("orderList", orderList);
            } else {
                req.setAttribute("orderList", orderList);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/order/view.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/view?action=add");
        }
    }

    public void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        if (idCustomer != -1) {
            int idOrder = Integer.parseInt(req.getParameter("id"));
            double total = orderService.getTotalById(idOrder);
            List<OrderDetail> list = orderDetailService.listOrderDetailById(idOrder);
            req.setAttribute("list",list);
            req.setAttribute("idOrder",idOrder);
            req.setAttribute("total",total);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/order/viewDetail.jsp");
            dispatcher.forward(req,resp);
        } else {
            resp.sendRedirect("/loginUser");
        }
    }

    public void showFormOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        if (idLogin != -1) {
            int idCart = cartService.getIdCartByIdCustomer(idCustomer);
            double total = cartDetailService.totalPerCartId(idCart);
            List<CartDetail> cartDetailList = cartDetailService.getListCartByIdCart(idCart);
            if (cartDetailList.isEmpty()){
                resp.sendRedirect("/view");
            } else {
                req.setAttribute("cartDetailList",cartDetailList);
                req.setAttribute("total",total);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/order/order_detail.jsp");
                dispatcher.forward(req,resp);
            }
        } else {
            resp.sendRedirect("/loginUser");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addOrder(req, resp);
    }

    public void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        int idCart = cartService.getIdCartByIdCustomer(idCustomer);
        double total = cartDetailService.totalPerCartId(idCart);
        Order order = new Order(total, idCart, idCustomer);
        int idOrder = orderService.add(order);
        List<CartDetail> cartDetailList = cartDetailService.getListCartByIdCart(idCart);
        for (CartDetail cartDetail : cartDetailList){
            OrderDetail orderDetail = new OrderDetail(idOrder,cartDetail.getIdProduct(),cartDetail.getQuantity(),cartDetail.getPrice());
            orderDetailIService.add(orderDetail);
        }

        cartService.deleteCartDetailById(idCart);
        resp.sendRedirect("/orderCustomer?action=view");
    }
}