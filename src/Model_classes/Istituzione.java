package Model_classes;

import DAO_classes.Istituzione_DAO;
import DAO_classes.Locazione_DAO;

import java.util.List;
import java.util.Objects;

public class Istituzione implements ModelClass{

    private String Nome;
    private String Nazione;
    private Istituzione_DAO dao = Istituzione_DAO.getDAO();

    public Istituzione(){

    }
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
        String ret = "'" + adjustDoubleQuotes(Nome) + "', '"+ adjustDoubleQuotes(Nazione) +"'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Nome = '"+ adjustDoubleQuotes(Nome) +
                     "' AND Nazione = '"+ adjustDoubleQuotes(Nazione) + "'";
        return ret;
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    public int toPK(){
        return this.getDao().getPK(this);
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return Nome + " ("+Nazione+")";
    }

    public String toDetailString() {
        return getNome() + ", "+ getNazione();
    }

    @Override
    public String toString() {
        return Nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Istituzione that = (Istituzione) o;

        if (!Objects.equals(Nome, that.Nome)) return false;
        return Objects.equals(Nazione, that.Nazione);
    }

    @Override
    public int hashCode() {
        int result = Nome != null ? Nome.hashCode() : 0;
        result = 31 * result + (Nazione != null ? Nazione.hashCode() : 0);
        return result;
    }
}
