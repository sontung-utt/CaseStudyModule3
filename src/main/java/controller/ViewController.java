package controller;

import model.Cart;
import model.Customer;
import model.CustomerAccount;
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

@WebServlet(name = "ViewController", value = "/view")
public class ViewController extends HttpServlet {
    CustomerService customerService = new CustomerService();
    IService<Customer> customerIService = new CustomerService();
    IService<CustomerAccount> customerAccountIService = new CustomerAccountService();
    IService<Product> productIService = new ProductService();
    IService<Cart> cartIService = new CartService();
    CartService cartService = new CartService();
    CartDetailService cartDetailService = new CartDetailService();
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerUserName") != null){
            String action = req.getParameter("action");
            if("add".equals(action)){
                showFormAddInfo(req, resp);
            } else {
                showView(req, resp);
            }

        } else {
            resp.sendRedirect("/home");
        }
    }

    public void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        if (idLogin != -1) {
            List<Product> productList = productIService.getAll();
            int idCustomer = customerService.getIdByUserId(idLogin);
            int idCart = cartService.getIdCartByIdCustomer(idCustomer);
            //int idOrder = orderService.getIdOrderByIdCustomer(idCustomer);
            int numProduct = cartDetailService.numProduct(idCart, idCustomer);
            req.setAttribute("productList",productList);
            req.setAttribute("idCart",idCart);
            req.setAttribute("idCustomer", idCustomer);
            req.setAttribute("numProduct", numProduct);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/views.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/loginUser");
        }

    }

    public void showFormAddInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        if (idLogin != -1) {
            CustomerAccount customerAccount = customerAccountIService.findById(idLogin);
            req.setAttribute("customerAccount", customerAccount);
            int id = customerService.getIdByUserId(idLogin);
            if (id == -1){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/add.jsp");
                dispatcher.forward(req, resp);
            } else {
                Customer customer = customerIService.findById(id);
                req.setAttribute("customer", customer);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/edit.jsp");
                dispatcher.forward(req, resp);
            }

        } else {
            resp.sendRedirect("/loginUser");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "add":
                add(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int idLogin = (int) session.getAttribute("idLogin");
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        if (name == null || name.trim().isEmpty() || ageStr == null || ageStr.trim().isEmpty() || gender == null || gender.trim().isEmpty() || address == null || address.trim().isEmpty() || phone == null || phone.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Thông tin không được để trống. Vui lòng điền đầy đủ các trường!");
            showFormAddInfo(req, resp);
            return;
        }
        if (customerService.checkExistPhone(phone)) {
            req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng!");
            showFormAddInfo(req, resp);
            return;
        }
        if (customerService.checkExistEmail(email)) {
            req.setAttribute("errorMessage", "Email đã được sử dụng!");
            showFormAddInfo(req, resp);
            return;
        }
        if (customerService.checkValidatePhone(phone)) {
            req.setAttribute("errorMessage", "Số địện thoại nhập không đúng định dạng!");
            showFormAddInfo(req, resp);
            return;
        }
        if (customerService.checkValidateEmail(email)) {
            req.setAttribute("errorMessage", "Email nhập không đúng định dạng!");
            showFormAddInfo(req, resp);
            return;
        }
        try {
            int age = Integer.parseInt(ageStr);
            Customer customer = new Customer(name,age,gender,address,phone,email,idLogin);
            customerService.add(customer,idLogin);
            int id = customerService.getIdByUserId(idLogin);
            Cart cart = new Cart(id);
            cartIService.add(cart);
            resp.sendRedirect("/view");
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Tuổi phải là một số hợp lệ.");
            showFormAddInfo(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int idLogin = (int) session.getAttribute("idLogin");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        if (name == null || name.trim().isEmpty() || ageStr == null || ageStr.trim().isEmpty() || gender == null || gender.trim().isEmpty() || address == null || address.trim().isEmpty() || phone == null || phone.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Thông tin không được để trống. Vui lòng điền đầy đủ các trường!");
            showFormAddInfo(req, resp);
            return;
        }
        Customer existCustomer = customerIService.findById(id);
        if (!existCustomer.getPhone().equals(phone)){
            if (customerService.checkExistPhone(phone)) {
                req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng!");
                showFormAddInfo(req, resp);
                return;
            }
            if (customerService.checkValidatePhone(phone)) {
                req.setAttribute("errorMessage", "Số địện thoại nhập không đúng định dạng!");
                showFormAddInfo(req, resp);
                return;
            }
        }
        if (!existCustomer.getEmail().equals(email)){
            if (customerService.checkExistEmail(email)) {
                req.setAttribute("errorMessage", "Email đã được sử dụng!");
                showFormAddInfo(req, resp);
                return;
            }
            if (customerService.checkValidateEmail(email)) {
                req.setAttribute("errorMessage", "Email nhập không đúng định dạng!");
                showFormAddInfo(req, resp);
                return;
            }
        }

        try {
            int age = Integer.parseInt(ageStr);
            Customer customer = new Customer(name,age,gender,address,phone,email,idLogin);
            customerService.update(id,customer,idLogin);
            resp.sendRedirect("/view");
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Tuổi phải là một số hợp lệ.");
            showFormAddInfo(req, resp);
        }
    }
}
