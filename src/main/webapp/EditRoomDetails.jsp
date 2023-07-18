<%--
  Created by IntelliJ IDEA.
  User: rarti
  Date: 07-07-2023
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.User" %>
<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page import="com.example.hotelbookingservlet.Model.Room" %>
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
        <h2>Room Information</h2>
        <form action="EditRoomServlet" method="post">
            <ul>
                <% List<Room> roomList1 = (List<Room>) request.getAttribute("roomList1");
                    for (Room room: roomList1) {%>

                <label for="roomName">Room Type</label>
                <input type="text" id="roomName" name="roomName" value="<%=room.getRoomType()%>" readonly></input><br><br>

                <label for="Count">Room Count</label>
                <input type="text" id="Count" name="Count" value="<%=room.getRoomCount()%>"></input><br><br>

                <label for="NOP">No of People</label>
                <input type="text" id="NOP" name="NOP" value="<%=room.getNoOfPeople()%>"></input><br><br>

                <label for="aminities">Aminities</label>
                <input type="text" id="aminities" name="aminities" value="<%=room.getAminities()%>"></input><br><br>

                <label for="roomPrice">Room Price</label>
                <input type="text" id="roomPrice" name="roomPrice" value="<%=room.getRoomPrice()%>"><br><br>

                <div class="form-group">
                    <button type="submit">Save</button>
                </div>
                <%}%>

            </ul>
        </form>
    </div>
</div>
</body>
</html>







