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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customers")

public class CustomerController extends HttpServlet {
    IService<Customer> customerIService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showCustomer(req, resp);
    }

    public void showCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerIService.getAll();
        if (customerList.isEmpty()) {
            req.setAttribute("errorMessage", "Hiện tại không có khách hàng nào.");
            req.setAttribute("customerList", customerList);
        } else {
            req.setAttribute("customerList", customerList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/list.jsp");
        dispatcher.forward(req, resp);
    }
}
