<%@ page import="java.util.List" %>
<%@ page import="com.example.hotelbookingservlet.Model.Role" %>
<%@ page import="com.example.hotelbookingservlet.Common.ErrorUtil" %>
<%@ page import="com.example.hotelbookingservlet.Common.Error" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.JPARole" %>
<%@ page import="com.example.hotelbookingservlet.JPAModel.JPAUser" %>
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

   <div class="errorList">
       <%
           List<Error>errorList=(List<Error>) request.getAttribute("errorList");
           if(errorList !=null && !errorList.isEmpty()){
       %>
       <ul>
           <% for (Error error: errorList){ %>
           <div>
               <li><%=error.getMessage()%></li>
               <%}%>

           </div>
       </ul>
       <%}%>
   </div>


<%--    <%ErrorUtil errorUtil1 = (ErrorUtil) request.getAttribute("error");%>--%>
<%--    <%if (errorUtil1 != null) {%>--%>
<%--    <%List<String> message = errorUtil1.getErrorMessages();%>--%>
<%--    <div class="error-message">--%>
<%--        <%=message%>--%>
<%--    </div>--%>
<%--    <% } %>--%>
<%--   </div>--%>


<%--    &lt;%&ndash; Display errors if they exist &ndash;%&gt;--%>
<%--    <c:if test="${not empty errors}">--%>
<%--        <div class="error">--%>
<%--            <ul>--%>
<%--                <c:forEach var="error" items="${errors}">--%>
<%--                    <li>${error}</li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </c:if>--%>

    <form action="JPARegistrationServlet" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name"><br>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email"><br>
        </div>

        <div class="form-group">
            <label for="contact">Contact No:</label>
            <input type="text" id="contact" name="contact"><br>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"><br>
        </div>

        <div class="form-group">
            <label for="role">Select Your Role:</label>
            <input type="text" id="role" name="role" list="roles"><br>
            <datalist id="roles">
                <% List<JPARole> role = (List<JPARole>) request.getAttribute("Role");
                    for (JPARole role1 : role) {%>
                <option value="<%=role1.getRole()%>"><%=role1.getRoleName()%>
                </option>
                <%}%>
            </datalist>
        </div>


        <div class="form-group">
            <button type="submit">Registration</button>
        </div>
    </form>
</div>
</body>
</html>


