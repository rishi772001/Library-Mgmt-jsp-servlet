<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>JSP - Hello World</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");

    if(error != null)
    {
        out.println(error);
    }
%>

<div class="container">
    <h1>Register Form:</h1>
    <div class="card">
        <div class="card-body">
            <form action="signup-servlet" method="post">

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="name"
                               placeholder="Enter Name" required>
                    </div>
                </div>


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
                        <input type="password" class="form-control" name="password" placeholder="Enter Password" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Re - enter Password</label>
                    <div class="col-sm-7">
                        <input type="password" class="form-control" name="repassword"
                               placeholder="Re Enter Password" required>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="login.jsp">Already a user, login here</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>