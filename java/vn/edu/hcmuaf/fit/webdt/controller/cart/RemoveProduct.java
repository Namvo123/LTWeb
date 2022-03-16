package vn.edu.hcmuaf.fit.webdt.controller.cart;

import vn.edu.hcmuaf.fit.webdt.beans.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * LỚP NÀY DÙNG ĐỂ XÓA SẢN PHẨM KHỎI CART
 */
@WebServlet(name = "RemoveProduct", value = "/cart-remove")
public class RemoveProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        //load cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart.get(id) == null) {//neu khong co san pham trong Map cart
            response.setStatus(404);
            return;
        }
        cart.remove(id);
        session.setAttribute("cart",cart);
//        request.getRequestDispatcher("cart.jsp").forward(request,response);
    }
}
