<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Information</title>
    <style>
        body {
            font-family: "Times New Roman";
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
    <div class="form-group">

        <h2>Personal Information</h2>
        <ul>
            <% List<User> user = (List<User>) request.getAttribute("datalist");
                for (User user1 : user) {%>
            <label for="name">Username</label>
            <input type="text" id="name" value="<%=user1.getName()%>" readonly></input><br><br>

            <label for="email">Your Email Id</label>
            <input type="text" id="email" value="<%=user1.getEmail()%>" readonly></input><br><br>

            <label for="contact">Your Contact No</label>
            <input type="text" id="contact" value="<%=user1.getContact()%>" readonly></input><br><br>

            <label for="password">Your Password</label>
            <input type="text" id="password" value="<%=user1.getPassword()%>" readonly>
            <%}%>

        </ul>
    </div>
</div>
</body>
</html>







