package DAO_classes;
import java.sql.*;
import java.util.*;

import Exceptions.InsertFailedException;
import Model_classes.Locazione;
import Model_classes.ModelClass;
import Model_classes.Sede;

public class Locazione_DAO implements CompPK_DaoClass{

    private static Locazione_DAO locazioneDAO = null;

    private Locazione_DAO(){}

    public static Locazione_DAO getDAO(){
        if (locazioneDAO == null)
            locazioneDAO = new Locazione_DAO();
        return locazioneDAO;
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

        try{
            Statement LocalStatement = this.getStatement();

            ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Locazione");

            while(LocalRS.next()){
                Sede sede_temp = Sede_DAO.getDAO().getByPK(LocalRS.getInt("sede_id"));

                Locazione Locazione_temp = this.setLocazione_tempFields(sede_temp, LocalRS);
                AllLocazione.add(Locazione_temp);
            }
            return AllLocazione;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return AllLocazione;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        ArrayList<ModelClass> AllLocazione = new ArrayList<>();

        try{
            Statement LocalStatement = this.getStatement();
            String command = "SELECT * FROM Main.Locazione " +
                             "WHERE "+Attr_in+" = '"+Value_in+"'";

            ResultSet LocalRS = LocalStatement.executeQuery(command);

            while(LocalRS.next()){
                Sede sede_temp = Sede_DAO.getDAO().getByPK(LocalRS.getInt("sede_id"));

                Locazione Locazione_temp = this.setLocazione_tempFields(sede_temp, LocalRS);
                AllLocazione.add(Locazione_temp);
            }
            return AllLocazione;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return AllLocazione;
    }

    private Locazione setLocazione_tempFields(Sede Sede_temp, ResultSet LocalRS) throws SQLException {
        Locazione Locazione_temp = new Locazione();
        Locazione_temp.setCollocazione(Sede_temp);
        Locazione_temp.setNome(LocalRS.getString("nome_loc"));
        Locazione_temp.setPostiDisponibili(LocalRS.getInt("postidisp"));
        return Locazione_temp;
    }

    public void Insert(ModelClass Locazione_temp) throws InsertFailedException {
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
        return null;
    }

    /** Locazione_DAO.getPK1 restituisce la PK intera della Sede a cui la Locazione fa riferimento */
    public Object getPK1(ModelClass Locazione_temp){
        return castToLoc(Locazione_temp).getCollocazione().toPK();
    }

    /** Locazione_DAO.getPK2 resituisce  il Nome della Locazione*/
    @Override
    public Object getPK2(ModelClass Locazione_temp) {
        return castToLoc(Locazione_temp).getNome();
    }

    private Locazione castToLoc(ModelClass locazione){
        return (Locazione) locazione;
    }

    public ModelClass getByPK(int Sede){
        return Sede_DAO.getDAO().getByPK(Sede);
    }

    public ModelClass getByCompositePK(Object sede_id, Object nome_loc){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Locazione WHERE Sede_ID = " + sede_id + " AND Nome_loc = '"+ nome_loc + "';";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                int Sede_PK = localRS.getInt("Sede_ID");
                Sede Sede_temp = (Sede) Sede_DAO.getDAO().getByPK(Sede_PK);
                return setLocazione_tempFields(Sede_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Locazione> getAll_bySede(int sedePK, Sede sede){
        List<Locazione> tempList = new ArrayList<>();
        try{
            Statement localStmt = getStatement();
            String command = "SELECT * FROM main.locazione " +
                             "WHERE sede_id = "+sedePK+";";

            ResultSet localRS = localStmt.executeQuery(command);
            while(localRS.next()){
                tempList.add(setLocazione_tempFields(sede, localRS));
            }
            return tempList;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tempList;
    }
}
