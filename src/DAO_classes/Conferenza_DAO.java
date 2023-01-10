package DAO_classes;

import Model_classes.Conferenza;
import Model_classes.ModelClass;
import Model_classes.Sede;

import java.sql.*;
import java.util.*;

public class Conferenza_DAO implements DaoClass{

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

                Conferenza Conferenza_temp = this.setConferenza_tempFields(LocalRS);
                AllConferenza.add(Conferenza_temp);
            }
            return AllConferenza;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
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

        return null;
    }

    private Conferenza setConferenza_tempFields(ResultSet LocalRS) throws SQLException {
        Conferenza Conferenza_temp = new Conferenza();
        Sede Sede_temp = getSede_temp(LocalRS);

        Conferenza_temp.setNome(LocalRS.getString("nomeconf"));
        Conferenza_temp.setDataInizio(LocalRS.getDate("DataInizio"));
        Conferenza_temp.setDataFine(LocalRS.getDate("DataFine"));
        Conferenza_temp.setDescrizione(LocalRS.getString("descrizione"));
        Conferenza_temp.setCollocazione(Sede_temp);
        return Conferenza_temp;
    }

    private static Sede getSede_temp(ResultSet LocalRS) throws SQLException {
        int Sede_PK = LocalRS.getInt("collocazione");
        return new Sede_DAO().getByPK(Sede_PK);
    }

    public void Insert(ModelClass Conf_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Conferenza VALUES (DEFAULT, "+ Conf_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
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
                Sede Sede_temp = (Sede)  new Sede_DAO().getByPK(localRS.getInt("collocazione"));
                return setConferenza_tempFields(localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
