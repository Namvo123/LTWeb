package vn.edu.hcmuaf.fit.webdt.controller.cart;

import com.google.gson.Gson;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

/**
 * LỚP NÀY LÀ ĐỂ THAY ĐỔI(UPDATE) SỐ LƯỢNG TRONG CART THEO Ý THÍCH
 */
@WebServlet(name = "UpdateQuantity", value = "/cart-updateQuantity")
public class UpdateQuantity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        //load cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart.get(id) == null) {//kiem tra san pham co trong
            response.setStatus(404);//neu khong co san pham thi tra ve 404
            return;
        }
        int quantity =cart.get(id).getQualitySold();//lay quantity hien co
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));//lay so quantity muon tang
        }catch (NumberFormatException e){
            response.getWriter().println(
                    new Gson().toJson(Map.of("quantity",quantity)));//neu no la chu thi tra ve so quantity hien co ban dau
            return;
        }

        int quantityAfterUpdate =cart.updateQuantitySold(id, quantity);//update quantity voi so quantity muon tang neu lon hon
                                                                    // so san pham trong shop no se tra lai tat ca san pham con lai trong shop
        session.setAttribute("cart",cart);
        response.getWriter().println(
                new Gson().toJson(Map.of("quantity",quantityAfterUpdate)));//tra ve so quantity moi


//        //cap nhat lai tong tien mot san pham
//        double totalMoney = cart.get(id).getTotalMoney();
//        session.setAttribute("cart",cart);
//        response.getWriter().println(
//                new Gson().toJson(Map.of("totalMoney",totalMoney)));
    }

}
