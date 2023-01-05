package Model_classes;

import DAO_classes.Sede_DAO;

public class Sede {
    private String nome;
    private String indirizzo;
    private String citta;

    private Sede_DAO dao = new Sede_DAO();

    public Sede(){}

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

    public Sede_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + this.nome + "', '"+ this.indirizzo +"', '"+ this.citta + "'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Nome = '"+ this.nome +
                     "' AND Indirizzo = '"+ this.indirizzo +
                     "' AND Città = '"+ this.citta + "'";
        return ret;
    }

    public int toPK(){
        Sede_DAO DAO_temp = this.getDao();
        return DAO_temp.getPK(this);
    }

}
