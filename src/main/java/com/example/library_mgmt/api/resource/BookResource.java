package com.example.library_mgmt.api.resource;

import com.example.library_mgmt.config.Connect;
import com.example.library_mgmt.models.Book;
import com.example.library_mgmt.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookResource {
    public List<Book> getAllBooks() throws SQLException {
        ResultSet rs = Connect.executeSelect("select * from books");
        List<Book> listOfBooks = new ArrayList<>();
        if (rs != null) {
            while(rs.next()){
                Book book = new Book();
                convertResultToBook(rs, book);
                listOfBooks.add(book);
            }
        }
        return listOfBooks;
    }

    public Book getBook(int id) throws SQLException {
        ResultSet rs = Connect.executeSelect("select * from books where book_isbn = '"+id+"'");
        Book book = new Book();
        if(rs != null && rs.next()){
            convertResultToBook(rs, book);
        }
        return book;
    }

    private void convertResultToBook(ResultSet rs, Book book) throws SQLException {
        book.setBookIsbn(rs.getInt("book_isbn"));
        book.setBookName(rs.getString("book_name"));
        book.setBookAuthor(rs.getString("book_author"));
        book.setBookQuantity(Integer.parseInt(rs.getString("book_quantity")));
        book.setCurrentQuantity(rs.getInt("current_quantity"));
        book.setBookCategory(rs.getString("book_category"));
    }
}
