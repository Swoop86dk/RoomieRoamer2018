package Resources.DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final String URL = "jdbc:mysql://159.65.56.14/RoomieRoamer";
    private static final String USERNAME = "crud";
    private static final String PASSWORD = "crud42";
    private static Connection singleton;

    public static void setConnection(Connection con) {
        singleton = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }
}
