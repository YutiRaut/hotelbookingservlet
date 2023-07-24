<%@ page import="com.example.hotelbookingservlet.Model.User" %>
<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.JPAUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Traveller</title>
    <style>
        body {
            font-family: "serif", "Segoe Print";
            background-color: #fafafa;
        }

        .panel {
            background-color: #b5dee5;
            border: 1px solid #ffffff;
            padding: 20px;
            margin-bottom: 20px;
        }

        .panel-heading {
            font-weight: bold;
            font-size: 30px;
            margin-bottom: 10px;
            alignment: center;
            text-align: center;
            font-family: "DejaVu Serif";
        }



        .form-section button[type="button1"] {
            display: inline-block;
            font-family: "DejaVu Serif Condensed";
            width: 15%;
            padding: 15px;
            font-size: 18px;
            margin-left:35%;
            background-color: #acdc60;
            border: none;
            color: #fff;
            cursor: pointer;

        }

        .form-section button[type="button2"] {
            display: inline-block;
            font-family: "DejaVu Serif Condensed";
            width: 15%;
            padding: 15px;
            font-size: 18px;
            background-color: violet;
            border: none;
            color: #fff;
            cursor: pointer;

        }

        .form-section button:hover {
            background-color: #b8dcba;
        }

    </style>
</head>
<body>
<%
    String name = ((JPAUser) session.getAttribute("CurrentUser")).getName();
%>
<h2>Hello <%=name%>!!! </h2>

<!-- Panel -->
<div class="panel">
    <div class="panel-heading">Welcome to Traveller</div>
</div>

<div class="form-section">

    <a href="ViewHotelDetailsServlet">
        <button type="button1">Hotel Management</button>
    </a>

    <a href="HotelNameServlet">
        <button type="button2">Room Management</button>
    </a>

</div>








</div>
</body>
</html>

