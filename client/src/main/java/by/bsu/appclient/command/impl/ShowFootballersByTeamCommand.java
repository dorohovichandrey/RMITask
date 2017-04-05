package by.bsu.appclient.command.impl;

import by.bsu.appclient.command.AbstractCommand;
import by.bsu.appclient.runner.ServiceProvider;
import by.bsu.appclient.view.tablemodel.FootballersTableModel;
import by.bsu.common.entity.Footballer;
import by.bsu.common.entity.Team;
import by.bsu.common.exception.ServiceException;
import by.bsu.common.remote.RemoteFootballerService;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by User on 26.03.2017.
 */
public class ShowFootballersByTeamCommand extends AbstractCommand {

    private RemoteFootballerService service = ServiceProvider.getInstance();

    private JTextField teamNameTextF = new JTextField();
    private JTextField cityTextF = new JTextField();
    private JComponent[] inputs = new JComponent[]{
            new JLabel("team name"),
            teamNameTextF,
            new JLabel("city"),
            cityTextF

    };

    protected void tryExecute() throws ServiceException {
        try {
            if (JOptionPane.showConfirmDialog(null, inputs, "Find footballers by team", JOptionPane.OK_CANCEL_OPTION) == 0) {
                Team team = initTeam();
                List<Footballer> footballerList = service.showByTeam(team);
                FootballersTableModel.getInstance().setFootballerList(footballerList);
                FootballersTableModel.getInstance().fireTableDataChanged();

            }
        }catch (RemoteException e){
            throw new RuntimeException("Problem with server", e);
        }
    }

    private Team initTeam() {
        String teamName = teamNameTextF.getText();
        String city = cityTextF.getText();
        return new Team(teamName, city);
    }
}
