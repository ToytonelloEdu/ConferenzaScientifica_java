import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class DBConnection
    {
        // istanza statica e privata della istanza di questa classe
        private static DBConnection dbcon = null;
        // istanza privata della connessione ad SQL
        private Connection conn = null;

        // costruttore private
        private DBConnection(){}

        // metodo pubblico per ottenere una istanza della classe privata
        public static DBConnection getDBConnection()
        {   // se la classe connessione è nulla, la crea
            if (dbcon == null) {
                dbcon = new DBConnection();
            }
            // e la restituisce
            return dbcon;
        }

        // metodo pubblico per ottenere la connessione
        public Connection getConnection()
        {
            String pwd = "sangio";
            BufferedReader b = null;
            try
            {   // se la connessione non esiste oppure è stata chiusa
                if(conn==null || conn.isClosed())
                {
                    // registra il driver
                    Class.forName("org.postgresql.Driver");
                    // chiama il DriverManager e chiedi la connessione
                    //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=bdd_test_schema", "postgres", pwd);
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Conferencer DB?currentSchema=main", "postgres", pwd);
                }
            } catch (SQLException | ClassNotFoundException  throwables) {
                throwables.printStackTrace();
            }

            return conn;
        }
    }



