package vn.edu.hcmuaf.fit.webdt.controller;

import vn.edu.hcmuaf.fit.webdt.Service.ProductServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * lop nay la lop servlet cua shop.jsp lay du lieu product tu lop ProductService
 */
@WebServlet(name = "ProductListController", value = "/ProductListController")
public class ProductListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_category = Integer.parseInt(request.getParameter("id"));//nhan id tu category.jsp
        //lay list product nao co id nhu id_category
        List<Product> productList = ProductServiceWithDB.getInstance().getProductListByIdCategory(id_category);
        request.setAttribute("productS", productList);

        //lay ten category bang id_category
        String nameCategory = ProductServiceWithDB.getInstance().getNameCategoryByID(id_category);
        request.setAttribute("categoryName",nameCategory);

        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
