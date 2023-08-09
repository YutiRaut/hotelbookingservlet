<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.Address" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.HotelEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel Information</title>
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

        .form-group input[type="text"]
        {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        .form-group button {
            font-family: DialogInput;
            width: 10%;
            margin-left: 40%;
            padding: 15px;
            font-size: 18px;
            background-color: #71e835;
            border: none;
            color: #171616;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #d5f897;
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
    </style>
</head>
<body>


<div class="panel">
    <div class="panel-heading">Hotel Information</div>
</div>

<div class="form-group">

    <a href="JPAHotelRegistration">
        <button type="button">ADD HOTEL</button>
    </a>
</div>
<div class="container">
    <div class="form-group">

        <table class="styled-table">
            <thead>
            <tr>
                <th>Hotel Name</th>
                <th>Licence No</th>
                <th>Star Rating</th>
                <th>Gst No</th>
                <th>Permites</th>
                <th>Image</th>
                <th>Address</th>
                <th>Pincode</th>
                <th>City</th>
                <th>State</th>
                <th>Edit</th>
            </thead>


            <tbody>
            <% List<HotelEntity> hotelList = (List<HotelEntity>) request.getAttribute("hotelEntities");
                for (HotelEntity hotel : hotelList) {%>

            <tr>
                <td><%=hotel.getHotelName()%>
                </td>
                <td><%=hotel.getLicenceNo()%>
                </td>
                <td><%=hotel.getStarRating()%>
                </td>
                <td><%=hotel.getGstNo()%>
                </td>
                <td><%=hotel.getPermits()%>
                </td>
                <td><img src="<%=hotel.getImage()%>" alt="No Image" width="80px" height="80px"></td>

                <td><%=hotel.getAddressEntity().getAddress()%>
                </td>
                <td><%=hotel.getAddressEntity().getPincode()%>
                </td>
                <td><%=hotel.getAddressEntity().getCityEntity().getCityNames()%>
                </td>
                <td><%=hotel.getAddressEntity().getCityEntity().getStateEntity().getState()%>
                </td>
                <td><a href="EditDetailsServlet?id=<%=hotel.getHotelId()%>">Edit</a> </td>

                <%}%>
            </tr>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
