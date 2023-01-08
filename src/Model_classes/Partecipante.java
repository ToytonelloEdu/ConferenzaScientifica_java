package Model_classes;

import DAO_classes.Partecipante_DAO;
import DAO_classes.Utente_DAO;

import java.sql.Timestamp;
import java.util.List;

public class Partecipante extends Utente{

    private Partecipante_DAO dao = new Partecipante_DAO();

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
        String ret = "'" + this.getTitolo() + "', '"+ this.getNome() +"', '" + this.getCognome() +"', '"
                     + this.getEmail() +"', 'Partecipante', '" + this.getIstit_afferenza().toPK() +"'";
        return ret;
    }

    public String toSQLctrl(){

        return "titolo = '"+ this.getTitolo() +"' AND " +
                "nome = '"+ this.getNome() +"' AND " +
                "cognome = '"+ this.getCognome()+ "' AND " +
                "email = '"+ this.getEmail()+ "' AND " +
                "tipo_utente = 'Partecipante' AND " +
                "istit_afferenza = "+ this.getIstit_afferenza().toPK();
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }

}
