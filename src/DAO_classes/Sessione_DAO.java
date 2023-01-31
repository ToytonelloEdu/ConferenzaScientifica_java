package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.Conferenza;
import Model_classes.ModelClass;
import Model_classes.*;
import Model_classes.Sessione;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.util.Date;

public class Sessione_DAO implements DaoClass{

    private static Sessione_DAO sessioneDAO = null;

    private Sessione_DAO(){}

    public static Sessione_DAO getDAO(){
        if (sessioneDAO == null)
            sessioneDAO = new Sessione_DAO();
        return sessioneDAO;
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

    @Override
    public List<ModelClass> getAll() {
        List<ModelClass> AllSessione = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Sessione ");

            return addModelClassesToList(AllSessione, LocalRS);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllSessione;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        List<ModelClass> AllSessione = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Sessione " +
                             "WHERE "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            return addModelClassesToList(AllSessione, LocalRS);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return AllSessione;
    }

    private List<ModelClass> addModelClassesToList(List<ModelClass> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Sessione Sessione_temp = this.setSessione_tempFields(resultSet, null);
            list.add(Sessione_temp);
        }
        return list;
    }

    @Override
    public Integer getPK(ModelClass Sessione_temp) {
        Integer pk = null;

        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT sessione_id FROM Main.Sessione WHERE " + Sessione_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                pk = localRS.getInt("sessione_id");
            else
                throw new RuntimeException("localRS vuoto");

            return pk;
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

                return setSessione_tempFields(localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Sessione setSessione_tempFields(ResultSet localRS) throws SQLException {
        Sessione Sessione_temp = new Sessione();
        Sessione_temp.setNome(localRS.getString("nome_sess"));

        Sessione_temp.setInizio(convertToLocalDateTime(localRS.getTimestamp("inizio")));
        Sessione_temp.setFine(convertToLocalDateTime(localRS.getTimestamp("fine")));

        Conferenza conf_temp = Conferenza_DAO.getDAO().getByPK(localRS.getInt("conferenza"));
        Sessione_temp.setConferenza(conf_temp);

        Locazione locazione_temp = (Locazione) Locazione_DAO.getDAO().getByCompositePK(localRS.getInt("sede"), localRS.getString("locazione"));
        Sessione_temp.setLocazione(locazione_temp);
        //set Chair
        Utente chair_temp = Partecipante_DAO.getDAO().getByPK(localRS.getInt("chair"));
        Sessione_temp.setChair(chair_temp);
        //set Keynote Speaker
        Partecipante keynote_speaker_temp = (Partecipante) Partecipante_DAO.getDAO().getByPK(localRS.getInt("Keynote_speaker"));
        Sessione_temp.setKeynote_speaker(keynote_speaker_temp);
        setEventoList_byRS_and_Sessione(localRS, Sessione_temp);
        return Sessione_temp;
    }

    public LocalDateTime convertToLocalDateTime(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toLocalDateTime();
    }

    @Override
    public void Insert(ModelClass Sessione_temp) throws InsertFailedException {
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

    public List<Sessione> getAllbyConference(int Conf_pk, Conferenza conf){
        List<Sessione> tempList = new ArrayList<>();
        try{
            Statement localStmt = getStatement();
            String command = "SELECT * FROM Main.Sessione " +
                             "WHERE conferenza = " + Conf_pk + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            while (localRS.next()){
                tempList.add(setSessione_tempFields(localRS, conf));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tempList;
    }

    private Sessione setSessione_tempFields(ResultSet localRS, Conferenza conf) throws SQLException {
        Sessione Sessione_temp = new Sessione();
        Sessione_temp.setNome(localRS.getString("nome_sess"));

        Sessione_temp.setInizio(convertToLocalDateTime(localRS.getTimestamp("inizio")));
        Sessione_temp.setFine(convertToLocalDateTime(localRS.getTimestamp("fine")));

        Sessione_temp.setConferenza(conf);
        //set Locazione
        Locazione locazione_temp = (Locazione) Locazione_DAO.getDAO().getByCompositePK(localRS.getInt("sede"), localRS.getString("locazione"));
        Sessione_temp.setLocazione(locazione_temp);
        //set Chair
        Utente chair_temp = Partecipante_DAO.getDAO().getByPK(localRS.getInt("chair"));
        Sessione_temp.setChair(chair_temp);
        //set Keynote Speaker
        Partecipante keynote_speaker_temp = (Partecipante) Partecipante_DAO.getDAO().getByPK(localRS.getInt("Keynote_speaker"));
        Sessione_temp.setKeynote_speaker(keynote_speaker_temp);

        setEventoList_byRS_and_Sessione(localRS, Sessione_temp);
        return Sessione_temp;
    }

    private void setEventoList_byRS_and_Sessione(ResultSet localRS, Sessione Sessione_temp) throws SQLException {
        List<? extends Evento> tempList = Intervento_DAO.getDAO().getAllEventi_bySessione(localRS.getInt("sessione_id"), Sessione_temp);
        Sessione_temp.setEventoList(tempList);
    }
}
