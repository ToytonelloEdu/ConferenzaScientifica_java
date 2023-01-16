package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.ModelClass;
import Model_classes.Sede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sede_DAO implements DaoClass{

            public Sede_DAO(){}

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

            public ArrayList<ModelClass> getAll(){
                ArrayList<ModelClass> AllSede = new ArrayList<>();

                try{
                    Statement LocalStatement = this.getStatement();

                    ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Sede");

                    while(LocalRS.next()){
                        Sede Sede_temp = setSede_tempFields(LocalRS);
                        AllSede.add(Sede_temp);
                    }
                    return AllSede;
                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                return AllSede;
            }

            @Override
            public List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in) {
                ArrayList<ModelClass> AllSede = new ArrayList<>();

                try{
                    Statement LocalStatement = this.getStatement();
                    String command = "SELECT * FROM Main.Sede " +
                                     "WHERE "+Attr_in+" = '"+Value_in+"';";

                    ResultSet LocalRS = LocalStatement.executeQuery(command);

                    while(LocalRS.next()){
                        Sede Sede_temp = setSede_tempFields(LocalRS);
                        AllSede.add(Sede_temp);
                    }
                    return AllSede;
                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }

                return null;
            }

            public void Insert(ModelClass Sede_temp) throws InsertFailedException {
                        try {
                            Statement LocalStatement = this.getStatement();
                            String command = "INSERT INTO Main.Sede VALUES (DEFAULT, "+ Sede_temp.toSQLrow() +");";

                            LocalStatement.execute(command);
                        }
                        catch (SQLException e){
                            throw new InsertFailedException(e.getMessage());
                        }
                    }

            public void Delete(ModelClass Sede_temp){
                try {
                    Statement LocalStatement = this.getStatement();
                    String command = "DELETE FROM Main.Sede WHERE " + Sede_temp.toSQLctrl() +";";

                    LocalStatement.execute(command);
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }


            public void Update(ModelClass OldSede, ModelClass NewSede){
                try {
                    Statement LocalStatement = this.getStatement();
                    String command = "UPDATE Main.Sede SET (Nome, Indirizzo, Città) = " +
                                     "("+ NewSede.toSQLrow() +") " +
                                     "WHERE " + OldSede.toSQLctrl() + ";";

                    LocalStatement.execute(command);
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }

            }

            public Integer getPK(ModelClass Sede_temp){
                try{
                    Statement localStmt = this.getStatement();
                    String command = "SELECT Sede_ID FROM Main.Sede WHERE " + Sede_temp.toSQLctrl() + ";";

                    ResultSet localRS = localStmt.executeQuery(command);
                    if(localRS.next())
                        return localRS.getInt("Sede_ID");
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                return null;
            }

            public Sede getByPK(int PK) {
                Sede Sede_temp = new Sede();
                try{
                    Statement localStmt = this.getStatement();
                    String command = "SELECT * FROM Main.Sede WHERE Sede_ID = " + PK + ";";

                    ResultSet localRS = localStmt.executeQuery(command);
                    if(localRS.next()) {
                        Sede_temp = setSede_tempFields(localRS);
                    }

                    return Sede_temp;
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                return null;
            }

            private Sede setSede_tempFields(ResultSet localRS) throws SQLException {
                Sede Sede_temp = new Sede();
                Sede_temp.setNome(localRS.getString("nome"));
                Sede_temp.setIndirizzo(localRS.getString("indirizzo"));
                Sede_temp.setCitta(localRS.getString("città"));
                return Sede_temp;
    }
}

