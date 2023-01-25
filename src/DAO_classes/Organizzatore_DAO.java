package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.Istituzione;
import Model_classes.ModelClass;
import Model_classes.Organizzatore;
import Model_classes.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Organizzatore_DAO extends Utente_DAO{
    private static Organizzatore_DAO organizzatoreDAO = null;

    private Organizzatore_DAO(){}

    public static Organizzatore_DAO getDAO(){
        if (organizzatoreDAO == null)
            organizzatoreDAO = new Organizzatore_DAO();
        return organizzatoreDAO;
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

    @Override
    public List<ModelClass> getAll() {
        List<ModelClass> AllOrganizzatori = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Utente WHERE tipo_utente = 'Organizzatore'");

            while (LocalRS.next()){
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                Utente Partecipante_temp = this.setUtente_tempFields(istituzione_temp, LocalRS);
                AllOrganizzatori.add(Partecipante_temp);
            }
            return AllOrganizzatori;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllOrganizzatori;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        List<ModelClass> AllOrganizzatori = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Utente WHERE tipo_utente = 'Organizzatore' " +
                             "AND "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                Utente Partecipante_temp = this.setUtente_tempFields(istituzione_temp, LocalRS);
                AllOrganizzatori.add(Partecipante_temp);
            }
            return AllOrganizzatori;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllOrganizzatori;
    }

    public void Insert(ModelClass Organizzatore) throws InsertFailedException {
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Utente VALUES (DEFAULT, "+ Organizzatore.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Organizzatore){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Utente WHERE "+ Organizzatore.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass oldOrg, ModelClass newOrg){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Utente SET (titolo, nome, cognome, email, tipo_utente, istit_afferenza) = " +
                    "("+ newOrg.toSQLrow() +") "+ "WHERE "+ oldOrg.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Organizzatore getByEmail(String email){

        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Utente WHERE tipo_utente = 'Organizzatore' AND email = '"+ email +"';";
            ResultSet LocalRS = localStmt.executeQuery(command);

            while (LocalRS.next()) {
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                return (Organizzatore) setUtente_tempFields(istituzione_temp, LocalRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
