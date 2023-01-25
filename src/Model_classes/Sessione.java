package Model_classes;

import DAO_classes.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Sessione implements ModelClass{

    private String nome;
    private LocalDateTime inizio;
    private LocalDateTime fine;
    private Conferenza conferenza;
    private Locazione locazione;
    private Utente chair;
    private Partecipante keynote_speaker;
    private List<? extends Evento> eventoList;

    private Sessione_DAO dao = Sessione_DAO.getDAO();

    public Sessione(){
    }

    public Sessione(String nome, LocalDateTime inizio, LocalDateTime fine, ModelClass conferenza, ModelClass locazione, ModelClass chair, ModelClass keynote_speaker){
        this.nome = nome;
        this.inizio = inizio;
        this.fine = fine;
        this.conferenza = (Conferenza) conferenza;
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

    public LocalDateTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalDateTime inizio) {
        this.inizio = inizio;
    }

    public LocalDateTime getFine() {
        return fine;
    }

    public void setFine(LocalDateTime fine) {
        this.fine = fine;
    }

    public Conferenza getConferenza() {
        return conferenza;
    }

    public void setConferenza(Conferenza conferenza) {
        this.conferenza = conferenza;
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

    public List<? extends Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<? extends Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @Override
    public Sessione_DAO getDao() {
        return dao;
    }

    @Override
    public String toSQLrow() {
        Object keynoteSP_PK;

        try{
            keynoteSP_PK = keynote_speaker.toPK();
        } catch (NullPointerException e){
            keynoteSP_PK = "null";
        }

        String ret = "'"+ adjustDoubleQuotes(nome) +"', '"+ Timestamp.valueOf(inizio) +"', '"+ Timestamp.valueOf(fine) + "', "+ this.conferenza.toPK() +
                     ", "+ this.locazione.toPK() +", '"+ adjustDoubleQuotes(locazione.getNome())  +"', "+ this.chair.toPK() +", "+ keynoteSP_PK;

        return ret;
    }

    @Override
    public String toSQLctrl() {
        Object keynoteSP_PK;

        try{
            keynoteSP_PK = keynote_speaker.toPK();
        } catch (NullPointerException e){
            keynoteSP_PK = "null";
        }

        return "nome_sess = '"+ adjustDoubleQuotes(nome) +"' AND " +
                "inizio = '"+ Timestamp.valueOf(inizio) +"' AND " +
                "fine = '"+ Timestamp.valueOf(fine) +"' AND " +
                "conferenza = "+ this.conferenza.toPK() +" AND " +
                "sede = "+ this.locazione.toPK() +" AND " +
                "locazione = '"+ adjustDoubleQuotes(locazione.getNome()) +"' AND " +
                "chair = "+ this.chair.toPK() +" AND " +
                "keynote_speaker = "+ keynoteSP_PK;
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    @Override
    public int toPK(){
        return this.getDao().getPK(this);
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }

    public String toDetailString() {
        return nome + " || " + locazione.getNome();
    }
}
