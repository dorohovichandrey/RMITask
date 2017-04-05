package by.bsu.appserver.dao;

import by.bsu.common.entity.Footballer;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.DAOException;

import java.util.List;

/**
 * Created by User on 21.03.2017.
 */
public interface FootballerDAOI extends EntityDAOI<Integer, Footballer> {
    List<Footballer> findByTeam(Team team) throws DAOException;
}
