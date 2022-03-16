package vn.edu.hcmuaf.fit.webdt.controller;

import vn.edu.hcmuaf.fit.webdt.Service.ProductServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * day la lop servlet cua search khi bam vao nut search
 */
@WebServlet(name = "SearchController", value = "/SearchController")
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String show = "Can't not find your search!";
        String searchName = request.getParameter("searchName");
        List<Product> productList = ProductServiceWithDB.getInstance().getAll(searchName);
        request.setAttribute("productSearch", productList);
        if(productList.size() != 0){//tuc la tim duoc
            show = "Search results for: '"+searchName +"'";
        }
        request.setAttribute("show",show);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
