package Config;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    
    public static void beginTransaction(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(false);
        }
    }
    
    public static void commit(Connection conn) throws SQLException {
        if (conn != null) {
            conn.commit();
            conn.setAutoCommit(true);
        }
    }
    
    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error en rollback: " + e.getMessage());
            }
        }
    }
}
