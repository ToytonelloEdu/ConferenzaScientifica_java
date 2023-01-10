package DAO_classes;

import Model_classes.Conferenza;
import Model_classes.ModelClass;
import Model_classes.*;
import Model_classes.Sessione;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.time.*;

public class Sessione_DAO implements DaoClass{

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

    @Override
    public List<ModelClass> getAll() {
        ArrayList<ModelClass> AllSessione = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Sessione");

            while (LocalRS.next()){
                Conferenza conferenza_temp = (Conferenza) new Conferenza_DAO().getByPK(LocalRS.getInt("conferenza"));
                Locazione locazione_temp = (Locazione) new Locazione_DAO().getByPK(LocalRS.getInt("locazione"));
                Utente chair_temp = (Utente) new Partecipante_DAO().getByPK(LocalRS.getInt("chair"));
                Partecipante keynote_speaker_temp = (Partecipante) new Partecipante_DAO().getByPK(LocalRS.getInt("Keynote_speaker"));

                Sessione Sessione_temp = this.setSessione_tempFields(conferenza_temp, locazione_temp, chair_temp, keynote_speaker_temp, LocalRS);
                AllSessione.add(Sessione_temp);
            }
            return AllSessione;
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

    @Override
    public Integer getPK(ModelClass Sessione_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT sessione_id FROM Main.Sessione WHERE " + Sessione_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("sessione_id");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
  public Sessione getByPK(int PK){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Sessione WHERE sessione_id = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                Conferenza conferenza_temp = (Conferenza) new Conferenza_DAO().getByPK(localRS.getInt("conferenza"));
                Locazione locazione_temp = (Locazione) new Locazione_DAO().getByCompositePK(localRS.getInt("sede"), localRS.getString("locazione"));
                Utente chair_temp = (Utente) new Partecipante_DAO().getByPK(localRS.getInt("chair"));
                Partecipante keynote_speaker_temp = (Partecipante) new Partecipante_DAO().getByPK(localRS.getInt("Keynote_speaker"));

                return setSessione_tempFields(conferenza_temp, locazione_temp, chair_temp, keynote_speaker_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Sessione setSessione_tempFields(Conferenza conferenza_temp, Locazione locazione_temp, Utente chair_temp, Partecipante keynote_speaker_temp,ResultSet localRS) throws SQLException {
        Sessione Sessione_temp;
        Sessione_temp = new Sessione();
        Sessione_temp.setNome(localRS.getString("nome_sess"));
        Sessione_temp.setInizio(convertToLocalDateTime(localRS.getDate("inizio")));
        Sessione_temp.setFine(convertToLocalDateTime(localRS.getDate("fine")));
        Sessione_temp.setConferenza(conferenza_temp);
        Sessione_temp.setLocazione(locazione_temp);
        Sessione_temp.setChair(chair_temp);
        Sessione_temp.setKeynote_speaker(keynote_speaker_temp);
        return Sessione_temp;
    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public void Insert(ModelClass Sessione_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Sessione (sessione_id, nome_sess, inizio, fine, conferenza, sede, locazione, chair, keynote_speaker) " +
                             "VALUES (DEFAULT, "+ Sessione_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete(ModelClass Sessione_temp) {
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Sessione WHERE "+ Sessione_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldSessione, ModelClass NewSessione){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Sessione SET (nome_sess, inizio, fine, conferenza, sede, locazione, chair, keynote_speaker) = " +
                    "("+ NewSessione.toSQLrow() +") "+ "WHERE "+ OldSessione.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
