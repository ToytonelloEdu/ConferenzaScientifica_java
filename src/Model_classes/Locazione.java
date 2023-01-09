package Model_classes;

import DAO_classes.Locazione_DAO;

import java.util.List;

public class Locazione implements ModelClass{
    private Sede collocazione;
    private String nome;
    private int postiDisponibili;
    private Locazione_DAO dao = new Locazione_DAO();

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
        String ret = this.collocazione.toPK() + ", '"+ this.nome +"', '"+ this.postiDisponibili + "'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Sede_ID = '"+ collocazione.toPK() +
                "' AND Nome_loc = '"+ this.nome +
                "' AND PostiDisp = '"+ this.postiDisponibili + "'";
        return ret;
    }

    public int toPK(){
        return this.getDao().getPK(this);
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }

}
