package controller;

import model.CartDetail;
import service.*;

import model.Cart;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    IService<Cart> cartIService = new CartService();
    CartService cartService = new CartService();
    IService<CartDetail> cartDetailIService = new CartDetailService();
    CustomerService customerService = new CustomerService();
    IService<Product> productIService = new ProductService();
    CartDetailService cartDetailService = new CartDetailService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerUserName") != null){
            String action = req.getParameter("action");
            switch (action){
                case "add":
                    showAddCart(req,resp);
                    break;
                case "cart":
                    showCartDetail(req, resp);
                    break;
                case "delete":
                    deleteCartDetail(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("/home");
        }

    }

    public void showAddCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        if (idLogin != -1) {
            String idProductStr = req.getParameter("idProduct");
            if (idProductStr!=null && !idProductStr.isEmpty()){
                int idProduct = Integer.parseInt(idProductStr);
                Product product = productIService.findById(idProduct);
                if (idCustomer != -1) {
                    int idCart = cartService.getIdCartByIdCustomer(idCustomer);
                    req.setAttribute("product", product);
                    req.setAttribute("idProduct",idProduct);
                    if (cartDetailService.checkExistProductInCart(idCart, idProduct)){
                        CartDetail cartDetail = cartDetailService.findByIdProduct(idCart,idProduct);
                        req.setAttribute("cartDetail",cartDetail);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/cart/edit.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/cart/add.jsp");
                        dispatcher.forward(req, resp);
                    }

                } else {
                    resp.sendRedirect("/view?action=add");
                }
            }

        } else {
            resp.sendRedirect("/loginUser");
        }
    }

    public void deleteCartDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        cartDetailIService.delete(idDelete);
        resp.sendRedirect("/cart?action=cart");
    }

    public void showCartDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        if (idCustomer != -1){
            int idCart = cartService.getIdCartByIdCustomer(idCustomer);
            List<CartDetail> cartDetailList = cartDetailService.getListCartByIdCart(idCart);
            if (cartDetailList.isEmpty()){
                req.setAttribute("errorMessage", "Giỏ hàng hiện tại đang trống!");
                req.setAttribute("cartDetailList", cartDetailList);
            } else {
                req.setAttribute("cartDetailList", cartDetailList);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/cart/cart.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/view?action=add");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "add":
                addCart(req, resp);
                break;
            case "edit":
                editCart(req, resp);
                break;
        }
    }

    public void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Product product = productIService.findById(idProduct);
        double price = product.getPrice();
        int idCart = cartService.getIdCartByIdCustomer(idCustomer);
        CartDetail cartDetail = new CartDetail(idCart,idProduct,quantity,price);
        cartDetailIService.add(cartDetail);
        resp.sendRedirect("/cart?action=cart");
    }

    public void editCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer idLogin = (Integer) session.getAttribute("idLogin");
        int idCustomer = customerService.getIdByUserId(idLogin);
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
//        Product product = productIService.findById(idProduct);
        int idCart = cartService.getIdCartByIdCustomer(idCustomer);
        CartDetail existCartDetail = cartDetailService.findByIdProduct(idCart,idProduct);
        int id = existCartDetail.getId();
        CartDetail cartDetail = new CartDetail(id,idCart,idProduct,quantity);
        cartDetailIService.update(id,cartDetail);
        resp.sendRedirect("/cart?action=cart");
    }
}
