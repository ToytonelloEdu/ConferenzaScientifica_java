package Model_classes;

import DAO_classes.Conf_Organ_DAO;

import java.util.List;

public class Conf_Organ implements ModelClass{

    private Conferenza conferenza;
    private Utente organizzatore;
    private String comitato;
    private Conf_Organ_DAO dao = new Conf_Organ_DAO();

    public Conf_Organ(){
    }

    public Conf_Organ(Conferenza conferenza, Utente organizzatore, String comitato){
        this.conferenza = conferenza;
        this.organizzatore = organizzatore;
        this.comitato = comitato;
    }

    public Conferenza getConferenza() {
        return conferenza;
    }

    public void setConferenza(Conferenza conferenza) {
        this.conferenza = conferenza;
    }

    public Utente getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(Utente organizzatore) {
        this.organizzatore = organizzatore;
    }

    public String getComitato() {
        return comitato;
    }

    public void setComitato(String comitato) {
        this.comitato = comitato;
    }

    @Override
    public Conf_Organ_DAO getDao() {
        return dao;
    }

    @Override
    public String toSQLrow() {
        String ret = this.conferenza.toPK() +", "+ this.organizzatore.toPK() +", '" + comitato +"'";
        return ret;
    }

    @Override
    public String toSQLctrl() {
        return  "conferenza = "+ this.conferenza.toPK() +" AND " +
                "organizzatore = "+ this.organizzatore.toPK() +" AND " +
                "comitato = '"+ this.comitato +"'";
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