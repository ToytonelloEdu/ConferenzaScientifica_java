package DAO_classes;

import Model_classes.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dbAccess_byClassName {
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

    public List<ModelClass> GetAll_byClassName(String SearchIn) {
        if(SearchIn.equals("Utente"))
            return new Partecipante_DAO().getAllUtenti();
        if(SearchIn.equals("Organizzatori"))
            return Organizzatore_DAO.getDAO().getAll();
        else
            try {
                DaoClass DAO = getDAObyClassName(SearchIn);
                return DAO.getAll();
            } catch (ClassNotFoundException | NullPointerException e) {
                return new ArrayList<>();
            }
    }

    private DaoClass getDAObyClassName(String SearchIn) throws ClassNotFoundException {
        try {
            Class<?> foundClass = Class.forName("DAO_classes." + SearchIn + "_DAO");
            Object DAO = foundClass.getDeclaredConstructor().newInstance();
            return castOfDAO(DAO);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            return null;
        }
    }

    private DaoClass castOfDAO(Object DAO){
        return (DaoClass) DAO;
    }

    public List<String> getColumns_Names(String Class){
        List<String> Lista_temp = new ArrayList<>();
        try {
            Statement localStmt = this.getStatement();
            if(Class.equals("Partecipante") || Class.equals("Organizzatore")){Class = "Utente";}
            String command = "SELECT attname FROM pg_catalog.pg_attribute " +
                    "WHERE attrelid = (SELECT oid FROM pg_catalog.pg_class WHERE relname = '"+ Class.toLowerCase() +"') " +
                    "AND attnum > 1;";

            ResultSet localRS = localStmt.executeQuery(command);
            while (localRS.next()){
                try {
                    Lista_temp.add(localRS.getString("attname"));
                }
                catch (Exception ignored){}
            }
            return Lista_temp;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String AdjustColumnName(String attributeName) throws Exception {
        //inserisci controlli per nomi
        if (attributeName.contains("cognome"))
            return "Cognome";
        else if (attributeName.contains("inizio"))
            return "Inizio";
        else if (attributeName.contains("fine"))
            return "Fine";
        else if(attributeName.contains("nome"))
            return "Nome";
        else if (attributeName.contains("titolo"))
            return "Titolo";
        else if(attributeName.contains("collocazione"))
            return "Collocazione";
        else if (attributeName.contains("afferenza"))
            return "Istituzione";
        else if (attributeName.contains("indirizzo"))
            return "Indirizzo";
        else if (attributeName.contains("città"))
            return "Città";
        else if (attributeName.contains("descrizione") || attributeName.contains("tipo_utente"))
            throw new Exception();
        return attributeName;
    }

    public String DiscardColumnName(String attrName) throws Exception{
        if (attrName.contains("descrizione") || attrName.contains("tipo_utente"))
            throw new Exception();
        return attrName;
    }

    public List<ModelClass> GetByClass_and_Attribute(String Class, String Attribute, String Value){
        if(Class.equals("Utente"))
            return new Partecipante_DAO().getAllUtenti_byAttribute(Attribute, Value);
        try{
            DaoClass DAO = getDAObyClassName(Class);
            return DAO.getAll_byAttribute(Attribute, Value);
        } catch (NullPointerException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
