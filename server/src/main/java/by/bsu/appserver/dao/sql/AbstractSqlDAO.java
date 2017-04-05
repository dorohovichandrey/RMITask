package by.bsu.appserver.dao.sql;

import by.bsu.appserver.connectionpool.ConnectionPool;
import by.bsu.appserver.connectionpool.ProxyConnection;
import by.bsu.common.entity.Entity;
import by.bsu.common.exception.ConnectionPoolException;
import by.bsu.common.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23.03.2017.
 */
public abstract class AbstractSqlDAO<K,T extends Entity> {

     private ProxyConnection connection;



    public AbstractSqlDAO() {}

    public AbstractSqlDAO(ProxyConnection connection) {
        this.connection = connection;
    }

    public ProxyConnection getConnection() {
        return connection;
    }

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    /*public abstract List<T> findAll() throws DAOException;
    public abstract T findEntityById(K id) throws DAOException;
    public abstract void editOperation(T entity) throws DAOException;
    public abstract void update(T entity) throws DAOException;
    public abstract void delete(K id) throws DAOException;*/


    protected T findEntityByPrStatement(String query, StatementMaster master, Object... params) throws DAOException {
        List<T> entityList = findEntityListByPrStatement(query, master, params);
        T entity = (entityList.isEmpty()) ? null : entityList.get(0);
        return entity;
    }

    protected List<T> findEntityListByPrStatement(String query, StatementMaster master, Object... params) throws DAOException {

        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedSt = connection.prepareStatement(query)) {
            master.prepare(preparedSt, params);
            List<T> entityList = takeEntityListByPrStatement(preparedSt);
            return entityList;
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    protected void editOperation(String query, StatementMaster master, Object... params) throws DAOException{

        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedSt = connection.prepareStatement(query)) {
            master.prepare(preparedSt, params);
            preparedSt.executeUpdate();
        } catch (ConnectionPoolException e){
            throw new DAOException("Connection don't got", e);
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }


    private List<T> takeEntityListByPrStatement(PreparedStatement preparedSt) throws DAOException {
        try(ResultSet rs = preparedSt.executeQuery()) {
            List<T> list = makeEntityList(rs);
            return list;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    protected List<T> findEntityListByQuery(String query) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
                Statement st = connection.createStatement()) {
            List<T> questionList = takeEntityListByQuery(query, st);
            return questionList;
        } catch (SQLException e){
            throw new DAOException(e);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        }
    }

    private List<T> takeEntityListByQuery(String query, Statement st) throws SQLException, DAOException{
        try(ResultSet rs = st.executeQuery(query)) {
            List<T> entityList = makeEntityList(rs);
            return entityList;
        }
    }

    private List<T> makeEntityList(ResultSet rs) throws SQLException, DAOException{
        List<T> list = new ArrayList<T>();
        while (rs.next()) {
            T entity = makeEntity(rs);
            list.add(entity);
        }
        return list;
    }

    protected abstract T makeEntity(ResultSet rs) throws SQLException, DAOException;



}
