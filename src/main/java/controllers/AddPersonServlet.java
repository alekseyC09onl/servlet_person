package controllers;

import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonService;

import java.io.IOException;
@WebServlet("/addServlet")
public class AddPersonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty()) {
            PersonDTO personDTO = new PersonDTO(firstName, lastName);
            personService.addPersonService(personDTO, email);
            req.getRequestDispatcher("Success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("addPerson.jsp").forward(req, resp);
        }
    }
}
