package Model_classes;

import DAO_classes.Locazione_DAO;
import DAO_classes.Sede_DAO;

public class Locazione {
    private Sede Collocazione;
    private String Nome;
    private int PostiDisponibili;
    private Locazione_DAO dao = new Locazione_DAO();


    public Locazione(Sede Collocazione, String Nome, int PostiDisponibili){
        this.Collocazione = Collocazione;
        this.Nome = Nome;
        this.PostiDisponibili = PostiDisponibili;
    }

    public Sede getCollocazione() {
        return Collocazione;
    }

    public void setCollocazione(Sede collocazione) {
        Collocazione = collocazione;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getPostiDisponibili() {
        return PostiDisponibili;
    }

    public void setPostiDisponibili(int postiDisponibili) {
        PostiDisponibili = postiDisponibili;
    }

    public Locazione_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'"+ this.Nome +"', '"+ this.PostiDisponibili + "'";
        return ret;
    }

}
