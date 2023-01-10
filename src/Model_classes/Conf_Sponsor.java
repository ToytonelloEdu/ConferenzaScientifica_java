package Model_classes;

import DAO_classes.Conf_Sponsor_DAO;
import DAO_classes.Ente_Organizzatore_DAO;

import java.math.BigDecimal;
import java.util.List;

public class Conf_Sponsor implements ModelClass{

    private Conferenza conferenza;
    private Sponsor sponsor;
    private BigDecimal importo;
    private Conf_Sponsor_DAO Dao = new Conf_Sponsor_DAO();

    public Conf_Sponsor(){}

    public Conf_Sponsor(Sponsor sponsor, Conferenza conferenza, String importo){
        this.conferenza = conferenza;
        this.sponsor = sponsor;
        this.importo = new BigDecimal(importo);
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

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public Conf_Sponsor_DAO getDao() {
        return Dao;
    }

    public String toSQLrow() {
        String ret = this.sponsor.toPK() +", "+ this.conferenza.toPK() +", " + importo ;
        return ret;
    }

    public String toSQLctrl() {


        return  "sponsor = "+ this.sponsor.toPK() +" AND " +
                "conferenza = "+ this.conferenza.toPK();
    }

    @Override
    public int toPK() {
        return 0;
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }
}

