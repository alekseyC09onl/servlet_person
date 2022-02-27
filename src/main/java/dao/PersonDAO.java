package dao;

import entity.Person;
import utils.ConnectionManager2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements DAO<Integer, Person> {
    private static final PersonDAO INSTANCE = new PersonDAO();
    private static final String GET_ALL_PERSONS_QUERY = "select id, firstName, lastName, email from schema_servlet.persons";
    private static final String GET_PERSON_BY_ID_QUERY = "select id, firstName, lastName, email from schema_servlet.persons where id = ?";
    private static final String ADD_PERSON_QUERY = "insert into schema_servlet.persons (firstName, lastName, email) VALUES (?, ?, ? )";
    private static final String DEL_PERSON_BY_DTO_QUERY = "delete from schema_servlet.persons where firstName = ? AND lastName = ?";
    private static final String GET_ID_PERSON_BY_DTO_QUERY = "select id from schema_servlet.persons where firstName = ? AND lastName = ?";
    private static final String UPDATE_PERSON_QUERY = "update schema_servlet.persons set firstName = ?, lastName = ?, email = ? WHERE firstName = ? AND lastName = ?;";

    private PersonDAO() {
    }

    public static PersonDAO getInstance() {
        return INSTANCE;
    }

    public void addPerson(Person person) {
        try (Connection connection = ConnectionManager2.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PERSON_QUERY);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Person findPersonById(Integer id) {
        try (Connection connection = ConnectionManager2.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PERSON_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return mapPerson(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delPersonById(Integer id) {
        try (Connection connection = ConnectionManager2.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DEL_PERSON_BY_DTO_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delPersonByDTO(Person person) {
        try (Connection connection = ConnectionManager2.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DEL_PERSON_BY_DTO_QUERY);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Person> getPersons() {
        List<Person> listPerson = new ArrayList<>();
        try (Connection connection = ConnectionManager2.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PERSONS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPerson.add(mapPerson(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPerson;
    }

    private Person mapPerson(ResultSet resultSet) {
        try {
            return new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("email")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePerson(Person oldPerson, Person newPerson) {
        try (Connection connection = ConnectionManager2.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSON_QUERY);
            preparedStatement.setString(4, oldPerson.getFirstName());
            preparedStatement.setString(5, oldPerson.getLastName());
            preparedStatement.setString(1, newPerson.getFirstName());
            preparedStatement.setString(2, newPerson.getLastName());
            preparedStatement.setString(3, newPerson.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
