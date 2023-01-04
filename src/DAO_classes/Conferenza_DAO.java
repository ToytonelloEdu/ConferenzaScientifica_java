package DAO_classes;

import Model_classes.Conferenza;
import Model_classes.Sede;

import java.sql.*;
import java.util.*;

public class Conferenza_DAO {

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

    public ArrayList<Conferenza> getAllConferenza(){
        ArrayList<Conferenza> AllConferenza = new ArrayList<>();
        Conferenza Conferenza_temp;
        Sede Sede_temp;

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Conferenza");

            while (LocalRS.next()){
                Integer Sede_PK = LocalRS.getInt("Collocazione");
                ResultSet SedeRS = LocalStmt.executeQuery("SELECT * FROM Main.Sede WHERE Sede_ID = '"+ Sede_PK + "';");
                if (SedeRS.next())
                    Sede_temp = new Sede(SedeRS.getString("nome"), SedeRS.getString("indirizzo"), SedeRS.getString("città"));
                else
                    Sede_temp = null;

                Conferenza_temp = this.setConferenza_tempFields(Sede_temp, LocalRS);
                AllConferenza.add(Conferenza_temp);
            }
            return AllConferenza;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Conferenza setConferenza_tempFields(Sede Sede_temp, ResultSet LocalRS) throws SQLException {
        Conferenza Conferenza_temp;
        Conferenza_temp = new Conferenza();
        Conferenza_temp.setNome(LocalRS.getString("nomeconf"));
        Conferenza_temp.setDataInizio(LocalRS.getDate("DataInizio"));
        Conferenza_temp.setDataFine(LocalRS.getDate("DataFine"));
        Conferenza_temp.setDescrizione(LocalRS.getString("descrizione"));
        Conferenza_temp.setCollocazione(Sede_temp);
        return Conferenza_temp;
    }

    private void InsertConferenza(){

    }

    private void DeleteConferenza(){

    }

    private void UpdateConferenza(){

    }

}
