package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento_Sociale_DAO extends Evento_DAO{

    private static Evento_Sociale_DAO evento_sociale_dao = null;

    private Evento_Sociale_DAO(){}

    public static Evento_Sociale_DAO getDAO(){
        if (evento_sociale_dao == null)
            evento_sociale_dao = new Evento_Sociale_DAO();
        return evento_sociale_dao;
    }

    private Statement getStatement() throws SQLException {
        try {
            DBConnection dbConnection = DBConnection.getDBConnection();

            Connection conn = dbConnection.getConnection();
            if (conn == null)
                throw new SQLException();

            // definisce lo statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("SET search_path TO Main");
            return stmt;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<ModelClass> getAll() {
        ArrayList<ModelClass> AllEv_sociali = new ArrayList<>();

        try {
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Ev_sociale");

            while (LocalRS.next()) {
                Sessione sessione_temp = Sessione_DAO.getDAO().getByPK(LocalRS.getInt("sessione"));

                Evento_Sociale Ev_sociale_temp = this.setEv_sociale_tempFields(sessione_temp, LocalRS);
                AllEv_sociali.add(Ev_sociale_temp);
            }
            return AllEv_sociali;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Evento_Sociale setEv_sociale_tempFields(Sessione sessione_temp, ResultSet localRS) throws SQLException {
        Evento_Sociale Ev_sociale_temp = new Evento_Sociale();
        Ev_sociale_temp.setInizio(convertToLocalDateTime(localRS.getTimestamp("inizio")));
        Ev_sociale_temp.setFine(convertToLocalDateTime(localRS.getTimestamp("fine")));
        Ev_sociale_temp.setTipo_evsociale(localRS.getString("tipo_evsociale"));
        Ev_sociale_temp.setDescrizione(localRS.getString("descrizione"));
        Ev_sociale_temp.setSessione(sessione_temp);
        return Ev_sociale_temp;
    }

    public LocalDateTime convertToLocalDateTime(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toLocalDateTime();
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        List<ModelClass> AllEv_sociale = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Ev_sociale " +
                    "WHERE "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){
                Sessione sessione_temp = Sessione_DAO.getDAO().getByPK(LocalRS.getInt("sessione"));

                Evento_Sociale Ev_sociale_temp = setEv_sociale_tempFields(sessione_temp, LocalRS);
                AllEv_sociale.add(Ev_sociale_temp);
            }
            return AllEv_sociale;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void Insert(ModelClass Ev_sociale_temp) throws InsertFailedException {
        try {
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Ev_sociale VALUES (DEFAULT, " + Ev_sociale_temp.toSQLrow() + ");";

            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Ev_sociale_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Ev_sociale WHERE " + Ev_sociale_temp.toSQLctrl() + ";";

            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldEv_sociale, ModelClass NewEv_sociale) {
        try {
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Ev_sociale SET (inizio, fine, tipo_evsociale, descrizione, sessione) = " +
                    "(" + NewEv_sociale.toSQLrow() + ") " + "WHERE " + OldEv_sociale.toSQLctrl();


            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK(ModelClass Ev_sociale_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT evsociale_id FROM Main.Ev_sociale WHERE " + Ev_sociale_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("evsociale_id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Evento_Sociale getByPK(int PK){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Ev_sociale WHERE evsociale_id = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                Sessione sessione_temp = (Sessione) Sessione_DAO.getDAO().getByPK(localRS.getInt("sessione"));

                return setEv_sociale_tempFields(sessione_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Evento_Sociale> getAll_bySessione(int sessPK, Sessione sess) {
        ArrayList<Evento_Sociale> AllEventoSociale = new ArrayList<>();

        try {
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.ev_sociale WHERE sessione = "+ sessPK);

            while (LocalRS.next()) {


                Evento_Sociale evento_sociale = this.setEv_sociale_tempFields(sess, LocalRS);
                AllEventoSociale.add(evento_sociale);

            }
            return AllEventoSociale;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return AllEventoSociale;
    }

}
