package DAO_classes;

import Model_classes.Conferenza;
import Model_classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Utente_DAO implements DaoClass{

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

    public Integer getPK(Utente Utente_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT utente_id FROM Main.Utente WHERE " + Utente_temp.toSQLctrlU() + ";";

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
                int Istituzione_PK = localRS.getInt("istit_afferenza");
                Istituzione Istituzione_temp = new Istituzione();
                Istituzione_temp = Istituzione_temp.getDao().getByPK(Istituzione_PK);
                return setUtente_tempFields(Istituzione_temp, localRS);
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
    public List<ModelClass> getAll() {
        return null;
    }

    @Override
    public Integer getPK(ModelClass Object) {
        return null;
    }
}
