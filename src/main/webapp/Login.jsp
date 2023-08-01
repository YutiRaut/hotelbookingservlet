<%@ page import="com.example.hotelbookingservlet.Common.ErrorUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Traveller Login</title>
    <style>
        body {
            font-family: "serif", "Segoe Print";
            background-color: #fafafa;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 40px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            text-align: center;
            font-family: "DejaVu Serif Condensed";
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            text-align: left;
        }

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        .form-group button {
            font-family: DialogInput;
            width: 30%;
            padding: 15px;
            font-size: 18px;
            background-color: violet;
            border: none;
            color: #fff;
            cursor: pointer;
            border-radius: 50px;
        }

        .form-group button:hover {
            background-color: #e9beee;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Traveller</h2>
    <% ErrorUtil errorUtil = (ErrorUtil) request.getAttribute("errorUtil");%>
    <% if (errorUtil != null && !errorUtil.getErrorMessages().isEmpty()) {%>
    <div class="error messages">
        <ul>
            <% String errorMessage =errorUtil.getErrorMessages();%>
            <li><%= errorMessage%>
            </li>
        </ul>
    </div>
    <% } %>
    <%ErrorUtil errorUtil1 = (ErrorUtil) request.getAttribute("InvalidError");%>
    <%if (errorUtil1 != null) {%>
    <%String message = errorUtil1.getErrorMessages();%>
    <div class="error-message">
        <%=message%>
    </div>
    <% } %>
    <form action="JpaLoginServlet" method="post">
        <div class="form-group">
            <b><label for="username">Username</label></b>
            <input type="text" id="username" name="username" placeholder="Enter your Email">
        </div>
        <div class="form-group">
            <b><label for="password">Password</label></b>
            <input type="password" id="password" name="password" placeholder="Enter your password">
        </div>
        <div class="form-group">
            <button type="submit">Log In</button>
        </div>

        <h5>don't have account?&nbsp;&nbsp;<a href="JPARegistrationServlet">SignUp</a></h5>
    </form>
</div>
</body>
</html>


