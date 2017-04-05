package by.bsu.appclient.view.frame;

import by.bsu.appclient.command.CommandType;
import by.bsu.appclient.exception.CommandException;
import by.bsu.appclient.view.tablemodel.FootballersTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 21.03.2017.
 */
public class CommandToolPanel extends ToolPanel<CommandType> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected FootballersTableModel footballersTableModel = FootballersTableModel.getInstance();

    private static final String ENUM_NAME = "by.bsu.appclient.command.CommandType";

    public CommandToolPanel() {
        super(ENUM_NAME);
    }

    protected void initToolButtonListener(final CommandType tool, JButton toolButton) {
        toolButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tool.getCommand().execute();
                }catch (CommandException ex){
                    LOGGER.error("Error in showAllCommand", e);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
