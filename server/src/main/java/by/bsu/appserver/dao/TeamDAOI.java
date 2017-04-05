package by.bsu.appserver.dao;

import by.bsu.common.entity.Team;
import by.bsu.common.exception.DAOException;

/**
 * Created by User on 21.03.2017.
 */
public interface TeamDAOI extends EntityDAOI<Integer, Team> {

    Team findById(Integer id) throws DAOException;
    Team findByNameAndCity(String name, String city) throws DAOException;

}
