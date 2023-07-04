<%@ page import="com.example.hotelbookingservlet.Model.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Information</title>
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
        <ul>
            <% List<Room> roomList = (List<Room>) request.getAttribute("roomList");
                for (Room room : roomList) {%>
            <label for="name">Room Type</label>
            <input type="text" id="name" value="<%=room.getRoomType()%>" readonly></input><br><br>

            <label for="count">Room Count</label>
            <input type="text" id="count" value="<%=room.getRoomCount()%>" readonly></input><br><br>

            <label for="NOP">Number Of People</label>
            <input type="text" id="NOP" value="<%=room.getNoOfPeople()%>" readonly></input><br><br>

            <label for="aminitie">Room Aminities</label>
            <input type="text" id="aminitie" value="<%=room.getAminities()%>" readonly ><br><br>

            <label for="roomPrice">Room Price</label>
            <input type="text" id="roomPrice" value="<%=room.getRoomPrice()%>" readonly>
            <%}%>
        </ul>
    </div>
</div>
</body>
</html>








