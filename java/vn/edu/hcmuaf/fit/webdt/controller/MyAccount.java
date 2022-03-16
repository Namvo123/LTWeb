package vn.edu.hcmuaf.fit.webdt.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MyAccount", value = "/MyAccount")
public class MyAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("auth") != null) {//neu chua dk thi qua trang login
            request.getRequestDispatcher("myAccount.jsp").forward(request,response);
            return;//return de no khong xuong phia duoi
        }
        response.sendRedirect("/Webdt/login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
