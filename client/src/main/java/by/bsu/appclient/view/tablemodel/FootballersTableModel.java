package by.bsu.appclient.view.tablemodel;

import by.bsu.common.entity.Footballer;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Model detect how information will be renders in a {@link javax.swing.JTable}.
 * <table style="border: 1px solid black; border-collapse: collapse;">
 * <caption>Footballers</caption>
 * <tr>
 * <th style="border: 1px solid black; border-collapse: collapse;">id</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Name</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Surname</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Age</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Team</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">City</th>
 * </tr>
 * <tr>
 * <th style="border: 1px solid black; border-collapse: collapse;">125</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Zinedin</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Zidan</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">27</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Juventus</th>
 * <th style="border: 1px solid black; border-collapse: collapse;">Turin</th>
 * </tr>
 * </table>
 *
 * @author Andrey Dorohovich
 * @see AbstractTableModel
 */

public class FootballersTableModel extends AbstractTableModel {

    private static FootballersTableModel instance = null;

    private FootballersTableModel() {
    }

    /**
     * Return instance of {@link FootballersTableModel}.
     *
     * @return {@link FootballersTableModel}.
     */
    public static FootballersTableModel getInstance() {
        if(instance == null)
        {
            instance = new FootballersTableModel();
        }
        return instance;
    }

    String[] columnNames = {"id", "Name", "Surname", "Age", "Team", "City"};
    List<Footballer> footballerList = new ArrayList<Footballer>();


    /**
     * Return list of footballers which is rendered in table.
     *
     * @return {@link java.util.List} of footballers.
     */
    public List<Footballer> getFootballerList() {
        return footballerList;
    }

    /**
     * Set list of footballers which will be rendered in table.
     *
     * @param footballerList {@link java.util.List} of footballers.
     */
    public void setFootballerList(List<Footballer> footballerList) {
        this.footballerList = footballerList;
    }

    public int getRowCount() {
        return footballerList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object object = null;
        switch (columnIndex){
            case 0 : object = footballerList.get(rowIndex).getId(); break;
            case 1 : object = footballerList.get(rowIndex).getName(); break;
            case 2 : object = footballerList.get(rowIndex).getSurname(); break;
            case 3 : object = footballerList.get(rowIndex).getAge(); break;
            case 4 : object = footballerList.get(rowIndex).getTeam().getTeamName(); break;
            case 5 : object = footballerList.get(rowIndex).getTeam().getCity(); break;
        }
        return object;
    }
}
