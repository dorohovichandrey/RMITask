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
 * Created by User on 21.03.2017.
 */
public class AddFootballerCommand extends AbstractCommand {

    private RemoteFootballerService service = ServiceProvider.getInstance();

    private JTextField nameTextF = new JTextField();
    private JTextField surnameTextF = new JTextField();
    private JTextField ageTextF = new JTextField();
    private JTextField teamNameTextF = new JTextField();
    private JTextField cityTextF = new JTextField();

    private JComponent[] inputs = new JComponent[]{
            new JLabel("name"),
            nameTextF,
            new JLabel("surname"),
            surnameTextF,
            new JLabel("age"),
            ageTextF,
            new JLabel("team name"),
            teamNameTextF,
            new JLabel("city"),
            cityTextF

    };

    protected void tryExecute() throws ServiceException {
        try {
            if (JOptionPane.showConfirmDialog(null, inputs, "Add footballer", JOptionPane.OK_CANCEL_OPTION) == 0) {
                Footballer footballer = initFootballer();
                service.create(footballer);
                List<Footballer> footballerList = service.showAll();
                FootballersTableModel.getInstance().setFootballerList(footballerList);
                FootballersTableModel.getInstance().fireTableDataChanged();

            }
        } catch (RemoteException e){
            throw new ServiceException("Prpblems with connection",e);
        }
    }

    private Footballer initFootballer() {
        String name = nameTextF.getText();
        String surname = surnameTextF.getText();
        Integer age = Integer.valueOf(ageTextF.getText());
        Team team = initTeam();
        return new Footballer(name, surname, age, team);

    }

    private Team initTeam() {
        String teamName = teamNameTextF.getText();
        String city = cityTextF.getText();
        return new Team(teamName, city);
    }
}

