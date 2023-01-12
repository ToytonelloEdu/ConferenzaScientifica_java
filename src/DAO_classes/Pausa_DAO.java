package DAO_classes;

import Model_classes.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        ArrayList<ModelClass> AllPause = new ArrayList<>();
        Sessione Sessione_temp = new Sessione();

        try {
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Pausa");

            while (LocalRS.next()) {
                int Sessione_PK = LocalRS.getInt("sessione");
                Sessione_temp = Sessione_temp.getDao().getByPK(Sessione_PK);

                Pausa Pausa_temp = this.setPausa_tempFields(Sessione_temp, LocalRS);
                AllPause.add(Pausa_temp);
            }
            return AllPause;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Pausa setPausa_tempFields(Sessione sessione_temp, ResultSet localRS) throws SQLException {
        Pausa Pausa_temp;
        Pausa_temp = new Pausa();
        Pausa_temp.setInizio(convertToLocalDateTime(localRS.getTimestamp("inizio")));
        Pausa_temp.setFine(convertToLocalDateTime(localRS.getTimestamp("fine")));
        Pausa_temp.setTipo_pausa(localRS.getString("tipo_pausa"));
        Pausa_temp.setSessione(sessione_temp);
        return Pausa_temp;
    }

    public LocalDateTime convertToLocalDateTime(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toLocalDateTime();
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        List<ModelClass> AllPausa = new ArrayList<>();
        Sessione Sessione_temp = new Sessione();

        try{
            Statement LocalStmt = this.getStatement();
            String command = "SELECT * FROM Main.Pausa " +
                    "WHERE "+Attr_in+" = '"+Value_in+"';";

            ResultSet LocalRS = LocalStmt.executeQuery(command);

            while (LocalRS.next()){
                int Sessione_PK = LocalRS.getInt("sessione");
                Sessione_temp = Sessione_temp.getDao().getByPK(Sessione_PK);

                Pausa Pausa_temp = setPausa_tempFields(Sessione_temp, LocalRS);
                AllPausa.add(Pausa_temp);
            }
            return AllPausa;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

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
                    "(" + NewPausa.toSQLrow() + ") " + "WHERE pausa_id = " + OldPausa.toPK();


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
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Pausa WHERE pausa_id = " + PK + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {
                Sessione sessione_temp = (Sessione) new Sessione_DAO().getByPK(localRS.getInt("sessione"));

                return setPausa_tempFields(sessione_temp, localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pausa> getAll_bySessione(int sessPK, Sessione sess) {
        ArrayList<Pausa> AllPausa = new ArrayList<>();

        try {
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.pausa WHERE sessione = "+ sessPK);

            while (LocalRS.next()) {


                Pausa pausaTemp = this.setPausa_tempFields(sess, LocalRS);
                AllPausa.add(pausaTemp);

            }
            return AllPausa;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return AllPausa;
    }

}
