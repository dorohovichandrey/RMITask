package by.bsu.appclient.command.impl;

import by.bsu.appclient.command.AbstractCommand;
import by.bsu.appclient.runner.ServiceProvider;
import by.bsu.appclient.view.tablemodel.FootballersTableModel;
import by.bsu.common.entity.Footballer;
import by.bsu.common.exception.ServiceException;
import by.bsu.common.remote.RemoteFootballerService;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 21.03.2017.
 */
public class DeleteFootballerByIdCommand extends AbstractCommand {

    private RemoteFootballerService service = ServiceProvider.getInstance();

    private JTextField idTextF = new JTextField();
    private JComponent[] inputs = new JComponent[]{
                new JLabel("id"),
                idTextF

    };

    protected void tryExecute() throws ServiceException {
        try {
            if (JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION) == 0) {
                Integer id = Integer.valueOf(idTextF.getText());
                service.delete(id);
                List<Footballer> footballerList = service.showAll();
                FootballersTableModel.getInstance().setFootballerList(footballerList);
                FootballersTableModel.getInstance().fireTableDataChanged();

            }
        } catch (RemoteException e){
            throw new ServiceException("Prpblems with connection",e);
        }
    }
}

