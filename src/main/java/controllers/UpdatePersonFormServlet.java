package controllers;

import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonService;

import java.io.IOException;

@WebServlet("/updatePerson")
public class UpdatePersonFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        String firstName = req.getParameter("oldFirstName");
        String lastName = req.getParameter("oldLastName");
        String newFirstName = req.getParameter("newFirstName");
        String newLastName = req.getParameter("newLastName");
        String newEmail = req.getParameter("newEmail");
        if (!firstName.isEmpty() && !lastName.isEmpty() && !newFirstName.isEmpty() && !newLastName.isEmpty() && !newEmail.isEmpty()) {
            PersonDTO personDTO = new PersonDTO(firstName, lastName);
            PersonDTO newPersonDTO = new PersonDTO(newFirstName, newLastName);
            personService.updatePersonService(personDTO, newPersonDTO, newEmail);
            req.getRequestDispatcher("Success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("update_form.jsp").forward(req, resp);
        }
    }
}
