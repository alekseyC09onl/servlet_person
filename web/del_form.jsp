<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.02.2022
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body class="body">
<form action="DeleteForm" method="post">
    <label for="firstName">First name:</label>
    <input type="text" name="firstName" id="firstName"/><br/>
    <label for="lastName">Last name:</label>
    <input id="delln" type="text" name="lastName" id="lastName"/><br/>
    <input type="submit" value="Delete person">
</form>
</body>
</html>
