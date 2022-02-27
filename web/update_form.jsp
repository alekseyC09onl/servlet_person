<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.02.2022
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body class="body">

<form action="updatePerson" method="post">
    <h2>Choose person, which you want to change</h2>
    <label for="oldFirstName">First name:</label>
    <input type="text" name="oldFirstName" id="oldFirstName"/><br/>
    <label for="oldLastName">Last name:</label>
    <input id="upln" type="text" name="oldLastName" id="oldLastName"/><br/>
    <br/>
    <h2>New persons data</h2>
    <label for="newFirstName">New first name:</label>
    <input type="text" name="newFirstName" id="newFirstName"/><br/>
    <label id="upnln" for="newLastName">New last name:</label>
    <input type="text" name="newLastName" id="newLastName"/><br/>
    <label for="newEmail">New email:</label>
    <input id="upemail" type="email" name="newEmail" id="newEmail"/><br/>
    <input type="submit" value="Update">
</form>

<form action="updatePerson" method="post">

</form>
</body>
</html>
