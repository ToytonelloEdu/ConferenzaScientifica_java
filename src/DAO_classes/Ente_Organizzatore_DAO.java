package DAO_classes;

import Model_classes.Conferenza;
import Model_classes.Ente_organizzatore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ente_Organizzatore_DAO {

    public Ente_Organizzatore_DAO(){

    }
    private Statement getStatement() throws SQLException {
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

    public void InsertEnteOrganizzatore(Ente_organizzatore Ente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Ente_Org VALUES ("+ Ente_Organizzatore_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
