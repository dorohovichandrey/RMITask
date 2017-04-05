package by.bsu.appclient.command;

import by.bsu.appclient.exception.CommandException;
import by.bsu.common.exception.ServiceException;
import org.apache.logging.log4j.LogManager;

/**
 * Created by User on 21.03.2017.
 */
public abstract class AbstractCommand {

    public void execute() throws CommandException{
        try {
            tryExecute();
        }catch (ServiceException e){
            LogManager.getLogger().error(e);
            e.printStackTrace();
            throw new CommandException("Problems with service", e);

        }
    }

    protected abstract void tryExecute() throws ServiceException;


}
