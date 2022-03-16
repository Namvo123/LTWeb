package vn.edu.hcmuaf.fit.webdt.controller;

import vn.edu.hcmuaf.fit.webdt.Service.OrderServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.Service.ProductServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * day la lop servlet cua Checkout de hien so tien
 */
@WebServlet(name = "CheckoutShow", value = "/Checkout")
public class CheckoutShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        double price = Math.round(cart.getTotalPrice()*10)/10;
//        double price = cart.getTotalPrice();
        session.setAttribute("price",price);
        request.getRequestDispatcher("checkout.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
