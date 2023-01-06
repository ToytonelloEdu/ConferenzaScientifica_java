package DAO_classes;

import Model_classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBGetterByClassName {
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

    public List GetAllByClassName(String SearchIn){
        switch (SearchIn) {
            case "Conferenza":
                Conferenza_DAO DAO = new Conferenza_DAO();
                return DAO.getAllConferenza();
            case "Sede":
                return new Sede_DAO().getAllSede();
//            case "Utente":
//                return new Utente_DAO().getAllUtente();
//            case "Sponsor":
        }

        return null;
    }

    public List<String> getColumns_Names(String Class){
        List<String> Lista_temp = new ArrayList<>();
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT attname FROM pg_catalog.pg_attribute " +
                    "WHERE attrelid = (SELECT oid FROM pg_catalog.pg_class WHERE relname = '"+ Class.toLowerCase() +"') " +
                    "AND attnum > 1;";

            ResultSet localRS = localStmt.executeQuery(command);
            while (localRS.next()){
                Lista_temp.add(localRS.getString("attname"));
            }
            return Lista_temp;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List GetByClass_and_Attribute(String Class, String Attribute, String Value){
        try{
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main."+Class.toLowerCase()+" WHERE " +
                             Attribute.toLowerCase()+ " = '"+ Value +"';";

            ResultSet localRS = localStmt.executeQuery(command);
            switch (Class) {
                case "Conferenza":
                    return getConferenzeByResultSet(localRS);
                case "Sede":
                    return getSediByResultSet(localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    private List<Sede> getSediByResultSet(ResultSet localRS) throws SQLException {
        List<Sede> Lista_temp = new ArrayList<>();
        Sede_DAO DAO = new Sede_DAO();
        while (localRS.next()) {
            Lista_temp.add(DAO.getByPK(localRS.getInt("sede_id")));
        }
        return Lista_temp;
    }

    private List<Conferenza> getConferenzeByResultSet(ResultSet localRS) throws SQLException {
        List<Conferenza> Lista_temp = new ArrayList<>();
        Conferenza_DAO DAO = new Conferenza_DAO();
        while (localRS.next()) {
            Lista_temp.add(DAO.getByPK(localRS.getInt("conf_id")));
        }
        return Lista_temp;
    }
}
