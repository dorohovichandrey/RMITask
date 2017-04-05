package by.bsu.appserver.dao.sql.impl;

import by.bsu.appserver.dao.FootballerDAOI;
import by.bsu.appserver.dao.TeamDAOI;
import by.bsu.common.entity.Footballer;
import by.bsu.common.entity.Team;
import by.bsu.appserver.dao.sql.AbstractSqlDAO;
import by.bsu.common.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 23.03.2017.
 */
public class FootballerSqlDAO extends AbstractSqlDAO<Integer, Footballer> implements FootballerDAOI {

    private TeamDAOI teamDAOI = new TeamSqlDAO();

    private final static String SELECTED_COLUMNS = "footballer.id, footballer.name, footballer.surname, footballer.age, footballer.id_team";
    private final static String INSERTED_COLUMNS = "footballer.name, footballer.surname, footballer.age, footballer.id_team";
    private final static String FIND_ALL = "SELECT " + SELECTED_COLUMNS + " FROM footballer WHERE footballer.deleted = 0";
    private final static String FIND_FOOTBALLERS_BY_TEAM = "SELECT " + SELECTED_COLUMNS + " FROM footballer INNER JOIN team ON footballer.id_team = team.id WHERE team.name = ? AND team.city = ? AND footballer.deleted = 0";
    private static final String DELETE_FOOTBALLER = "UPDATE footballer SET footballer.deleted = 1 WHERE footballer.id = ?";
    private static final String CREATE_FOOTBALLER = "INSERT INTO footballer (" + INSERTED_COLUMNS + ") VALUES (?, ?, ?, ?)";



    @Override
    public void update(Footballer entity) throws DAOException {

    }

    @Override
    public void create(Footballer entity) throws DAOException {
        editOperation(CREATE_FOOTBALLER, (prSt, params) -> {
            prSt.setString(1, entity.getName());
            prSt.setString(2, entity.getSurname());
            prSt.setInt(3, entity.getAge());
            prSt.setInt(4, entity.getTeam().getId());
        }, entity );
    }

    @Override
    public void delete(Integer id) throws DAOException {
        editOperation(DELETE_FOOTBALLER, ((prSt, params) -> prSt.setInt(1, id)), id);
    }

    @Override
    public List<Footballer> findAll() throws DAOException {
        return findEntityListByQuery(FIND_ALL);
    }

    @Override
    public List<Footballer> findByTeam(Team team) throws DAOException {
        return findEntityListByPrStatement(FIND_FOOTBALLERS_BY_TEAM, ((prSt, params) ->
        {
            prSt.setString(1, team.getTeamName());
            prSt.setString(2, team.getCity());
        }), team);
    }

    @Override
    protected Footballer makeEntity(ResultSet rs) throws SQLException, DAOException {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        int age = rs.getInt(4);
        int teamId = rs.getInt(5);

        Team team = teamDAOI.findById(teamId);
        return new Footballer(id, name, surname, age, team);
    }
}
