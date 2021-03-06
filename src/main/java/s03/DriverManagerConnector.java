package s03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jd.Config.*;

public class DriverManagerConnector {
    private static final Logger LOG = LoggerFactory.getLogger(DriverManagerConnector.class);

    /**
     * The following static initializer should not be required anymore
     */
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            // Class.forName("oracle.jdbc.OracleDriver");    
//        } catch (ClassNotFoundException cnfe) {
//            cnfe.printStackTrace();
//            throw new IllegalStateException("Can't load JDBC driver " + cnfe.getMessage());
//        }
//    }

    public static void main(String[] args) {
        System.out.println(getInfo());
    }

    public static String getInfo() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String db = conn.getMetaData().getDatabaseProductName();

            String schema = conn.getCatalog(); // MySQL
            if (schema == null) {
                schema = conn.getSchema(); // Oracle
            }

            return String.format("Connected to %s database, schema %s", db, schema);
        } catch (SQLException e) {
            LOG.error("Failure accessing DB", e);
            throw new IllegalStateException("Can't get schema name");
        }
    }
}
