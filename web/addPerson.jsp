<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.02.2022
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
</head>
<body class="body">
<h1>Add person form</h1>
<link rel="stylesheet" href="css/main.css">
<form action="addServlet" method="post">
    <label for="firstName">First name:</label>
    <input type="text" name="firstName" id="firstName"/><br/>
    <label for="lastName">Last name:</label>
    <input id="addln" type="text" name="lastName" id="lastName"/><br/>
    <label for="email">Email:</label>
    <input id="form" type="email" name="email" id="email"/><br/>
    <input type="submit" value="Add person">
</form>
</body>
</html>
