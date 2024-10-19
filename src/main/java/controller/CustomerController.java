package controller;

import model.Customer;
import service.CustomerService;
import service.IService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customers")

public class CustomerController extends HttpServlet {
    IService<Customer> customerIService = new CustomerService();
    CustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
            showCustomer(req, resp);
        } else {
            resp.sendRedirect("/home?action=staff");
        }

    }

    public void showCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList;
        String name = req.getParameter("name");
        if (name!=null && !name.trim().isEmpty()){
            customerList = customerService.getCustomerByName(name);
        } else {
            customerList = customerService.getAll();
        }
        if (customerList.isEmpty()) {
            req.setAttribute("errorMessage", "Không có khách hàng nào.");
            req.setAttribute("customerList", customerList);
        } else {
            req.setAttribute("customerList", customerList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/list.jsp");
        dispatcher.forward(req, resp);
    }
}
