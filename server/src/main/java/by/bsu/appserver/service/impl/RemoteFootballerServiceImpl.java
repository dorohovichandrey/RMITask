package by.bsu.appserver.service.impl;

import by.bsu.appserver.dao.TeamDAOI;
import by.bsu.appserver.dao.FootballerDAOI;
import by.bsu.appserver.dao.sql.impl.FootballerSqlDAO;
import by.bsu.appserver.dao.sql.impl.TeamSqlDAO;
import by.bsu.common.entity.Footballer;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.DAOException;
import by.bsu.common.exception.ServiceException;
import by.bsu.common.remote.RemoteFootballerService;

import java.util.List;

/**
 * Created by User on 21.03.2017.
 */
public class RemoteFootballerServiceImpl implements RemoteFootballerService {


    FootballerDAOI footballerDAO = new FootballerSqlDAO();
    TeamDAOI teamDAO = new TeamSqlDAO();

    public List<Footballer> showAll() throws ServiceException {
        try{
        return footballerDAO.findAll();
        } catch (DAOException e){
            throw new ServiceException("DAO work incorrect", e);
        }
    }

    public List<Footballer> showByTeam(Team team) throws ServiceException {
        try{
            return footballerDAO.findByTeam(team);
        } catch (DAOException e){
            throw new ServiceException("DAO work incorrect", e);
        }
    }

    public void delete(Integer id) throws ServiceException {

        try{
            footballerDAO.delete(id);
        } catch (DAOException e){
            throw new ServiceException("DAO work incorrect", e);
        }

    }

    public void create(Footballer footballer) throws ServiceException {
        try {
            String teamName = footballer.getTeam().getTeamName();
            String city = footballer.getTeam().getCity();
            Team team = initTeam(teamName, city);
            footballer.setTeam(team);
            footballerDAO.create(footballer);
        } catch (DAOException e){
            throw new ServiceException("Problem with DAO", e);
        }

    }

    private Team initTeam(String teamName, String city) throws DAOException {
        Team team = teamDAO.findByNameAndCity(teamName, city);
        if(team == null){
            teamDAO.create(new Team(teamName, city));
            team = teamDAO.findByNameAndCity(teamName, city);
        }
        return team;
    }
}
