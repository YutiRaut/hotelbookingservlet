<%@ page import="com.example.hotelbookingservlet.Model.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Information</title>
    <style>
        body {
            font-family: "Times New Roman";
            background-color: #fafafa;
        }
        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: inline-block;
            margin-bottom: 5px;
            text-align: center;
            font-size: 20px;
        }

        .form-group input[type="text"]
        {
            display: inline-block;
            width: 20%;
            padding: 10px;
            font-size: 16px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        .form-group button {
            display: inline-block;
            font-family: DialogInput;
            width: 10%;
            padding: 10px;
            font-size: 18px;
            background-color: #71e835;
            border: none;
            color: #171616;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #d5f897;
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

    <script>
        $(document).on('change', '#hotel', function () {
            var hotelID = $(this).val();
            const currURL = new URL($(location).attr('href'));
            currURL.searchParams.set("hotelID", hotelID)
            location.replace(currURL.toString());
        });
    </script>

</head>
<body>

<div class="panel">
    <div class="panel-heading">Room Information</div>
</div>

<div class="form-group label">
    <form action="HotelNameServlet" method="post">
        <select id="hotel" name="hotel">
            <option value="" disabled selected>Select a hotel</option>
            <% List<Hotel> hotelName = (List<Hotel>) request.getAttribute("hotelName");
                for (Hotel hotel : hotelName) { %>
            <option value="<%= hotel.getHotelId() %>"><%= hotel.getHotelName() %></option>
            <% } %>
        </select><br><br>
        <input type="submit" value="Submit">
    </form>
</div>

</div>
<div class="form-group">
    <a href="RoomRegistrationServlet">

            <button type="button">ADD ROOM</button>
    </a>
</div>

<div class="form-group">



    <div class="form-group">

        <table class="styled-table">
            <thead>
            <tr>
                <th>Hotel Name</th>
                <th>Room Type</th>
                <th>Room Count</th>
                <th>No Of People</th>
                <th>Aminities</th>
                <th>Room Price</th>
                <th>images</th>
                <th>Edit</th>


            </thead>

          <tbody>
           <% if (request.getAttribute("roomList") != null) {
               List<Room> roomList = (List<Room>) request.getAttribute("roomList");
               for (Room room: roomList) {%>

         <tr>
               <td><%=room.getHoteldata().getHotelName()%></td>
               <td><%=room.getRoomType()%></td>
                <td><%=room.getRoomCount()%></td>
               <td><%=room.getNoOfPeople()%></td>
               <td><%=room.getAminities()%></td>
               <td><%=room.getRoomPrice()%></td>
             <td><img src="<%=room.getImage()%>" alt="No Image" width="80px" height="80px"></td>

              <td><a href="EditRoomServlet?id=<%=room.getRoomid()%>">Edit</a> </td>
               <%}
               }%>
           </tr>
           </tbody>

        </table>
    </div>
</div>
</body>
</html>








