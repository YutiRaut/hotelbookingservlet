
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Traveller</title>
    <style>
        .panel{
            background-color: #b5dee5;
            border: 1px solid #ffffff;
            padding: 20px;
            margin-bottom: 20px;


        }
        .panel-heading{
            font-weight: bold;
            font-size: 30px;
            margin-bottom: 10px;
            alignment: center;
            text-align: center;
            font-family:"DejaVu Serif";
        }
        .box{
            display: inline-block;
            width: 250px;
            height: 150px;
            background-color: rgb(222, 239, 195);
            border: 1px solid rgba(255, 255, 255, 0.87);
            margin: 20px;
            text-align: center;
            line-height: 150px;
            cursor: pointer;
            font-family: "DejaVu Serif Condensed";
            font-size: 20px;

        }

        .box2{
            position: absolute;
            margin-bottom: 50%;
            left: 30%;
            text-align: center;
        }
        .box3{
            position: absolute;
            margin-bottom: 50%;
            left:50%
        }

        .box4{
            position: absolute;
            margin-top: 18%;
            left: 30%;
        }
        .box5{
            position: absolute;
            margin-top: 18%;
            left:50%
        }




    </style>
</head>
<body>
<%
    String name = (String) session.getAttribute("namekey");
%>
<h2>Hello <%=name%>!!! </h2>

<!-- Panel -->
<div class="panel">
    <div class="panel-heading" >Welcome to Traveller</div>
</div>
<a href="HotelRegistration">
    <div class="box box2">Register Your Hotel</div>
</a>
<a href="index.jsp.">
    <div class="box box3">Modify Details</div>
</a>
<a href="Registration.jsp">
    <div class=" box box4">View Details</div>
</a>
<a href="Registration.jsp">
    <div class=" box box5">Delete</div>
</a>

</body>
</html>
