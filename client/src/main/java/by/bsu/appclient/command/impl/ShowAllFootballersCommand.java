package by.bsu.appclient.command.impl;

import by.bsu.appclient.command.AbstractCommand;
import by.bsu.appclient.runner.ServiceProvider;
import by.bsu.appclient.view.tablemodel.FootballersTableModel;
import by.bsu.common.entity.Footballer;
import by.bsu.common.exception.ServiceException;
import by.bsu.common.remote.RemoteFootballerService;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 21.03.2017.
 */
public class ShowAllFootballersCommand extends AbstractCommand {

    private RemoteFootballerService footballerService = ServiceProvider.getInstance();

    protected void tryExecute() throws ServiceException {
        try {
            List<Footballer> footballerList = footballerService.showAll();
            FootballersTableModel.getInstance().setFootballerList(footballerList);
            FootballersTableModel.getInstance().fireTableDataChanged();
        }catch (RemoteException e){
            throw new RuntimeException("Problem with server", e);
        }

    }
}
