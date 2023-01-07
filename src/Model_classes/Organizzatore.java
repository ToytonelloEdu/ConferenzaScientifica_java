package Model_classes;

import DAO_classes.Organizzatore_DAO;
import DAO_classes.Partecipante_DAO;

import java.sql.SQLException;
import java.sql.Statement;

public class Organizzatore extends Utente{

    private Organizzatore_DAO dao = new Organizzatore_DAO();

    public Organizzatore(){

    }

    public Organizzatore(String Titolo, String Nome, String Cognome, String Email, Istituzione Istit_afferenza){
        setTitolo(Titolo);
        setNome(Nome);
        setCognome(Cognome);
        setEmail(Email);
        setIstit_afferenza(Istit_afferenza);
    }

    public Organizzatore_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + this.getTitolo() + "', '"+ this.getNome() +"', '" + this.getCognome() +"', '"
                + this.getEmail() +"', 'Organizzatore', '" + this.getIstit_afferenza().toPK() +"'";
        return ret;
    }

    public String toSQLctrl(){

        return "titolo = '"+ this.getTitolo() +"' AND " +
                "nome = '"+ this.getNome() +"' AND " +
                "cognome = '"+ this.getCognome()+ "' AND " +
                "email = '"+ this.getEmail()+ "' AND " +
                "tipo_utente = 'Organizzatore' AND " +
                "istit_afferenza = "+ this.getIstit_afferenza().toPK();
    }


}
