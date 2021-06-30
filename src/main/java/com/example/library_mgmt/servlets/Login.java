package com.example.library_mgmt.servlets;

import com.example.library_mgmt.config.Connect;
import com.example.library_mgmt.config.Security;
import com.example.library_mgmt.models.Admin;
import com.example.library_mgmt.models.Student;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class Login extends HttpServlet {
    public void init() {}

    public static boolean login_admin(Admin admin)
    {
        try
        {
            ResultSet rs = Connect.executeSelect("select * from admin where email = '"+ admin.getEmail() +"' and password = '"+ admin.getPassword() +"'");
            if (rs != null && rs.next()) {
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean login_student(Student student)
    {
        try
        {
            ResultSet rs = Connect.executeSelect("select * from student where stud_rollno = '"+ student.getRollno() +"' and stud_password = '"+ student.getPassword() +"'");
            if (rs != null && rs.next()) {
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        String user = request.getParameter("user");

        if (user.equals("admin")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            password = Security.encrypt(password);

            // create admin
            Admin admin = new Admin(email, password);

            // admin authentication
            if (login_admin(admin)) {
                session.setAttribute("userId", request.getParameter("email"));
                response.sendRedirect("admin-home");
            } else {
                request.setAttribute("error", "Something went wrong, Please enter valid credentials and try again!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else if (user.equals("student")) {
            int rollno = -1;
            try {
                rollno = Integer.parseInt(request.getParameter("rollno"));
            } catch (NumberFormatException e){
                request.setAttribute("error", "Something went wrong, Please enter valid credentials and try again!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            String password = request.getParameter("password");

            password = Security.encrypt(password);
            Student student = new Student(rollno, password);

            if (login_student(student)) {
                session.setAttribute("userId", rollno);
                response.sendRedirect("student-home");
            } else {
                request.setAttribute("error", "Something went wrong, Please enter valid credentials and try again!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }


        }

    }
}
