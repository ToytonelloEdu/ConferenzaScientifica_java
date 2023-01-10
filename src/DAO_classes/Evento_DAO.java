package DAO_classes;

import Model_classes.ModelClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Evento_DAO implements DaoClass{

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

    public abstract void Insert(ModelClass temp);

    public abstract void Delete(ModelClass temp);

    public abstract void Update(ModelClass oldTemp, ModelClass newTemp);
    @Override
    public abstract List<ModelClass> getAll();

}
