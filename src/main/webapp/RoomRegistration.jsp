<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rarti
  Date: 04-07-2023
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RoomRegistration</title>
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
    <form action="RoomRegistrationServlet" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <h2>Room Information</h2>


                    <select id="hotel" name="hotel">
                        <option value="" disabled selected>Select a hotel</option>
                        <% List<Hotel> hotelName = (List<Hotel>) request.getAttribute("hotelName");
                            for (Hotel hotel : hotelName) { %>
                        <option value="<%= hotel.getHotelId() %>"><%= hotel.getHotelName() %></option>
                        <% } %>
                    </select><br><br>

            <input type="text" name="Premium" value="Premium" readonly>
            <input type="text" name="premiumCount" placeholder="Premiumcount">
            <input type="text" name="prePrice" placeholder="PricePerDay">
            <input type="text" name="preNoP" placeholder="No Of People">
            <input type="text" name="preFacility" placeholder="Provided Facility">
            <label for="preimage">Upload Image: </label>
            <input type="file" id="preimage" name="preimage"><br><br>

            <input type="text" name="SemiDeluxe" value="Semi Deluxe" readonly>
            <input type="text" name="semiCount" placeholder="Semi Deluxe Count">
            <input type="text" name="semiPrice" placeholder="PricePerDay">
            <input type="text" name="semiNoP" placeholder="No Of People">
            <input type="text" name="semiFacility" placeholder="Provided Facility">
            <label for="semiimage">Upload Image: </label>
            <input type="file" id="semiimage" name="semiimage"><br><br>


            <input type="text" name="Deluxe" value="Deluxe" readonly>
            <input type="text" name="deluxeCount" placeholder="Deluxe Count">
            <input type="text" name="deluxePrice" placeholder="PricePerDay">
            <input type="text" name="deluxeNoP" placeholder="No Of People">
            <input type="text" name="deluxeFacility" placeholder="Provided Facility">
            <label for="deimage">Upload Image: </label>
            <input type="file" id="deimage" name="deimage"><br><br>

            <input type="text" name="Suite" value="Suite" readonly>
            <input type="text" name="suiteCount" placeholder="Suite Count">
            <input type="text" name="suitePrice" placeholder="PricePerDay">
            <input type="text" name="suitNoP" placeholder="No Of People">
            <input type="text" name="suiteFacility" placeholder="Provided Facility">
            <label for="suitimage">Upload Image: </label>
            <input type="file" id="suitimage" name="suitimage"><br><br>
        </div>
        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>

</body>
</html>
