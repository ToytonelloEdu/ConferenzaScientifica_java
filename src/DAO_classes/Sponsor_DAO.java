package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sponsor_DAO implements DaoClass{

    private static Sponsor_DAO sponsorDAO = null;

    private Sponsor_DAO(){}

    public static Sponsor_DAO getDAO(){
        if (sponsorDAO == null)
            sponsorDAO = new Sponsor_DAO();
        return sponsorDAO;
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

    public ArrayList<ModelClass> getAll(){
        ArrayList<ModelClass> AllSponsor = new ArrayList<>();

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Sponsor");

            while(LocalRS.next()){
                Sponsor Sponsor_temp = setSponsor_tempFields(LocalRS);
                AllSponsor.add(Sponsor_temp);
            }
            return AllSponsor;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return AllSponsor;
    }

    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        ArrayList<ModelClass> AllSponsor = new ArrayList<>();

        try{
            Statement LocalStatement = this.getStatement();
            String command = "SELECT * FROM Main.Sponsor " +
                    "WHERE "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStatement.executeQuery(command);

            while(LocalRS.next()){
                Sponsor Sponsor_temp = setSponsor_tempFields(LocalRS);
                AllSponsor.add(Sponsor_temp);
            }
            return AllSponsor;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void Insert(ModelClass Sponsor_temp) throws InsertFailedException {
        try {
            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Sponsor VALUES (DEFAULT, "+ Sponsor_temp.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            throw new InsertFailedException(e.getMessage());
        }
    }

    public void Delete(ModelClass Sponsor_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "DELETE FROM Main.Sponsor WHERE " + Sponsor_temp.toSQLctrl() +";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldSponsor, ModelClass NewSponsor){
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

    public Integer getPK(ModelClass Sponsor_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "SELECT Sponsor_ID FROM Main.Sponsor WHERE " + Sponsor_temp.toSQLctrl() + ";";

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
                Sponsor_temp = setSponsor_tempFields(localRS);
            }

            return Sponsor_temp;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Sponsor setSponsor_tempFields(ResultSet localRS) throws SQLException {
        Sponsor Sponsor_temp = new Sponsor();
        Sponsor_temp.setNome(localRS.getString("nome"));
        Sponsor_temp.setPartitaIVA(localRS.getString("partitaiva"));
        return Sponsor_temp;
    }

}
