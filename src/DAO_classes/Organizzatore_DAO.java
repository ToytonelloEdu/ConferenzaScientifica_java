package DAO_classes;

import Model_classes.Istituzione;
import Model_classes.ModelClass;
import Model_classes.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Organizzatore_DAO extends Utente_DAO implements DaoClass{

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
        ArrayList<ModelClass> AllOrganizzatori = new ArrayList<>();
        Istituzione Istituzione_temp = new Istituzione();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Utente WHERE tipo_utente = 'Organizzatore'");

            while (LocalRS.next()){
                int Istituzione_PK = LocalRS.getInt("istit_afferenza");
                Istituzione_temp = Istituzione_temp.getDao().getByPK(Istituzione_PK);

                Utente Partecipante_temp = this.setUtente_tempFields(Istituzione_temp, LocalRS);
                AllOrganizzatori.add(Partecipante_temp);
            }
            return AllOrganizzatori;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void Insert(ModelClass Organizzatore){
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


}
