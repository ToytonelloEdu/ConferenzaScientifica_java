package Model_classes;

import DAO_classes.Partecipante_DAO;
import DAO_classes.Utente_DAO;

import java.sql.Timestamp;
import java.util.List;

public class Partecipante extends Utente{

    private Partecipante_DAO dao = Partecipante_DAO.getDAO();

    public Partecipante(){

    }

    public Partecipante(String Titolo, String Nome, String Cognome, String Email, Istituzione Istit_afferenza){
        setTitolo(Titolo);
        setNome(Nome);
        setCognome(Cognome);
        setEmail(Email);
        setIstit_afferenza(Istit_afferenza);
    }

    public Partecipante_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + adjustDoubleQuotes(getTitolo()) + "', '"+ adjustDoubleQuotes(getNome()) +"', '" + adjustDoubleQuotes(getCognome()) +"', '"
                     + adjustDoubleQuotes(getEmail()) +"', 'Partecipante', '" + getIstit_afferenza().toPK() +"'";
        return ret;
    }

    public String toSQLctrl(){

        return "titolo = '"+ adjustDoubleQuotes(getTitolo()) +"' AND " +
                "nome = '"+ adjustDoubleQuotes(getNome()) +"' AND " +
                "cognome = '"+ adjustDoubleQuotes(getCognome()) + "' AND " +
                "email = '"+ adjustDoubleQuotes(getEmail()) + "' AND " +
                "tipo_utente = 'Partecipante' AND " +
                "istit_afferenza = "+ this.getIstit_afferenza().toPK();
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

}
