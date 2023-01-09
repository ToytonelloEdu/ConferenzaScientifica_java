package DAO_classes;

import Model_classes.ModelClass;
import Model_classes.Sessione;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
        return null;
    }

    @Override
    public Integer getPK(ModelClass Object) {
        return null;
    }

    @Override
    public ModelClass getByPK(int PK) {
        return null;
    }

    @Override
    public void Insert(ModelClass Sessione_temp){
        try{
            Statement localStmt = this.getStatement();
            String command = "INSERT INTO Main.Sessione VALUES (DEFAULT, "+ Sessione_temp.toSQLrow() +");";

            localStmt.execute(command);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete(ModelClass obj) {

    }

    @Override
    public void Update(ModelClass oldObj, ModelClass newObj) {

    }
}
