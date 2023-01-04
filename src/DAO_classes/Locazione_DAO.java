package DAO_classes;
import java.sql.*;
import java.util.*;
import Model_classes.Locazione;
import Model_classes.Sede;

public class Locazione_DAO {

    public Locazione_DAO(){

    }
    public Statement getStatement() throws SQLException {
        try{
            DBConnection dbConnection = DBConnection.getDBConnection();

            Connection conn = dbConnection.getConnection();
            if (conn == null)
                throw new SQLException();

            // definisce lo statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("SET search_path TO Main");
            return stmt;
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public void InsertLocazione(Locazione Locazione_temp){
        try {
            Sede Sede_temp = Locazione_temp.getCollocazione();
            Sede_DAO Sede_DAO_temp = Sede_temp.getDao();
            Integer Pk_Sede_Locazione = Sede_DAO_temp.getPK(Sede_temp);
            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Locazione VALUES ('"+ Pk_Sede_Locazione.toString() + "', " +Locazione_temp.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
