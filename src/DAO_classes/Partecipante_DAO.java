package DAO_classes;

import Model_classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Partecipante_DAO extends Utente_DAO{

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

    public ArrayList<Utente> getAllPartecipanti(){
        ArrayList<Utente> AllPartecipanti = new ArrayList<>();
        Istituzione Istituzione_temp = new Istituzione();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Utente WHERE tipo_utente = 'Partecipante'");

            while (LocalRS.next()){
                int Istituzione_PK = LocalRS.getInt("istit_afferenza");
                Istituzione_temp = Istituzione_temp.getDao().getByPK(Istituzione_PK);

                Utente Partecipante_temp = this.setUtente_tempFields(Istituzione_temp, LocalRS);
                AllPartecipanti.add(Partecipante_temp);
            }
            return AllPartecipanti;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void InsertUtente_Partecipante(Utente Utente_Partecipante_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Utente VALUES (DEFAULT, "+ Utente_Partecipante_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DeleteUtente_Partecipante(Utente Utente_Partecipante_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Utente WHERE "+ Utente_Partecipante_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateUtente_Partecipante(Utente OldUtente_Partecipante, Utente NewUtente_Partecipante){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Utente SET (titolo, nome, cognome, email, tipo_utente, istit_afferenza) = " +
                    "("+ NewUtente_Partecipante.toSQLrow() +") "+ "WHERE "+ OldUtente_Partecipante.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
