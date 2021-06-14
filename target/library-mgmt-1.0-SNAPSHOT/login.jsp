<%--
  Created by IntelliJ IDEA.
  User: Rishi Raj G
  Date: 10-06-2021
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        out.println(error);
    }
%>
<div class="container" style="margin-top: 30px">
    <h2>Login</h2>
    <div class="card">
        <div class="card-body">
            <form action="login-servlet" method="post">

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">User Type</label>
                    <div class="col-sm-2">
                        <input type="radio" name="user"
                               value="admin" required>
                    </div>
                    <div class="col-sm-2"> admin</div>

                    <div class="col-sm-2">
                        <input type="radio" name="user"
                               value="student" required>
                    </div>
                    <div class="col-sm-2"> Student</div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">E - mail(Only for admin)</label>
                    <div class="col-sm-7">
                        <input type="email" class="form-control" name="email"
                               placeholder="Enter Email">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Student Roll No(Only for Students)</label>
                    <div class="col-sm-7">
                        <input type="number" class="form-control" name="rollno"
                               placeholder="Enter Roll No">
                    </div>
                </div>

                <div class=" form-group row">
                    <label class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" name="password" placeholder="Enter Password"
                               required>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="index.jsp">New user, sign up here</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
