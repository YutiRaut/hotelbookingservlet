<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Traveller Registration</title>
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
        }

        h1 {
            text-align: center;
            font-family: "DejaVu Serif Condensed";
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
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
            width: 100%;
            padding: 10px;
            font-size: 20px;
            background-color: skyblue;
            border: none;
            color: #fff;
            cursor: pointer;
            border-radius: 50px;
        }

        .form-group button:hover {
            background-color: lightskyblue;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Traveller</h1>
    <form action="Validation.jsp" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required><br>
        </div>

        <div class="form-group">
            <label for="contact">Contact No:</label>
            <input type="text" id="contact" name="contact" required><br>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
        </div>

        <div class="form-group">
            <label for="Roles">Select Your Role:</label>
            <input type="text" id="role" name="role" list="Roles">

            <datalist id="Roles">
                <option value="1" selected>Admin</option>
                <option value="2">Hotel Owner</option>
                <option value="3">Traveller</option>
            </datalist>
        </div>
        
        <div class="form-group">
            <button type="submit">Registration</button>
        </div>
    </form>
</div>
</body>
</html>


