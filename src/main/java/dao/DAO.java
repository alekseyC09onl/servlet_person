package dao;

import java.util.List;

public interface DAO < K, T> {

    void addPerson(T entity);

    T findPersonById(K id);

    void delPersonById(K id);

    void delPersonByDTO(T entity);

    List<T> getPersons();
}
