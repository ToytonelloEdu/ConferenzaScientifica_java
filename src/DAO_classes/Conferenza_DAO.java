package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.Conferenza;
import Model_classes.ModelClass;
import Model_classes.Sede;
import Model_classes.Sessione;

import java.sql.*;
import java.util.*;

public class Conferenza_DAO implements DaoClass{

    private static Conferenza_DAO conferenzaDAO = null;

    private Conferenza_DAO(){}

    public static Conferenza_DAO getDAO(){
        if (conferenzaDAO == null)
            conferenzaDAO = new Conferenza_DAO();
        return conferenzaDAO;
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
        List<ModelClass> AllConferenza = new ArrayList<>();


        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Conferenza");

            while (LocalRS.next()){
                Conferenza Conferenza_temp = setConferenza_tempFields(LocalRS);
                AllConferenza.add(Conferenza_temp);
            }
            return AllConferenza;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllConferenza;
    }

    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in){
        List<ModelClass> AllConferenza = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Conferenza " +
                             "WHERE "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){

                Conferenza Conferenza_temp = setConferenza_tempFields(LocalRS);
                AllConferenza.add(Conferenza_temp);
            }
            return AllConferenza;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return AllConferenza;
    }

    private Conferenza setConferenza_tempFields(ResultSet LocalRS) throws SQLException {
        Conferenza Conferenza_temp = new Conferenza();
        int Conf_PK = LocalRS.getInt("conf_id");
        Sede Sede_temp = getSede_temp(LocalRS);
        List<Sessione> sessList_temp = getSessList_temp(Conf_PK, Conferenza_temp);

        Conferenza_temp.setNome(LocalRS.getString("nomeconf"));
        Conferenza_temp.setDataInizio(LocalRS.getDate("DataInizio"));
        Conferenza_temp.setDataFine(LocalRS.getDate("DataFine"));
        Conferenza_temp.setDescrizione(LocalRS.getString("descrizione"));
        Conferenza_temp.setCollocazione(Sede_temp);
        Conferenza_temp.setSessioneList(sessList_temp);
        return Conferenza_temp;
    }

    private List<Sessione> getSessList_temp(int conf_pk, Conferenza conferenza_temp) {
        return Sessione_DAO.getDAO().getAllbyConference(conf_pk, conferenza_temp);
    }

    private Sede getSede_temp(ResultSet LocalRS) throws SQLException {
        int Sede_PK = LocalRS.getInt("collocazione");
        return Sede_DAO.getDAO().getByPK(Sede_PK);
    }

    public void Insert(ModelClass Conf_temp) throws InsertFailedException {
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Conferenza VALUES (DEFAULT, "+ Conf_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            throw new InsertFailedException(e.getMessage());
        }
    }

    public void Delete(ModelClass Conf_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Conferenza WHERE "+ Conf_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldConf, ModelClass NewConf){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Conferenza SET (nomeconf, datainizio, datafine, descrizione, collocazione) = " +
                             "("+ NewConf.toSQLrow() +") "+ "WHERE "+ OldConf.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK(ModelClass Conf_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Conf_ID FROM Main.Conferenza WHERE " + Conf_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Conf_ID");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Conferenza getByPK(int PK){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Conferenza WHERE Conf_ID = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                Sede Sede_temp = (Sede)  Sede_DAO.getDAO().getByPK(localRS.getInt("collocazione"));
                return setConferenza_tempFields(localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
