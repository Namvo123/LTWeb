package vn.edu.hcmuaf.fit.webdt.controller;

import vn.edu.hcmuaf.fit.webdt.Service.ContactServiceWithDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Contact", value = "/Contact")
public class Contact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String email = request.getParameter("emails");
        String sub = request.getParameter("sub");
        String message = request.getParameter("message");

        boolean result = ContactServiceWithDB.getInstance().saveContact(name, email, sub, message);
        if(result){
            response.sendRedirect("/Webdt/HomePage");
        }
    }
}
