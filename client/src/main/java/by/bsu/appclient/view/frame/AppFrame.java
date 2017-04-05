package by.bsu.appclient.view.frame;

import by.bsu.appclient.view.tablemodel.FootballersTableModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;

/**
 * Created by User on 21.03.2017.
 */
public class AppFrame extends JFrame {

    public AppFrame(){
        super("Fotballers base");
        setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, new CommandToolPanel());
        mainPanel.add(BorderLayout.CENTER, new JScrollPane(new JTable(FootballersTableModel.getInstance())));
        //getContentPane().add(new JScrollPane(new JTable(FootballersTableModel.getInstance())));
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AppFrame();
    }
}
