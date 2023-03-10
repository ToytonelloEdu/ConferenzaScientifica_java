package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Partecipante_DAO extends Utente_DAO{

    private static Partecipante_DAO partecipanteDAO = null;

    private Partecipante_DAO(){}

    public static Partecipante_DAO getDAO(){
        if (partecipanteDAO == null)
            partecipanteDAO = new Partecipante_DAO();
        return partecipanteDAO;
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

    public List<ModelClass> getAll(){
        ArrayList<ModelClass> AllPartecipanti = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Utente WHERE tipo_utente = 'Partecipante'");

            while (LocalRS.next()){
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                Utente Partecipante_temp = this.setUtente_tempFields(istituzione_temp, LocalRS);
                AllPartecipanti.add(Partecipante_temp);
            }
            return AllPartecipanti;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllPartecipanti;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        ArrayList<ModelClass> AllPartecipanti = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Utente WHERE tipo_utente = 'Partecipante' " +
                             "AND "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                Utente Partecipante_temp = this.setUtente_tempFields(istituzione_temp, LocalRS);
                AllPartecipanti.add(Partecipante_temp);
            }
            return AllPartecipanti;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllPartecipanti;
    }

    public void Insert(ModelClass Partecip_temp) throws InsertFailedException {
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Utente VALUES (DEFAULT, "+ Partecip_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Part_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Utente WHERE "+ Part_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldPart, ModelClass NewPart) {
        try {
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Utente SET (titolo, nome, cognome, email, tipo_utente, istit_afferenza) = " +
                    "(" + NewPart.toSQLrow() + ") " + "WHERE " + OldPart.toSQLctrl();


            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
