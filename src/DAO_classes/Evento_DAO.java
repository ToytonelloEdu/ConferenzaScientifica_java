package DAO_classes;

import Model_classes.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
    public abstract List<? extends Evento> getAll_bySessione(int sessPK, Sessione sess);
    public List<? extends Evento> getAllEventi_bySessione(int sessPK, Sessione sess){
        List<Evento> allEventi_list = new ArrayList<>();

        List<Intervento> interventoList = new Intervento_DAO().getAll_bySessione(sessPK, sess);
        allEventi_list.addAll(interventoList);

        List<Pausa> pausaList = new Pausa_DAO().getAll_bySessione(sessPK, sess);
        allEventi_list.addAll(pausaList);

        List<Evento_Sociale> evento_socialeList = new Evento_Sociale_DAO().getAll_bySessione(sessPK, sess);
        allEventi_list.addAll(evento_socialeList);

        allEventi_list.sort(Comparator.comparing(Evento::getInizio));

        return allEventi_list;
    }
}
