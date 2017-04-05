package by.bsu.common.remote;

import by.bsu.common.entity.Footballer;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.ServiceException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



/**
 * <code>RemoteFootballerService</code> is an interface for remote server. <p>The class implementing this interface
 * is located on the server. Using this interface appclient can have remote access to class on server.</p>
 *
 * @author Andrey Dorohovich
 * @see Remote
 */

public interface RemoteFootballerService extends Remote {


    /**
     * Finds all footballers.
     *
     * @return {@link java.util.List} of footballers
     * @throws RemoteException  if exist problem with remote invocation.
     * @throws ServiceException  if exist problem with DAO.
     */
    public List<Footballer> showAll() throws RemoteException, ServiceException;

    /**
     * Finds footballers who play in concrete team.
     * @param  team  football team
     * @return {@link java.util.List} of footballers
     * @throws RemoteException  if exist problem with remote invocation.
     * @throws ServiceException  if exist problem with DAO.
     */
    public List<Footballer> showByTeam(Team team) throws RemoteException, ServiceException;

    /**
     * Delete player from database by id.
     * @param  id  footballer's id.
     * @throws RemoteException  if exist problem with remote invocation.
     * @throws ServiceException  if exist problem with DAO.
     */
    public void delete(Integer id) throws RemoteException, ServiceException;

    /**
     * Add player to database.
     * @param  footballer added footballer
     * @throws RemoteException  if exist problem with remote invocation.
     * @throws ServiceException  if exist problem with DAO.
     */
    public void create(Footballer footballer) throws RemoteException, ServiceException;

}
