package by.bsu.appserver.dao;

import by.bsu.common.entity.Entity;
import by.bsu.common.exception.DAOException;

import java.util.List;

/**
 * Created by User on 21.03.2017.
 */
public interface EntityDAOI<K,T extends Entity<K>>{
    void create(T entity) throws DAOException;
    void delete(K id) throws DAOException;
    void update(T entity) throws DAOException;
    List<T> findAll() throws DAOException;
}
