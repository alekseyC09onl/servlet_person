package service;

import dto.PersonDTO;
import entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ConnectionManager2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {
    PersonService personService = PersonService.getInstance();
    Person expectedPerson;
    PersonDTO expectedPersonDTO;
    Person expectedNewPerson;
    String ADD_PERSON_QUERY_FOR_TEST = "select firstName, lastName, email from schema_servlet.persons where firstName = ? AND lastName = ? AND email = ?";
    Connection connection;
    Person newActualPerson;
    PersonDTO updatablePersonDTO;
    PersonDTO updatePersonDTO;
    Person checkUpdatePerson;

    @BeforeEach
    void init() {
        expectedPerson = new Person();
        expectedPerson.setFirstName("bob");
        expectedPerson.setLastName("spenser");
        expectedPerson.setEmail("bob@gmail.com");
        expectedPersonDTO = new PersonDTO(expectedPerson.getFirstName(), expectedPerson.getLastName());
        expectedNewPerson = new Person();
        expectedNewPerson.setFirstName("testFirstName");
        expectedNewPerson.setLastName("testLastName");
        expectedNewPerson.setEmail("test@mail.ru");
        connection = ConnectionManager2.get();
        updatablePersonDTO = new PersonDTO("alex", "kross");
        updatePersonDTO = new PersonDTO("testFirstName", "testLastName");
        checkUpdatePerson = new Person();
    }

    @Test
    @DisplayName("Testing test method")
    void testCalc() {
        assertEquals(4, 2 + 2);
    }

    @Test
    @DisplayName("Testing method which get person's firstname and lastname by id")
    void testGetPersonDTOById() {
        PersonDTO actualPersonDTO = personService.getPersonDTOById(7);
        assertEquals(expectedPersonDTO, actualPersonDTO);
    }

    @Test
    @DisplayName("Testing failed method which get person's firstname and lastname by id")
    void failedTestGetPersonDTOById() {
        PersonDTO actualPersonDTO = personService.getPersonDTOById(4);
        assertNotEquals(expectedPersonDTO, actualPersonDTO);
    }

    @Test
    @DisplayName("Testing method which add person to database")
    void testAddPersonService() {
        personService.addPersonService(new PersonDTO(expectedNewPerson.getFirstName(), expectedNewPerson.getLastName()), expectedNewPerson.getEmail());
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PERSON_QUERY_FOR_TEST)) {
            preparedStatement.setString(1, expectedNewPerson.getFirstName());
            preparedStatement.setString(2, expectedNewPerson.getLastName());
            preparedStatement.setString(3, expectedNewPerson.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newActualPerson = new Person(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertEquals(expectedNewPerson, newActualPerson);
    }

    @Test
    @DisplayName("Testing method which update person in database")
    void testUpdatePersonService() {
        personService.updatePersonService(updatablePersonDTO, updatePersonDTO, "test@mail.ru");
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PERSON_QUERY_FOR_TEST)) {
            preparedStatement.setString(1, updatePersonDTO.getFirstName());
            preparedStatement.setString(2, updatePersonDTO.getLastName());
            preparedStatement.setString(3, "test@mail.ru");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                checkUpdatePerson = new Person(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertEquals(new Person(updatePersonDTO.getFirstName(), updatePersonDTO.getLastName(), "test@mail.ru"), checkUpdatePerson);
    }
}
