package DAO_classes;
import java.sql.*;
import java.util.*;
import Model_classes.Istituzione;
import Model_classes.ModelClass;

public class Istituzione_DAO implements DaoClass{

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

    public List<ModelClass> getAll(){
        ArrayList<ModelClass> AllIstituzione = new ArrayList<>();

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Istituzione");

            while(LocalRS.next()){
                Istituzione Istituzione_temp = new Istituzione();
                setIstituzione_tempFields(Istituzione_temp, LocalRS);
                AllIstituzione.add(Istituzione_temp);
            }
            return AllIstituzione;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return AllIstituzione;
    }

    public void Insert(ModelClass Istituzione){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Istituzione VALUES (DEFAULT, "+ Istituzione.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Istituzione){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "DELETE FROM Main.Istituzione WHERE " + Istituzione.toSQLctrl() +";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldIstit, ModelClass NewIstit){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "UPDATE Main.Istituzione SET (Nome, Nazione) = " +
                    "("+ NewIstit.toSQLrow() +") " +
                    "WHERE " + OldIstit.toSQLctrl() + ";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public Integer getPK(ModelClass Istituzione){
        try{
            Statement localStmt = this.getStatement();
            String command = "SELECT Istit_ID FROM Main.Istituzione WHERE " + Istituzione.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if(localRS.next())
                return localRS.getInt("Istit_ID");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Istituzione getByPK(int PK) {
        Istituzione Istituzione_temp = new Istituzione();
        try{
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Istituzione WHERE Istit_ID = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if(localRS.next()) {
                setIstituzione_tempFields(Istituzione_temp, localRS);
            }

            return Istituzione_temp;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void setIstituzione_tempFields(Istituzione Istituzione_temp, ResultSet localRS) throws SQLException {
        Istituzione_temp.setNome(localRS.getString("nome"));
        Istituzione_temp.setNazione(localRS.getString("nazione"));
    }
}
