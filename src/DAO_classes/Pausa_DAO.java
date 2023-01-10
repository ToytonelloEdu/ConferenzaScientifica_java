package DAO_classes;

import Model_classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pausa_DAO extends Evento_DAO {

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
        ArrayList<ModelClass> AllPartecipanti = new ArrayList<>();
        Istituzione Istituzione_temp = new Istituzione();

        try {
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Utente WHERE tipo_utente = 'Partecipante'");

            while (LocalRS.next()) {
                int Istituzione_PK = LocalRS.getInt("istit_afferenza");
                Istituzione_temp = Istituzione_temp.getDao().getByPK(Istituzione_PK);

//                Utente Partecipante_temp = this.setUtente_tempFields(Istituzione_temp, LocalRS);
//                AllPartecipanti.add(Partecipante_temp);
            }
            return AllPartecipanti;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        return null;
    }

    public void Insert(ModelClass Pausa_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Pausa VALUES (DEFAULT, " + Pausa_temp.toSQLrow() + ");";

            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Pausa_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Pausa WHERE " + Pausa_temp.toSQLctrl() + ";";

            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldPausa, ModelClass NewPausa) {
        try {
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Pausa SET (inizio, fine, tipo_pausa, sessione) = " +
                    "(" + NewPausa.toSQLrow() + ") " + "WHERE " + OldPausa.toSQLctrl();


            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK(ModelClass Pausa_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT pausa_id FROM Main.Pausa WHERE " + Pausa_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("pausa_id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Pausa getByPK(int PK){
//        try {
//            Statement localStmt = this.getStatement();
//            String command = "SELECT * FROM Main.Pausa WHERE pausa_id = " + PK + ";";
//
//            ResultSet localRS = localStmt.executeQuery(command);
//            if (localRS.next()) {
//                Conferenza conferenza_temp = (Conferenza) new Conferenza_DAO().getByPK(localRS.getInt("conferenza"));
//                Locazione locazione_temp = (Locazione) new Locazione_DAO().getByPK(localRS.getInt("locazione"));
//                Utente chair_temp = (Utente) new Partecipante_DAO().getByPK(localRS.getInt("chair"));
//                Partecipante keynote_speaker_temp = (Partecipante) new Partecipante_DAO().getByPK(localRS.getInt("Keynote_speaker"));
//
//                return setSessione_tempFields(conferenza_temp, locazione_temp, chair_temp, keynote_speaker_temp, localRS);
//            }
//        }
//        catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
        return null;
    }

}
