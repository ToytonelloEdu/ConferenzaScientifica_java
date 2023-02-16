package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.*;
import org.postgresql.util.PGmoney;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conf_Sponsor_DAO implements CompPK_DaoClass {

    private static Conf_Sponsor_DAO confSponsorDAO = null;

    private Conf_Sponsor_DAO(){}

    public static Conf_Sponsor_DAO getDAO(){
        if (confSponsorDAO == null)
            confSponsorDAO = new Conf_Sponsor_DAO();
        return confSponsorDAO;
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

    public List<ModelClass> getAll(){
        ArrayList<ModelClass> AllConf_Sponsor = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Sponsorizzazioni");

            while (LocalRS.next()){
                Conf_Sponsor Conf_Sponsor_temp = this.setConf_Sponsor_tempFields(LocalRS);
                AllConf_Sponsor.add(Conf_Sponsor_temp);
            }
            return AllConf_Sponsor;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
        ArrayList<ModelClass> AllConf_Sponsor = new ArrayList<>();

        try{
            Statement LocalStmt = this.getStatement();

            ResultSet LocalRS = LocalStmt.executeQuery("SELECT * FROM Main.Sponsorizzazioni WHERE "+Attr_in+" = "+Value_in+";");

            while (LocalRS.next()){
                Conf_Sponsor Conf_Sponsor_temp = this.setConf_Sponsor_tempFields(LocalRS);
                AllConf_Sponsor.add(Conf_Sponsor_temp);
            }
            return AllConf_Sponsor;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void Insert(ModelClass Conf_Sponsor_temp) throws InsertFailedException {
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Sponsorizzazioni VALUES ("+ Conf_Sponsor_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Delete(ModelClass Conf_Sponsor_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "DELETE FROM Main.Sponsorizzazioni WHERE "+ Conf_Sponsor_temp.toSQLctrl()+ ";";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void Update(ModelClass OldConf_Sponsor, ModelClass NewConf_Sponsor){
        try{
            Statement localStmt = this.getStatement();
            String command = "UPDATE Main.Sponsorizzazioni SET (sponsor, conferenza, importo) = " +
                    "("+ NewConf_Sponsor.toSQLrow() +") "+ "WHERE "+ OldConf_Sponsor.toSQLctrl();


            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Integer getPK(ModelClass obj){
        return null;
    }

    public Integer getPK1(ModelClass Conf_Sponsor_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Sponsor FROM Main.Sponsorizzazioni WHERE " + Conf_Sponsor_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Sponsor");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Integer getPK2(ModelClass Conf_Sponsor_temp){
        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT Conferenza FROM Main.Sponsorizzazioni WHERE " + Conf_Sponsor_temp.toSQLctrl() + ";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next())
                return localRS.getInt("Conferenza");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ModelClass getByPK(int PK) {
        return null;
    }

    public Conf_Sponsor getByCompositePK(Object sponsor, Object conferenza ){

        try {
            Statement localStmt = this.getStatement();
            String command = "SELECT * FROM Main.Sponsorizzazioni WHERE Sponsor = " + ((Sponsor) sponsor).toPK() +
                             " AND Conferenza = "+ ((Conferenza) conferenza).toPK() +";";

            ResultSet localRS = localStmt.executeQuery(command);
            if (localRS.next()) {

                return setConf_Sponsor_tempFields(localRS);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Conf_Sponsor setConf_Sponsor_tempFields(ResultSet localRS)throws SQLException {
        Conf_Sponsor Conf_Sponsor_temp = new Conf_Sponsor();

        Sponsor Sponsor_temp = getSponsor_temp(localRS);
        Conf_Sponsor_temp.setSponsor(Sponsor_temp);

        Conferenza conferenza_temp = getConferenza_temp(localRS);
        Conf_Sponsor_temp.setConferenza(conferenza_temp);
        Conf_Sponsor_temp.setImporto(getImporto(localRS));

        return Conf_Sponsor_temp;
    }

    private BigDecimal getImporto(ResultSet localRS) throws SQLException {
        String imp = localRS.getString("importo");
        imp = imp.replaceAll("\\.", "");
        imp = imp.replaceAll(" €", "");
        imp = imp.replaceAll(",", "\\.");
        return BigDecimal.valueOf(Double.parseDouble(imp));
    }

    private Sponsor getSponsor_temp(ResultSet localRS) throws SQLException {
        int Sponsor_PK = localRS.getInt("Sponsor");
        return Sponsor_DAO.getDAO().getByPK(Sponsor_PK);
    }

    private Conferenza getConferenza_temp(ResultSet localRS) throws SQLException {
        int Conferenza_PK = localRS.getInt("Conferenza");
        return Conferenza_DAO.getDAO().getByPK(Conferenza_PK);
    }

}
