package controllers;

import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/person")
public class ShowPersonByIdServlet extends HttpServlet {
    private final PersonService personService = PersonService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer personId = null;

        if (req.getParameter("id") != null) {
            personId = Integer.parseInt(req.getParameter("id"));
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        if (personId != null) {
            PersonDTO personDTO = personService.getPersonDTOById(personId);
            PrintWriter writer = resp.getWriter();
                writer.write("<h1>Person with id " + personId + " has the following data:");
                writer.write("<table>");
                writer.write("<tr><th>First name</th><th>Last name</th></tr>");
                writer.write("<tr><td>" + personDTO.getFirstName() + "</td><td>" + personDTO.getLastName() + "</td></tr>");
        }
    }
}
