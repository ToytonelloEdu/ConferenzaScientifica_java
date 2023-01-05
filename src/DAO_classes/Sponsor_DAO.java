package DAO_classes;

import Model_classes.Sede;
import Model_classes.Sponsor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sponsor_DAO {

    public Sponsor_DAO(){}

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

    public ArrayList<Sponsor> getAllSponsor(){
        ArrayList<Sponsor> AllSponsor = new ArrayList<>();

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Sponsor");

            while(LocalRS.next()){
                Sponsor Sponsor_temp = new Sponsor();
                setSponsor_tempFields(Sponsor_temp, LocalRS);
                AllSponsor.add(Sponsor_temp);
            }
            return AllSponsor;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return AllSponsor;
    }

    public void InsertSponsor(Sponsor Sponsor_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Sponsor VALUES (DEFAULT, "+ Sponsor_temp.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DeleteSponsor(Sponsor Sponsor_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "DELETE FROM Main.Sponsor WHERE " + Sponsor_temp.toSQLctrl() +";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateSponsor(Sponsor OldSponsor, Sponsor NewSponsor){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "UPDATE Main.Sponsor SET (Nome, Partitaiva) = " +
                    "("+ NewSponsor.toSQLrow() +") " +
                    "WHERE " + OldSponsor.toSQLctrl() + ";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public Integer getPK(Sponsor temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "SELECT Sponsor_ID FROM Main.Sponsor WHERE " + temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if(localRS.next())
                return localRS.getInt("Sponsor_ID");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Sponsor getByPK(int PK) {
        Sponsor Sponsor_temp = new Sponsor();
        try{
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Sponsor WHERE Sponsor_ID = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if(localRS.next()) {
                setSponsor_tempFields(Sponsor_temp, localRS);
            }

            return Sponsor_temp;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void setSponsor_tempFields(Sponsor Sponsor_temp, ResultSet localRS) throws SQLException {
        Sponsor_temp.setNome(localRS.getString("nome"));
        Sponsor_temp.setPartitaIVA(localRS.getString("partitaiva"));
    }

}
