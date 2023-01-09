package Model_classes;

import DAO_classes.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class Sessione implements ModelClass{

    private String nome;
    private Date inizio;
    private Date fine;
    private Conferenza conferenza;
    private Sede sede;
    private Locazione locazione;
    private Utente chair;
    private Partecipante keynote_speaker;

    private Sessione_DAO dao = new Sessione_DAO();

    public Sessione(){
    }

    public Sessione(String nome, Date inizio, Date fine, ModelClass conferenza, ModelClass sede, ModelClass locazione, ModelClass chair, ModelClass keynote_speaker){
        this.nome = nome;
        this.inizio = inizio;
        this.fine = fine;
        this.conferenza = (Conferenza) conferenza;
        this.sede = (Sede) sede;
        this.locazione = (Locazione) locazione;
        this.chair = (Utente) chair;
        this.keynote_speaker = (Partecipante) keynote_speaker;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getInizio() {
        return inizio;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    public Date getFine() {
        return fine;
    }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public Conferenza getConferenza() {
        return conferenza;
    }

    public void setConferenza(Conferenza conferenza) {
        this.conferenza = conferenza;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Locazione getLocazione() {
        return locazione;
    }

    public void setLocazione(Locazione locazione) {
        this.locazione = locazione;
    }

    public Utente getChair() {
        return chair;
    }

    public void setChair(Utente chair) {
        this.chair = chair;
    }

    public Partecipante getKeynote_speaker() {
        return keynote_speaker;
    }

    public void setKeynote_speaker(Partecipante keynote_speaker) {
        this.keynote_speaker = keynote_speaker;
    }

    @Override
    public Sessione_DAO getDao() {
        return dao;
    }

    @Override
    public String toSQLrow() {
        Timestamp InizioTimestamp = convertToTimestamp(this.inizio);
        Timestamp FineTimestamp = convertToTimestamp(this.fine);

        String ret = "'"+ this.nome+"', '"+ InizioTimestamp +"', '"+ FineTimestamp + "', '"+ this.conferenza.toPK() +
                     "', "+ this.sede.toPK() +"', "+ this.locazione.toPK() +"', "+ this.chair.toPK() +"', "+ this.keynote_speaker.toPK();

        return ret;
    }

    private Timestamp convertToTimestamp(Date data) {
        return new Timestamp(data.getTime());
    }

    @Override
    public String toSQLctrl() {
        return null;
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
