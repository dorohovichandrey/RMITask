package by.bsu.appserver.dao.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by User on 23.03.2017.
 */
public interface StatementMaster {
    void prepare(PreparedStatement prSt, Object... params) throws SQLException;
}