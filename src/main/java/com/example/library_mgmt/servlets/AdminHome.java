package com.example.library_mgmt.servlets;

import com.example.library_mgmt.config.Connect;
import com.example.library_mgmt.models.Book;
import com.example.library_mgmt.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name="AdminHome", value = "/admin-home")
public class AdminHome extends HttpServlet {
    public void init(){}

    public static ResultSet readCurrentBooks() {
        String getCurrentBooks = "select * from borrower inner join student on borrower.stud_rollno = student.stud_rollno inner join books on borrower.book_isbn = books.book_isbn where borrower.is_returned = 'false'";
        try {
            return Connect.executeSelect(getCurrentBooks);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static ResultSet readCategories() {
        String getCategories = "select * from category";
        try {
            return Connect.executeSelect(getCategories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static boolean isExistingBook(int isbn) {
        String check = "select * from books where book_isbn = '"+isbn+"'";
        try {
            ResultSet rs = Connect.executeSelect(check);
            if(rs != null && rs.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void addBooks(Book book) throws SQLException, NumberFormatException {
        int book_isbn = book.getBookIsbn();
        if(!isExistingBook(book_isbn)){
            String addNewBook = "Insert into books values('"+book_isbn+"','"+book.getBookName()+"','"+book.getBookAuthor()+"','"+book.getBookQuantity()+"','"+book.getBookCategory()+"', '"+book.getBookQuantity()+"')";
            Connect.execute(addNewBook);
        } else {
            String updateBook = "update books set current_quantity = current_quantity + '"+book.getBookQuantity()+"', book_quantity = book_quantity + '"+book.getBookQuantity()+"' where book_isbn = '"+book_isbn+"'";
            Connect.execute(updateBook);
        }
    }

    public static void deleteBook(int isbn) throws SQLException {
        String getQuantity = "select * from books where book_isbn = '"+isbn+"'";
        ResultSet quantity = Connect.executeSelect(getQuantity);
        if (quantity != null) {
            quantity.next();
            int count = quantity.getInt("current_quantity");
            int total = quantity.getInt("book_quantity");
            if(total == 1 && count == 1){
                String delete = "delete from books where book_isbn = '"+isbn+"'";
                Connect.execute(delete);
            }
            else if(count >= 1) {
                String delete = "update books set book_quantity = book_quantity - 1, current_quantity = current_quantity - 1 where book_isbn = '"+isbn+"'";
                Connect.execute(delete);
            }
        }
    }

    public void addCategory(Category category) throws SQLException {
        String addCategory = "insert into category values('"+category.getCategory_name()+"', '"+category.getShelfNo()+"', '"+category.getFloorNo()+"')";
        Connect.execute(addCategory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        if(isbn != null){
            try {
                deleteBook(Integer.parseInt(isbn));
                response.sendRedirect("admin-home");
            } catch (SQLException throwables) {
                System.out.println("Could not delete book!!");
                throwables.printStackTrace();
            }
        } else {
            request.setAttribute("books", Connect.read_books());
            ResultSet currentBooks = readCurrentBooks();
            ResultSet categories = readCategories();
            if(request.getAttribute("error") != null)
                request.setAttribute("error", "Something went wrong, please try again!!");
            request.setAttribute("categories", categories);
            request.setAttribute("currentBooks", currentBooks);
            request.getRequestDispatcher("admin_home.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("book_name");
        String author = request.getParameter("book_author");
        String category = request.getParameter("book_category");
        int isbn = -1;
        int quantity = -1;
        try {
            isbn = Integer.parseInt(request.getParameter("book_isbn"));
            quantity = Integer.parseInt(request.getParameter("book_quantity"));
        } catch (NumberFormatException e){
            e.printStackTrace();
            response.sendRedirect("admin-home?error=true");
        }

        // add category
        String categoryName = request.getParameter("category_name");
        if(categoryName != null){
            String floorNo = request.getParameter("floor_no");
            String shelfNo = request.getParameter("shelf_no");
            Category categoryObj = new Category(categoryName, shelfNo, floorNo);
            try {
                addCategory(categoryObj);
            } catch (SQLException throwables) {
                response.sendRedirect("admin-home?error=true");
                throwables.printStackTrace();
            }
        }
        // add book
        else {
            try {
                Book book = new Book(isbn, name, author, quantity, category, quantity);
                addBooks(book);
                response.sendRedirect("admin-home");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                response.sendRedirect("admin-home?error=true");
            }
        }

    }
}
