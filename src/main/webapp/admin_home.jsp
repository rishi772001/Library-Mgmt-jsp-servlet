<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Rishi Raj G
  Date: 09-06-2021
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userId = null;
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("/login.jsp");
    } else {
        userId = session.getAttribute("userId").toString();
    }
%>

<%
    ResultSet books = (ResultSet) request.getAttribute("books");
    ResultSet currentBooks = (ResultSet) request.getAttribute("currentBooks");
    ResultSet categories = (ResultSet) request.getAttribute("categories");
%>

<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>
<body style="text-align: center;">
<%--<h1>Hii Admin, your id is <%= userId %></h1>--%>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        out.println(error);
    }
%>

<form action="logout-servlet" method="post">
    <input type="submit" value="Log out" class="btn danger logout-btn">
</form>

<div class="container" style="margin: 40px">
    <h3>Books</h3>
    <% if (books != null) { %>
    <table class="table">
        <tr>
            <th>Isbn</th>
            <th>Name</th>
            <th>Author</th>
            <th>Quantity</th>
            <th>Total Quantity</th>
            <th title="Only possible if quantity is greater than 1">Delete</th>
        </tr>
        <% while (books.next()) { %>
        <tr>
            <td><%=books.getString("book_isbn") %>
            </td>
            <td><%=books.getString("book_name") %>
            </td>
            <td><%=books.getString("book_author") %>
            </td>
            <td><%=books.getString("current_quantity") %>
            </td>
            <td><%=books.getString("book_quantity") %>
            </td>
            <td><a href="admin-home?isbn=<%=books.getString("book_isbn") %>">Delete</a></td>
        </tr>
        <% } %>
    </table>
    <% } %>
    <h3>Books Usage</h3>
    <% if (currentBooks != null) { %>
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Student</th>
            <th>Due Date</th>
            <th>Borrow Date</th>
        </tr>
        <% while (currentBooks.next()) { %>
        <tr>
            <td><%=currentBooks.getString("book_name") %>
            </td>
            <td><%=currentBooks.getString("stud_name") %>
            </td>
            <td><%=currentBooks.getString("due_date") %>
            </td>
            <td><%=currentBooks.getString("borrow_date") %>
            </td>
        </tr>
        <% } %>
    </table>
    <% } %>

    <h3>Book Categories</h3>
    <% if (categories != null) { %>
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Shelf No</th>
            <th>Floor NO</th>
        </tr>
        <% while (categories.next()) { %>
        <tr>
            <td><%=categories.getString("category_name") %>
            </td>
            <td><%=categories.getString("shelf_no") %>
            </td>
            <td><%=categories.getString("floor_no") %>
            </td>
        </tr>
        <% } %>
    </table>
    <% } %>
    <br/>
    <br/>
    <h3>Add Books</h3>
    <form action="admin-home" method="post">
        <input type="text" class="form-control" name="book_isbn" placeholder="Book isbn"><br/>
        <input type="text" class="form-control" name="book_name" placeholder="Book name"><br/>
        <input type="text" class="form-control" name="book_author" placeholder="Book author"><br/>
        <input type="text" class="form-control" name="book_category" placeholder="Book category"><br/>
        <input type="text" class="form-control" name="book_quantity" placeholder="Book quantity"><br/>
        <input type="submit" class="btn btn-primary">
    </form>
</div>
</body>
</html>
