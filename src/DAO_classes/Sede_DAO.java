package DAO_classes;

import Model_classes.Sede;

import java.sql.*;
import java.util.ArrayList;

public class Sede_DAO {

            public Sede_DAO(){

            }

            public Statement getStatement() throws SQLException {
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

            public ArrayList<Sede> getAllSede(){
                ArrayList<Sede> AllSede = new ArrayList<>();
                Sede Sede_temp;

                try{
                    Statement LocalStatement = this.getStatement();

                    ResultSet LocalRS = LocalStatement.executeQuery("SELECT * FROM Main.Sede");

                    while(LocalRS.next()){
                        Sede_temp = new Sede(LocalRS.getString("nome"), LocalRS.getString("indirizzo"), LocalRS.getString("città"));
                        AllSede.add(Sede_temp);
                    }
                    return AllSede;
                }
                catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                return AllSede;
            }

            public void InsertSede(Sede Sede_temp){
                try {
                    Statement LocalStatement = this.getStatement();
                    String command = "INSERT INTO Main.Sede VALUES (DEFAULT, "+ Sede_temp.toSQLrow() +");";

                    LocalStatement.execute(command);
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }

            public void DeleteSede(Sede Sede_temp){
                try {
                    Statement LocalStatement = this.getStatement();
                    String command = "DELETE FROM Main.Sede WHERE " + Sede_temp.toSQLctrl() +";";

                    LocalStatement.execute(command);
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }


            public void UpdateSede(Sede OldSede, Sede NewSede){
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

            public Integer getPK(Sede temp){
                try{
                    Statement localStmt = this.getStatement();
                    String command = "SELECT Sede_ID FROM Main.Sede WHERE " + temp.toSQLctrl() + ";";

                    ResultSet localRS = localStmt.executeQuery(command);
                    if(localRS.next())
                        return localRS.getInt("Sede_ID");
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                return null;
            }

        }

