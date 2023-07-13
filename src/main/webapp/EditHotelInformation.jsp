<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.User" %>
<%@ page import="com.example.hotelbookingservlet.Model.Hotel" %>
<%@ page import="com.example.hotelbookingservlet.Model.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous">
</script>
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


    <script>
        $(document).on('change', '#states', function () {
            var stateID = $(this).val();
            const currURL = new URL($(location).attr('href'));
            currURL.searchParams.set("stateid1", stateID)
            location.replace(currURL.toString());
        });
    </script>

</head>
<body>
<div class="container">
    <div class="form-group">
        <h2>Hotel Information</h2>
        <form action="EditDetailsServlet" method="post">
        <ul>
            <% List<Hotel> hotelList = (List<Hotel>) request.getAttribute("hotelList1");
                for (Hotel hotel : hotelList) {%>
            <label for="Hotelname">Hotel name</label>
            <input type="text" id="Hotelname" name="Hotelname" value="<%=hotel.getHotelName()%>"></input><br><br>

            <label for="Licence">Your LicenceNo</label>
            <input type="text" id="Licence" name="Licence" value="<%=hotel.getLicenceNo()%>"></input><br><br>

            <label for="starRating">Your Star Rating</label>
            <input type="text" id="starRating" name="starRating" value="<%=hotel.getStarRating()%>"></input><br><br>

            <label for="GSTNo">Your GstNo</label>
            <input type="text" id="GSTNo" name="GSTNo" value="<%=hotel.getGstNo()%>"><br><br>

            <label for="permities">Your Permities</label>
            <input type="text" id="permities" name="permities" value="<%=hotel.getPermits()%>"><br><br>

            <label for="Address">Address</label>
            <input type="text" id="Address" name="Address" value="<%=hotel.getAddressline().getAddress()%>"><br><br>

            <label for="Pincode">Pincode</label>
            <input type="text" id="Pincode" name="Pincode" value="<%=hotel.getAddressline().getPincode()%>"><br><br>
            <input type="text" placeholder="Select State" id="states" list="state"><br>
            <datalist id="state" onchange="">
                <% List<Address> addresses = (List<Address>) request.getAttribute("stateID1");
                    for (Address address : addresses) {%>
                <option value="<%=address.getStateId()%>"><%=address.getStateList()%>
                </option>
                <%}%>
            </datalist><br><br>

            <input type="text" placeholder="Select cityId" name="City" list="cityId"><br>
            <datalist id="cityId">
                <% if (request.getAttribute("cityID1") != null) {
                    List<Address> addresses1 = (List<Address>) request.getAttribute("cityID1");
                    for (Address address : addresses1) {%>
                <option value="<%=address.getCityId()%>"><%=address.getViewCity()%>
                </option>
                <%
                        }
                    }
                %>
            </datalist><br><br>
            <div class="form-group">
                <button type="submit">Add</button>
            </div>
            <%}%>

        </ul>
        </form>
    </div>
</div>
</body>
</html>







