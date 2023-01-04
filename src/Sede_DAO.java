import java.sql.*;

public class Sede_DAO {
        public static void main(String[] args) throws SQLException {
                Connection conn = null;
                // istanzia la classe di connessione al DB
                DBConnection dbConnection = DBConnection.getDBConnection();
                // recupera la connessione
                conn = dbConnection.getConnection();

                if (conn == null) {
                    System.out.println("Connessione NON riuscita!");
                    System.exit(0);
                }
                System.out.println("Connessione OK!");

                // definisce lo statement
                Statement stmt = null;
            }
        }

