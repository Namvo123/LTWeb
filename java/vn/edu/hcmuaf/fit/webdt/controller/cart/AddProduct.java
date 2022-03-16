package vn.edu.hcmuaf.fit.webdt.controller.cart;

import vn.edu.hcmuaf.fit.webdt.Service.ProductServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;
import vn.edu.hcmuaf.fit.webdt.beans.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/**
 * LỚP NÀY LÀ 1 LỚP SERVLET DÙNG ĐỂ THÊM SẢN PHẨM VÀO CART
 */
@WebServlet(name = "AddProduct", value = "/Cart-add")
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get product id from request
        int id = Integer.parseInt(request.getParameter("id"));//nhan int id tu shop.jsp theo duong link :href="Cart-add?id=${p.id}"
        Product product = ProductServiceWithDB.getInstance().getById(id);
        if(product.getQualitySold() == 0) {//vì lúc đầu quality của nó bằng 0 nên nếu nó bằng 0 thì set nó =1
            product.setQualitySold(1);
        }
        if(product != null){
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if(cart == null){
                cart = Cart.getInstance();
            }
            cart.put(product);
            session.setAttribute("cart",cart);//add session card
        }
//        response.sendRedirect("/Webdt/cart");
//        request.getRequestDispatcher("/ProductListController").forward(request,response);
        request.getRequestDispatcher("/cart").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
