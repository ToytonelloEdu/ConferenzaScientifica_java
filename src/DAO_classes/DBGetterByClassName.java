package DAO_classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class DBGetterByClassName {
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

    public List GetAllByClassName(String SearchIn){
        switch (SearchIn) {
            case "Conferenza":
                Conferenza_DAO DAO = new Conferenza_DAO();
                return DAO.getAllConferenza();
            case "Sede":
                return new Sede_DAO().getAllSede();
        }
        return null;
    }

}
