package com.example.library_mgmt.models;

public class Book {
    private int bookIsbn;
    private String bookName;
    private String bookAuthor;
    private int bookQuantity;
    private String bookCategory;
    private int currentQuantity;

    public Book(){}

    public Book(int book_isbn, String book_name, String book_author, int book_quantity, String book_category, int current_quantity) {
        this.bookIsbn = book_isbn;
        this.bookName = book_name;
        this.bookAuthor = book_author;
        this.bookQuantity = book_quantity;
        this.bookCategory = book_category;
        this.currentQuantity = current_quantity;
    }

    public int getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(int bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}
