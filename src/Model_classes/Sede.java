package Model_classes;

import DAO_classes.Sede_DAO;

import java.util.ArrayList;
import java.util.List;

public class Sede implements ModelClass{
    private String nome;
    private String indirizzo;
    private String citta;

    private Sede_DAO dao = Sede_DAO.getDAO();

    private List<Locazione> locazioneList;

    public Sede(){
        locazioneList = new ArrayList<>();
    }

    public Sede(String nome, String indirizzo, String citta){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Locazione> getLocazioneList() {
        return locazioneList;
    }

    public void setLocazioneList(List<Locazione> locazioneList) {
        this.locazioneList = locazioneList;
    }

    public Sede_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + adjustDoubleQuotes(nome) + "', '"+ adjustDoubleQuotes(indirizzo) +"', '"+ adjustDoubleQuotes(citta) + "'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Nome = '"+ adjustDoubleQuotes(nome) +
                     "' AND Indirizzo = '"+ adjustDoubleQuotes(indirizzo) +
                     "' AND Città = '"+ adjustDoubleQuotes(citta) + "'";
        return ret;
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    public int toPK(){
        return dao.getPK(this);
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return this.nome + " | " + this.indirizzo + " | " + " | " + this.citta;
    }

    public String toDetailString() {
        return this.nome + ", \n" + this.indirizzo + " (" +this.citta + ")";
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sede sede = (Sede) o;

        if (!nome.equals(sede.nome)) return false;
        if (!indirizzo.equals(sede.indirizzo)) return false;
        return citta.equals(sede.citta);
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + indirizzo.hashCode();
        result = 31 * result + citta.hashCode();
        result = 31 * result + dao.hashCode();
        result = 31 * result + locazioneList.hashCode();
        return result;
    }
}
