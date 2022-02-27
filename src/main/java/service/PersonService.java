package service;

import dao.PersonDAO;
import dto.PersonDTO;
import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    PersonDAO personDAO = PersonDAO.getInstance();
    private static final PersonService INSTANCE = new PersonService();

    public static PersonService getInstance() {
        return INSTANCE;
    }

    public List<PersonDTO> getAllPersonList() {
        List<PersonDTO> personDTOList = new ArrayList<>();
        personDAO.getPersons().stream()
                .forEach(person -> personDTOList.add(new PersonDTO(person.getFirstName(), person.getLastName())));
        return personDTOList;
    }

    public PersonDTO getPersonDTOById(Integer id) {
        return new PersonDTO(personDAO.findPersonById(id).getFirstName(), personDAO.findPersonById(id).getLastName());
    }

    public void addPersonService(PersonDTO personDTO, String email) {
        personDAO.addPerson(new Person(personDTO.getFirstName(), personDTO.getLastName(), email));
    }

    public void delPersonByDTOService(PersonDTO personDTO) {
        personDAO.delPersonByDTO(new Person(personDTO.getFirstName(), personDTO.getLastName()));
    }

    public void updatePersonService(PersonDTO personDTO, PersonDTO newPersonDTO, String newEmail) {
        personDAO.updatePerson(new Person(personDTO.getFirstName(), personDTO.getLastName()), new Person(newPersonDTO.getFirstName(), newPersonDTO.getLastName(), newEmail));
    }
}
