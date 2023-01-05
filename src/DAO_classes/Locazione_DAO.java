package DAO_classes;
import java.sql.*;
import java.util.*;
import Model_classes.Locazione;
import Model_classes.Sede;

public class Locazione_DAO {

    public Locazione_DAO(){

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

    public ArrayList<Locazione> getAllLocazione(){
        ArrayList<Locazione> AllLocazione = new ArrayList<>();
        Locazione Locazione_temp;

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Locazione");

            while(LocalRS.next()){



            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void InsertLocazione(Locazione Locazione_temp){
        try {
            Integer Pk_Sede_Locazione = getSede_ID(Locazione_temp);

            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Locazione VALUES ('"+ Pk_Sede_Locazione.toString() + "', " +Locazione_temp.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DeleteLocazione(Locazione Locazione_temp){
        try {
            Integer Pk_Sede_Locazione = getSede_ID(Locazione_temp);

            Statement LocalStatement = this.getStatement();
            String command = "DELETE FROM Main.Locazione WHERE " + Locazione_temp.toSQLctrl(Pk_Sede_Locazione) +";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateLocazione(Locazione OldLocazione, Locazione NewLocazione){
        try {
            Integer Pk_OldSede_Locazione = getSede_ID(OldLocazione);
            Integer Pk_NewSede_Locazione = getSede_ID(NewLocazione);

            Statement LocalStatement = this.getStatement();
            String command = "UPDATE Main.Locazione SET (Sede_ID, Nome_loc, PostiDisp) = " +
                    "('"+ Pk_NewSede_Locazione.toString() +"', " + NewLocazione.toSQLrow() +") " +
                    "WHERE " + OldLocazione.toSQLctrl(Pk_OldSede_Locazione) + ";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private  Integer getSede_ID(Locazione Locazione_temp) {
        Sede Sede_temp = Locazione_temp.getCollocazione();
        Sede_DAO Sede_DAO_temp = Sede_temp.getDao();
        Integer Pk_Sede_Locazione = Sede_DAO_temp.getPK(Sede_temp);
        return Pk_Sede_Locazione;
    }

}
