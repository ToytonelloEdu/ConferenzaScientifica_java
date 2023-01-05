package Model_classes;

import DAO_classes.Istituzione_DAO;
import DAO_classes.Locazione_DAO;

public class Istituzione {

    private String Nome;
    private String Nazione;
    private Istituzione_DAO dao = new Istituzione_DAO();

    public Istituzione(String Nome, String Nazione){
        this.Nome = Nome;
        this.Nazione = Nazione;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNazione() {
        return Nazione;
    }

    public void setNazione(String nazione) {
        Nazione = nazione;
    }

    public Istituzione_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + this.Nome + "', '"+ this.Nazione +"'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Nome = '"+ this.Nome +
                     "' AND Nazione = '"+ this.Nazione + "'";
        return ret;
    }
}
