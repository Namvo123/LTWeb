package vn.edu.hcmuaf.fit.webdt.controller;

import vn.edu.hcmuaf.fit.webdt.Service.ProductServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * day la lop servlet chi tiet san pham
 */
@WebServlet(name = "SingleProduct", value = "/SingleProduct")
public class SingleProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        //
        Product product = ProductServiceWithDB.getInstance().getById(id);//lay product bang id duoc gui qua duong link
        session.setAttribute("singleProduct",product);//luu singleProduct vao session

        //
        List<String> productThumbnail = ProductServiceWithDB.getInstance().getImgById(id);//lay danh sach cac hinh
        request.setAttribute("listThumbnail",productThumbnail);//luu danh sach hinh vao session

        //lay ten category bang
        String categoryName = ProductServiceWithDB.getInstance().getNameCategoryByProduct(product);
        request.setAttribute("categoryName" , categoryName);

        request.getRequestDispatcher("single-product.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
