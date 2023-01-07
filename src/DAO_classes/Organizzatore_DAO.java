package DAO_classes;

import Model_classes.Utente;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Organizzatore_DAO extends Utente_DAO {

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

    public void InsertUtente_Organizzatore(Utente Utente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Utente VALUES (DEFAULT, "+ Utente_Organizzatore_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DeleteUtente_Organizzatore(Utente Utente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Utente WHERE "+ Utente_Organizzatore_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateUtente_Organizzatore(Utente OldUtente_Organizzatore, Utente NewUtente_Organizzatore){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Utente SET (titolo, nome, cognome, email, tipo_utente, istit_afferenza) = " +
                    "("+ NewUtente_Organizzatore.toSQLrow() +") "+ "WHERE "+ OldUtente_Organizzatore.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
