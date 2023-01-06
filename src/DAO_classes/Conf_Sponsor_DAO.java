package DAO_classes;

import Model_classes.Conf_Sponsor;
import Model_classes.Ente_organizzatore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Conf_Sponsor_DAO {

    public Conf_Sponsor_DAO(){

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

    public void InsertConf_Sponsor(Conf_Sponsor Conf_Sponsor_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Sponsorizzazioni VALUES ("+ Conf_Sponsor_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
