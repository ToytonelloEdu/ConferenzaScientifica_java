package DAO_classes;
import java.sql.*;
import java.util.*;

import Model_classes.Conferenza;
import Model_classes.Locazione;
import Model_classes.ModelClass;
import Model_classes.Sede;

public class Locazione_DAO implements CompPK_DaoClass{

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

    public ArrayList<ModelClass> getAll(){
        ArrayList<ModelClass> AllLocazione = new ArrayList<>();
        Sede Sede_temp = new Sede();

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Locazione");

            while(LocalRS.next()){
                int Sede_PK = LocalRS.getInt("sede_id");
                Sede_temp = (Sede) Sede_temp.getDao().getByPK(Sede_PK);

                Locazione Locazione_temp = this.setLocazione_tempFields(Sede_temp, LocalRS);
                AllLocazione.add(Locazione_temp);
            }
            return AllLocazione;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Locazione setLocazione_tempFields(Sede Sede_temp, ResultSet LocalRS) throws SQLException {
        Locazione Locazione_temp;
        Locazione_temp = new Locazione();
        Locazione_temp.setCollocazione(Sede_temp);
        Locazione_temp.setNome(LocalRS.getString("nome_loc"));
        Locazione_temp.setPostiDisponibili(LocalRS.getInt("postidisp"));
        return Locazione_temp;
    }

    public void Insert(ModelClass Locazione_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "INSERT INTO Main.Locazione VALUES ("+ Locazione_temp.toSQLrow() +");";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Locazione_temp){
        try {
            Statement LocalStatement = this.getStatement();
            String command = "DELETE FROM Main.Locazione WHERE " + Locazione_temp.toSQLctrl() +";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldLocazione, ModelClass NewLocazione){
        try {

            Statement LocalStatement = this.getStatement();
            String command = "UPDATE Main.Locazione SET (Sede_ID, Nome_loc, PostiDisp) = " +
                    "("+ NewLocazione.toSQLrow() +") WHERE " + OldLocazione.toSQLctrl() + ";";

            LocalStatement.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public Integer getPK(ModelClass Locazione_temp){
        return castToLoc(Locazione_temp).getCollocazione().toPK();
    }

    private Locazione castToLoc(ModelClass locazione){
        return (Locazione) locazione;
    }

    public ModelClass getByPK(int Sede){
        return new Sede_DAO().getByPK(Sede);
    }

    public ModelClass getByCompositePK(Object sede_id, Object nome_loc){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Locazione WHERE Sede_ID = " + sede_id + " AND Nome_loc = '"+ nome_loc + "';";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                int Sede_PK = localRS.getInt("Sede_ID");
                Sede Sede_temp = (Sede) new Sede_DAO().getByPK(Sede_PK);
                return setLocazione_tempFields(Sede_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
