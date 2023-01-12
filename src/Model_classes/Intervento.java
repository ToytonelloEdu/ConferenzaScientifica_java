package Model_classes;

import DAO_classes.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Intervento extends Evento{

    private String Abstract;
    private Sessione sessione;
    private Utente partecipante;
    private Intervento_DAO dao = new Intervento_DAO();

    public Intervento(){
    }

    public Intervento(LocalDateTime inizio, LocalDateTime fine, String Abstract, Sessione sessione, Utente partecipante){
        setInizio(inizio);
        setFine(fine);
        this.Abstract = Abstract;
        this.sessione = sessione;
        this.partecipante = partecipante;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public Sessione getSessione() {
        return sessione;
    }

    public void setSessione(Sessione sessione) {
        this.sessione = sessione;
    }

    public Utente getPartecipante() {
        return partecipante;
    }

    public void setPartecipante(Utente partecipante) {
        this.partecipante = partecipante;
    }

    public Intervento_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + Timestamp.valueOf(getInizio()) + "', '"+ Timestamp.valueOf(getFine()) +"', '" + this.Abstract +"', "
                + this.getSessione().toPK() +", "+ this.getPartecipante().toPK() +"";
        return ret;
    }

    @Override
    public String toSQLctrl() {
        return "inizio = '"+ Timestamp.valueOf(getInizio()) +"' AND " +
                "fine = '"+ Timestamp.valueOf(getFine()) +"' AND " +
                "abstract = '"+ this.Abstract + "' AND " +
                "sessione = "+ this.getSessione().toPK() + " AND " +
                "partecipante = "+ this.getPartecipante().toPK();
    }

    @Override
    public int toPK() {
        return super.toPK();
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return super.toGUI_Output(outputList);
    }

    @Override
    public String toDetailsString() {
        return "Intervento |" + getPartecipante().getNome() + " " + getPartecipante().getCognome() + " || "+ getInizio() + " | " +  getFine() + " ||";
    }
}
