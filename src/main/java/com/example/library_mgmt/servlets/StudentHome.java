package com.example.library_mgmt.servlets;

import com.example.library_mgmt.config.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name="StudentHome", value = "/student-home")
public class StudentHome extends HttpServlet {
    public void init() {}

    public static ResultSet getStudentBooks(int stud_id){
        String getBooks = "select * from borrower inner join books on borrower.book_isbn = books.book_isbn where stud_rollno = '"+stud_id+"' and is_returned = 'false'";
        ResultSet rs = null;
        try {
            rs = Connect.executeSelect(getBooks);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stud_id = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        request.setAttribute("books", Connect.read_books());
        request.setAttribute("currentBooks", getStudentBooks(stud_id));
        request.getRequestDispatcher("student_home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}