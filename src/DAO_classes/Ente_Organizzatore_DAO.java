package DAO_classes;

import Model_classes.*;

import java.sql.*;
import java.util.*;

public class Ente_Organizzatore_DAO implements CompPK_DaoClass {

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

    public List<ModelClass> getAll(){
        List<ModelClass> AllEnte_organizzatore = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Ente_org");

            while (LocalRS.next()){
                Conferenza conferenza_temp = new Conferenza_DAO().getByPK(LocalRS.getInt("conferenza"));
                Istituzione istituzione_temp = new Istituzione_DAO().getByPK(LocalRS.getInt("istituzione"));

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

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        return null;
    }

    private Ente_organizzatore setEnteorg_tempFields(Conferenza conferenza_temp, Istituzione istituzione_temp)throws SQLException {
        Ente_organizzatore enteorg_temp;
        enteorg_temp = new Ente_organizzatore();
        enteorg_temp.setConferenza(conferenza_temp);
        enteorg_temp.setIstituzione(istituzione_temp);
        return enteorg_temp;
    }


    public void Insert(ModelClass Ente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Ente_Org VALUES ("+ Ente_Organizzatore_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Ente_Organizzatore_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Ente_Org WHERE "+ Ente_Organizzatore_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldEnteOrg, ModelClass NewEnteOrg){
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

    @Override
    public Integer getPK(ModelClass Object) {
        return null;
    }

    public Integer getPK1(ModelClass enteorg_temp){
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

    public Integer getPK2(ModelClass enteorg_temp){
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

    @Override
    public ModelClass getByPK(int PK) {
        return null;
    }

    public ModelClass getByCompositePK(Object conferenza, Object istituzione){

        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Ente_org WHERE Conferenza = " + conferenza + " AND Istituzione = "+ istituzione +";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                int Conferenza_PK = localRS.getInt("Conferenza");
                Conferenza conferenza_temp = new Conferenza_DAO().getByPK(Conferenza_PK);
                int Istituzione_PK = localRS.getInt("Istituzione");
                Istituzione istituzione_temp = new Istituzione_DAO().getByPK(Istituzione_PK);

                return setEnteorg_tempFields(conferenza_temp, istituzione_temp);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



}
