package vn.edu.hcmuaf.fit.webdt.controller.cart;

import vn.edu.hcmuaf.fit.webdt.beans.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * LỚP NÀY LÀ 1 LỚP SERVLET DÙNG ĐỂ HIỆN SẢN PHẨM ĐƯỢC THÊM VÀO CART
 */
@WebServlet(name = "ShowProduct", value = "/cart")
public class ShowProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get cart from session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null){
            cart = Cart.getInstance();
        }
        session.setAttribute("cart",cart);//add cart vao session
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
