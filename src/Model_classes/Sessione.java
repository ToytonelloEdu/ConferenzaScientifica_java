package Model_classes;

import DAO_classes.*;
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

    private Sessione_DAO dao = new Sessione_DAO();

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

    @Override
    public Sessione_DAO getDao() {
        return dao;
    }

    @Override
    public String toSQLrow() {
        Object knSp_PK;
        try{
            knSp_PK = this.keynote_speaker.toPK();
        }
        catch (NullPointerException e){
            knSp_PK = "'null'";
        }

        return "'"+ this.nome+"', '"+ this.inizio +"', '"+ this.fine + "', "+ this.conferenza.toPK() +
                     ", "+ this.locazione.toPK() +", '"+ this.locazione.getNome() +"', "+ this.chair.toPK() +", "+ knSp_PK;
    }

    @Override
    public String toSQLctrl() {
        Object knSp_PK;
        try{
            knSp_PK = this.keynote_speaker.toPK();
        }
        catch (NullPointerException e){
            knSp_PK = "'null'";
        }

        return "nome_sess = '"+ this.nome +"' AND " +
                "inizio = '"+ this.inizio +"' AND " +
                "fine = '"+ this.fine +"' AND " +
                "conferenza = "+ this.conferenza.toPK() +" AND " +
                "sede = "+ this.locazione.toPK() +" AND " +
                "locazione = '"+ this.locazione.getNome() +"' AND " +
                "chair = "+ this.chair.toPK() +" AND " +
                "keynote_speaker = "+ knSp_PK;
    }

    @Override
    public int toPK(){
        return this.getDao().getPK(this);
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }

}
