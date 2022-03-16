package vn.edu.hcmuaf.fit.webdt.controller.cart;

import vn.edu.hcmuaf.fit.webdt.Service.OrderServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.Service.ProductServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;
import vn.edu.hcmuaf.fit.webdt.beans.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * LỚP NÀY DÙNG ĐỂ LƯU SẢN PHẨM TRONG CART VÀO CSDL orders và order_details
 */
@WebServlet(name = "CheckoutController", value = "/cart-checkOut")
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        if(session.getAttribute("auth") == null){//neu chưa đk( đk xong nó mới lưu user với tên là auth trong session)
            response.sendRedirect("/Webdt/login.jsp");
            return;
        }
        if(session.getAttribute("cart") == null){//nếu khong co san pham nao trong cart thi quay ve lai trang cart
            response.sendRedirect("/Webdt/cart");
            return;
        }
        boolean result = OrderServiceWithDB.getInstance().createOrder((User)session.getAttribute("auth"),(Cart) session.getAttribute("cart"));
        boolean updateQuantityLest = ProductServiceWithDB.getInstance().updateQuantity((Cart) session.getAttribute("cart"));
        session.removeAttribute("cart");//sau khi thanh toan va luu vao csdl roi thi cac san pham trong cart se bi xoa
        if(result) {
            response.sendRedirect("/Webdt/HomePage");
        }else {
            response.sendRedirect("/Webdt/cart");
        }
    }
}
