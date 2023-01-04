package DAO_classes;

import Concrete_classes.Sede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


        }

