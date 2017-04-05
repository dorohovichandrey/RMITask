package by.bsu.appserver.dao.xml.footballerdaoimpl;

import by.bsu.appserver.dao.FootballerDAOI;
import by.bsu.common.entity.Footballer;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.DAOException;

import java.util.List;

/**
 * Created by User on 29.03.2017.
 */
public class FootballerXmlDAO implements FootballerDAOI {

    @Override
    public void create(Footballer entity) throws DAOException {

    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public List<Footballer> findByTeam(Team team) throws DAOException {
        return null;
    }

    @Override
    public void update(Footballer entity) throws DAOException {

    }

    @Override
    public List<Footballer> findAll() throws DAOException {
        return null;
    }
}
