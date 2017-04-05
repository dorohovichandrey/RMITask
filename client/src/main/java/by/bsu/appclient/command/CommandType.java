package by.bsu.appclient.command;

import by.bsu.appclient.command.impl.AddFootballerCommand;
import by.bsu.appclient.command.impl.DeleteFootballerByIdCommand;
import by.bsu.appclient.command.impl.ShowAllFootballersCommand;
import by.bsu.appclient.command.impl.ShowFootballersByTeamCommand;

/**
 * Created by User on 21.03.2017.
 */
public enum CommandType {
    SHOW_ALL(new ShowAllFootballersCommand()),
    ADD(new AddFootballerCommand()),
    DELETE(new DeleteFootballerByIdCommand()),
    SHOW_BY_TEAM(new ShowFootballersByTeamCommand());

    private AbstractCommand command;

    CommandType(AbstractCommand command) {
        this.command = command;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
