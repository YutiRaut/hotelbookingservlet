<%@ page import="com.example.hotelbookingservlet.Model.Address" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.AddressEntity" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.CityEntity" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.StateEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous">
</script>
<head>
    <title> Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-section {
            display: none;
        }

        .form-section.current {
            display: block;
        }

        .form-section h2 {

            margin-bottom: 20px;
        }

        .form-section input[type="text"] {
            width: 90%;
            padding: 12px;
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 7px;
        }

        .form-section button[type="button1"] {
            width: 20%;
            padding: 15px;
            font-size: 16px;
            margin-left: 75%;
            background-color: violet;
            border: none;
            color: #fff;
            cursor: pointer;
            border-radius: 50px;
        }

        .form-section button[type="button2"] {
            width: 20%;
            padding: 15px;
            font-size: 16px;
            margin-right: 20%;
            background-color: violet;
            border: none;
            color: #fff;
            cursor: pointer;
            border-radius: 50px;
        }

        .form-section button:hover {
            background-color: #45a049;
        }

    </style>
    <script>
        $(document).on('change', '#states', function () {
            var stateID = $(this).val();
            const currURL = new URL($(location).attr('href'));
            currURL.searchParams.set("stateid", stateID)
            location.replace(currURL.toString());
        });
    </script>


</head>
<body>
<div class="container">
    <div class="form-section current">
        <h2>Hotel Information</h2>
        <form id="step1" action="JPAHotelRegistration" method="post" enctype="multipart/form-data">


            <input type="text" id="HotelName" name="HotelName" placeholder="Enter Hotel Name">
            <input type="text" id="LicenceNo" name="LicenceNo" placeholder="Licence Number">
            <input type="text" id="StarRating" name="StarRating" placeholder="Star Rating">
            <input type="text" id="GstNo" name="GstNo" placeholder="Gst No">
            <input type="text" id="permits" name="permits" placeholder="Permits">
            <input type="text" placeholder="Select State" id="states" list="state"><br>
            <datalist id="state" onchange="">
                <% List<StateEntity> stateEntities = (List<StateEntity>) request.getAttribute("stateID");
                    for (StateEntity stateEntity : stateEntities) {%>
                <option value="<%=stateEntity.getStateId()%>"><%=stateEntity.getState()%>
                </option>
                <%}%>
            </datalist>
            <input type="text" placeholder="Select cityId" name="City" list="cityId"><br>
            <datalist id="cityId">
                <% if (request.getAttribute("cityID") != null) {
                    List<CityEntity> cityEntities = (List<CityEntity>) request.getAttribute("cityID");
                    for (CityEntity cityEntity : cityEntities) {%>
                <option value="<%=cityEntity.getCityId()%>"><%=cityEntity.getCityNames()%>
                </option>
                <%
                        }
                    }
                %>
            </datalist>
            <input type="text" id="address" name="address" placeholder="Address">
            <input type="text" id="pincode" name="pincode" placeholder="Pincode">

            <label for="image">Upload Image: </label><br><br>
            <input type="file" id="image" name="image">

            <button type="button1">Submit</button>
        </form>
    </div>
</div>


</body>
</html>
