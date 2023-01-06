package Model_classes;

import DAO_classes.Conf_Sponsor_DAO;
import DAO_classes.Ente_Organizzatore_DAO;

public class Conf_Sponsor {

    private Conferenza conferenza;
    private Sponsor sponsor;
    private Double importo;
    private Conf_Sponsor_DAO Dao = new Conf_Sponsor_DAO();

    public Conf_Sponsor(){

    }

    public Conf_Sponsor(Sponsor sponsor, Conferenza conferenza, Double importo){
        this.conferenza = conferenza;
        this.sponsor = sponsor;
        this.importo = importo;
    }

    public Conferenza getConferenza() {
        return conferenza;
    }

    public void setConferenza(Conferenza conferenza) {
        this.conferenza = conferenza;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public Conf_Sponsor_DAO getDao() {
        return Dao;
    }

    public String toSQLrow() {
        String ret = this.sponsor.toPK() +", "+ this.conferenza.toPK() +" AND " +
                "importo = "+ importo +";";
        return ret;
    }
}
