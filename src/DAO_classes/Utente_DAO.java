package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Utente_DAO implements DaoClass{

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

    public List<ModelClass> getAllUtenti(){
        ArrayList<ModelClass> AllUtenti = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Utente");

            while (LocalRS.next()){
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                Utente Partecipante_temp = this.setUtente_tempFields(istituzione_temp, LocalRS);
                AllUtenti.add(Partecipante_temp);
            }
            return AllUtenti;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<ModelClass> getAllUtenti_byAttribute(String Attr_in, String Value_in){
        ArrayList<ModelClass> AllUtenti = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Utente " +
                             "WHERE "+Attr_in+" = '"+Value_in+"';" ;

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(LocalRS.getInt("istit_afferenza"));

                Utente Partecipante_temp = this.setUtente_tempFields(istituzione_temp, LocalRS);
                AllUtenti.add(Partecipante_temp);
            }
            return AllUtenti;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    };

    public Integer getPK(ModelClass Utente_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT utente_id FROM Main.Utente WHERE " + Utente_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("utente_id");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Utente getByPK(int PK){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Utente WHERE utente_id = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                Istituzione istituzione_temp = Istituzione_DAO.getDAO().getByPK(localRS.getInt("istit_afferenza"));

                return setUtente_tempFields(istituzione_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected Utente setUtente_tempFields(Istituzione istituzione_temp, ResultSet localRS) throws SQLException {
        Utente Utente_temp;
        if(localRS.getString("tipo_utente").equals("Organizzatore")) {
            Utente_temp = new Organizzatore();
        }
        else {
            Utente_temp = new Partecipante();
        }

        Utente_temp.setTitolo(localRS.getString("Titolo"));
        Utente_temp.setNome(localRS.getString("Nome"));
        Utente_temp.setCognome(localRS.getString("Cognome"));
        Utente_temp.setEmail(localRS.getString("Email"));
        Utente_temp.setIstit_afferenza(istituzione_temp);
        return Utente_temp;
    }

    @Override
    public abstract void Insert(ModelClass temp) throws InsertFailedException;
    @Override
    public abstract void Delete(ModelClass temp);
    @Override
    public abstract void Update(ModelClass oldTemp, ModelClass newTemp);
    @Override
    public abstract List<ModelClass> getAll();
    @Override
    public abstract List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in);
}
