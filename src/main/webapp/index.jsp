<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body><%
    String name = (String) request.getAttribute("name_key");
%>
<h2> Welcome <%=name%>
</h2>
<br/>
<a href="HelloServlet">Hello Servlet</a>
</body>
</html>