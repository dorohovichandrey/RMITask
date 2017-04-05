package by.bsu.appserver.connectionpool;

import by.bsu.common.exception.ConnectionProducerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by User on 23.03.2017.
 */
public class ConnectionProducer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String URL = "jdbc:mysql://localhost:3306/football";
    private static final String USER = "root";
    private static final String PASSWORD = "7102555andre";
    private static final String AUTO_RECONNECT = "true";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String USE_UNICODE = "true";

    private Properties configProp;

    {
        configProp = new Properties();
        configProp.put("user", USER);
        configProp.put("password", PASSWORD);
        configProp.put("autoReconnect", AUTO_RECONNECT);
        configProp.put("characterEncoding", CHARACTER_ENCODING);
        configProp.put("useUnicode", USE_UNICODE);
        configProp.put("useSSL", "false");
    }


    ConnectionProducer() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("Problem with DriverManager registration", e);
            throw new RuntimeException(e);
        }
    }

    ProxyConnection produce() throws ConnectionProducerException {
        try {
            return tryProduce();
        } catch (SQLException e) {
            throw new ConnectionProducerException("Connection was not produced", e);
        }

    }

    private ProxyConnection tryProduce() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, configProp);
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }

}
