<%--
  Created by IntelliJ IDEA.
  User: rarti
  Date: 04-07-2023
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Details</title>
    <style>
        body {
            font-family: "serif", "Segoe Print";
            background-color: #fafafa;
        }

        .container {
            max-width: 300px;
            margin: 0 auto;
            padding: 20px;
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
    <form action="ViewDetailsServlet">
        <div class="form-group">
            <h3>View Personal Details</h3>
            <div class="form-group">
                <button type="submit">View</button>
            </div>
        </div>
    </form>
</div>
<br><br>

<div class="container">
    <form action="ViewHotelDetailsServlet">
        <div class="form-group">
            <h3>View Hotel Details</h3>
            <div class="form-group">
                <button type="submit">View</button>
            </div>
        </div>
    </form>
</div>
<br><br>

<div class="container">
    <form action="ViewRoomInformationServlet" >
        <div class="form-group">
            <h3>View Room Details</h3>
            <div class="form-group">
                <button type="submit">View</button>
            </div>
        </div>
    </form>
</div>
<br><br>
</body>
</html>
