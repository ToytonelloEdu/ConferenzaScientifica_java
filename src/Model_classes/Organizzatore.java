package Model_classes;

import DAO_classes.Organizzatore_DAO;

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
        String ret = "'" + adjustDoubleQuotes(getTitolo()) + "', '"+ adjustDoubleQuotes(getNome()) +"', '" + adjustDoubleQuotes(getCognome()) +"', '"
                + adjustDoubleQuotes(getEmail()) +"', 'Organizzatore', '" + this.getIstit_afferenza().toPK() +"'";
        return ret;
    }

    public String toSQLctrl(){

        return "titolo = '"+ adjustDoubleQuotes(getTitolo()) +"' AND " +
                "nome = '"+ adjustDoubleQuotes(getNome()) +"' AND " +
                "cognome = '"+ adjustDoubleQuotes(getCognome()) + "' AND " +
                "email = '"+ adjustDoubleQuotes(getEmail()) + "' AND " +
                "tipo_utente = 'Organizzatore' AND " +
                "istit_afferenza = "+ this.getIstit_afferenza().toPK();
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    @Override
    public String toString() {
        return getNome() +" "+ getCognome() +", "+ getTitolo();
    }
}
