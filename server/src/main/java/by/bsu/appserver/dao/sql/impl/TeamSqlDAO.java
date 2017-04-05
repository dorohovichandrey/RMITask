package by.bsu.appserver.dao.sql.impl;

import by.bsu.appserver.dao.TeamDAOI;
import by.bsu.appserver.dao.sql.AbstractSqlDAO;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by User on 23.03.2017.
 */
public class TeamSqlDAO extends AbstractSqlDAO<Integer, Team> implements TeamDAOI {

    private static final String SELECTED_COLUMNS = "team.id, team.name, team.city";
    private static final String INSERTED_COLUMNS = "team.name, team.city";
    private static final String FIND_TEAM_BY_ID = "SELECT " + SELECTED_COLUMNS + " FROM team WHERE id = ?";
    private static final String FIND_TEAM_BY_NAME_AND_CITY = "SELECT " + SELECTED_COLUMNS + " FROM team WHERE name = ? AND city = ?";
    private static final String CREATE_TEAM = "INSERT INTO team (" + INSERTED_COLUMNS + ") VALUES (?, ?)";

    @Override
    public Team findByNameAndCity(String name, String city) throws DAOException {

        return findEntityByPrStatement(FIND_TEAM_BY_NAME_AND_CITY, ((prSt, params) -> {
            prSt.setString(1, name);
            prSt.setString(2, city);
        }), name, city);
    }

    @Override
    public void create(Team entity) throws DAOException {
            editOperation(CREATE_TEAM, ((prSt, params) -> {
                prSt.setString(1, entity.getTeamName());
                prSt.setString(2, entity.getCity());
            }) ,entity);
    }

    @Override
    public void update(Team entity) throws DAOException {

    }

    @Override
    public Team findById(Integer id) throws DAOException {
        return findEntityByPrStatement(FIND_TEAM_BY_ID, (prSt, params) -> prSt.setInt(1, id), id);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    protected Team makeEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String teamName = rs.getString(2);
        String city = rs.getString(3);
        return new Team(id, teamName, city);
    }
}
