package vn.edu.hcmuaf.fit.webdt;

import vn.edu.hcmuaf.fit.webdt.Service.UserServiceWithDB;
import vn.edu.hcmuaf.fit.webdt.beans.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//ĐÂY LÀ MỘT SERVLET ĐĂNG NHẬP VỚI DỮ LIỆU TỪ DATABASE
//DAY LA MOT SERVLET CHU KHONG PHAI MOT LOP JAVA BINH THUONG

@WebServlet(name = "loginWithCSDL", value = "/loginWithCSDL")
public class loginWithCSDL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        User user =UserServiceWithDB.getInstance().checkLogin(userName,password);
        if(user != null){
            //set(gui) session user
            HttpSession session = request.getSession();
            session.setAttribute("auth", user);
            //check neu co username va password trong db thi chuyen den trang index.jsp
            response.sendRedirect("/Webdt/HomePage");
//            response.sendRedirect("index2.jsp");

        }else{
            //neu khong thi gui mot attribute ten la error voi value la "username or password is incorred"
            request.setAttribute("error", "username or password is incorred");
            request.getRequestDispatcher("login.jsp").forward(request, response);//copy du lieu tu login.jsp (html, css)
            //va co them mot div de hien dong chu "username or password is incorred"
        }
    }
}
