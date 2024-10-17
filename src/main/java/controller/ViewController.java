package controller;

import model.Customer;
import model.CustomerAccount;
import service.CustomerAccountService;
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

@WebServlet(name = "ViewController", value = "/view")
public class ViewController extends HttpServlet {
    CustomerService customerService = new CustomerService();
    IService<CustomerAccount> customerAccountIService = new CustomerAccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerUserName") != null){
            String action = req.getParameter("action");
            switch (action) {
                case "view":
                    showView(req, resp);
                    break;
                case "add":
                    showFormAddInfo(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/home");
        }
    }

    public void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/view.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormAddInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));

        CustomerAccount customerAccount = customerAccountIService.findById(userId);
        req.setAttribute("customerAccount", customerAccount);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customer/add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        add(req, resp);
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

            // Set an error message and redirect back to the form
            req.setAttribute("errorMessage", "Thông tin không được để trống. Vui lòng điền đầy đủ các trường!");
            req.getRequestDispatcher("/customer/add.jsp").forward(req, resp);
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
        int age = Integer.parseInt(ageStr);
        Customer customer = new Customer(name,age,gender,address,phone,email,idLogin);
        customerService.add(customer,idLogin);
    }
}
