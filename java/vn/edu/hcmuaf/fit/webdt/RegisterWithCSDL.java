package vn.edu.hcmuaf.fit.webdt;

import vn.edu.hcmuaf.fit.webdt.Service.UserServiceWithDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//ĐÂY LÀ MỘT SERVLET ĐĂNG KÝ , THÊM DỮ LIỆU VÀO DATABASE
//DAY LA MOT SERVLET CHU KHONG PHAI MOT LOP JAVA BINH THUONG

@WebServlet(name = "RegisterWithCSDL", value = "/RegisterWithCSDL")
public class RegisterWithCSDL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String gender = request.getParameter("gender");
        String card_number = request.getParameter("card_number");

        String day_birth =year+ "-" +month+ "-" +day +" 00:00:00";

        //neu dang ky thanh cong thi tro lai trang login
        if(UserServiceWithDB.getInstance().register(username,password, email, day_birth,gender, card_number)){
            response.sendRedirect("login.jsp");
        }else{
            //neu khong thi gui mot attribute ten la error voi value la "username or password is incorred"
            request.setAttribute("error", "username has already token");
            request.getRequestDispatcher("SignUp.jsp").forward(request,response);//copy du lieu tu login.jsp (html, css)
            //va co them mot div de hien dong chu "username or password is incorred"
        }
    }
}
