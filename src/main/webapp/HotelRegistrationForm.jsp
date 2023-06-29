<%@ page import="com.example.hotelbookingservlet.Model.Address" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rarti
  Date: 28-06-2023
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
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

        .steps {
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
        }

        .step {
            flex-grow: 1;
            text-align: center;
            color: #999999;
        }

        .step.active {
            color: #000000;
            font-weight: bold;
        }

        .progress-bar {
            width: 100%;
            background-color: #f1f1f1;
            border-radius: 5px;
            height: 10px;
            margin-bottom: 20px;
        }

        .progress {
            width: 0%;
            height: 100%;
            background-color: #4CAF50;
            border-radius: 5px;
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
    <div class="steps">
        <div class="step active">Step 1</div>
        <div class="step">Step 2</div>
        <div class="step">Step 3</div>
    </div>
    <div class="progress-bar">
        <div class="progress"></div>
    </div>
    <div class="form-section current">
        <h2>Step 1: Hotel Information</h2>
        <form action="HotelRegistration" method="post">
            <input type="text" placeholder="Enter Hotel Name" required>
            <input type="text" placeholder="Licence Number" required>
            <input type="text" placeholder="Star Rating" required>
            <input type="text" placeholder="Gst No" required>
            <input type="text" placeholder="Permits" required>
            <input type="text" placeholder="Select State" id="states" list="state" required><br>
            <datalist id="state">
                <% List<Address> addresses = (List<Address>) request.getAttribute("stateID");
                    for (Address address : addresses) {%>
                <option value="<%=address.getState_id()%>"><%=address.getViewState()%>
                </option>
                <%}%>
            </datalist>
            <input type="text" placeholder="Select city" name="City" list="city" required><br>
            <datalist id="city">
                <% if(request.getAttribute("cityID")!=null){
                    List<Address> addresses1 = (List<Address>) request.getAttribute("cityID");
                    for (Address address : addresses1) {%>
                <option value="<%=address.getCity()%>"><%=address.getViewCity()%>
                </option>
                <%}
                }%>
            </datalist>

            <input type="text" placeholder="Address" required>
            <input type="text" placeholder="Pincode" required>
            <button type="button1" onclick="nextStep()">Next</button>
        </form>
    </div>
    <div class="form-section">
        <h2>Step 2: Room Information</h2>
        <form>
            <input type="text" placeholder="Total Rooms You Want To List" required>
            <input type="text" placeholder="Premium" required>
            <input type="text" placeholder="Semi Deluxe Count" required>
            <input type="text" placeholder="Deluxe Count" required>
            <input type="text" placeholder="Suite Count" required>

            <button type="button2" onclick="previousStep()">Previous</button>
            <button type="button1" onclick="nextStep()">Next</button>

        </form>
    </div>

    <div class="form-section">
        <h2>Step 3:Room Amenity</h2>
        <form>
            <input type="text" placeholder="Premium Amenity " required>
            <input type="text" placeholder="Semi Deluxe Amenity" required>
            <input type="text" placeholder="Deluxe Amenity" required>
            <input type="text" placeholder="Suite Amenity" required>
            <button type="button2" onclick="previousStep()">Previous</button>
            <button type="button1">Submit</button>
        </form>
    </div>
</div>

<script>
    const formSections = document.querySelectorAll('.form-section');
    const progress = document.querySelector('.progress');
    const steps = document.querySelectorAll('.step');

    let currentStep = 0;

    function nextStep() {
        if (currentStep < formSections.length - 1) {
            formSections[currentStep].classList.remove('current');
            currentStep++;
            formSections[currentStep].classList.add('current');
            updateProgress();
            updateSteps();
        }
    }

    function previousStep() {
        if (currentStep > 0) {
            formSections[currentStep].classList.remove('current');
            currentStep--;
            formSections[currentStep].classList.add('current');
            updateProgress();
            updateSteps();
        }
    }

    function updateProgress() {
        const percent = (currentStep / (formSections.length - 1)) * 100;
        progress.style.width = percent + '%';
    }

    function updateSteps() {
        steps.forEach((step, index) => {
            if (index === currentStep) {
                step.classList.add('active');
            } else {
                step.classList.remove('active');
            }
        });
    }
</script>
</body>
</html>