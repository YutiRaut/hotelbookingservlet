<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validation Page</title>
</head>
<body>
<%!
    private static final String EMAIL_REGIX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";%>
<%
    String email = request.getParameter("email");
    if (!email.matches(EMAIL_REGIX)) {
        PrintWriter pd = response.getWriter();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/RegistrationServlet.jsp");
        requestDispatcher.include(request, response);
        response.setContentType("text/html");
        pd.println("<center><h2 style='color:red'>Invalid Email Format!!!</h2></center>");
    } else {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegistrationServlet");
        requestDispatcher.forward(request, response);
    }
%>
</body>
</html>
