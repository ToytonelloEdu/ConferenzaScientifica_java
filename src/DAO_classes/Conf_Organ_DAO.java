package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conf_Organ_DAO implements CompPK_DaoClass{

    private static Conf_Organ_DAO confOrganDAO = null;

    private Conf_Organ_DAO(){}

    public static Conf_Organ_DAO getDAO(){
        if (confOrganDAO == null)
            confOrganDAO = new Conf_Organ_DAO();
        return confOrganDAO;
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
        ArrayList<ModelClass> AllConf_Organ = new ArrayList<>();


        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Organizzatori");

            while (LocalRS.next()){
                Conf_Organ Conf_Organ_temp = setConf_Organ_tempFields(LocalRS);
                AllConf_Organ.add(Conf_Organ_temp);
            }
            return AllConf_Organ;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        ArrayList<ModelClass> AllConf_Organ = new ArrayList<>();


        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Organizzatori WHERE "+Attr_in+" = "+Value_in+";");

            while (LocalRS.next()){
                Conf_Organ Conf_Organ_temp = setConf_Organ_tempFields(LocalRS);
                AllConf_Organ.add(Conf_Organ_temp);
            }
            return AllConf_Organ;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void Insert(ModelClass Conf_Organ_temp) throws InsertFailedException {
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Organizzatori VALUES ("+ Conf_Organ_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            throw new InsertFailedException(e.getMessage());
        }
    }

    public void Delete(ModelClass Conf_Organ_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Organizzatori WHERE "+ Conf_Organ_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldConf_Organ, ModelClass NewConf_Organ){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Organizzatori SET (conferenza, organizzatore, comitato) = " +
                    "("+ NewConf_Organ.toSQLrow() +") "+ "WHERE "+ OldConf_Organ.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK(ModelClass obj){
        return null;
    }

    public Integer getPK1(ModelClass Conf_Organ_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Conferenza FROM Main.Organizzatori WHERE " + Conf_Organ_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Conferenza");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Integer getPK2(ModelClass Conf_Organ_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Organizzatore FROM Main.Organizzatori WHERE " + Conf_Organ_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Organizzatore");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ModelClass getByPK(int PK) {
        return null;
    }

    public Conf_Organ getByCompositePK(Object conferenza, Object organizzatore ){

        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Organizzatori WHERE Conferenza = " + ((Conferenza) conferenza).toPK() +
                    " AND Organizzatore = "+ ((Organizzatore) organizzatore).toPK() +";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {

                return setConf_Organ_tempFields(localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Conf_Organ setConf_Organ_tempFields(ResultSet localRS)throws SQLException {
        Conf_Organ Conf_Organ_temp = new Conf_Organ();

        Conferenza conferenza_temp = getConferenza_temp(localRS);
        Conf_Organ_temp.setConferenza(conferenza_temp);

        Organizzatore Organizzatore_temp = (Organizzatore) getOrganizzatore_temp(localRS);
        Conf_Organ_temp.setOrganizzatore(Organizzatore_temp);

        Conf_Organ_temp.setComitato(localRS.getString("comitato"));

        return Conf_Organ_temp;
    }

    private Conferenza getConferenza_temp(ResultSet localRS) throws SQLException {
        int Conferenza_PK = localRS.getInt("Conferenza");
        return Conferenza_DAO.getDAO().getByPK(Conferenza_PK);
    }

    private Utente getOrganizzatore_temp(ResultSet localRS) throws SQLException {
        int Organizzatore_PK = localRS.getInt("Organizzatore");
        return Organizzatore_DAO.getDAO().getByPK(Organizzatore_PK);
    }

}
