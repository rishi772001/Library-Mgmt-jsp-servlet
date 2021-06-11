<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Rishi Raj G
  Date: 10-06-2021
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userId = null;
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
    } else {
        userId = session.getAttribute("userId").toString();
    }
%>

<%
    ResultSet books = (ResultSet) request.getAttribute("books");
    ResultSet currentBooks = (ResultSet) request.getAttribute("currentBooks");
%>


<html>
<head>
    <title>Student</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>
<body style="text-align: center;">
<%--<h1>Hii Student, your id is <%= userId %></h1>--%>
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
    <table class="table">
        <tr>
            <th>Isbn</th>
            <th>Name</th>
            <th>Author</th>
            <th>Quantity</th>
            <th>Borrow</th>
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
            <td><a href="add-booking?isbn=<%=books.getString("book_isbn")%>&return=false">Borrow</a></td>
        </tr>
        <% } %>
    </table>

    <h3>Your Books</h3>
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Due Date</th>
            <th>Borrow Date</th>
            <th>Return</th>
        </tr>
        <% while (currentBooks.next()) { %>
        <tr>
            <td><%=currentBooks.getString("book_name") %>
            </td>
            <td><%=currentBooks.getString("due_date") %>
            </td>
            <td><%=currentBooks.getString("borrow_date") %>
            </td>
            <td>
                <a href="add-booking?isbn=<%=currentBooks.getString("book_isbn")%>&return=true&borrow=<%=currentBooks.getString("borrow_id")%>">Return</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
