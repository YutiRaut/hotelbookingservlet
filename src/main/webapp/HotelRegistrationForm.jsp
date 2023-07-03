<%@ page import="com.example.hotelbookingservlet.Model.Address" %>
<%@ page import="java.util.List" %>
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
        <form id="step1" action="HotelRegistrationServlet" method="post">
            <input type="text" id="HotelName" name="HotelName" placeholder="Enter Hotel Name" >
            <input type="text" id="LicenceNo" name="LicenceNo" placeholder="Licence Number">
            <input type="text" id="StarRating" name="StarRating" placeholder="Star Rating">
            <input type="text" id="GstNo" name="GstNo" placeholder="Gst No">
            <input type="text" id="permits" name="permits" placeholder="Permits">
            <input type="text" placeholder="Select State" id="states" list="state"><br>
            <datalist id="state" onchange="">
                <% List<Address> addresses = (List<Address>) request.getAttribute("stateID");
                    for (Address address : addresses) {%>
                <option value="<%=address.getStateId()%>"><%=address.getStateList()%>
                </option>
                <%}%>
            </datalist>
            <input type="text" placeholder="Select cityId" name="City" list="cityId"><br>
            <datalist id="cityId">
                <% if(request.getAttribute("cityID")!=null){
                    List<Address> addresses1 = (List<Address>) request.getAttribute("cityID");
                    for (Address address : addresses1) {%>
                <option value="<%=address.getCityId()%>"><%=address.getViewCity()%>
                </option>
                <%}
                }%>
            </datalist>
            <input type="text" id="address" name="address" placeholder="Address" required>
            <input type="text" id="pincode" name="pincode" placeholder="Pincode" required>
            <button type="button1">Submit</button>
        </form>
    </div>
    <div class="form-section">
        <h2>Step 2: Room Information</h2>
        <form>
            <input type="text" id="totalCount" placeholder="Total Rooms You Want To List" required>
            <input type="text" id="premium" placeholder="Premium" required>
            <input type="text" id="semiDeluxe" placeholder="Semi Deluxe Count" required>
            <input type="text" id="deluxe" placeholder="Deluxe Count" required>
            <input type="text" id="suite" placeholder="Suite Count" required>

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
            <button type="button2" onclick="">Previous</button>
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

    function submitAndNext(event){
        event.preventDefault();
        alert("Submited form and proceding to next step");
        document.getElementById("step1").submit();
    }
</script>
</body>
</html>
