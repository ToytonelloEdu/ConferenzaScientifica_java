import java.sql.*;

public class Sede_DAO {
        public static void main(String[] args) throws SQLException {
            try{
                Class.forName("org.postgresql.Driver");
            } catch(ClassNotFoundException e){
                System.out.println("Driver non trovato");
            }
        }
    }

