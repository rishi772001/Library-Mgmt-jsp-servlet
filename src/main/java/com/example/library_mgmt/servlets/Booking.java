package com.example.library_mgmt.servlets;

import com.example.library_mgmt.config.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "addBooking", value = "/add-booking")
public class Booking extends HttpServlet {
    int DUE_DAYS = 30;

    public void init() {
    }

    public void borrow(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        int book_id = Integer.parseInt(request.getParameter("isbn"));
        int student_id = Integer.parseInt(session.getAttribute("userId").toString());
        String today = LocalDate.now().toString();
        String due_date = LocalDate.now().plusDays(DUE_DAYS).toString();

        String addToBorrow = "insert into borrower (stud_rollno, book_isbn, borrow_date, due_date, is_returned) values ('" + student_id + "','" + book_id + "','" + today + "','" + due_date + "', 'false')";
        String update_availability = "update books set current_quantity = current_quantity - 1 where book_isbn = '" + book_id + "' ";


        Connect.executeTransaction(new String[]{addToBorrow, update_availability});
        request.getRequestDispatcher("student-home").forward(request, response);

    }

    public void returnBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String today = LocalDate.now().toString();
        int book_id = Integer.parseInt(request.getParameter("isbn"));
        int borrowId = Integer.parseInt(request.getParameter("borrow"));

        String updateBorrow = "update borrower set is_returned = 'true', return_date = '" + today + "' where borrow_id = '" + borrowId + "'";
        String update_availability = "update books set current_quantity = current_quantity + 1 where book_isbn = '" + book_id + "' ";


        Connect.executeTransaction(new String[]{updateBorrow, update_availability});
        request.getRequestDispatcher("student-home").forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isReturn = request.getParameter("return");

        if (isReturn.equals("false")) {
            try {
                borrow(request, response);
            } catch (SQLException throwables) {
                request.setAttribute("error", "Could not borrow book, Please try again!!");
                request.getRequestDispatcher("student-home").forward(request, response);
                throwables.printStackTrace();
            }
        } else if (isReturn.equals("true")) {
            try {
                returnBook(request, response);
            } catch (SQLException throwables) {
                request.setAttribute("error", "Could not return book, Please try again!!");
                request.getRequestDispatcher("student-home").forward(request, response);
                throwables.printStackTrace();
            }
        }
    }
}
