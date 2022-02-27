package controllers;

import dto.PersonDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonService;

import java.io.IOException;

@WebServlet("/DeleteForm")
public class DeletePersonFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            PersonDTO personDTO = new PersonDTO(firstName, lastName);
            personService.delPersonByDTOService(personDTO);
            req.getRequestDispatcher("Success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("del_form.jsp").forward(req, resp);
        }

    }
}
