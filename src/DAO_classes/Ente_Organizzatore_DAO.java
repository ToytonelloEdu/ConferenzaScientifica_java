package DAO_classes;

import Model_classes.Conferenza;
import Model_classes.Ente_organizzatore;
import Model_classes.Istituzione;
import Model_classes.Sede;

import java.sql.*;
import java.util.*;

public class Ente_Organizzatore_DAO {

    public Ente_Organizzatore_DAO(){

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

    public ArrayList<Ente_organizzatore> getAllEnte_organizzatore(){
        ArrayList<Ente_organizzatore> AllEnte_organizzatore = new ArrayList<>();
        Conferenza conferenza_temp = new Conferenza();
        Istituzione istituzione_temp = new Istituzione();


        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Ente_organizzatore");

            while (LocalRS.next()){
                int Conferenza_PK = LocalRS.getInt("Conferenza");
                conferenza_temp = conferenza_temp.getDao().getByPK(Conferenza_PK);
                int Istituzione_PK = LocalRS.getInt("Istituzione");
                istituzione_temp = istituzione_temp.getDao().getByPK(Istituzione_PK);

                Ente_organizzatore Enteorg_temp = this.setEnteorg_tempFields(conferenza_temp, istituzione_temp);
                AllEnte_organizzatore.add(Enteorg_temp);
            }
            return AllEnte_organizzatore;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Ente_organizzatore setEnteorg_tempFields(Conferenza conferenza_temp, Istituzione istituzione_temp)throws SQLException {
        Ente_organizzatore enteorg_temp;
        enteorg_temp = new Ente_organizzatore();
        enteorg_temp.setConferenza(conferenza_temp);
        enteorg_temp.setIstituzione(istituzione_temp);
        return enteorg_temp;
    }


    public void InsertEnteOrganizzatore(Ente_organizzatore Ente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Ente_Org VALUES ("+ Ente_Organizzatore_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void DeleteEnteOrganizzatore(Ente_organizzatore Ente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Ente_Org WHERE "+ Ente_Organizzatore_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void UpdateEnteOrganizzatore(Ente_organizzatore OldEnteOrg, Ente_organizzatore NewEnteOrg){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Ente_Org SET (conferenza, istituzione) = " +
                    "("+ NewEnteOrg.toSQLrow() +") "+ "WHERE "+ OldEnteOrg.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK_conferenza(Ente_organizzatore enteorg_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Conferenza FROM Main.Ente_org WHERE " + enteorg_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Conferenza");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Integer getPK_istituzione(Ente_organizzatore enteorg_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Istituzione FROM Main.Ente_org WHERE " + enteorg_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Istituzione");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Ente_organizzatore getByPK(Conferenza conferenza, Istituzione istituzione){
        Conferenza conferenza_temp = new Conferenza();
        Istituzione istituzione_temp = new Istituzione();

        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Ente_org WHERE Conferenza = " + conferenza + " AND Istituzione = "+ istituzione +";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                int Conferenza_PK = localRS.getInt("Conferenza");
                conferenza_temp = conferenza_temp.getDao().getByPK(Conferenza_PK);
                int Istituzione_PK = localRS.getInt("Istituzione");
                istituzione_temp = istituzione_temp.getDao().getByPK(Istituzione_PK);

                return setEnteorg_tempFields(conferenza_temp, istituzione_temp);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
