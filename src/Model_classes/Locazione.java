package Model_classes;

import DAO_classes.Locazione_DAO;

import java.util.List;
import java.util.Objects;

public class Locazione implements ModelClass{
    private Sede collocazione;
    private String nome;
    private int postiDisponibili;
    private Locazione_DAO dao = Locazione_DAO.getDAO();

    public Locazione(){

    }

    public Locazione(Sede Collocazione, String Nome, int PostiDisponibili){
        this.collocazione = Collocazione;
        this.nome = Nome;
        this.postiDisponibili = PostiDisponibili;
    }

    public Sede getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(Sede collocazione) {
        this.collocazione = collocazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public Locazione_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = this.collocazione.toPK() + ", '"+ adjustDoubleQuotes(nome) +"', "+ this.postiDisponibili;
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Sede_ID = '"+ collocazione.toPK() +
                "' AND Nome_loc = '"+ adjustDoubleQuotes(nome) +
                "' AND PostiDisp = '"+ postiDisponibili + "'";
        return ret;
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    public int toPK(){
        return this.collocazione.toPK();
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locazione locazione = (Locazione) o;

        if (!Objects.equals(collocazione, locazione.collocazione))
            return false;
        return Objects.equals(nome, locazione.nome);
    }

    @Override
    public int hashCode() {
        int result = collocazione != null ? collocazione.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}
