<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Information</title>
    <style>
        body {
            font-family: "Times New Roman";
            background-color: #fafafa;
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
        .styled-table {
            width: 100%;
            border-collapse: collapse;
            font-family: Arial, sans-serif;
        }

        .styled-table thead th {
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            padding: 8px;
        }

        .styled-table tbody td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        .styled-table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .styled-table tbody tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-group">

        <h2>Hotel Information</h2>
       <table class="styled-table">
           <thead>
           <tr>
               <th>Hotel Name</th>
               <th>Licence No</th>
               <th>Star Rating</th>
               <th>Gst No</th>
               <th>Permites</th>
               <th>Address</th>
               <th>Pincode</th>
               <th>City</th>
               <th>State</th>
           </tr>
           </thead>
           <tbody>


           <tr>

               <td></td>
           </tr>

           </tbody>

        </table>
    </div>
</div>
</body>
</html>









