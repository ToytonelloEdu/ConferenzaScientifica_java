package DAO_classes;
import java.sql.*;
import java.util.*;
import Model_classes.Istituzione;
import Model_classes.Locazione;
import Model_classes.Sede;

public class Istituzione_DAO {

    public Istituzione_DAO(){

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

    public ArrayList<Istituzione> getAllIstituzione(){
        ArrayList<Istituzione> AllIstituzione = new ArrayList<>();
        Istituzione Istituzione_temp;

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Istituzione");

            while(LocalRS.next()){
                Istituzione_temp = new Istituzione(LocalRS.getString("nome"), LocalRS.getString("nazione"));
                AllIstituzione.add(Istituzione_temp);
            }
            return AllIstituzione;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return AllIstituzione;
    }

    public void InsertIstituzione(Istituzione Istituzione_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Istituzione VALUES (DEFAULT, "+ Istituzione_temp.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DeleteIstituzione(Istituzione Istituzione_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "DELETE FROM Main.Istituzione WHERE " + Istituzione_temp.toSQLctrl() +";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateIstituzione(Istituzione OldIstituzione, Istituzione NewIstituzione){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "UPDATE Main.Istituzione SET (Nome, Nazione) = " +
                    "("+ NewIstituzione.toSQLrow() +") " +
                    "WHERE " + OldIstituzione.toSQLctrl() + ";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
