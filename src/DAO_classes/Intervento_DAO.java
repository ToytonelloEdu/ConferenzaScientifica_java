package DAO_classes;


import Model_classes.*;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Intervento_DAO implements DaoClass{

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
        ArrayList<ModelClass> AllInterventi = new ArrayList<>();

        try {
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Intervento");

            while (LocalRS.next()) {
                Sessione sessione_temp = new Sessione_DAO().getByPK(LocalRS.getInt("sessione"));
                Utente partecipante_temp = new Partecipante_DAO().getByPK(LocalRS.getInt("partecipante"));

                Intervento Intervento_temp = this.setIntervento_tempFields(partecipante_temp, sessione_temp, LocalRS);
                AllInterventi.add(Intervento_temp);
            }
            return AllInterventi;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Intervento setIntervento_tempFields(Utente partecipante_temp, Sessione sessione_temp, ResultSet localRS) throws SQLException {
        Intervento Intervento_temp = new Intervento();
        Intervento_temp.setInizio(convertToLocalDateTime(localRS.getTimestamp("inizio")));
        Intervento_temp.setFine(convertToLocalDateTime(localRS.getTimestamp("fine")));
        Intervento_temp.setAbstract(localRS.getString("abstract"));
        Intervento_temp.setSessione(sessione_temp);
        Intervento_temp.setPartecipante(partecipante_temp);
        return Intervento_temp;
    }

    public LocalDateTime convertToLocalDateTime(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toLocalDateTime();
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        List<ModelClass> AllIntervento = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Intervento " +
                    "WHERE "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){
                Sessione sessione_temp = new Sessione_DAO().getByPK(LocalRS.getInt("sessione"));
                Utente partecipante_temp = new Partecipante_DAO().getByPK(LocalRS.getInt("partecipante"));

                Intervento Intervento_temp = setIntervento_tempFields(partecipante_temp, sessione_temp, LocalRS);
                AllIntervento.add(Intervento_temp);
            }
            return AllIntervento;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void Insert(ModelClass Intervento_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Intervento VALUES (DEFAULT, " + Intervento_temp.toSQLrow() + ");";

            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Intervento_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Intervento WHERE " + Intervento_temp.toSQLctrl() + ";";

            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldIntervento, ModelClass NewIntervento) {
        try {
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Intervento SET (inizio, fine, abstract, sessione, partecipante) = " +
                    "(" + NewIntervento.toSQLrow() + ") " + "WHERE " + OldIntervento.toSQLctrl();


            localStmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK(ModelClass Intervento_temp) {
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Intervento_id FROM Main.Intervento WHERE " + Intervento_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Intervento_id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Intervento getByPK(int PK){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Intervento WHERE intervento_id = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                Sessione sessione_temp = new Sessione_DAO().getByPK(localRS.getInt("sessione"));
                Utente partecipante_temp = new Partecipante_DAO().getByPK(localRS.getInt("partecipante"));

                return setIntervento_tempFields(partecipante_temp, sessione_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


}



