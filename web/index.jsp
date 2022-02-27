<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page import="service.PersonService" %>
<%@ page import="dto.PersonDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2022
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="css/main.css">
  </head>
  <body class="body">
  <form class="form" action="startServlet" method="post">
    <input type="submit" value="Add person">
  </form>
  <form action="updPerson" method="post">
    <input type="submit" value="Update person">
  </form>
  <form action="delPerson" method="post">
    <input type="submit" value="Delete person">
  </form>

  <table id="table">
    <caption>Persons</caption>
    <% PersonService personService = PersonService.getInstance();
      List<PersonDTO> personDTOList = personService.getAllPersonList();

    %>

    <tr><th>First name</th><th>Last name</th></tr>
    <%
      for (PersonDTO personDTO : personDTOList) {
    %>
    <tr><th><% out.print(personDTO.getFirstName());%></th><th><% out.print(personDTO.getLastName());%></th></tr>
    <%
      }
    %>
  </table>
  </body>
</html>
