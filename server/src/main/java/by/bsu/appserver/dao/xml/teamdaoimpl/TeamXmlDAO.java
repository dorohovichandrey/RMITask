package by.bsu.appserver.dao.xml.teamdaoimpl;

import by.bsu.appserver.dao.TeamDAOI;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.DAOException;

import java.util.Collections;
import java.util.List;

/**
 * Created by User on 28.03.2017.
 */
public class TeamXmlDAO implements TeamDAOI {

    private TeamStAXBuilder teamStAXBuilder = new TeamStAXBuilder();

    @Override
    public void create(Team entity) throws DAOException {

    }

    @Override
    public Team findById(Integer id) throws DAOException {

        List<Team> teams = teamStAXBuilder.buildTeams();
        Team teamKey = new Team();
        teamKey.setId(id);
        int index = Collections.binarySearch(teams, teamKey, ((o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }));
        return teams.get(index);
    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public Team findByNameAndCity(String name, String city) throws DAOException {
        return null;
    }

    @Override
    public void update(Team entity) throws DAOException {

    }

    @Override
    public List<Team> findAll() throws DAOException {
        return null;
    }
}
