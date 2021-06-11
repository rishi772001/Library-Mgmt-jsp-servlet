package com.example.library_mgmt.servlets;

import com.example.library_mgmt.config.Connect;
import com.example.library_mgmt.models.Admin;
import com.example.library_mgmt.models.Student;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "signupServlet", value = "/signup-servlet")
public class SignupServlet extends HttpServlet {

    public void init() {}

    public static boolean signup_admin(Admin admin) throws SQLException {

            ResultSet rs = Connect.executeSelect("select * from admin where email = '"+ admin.getEmail() +"'");
            if (!rs.next()) {
                return Connect.execute("Insert into admin (name, email, password) values('"+ admin.getName() +"', '"+ admin.getEmail() +"', '"+ admin.getPassword() +"')student.");
            } else {
                // user already exists
                System.out.println("user already exists");
                return false;
            }

    }

    public static boolean signup_student(Student student) throws SQLException {

            ResultSet rs = Connect.executeSelect("select * from student where stud_rollno = '"+ student.getRollno() +"'");
            if (!rs.next()) {
                return Connect.execute("Insert into student (stud_name, stud_rollno, stud_password) values('" + student.getName() + "', '" + student.getRollno() + "', '" + student.getPassword() + "')");
            } else {
                // user already exists
                System.out.println("User already exist");
                return false;
            }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email;
        int rollno;

        if(password.length() <= 6 || !password.equals(repassword)) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            if (user.equals("admin")) {
                email = request.getParameter("email");

                // craete new admin
                Admin admin = new Admin(name, email, password);

                // add admin
                try {
                    if (signup_admin(admin)) {
                        System.out.println("Signup success");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }

                    else {
                        request.setAttribute("error", "Something went wrong, Please try again by entering all required fields!!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    request.setAttribute("error", "Something went wrong, Please try again!!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }

            } else if (user.equals("student")) {
                rollno = Integer.parseInt(request.getParameter("rollno"));

                Student student = new Student(rollno, name, password);
                try {
                    if (signup_student(student)) {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Something went wrong, Please try again!!");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    request.setAttribute("error", "Something went wrong, Please try again by entering all required fields!!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        }
    }

    public void destroy() {
    }
}