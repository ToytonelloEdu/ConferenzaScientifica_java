package Model_classes;

import DAO_classes.Conferenza_DAO;

import java.sql.Timestamp;
import java.util.Date;

public class Conferenza {
    private String nome;
    private Date dataInizio;
    private Date dataFine;
    private String descrizione;
    private Sede collocazione;

    private Conferenza_DAO dao = new Conferenza_DAO();

    public Conferenza(){

    }
    public Conferenza(String nome, Date dataInizio, Date dataFine, String descrizione, Sede collocazione){
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.descrizione = descrizione;
        this.collocazione = collocazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Sede getCollocazione() {
        return collocazione;
    }

    public void setCollocazione(Sede collocazione) {
        this.collocazione = collocazione;
    }

    public Conferenza_DAO getDao() {
        return dao;
    }

    public String toSQLrow(){
        Timestamp DataInizioTimestamp = convertToTimestamp(this.dataInizio);
        Timestamp DataFineTimestamp = convertToTimestamp(this.dataFine);

        String ret = "'"+ this.nome+"', '"+ DataInizioTimestamp +"', '"+ DataFineTimestamp+ "', '"+ this.descrizione + "', "+this.collocazione.toPK();

        return ret;
    }

    public String toSQLctrl(){
        Timestamp DataInizioTimestamp = convertToTimestamp(this.dataInizio);
        Timestamp DataFineTimestamp = convertToTimestamp(this.dataFine);

        return "nomeconf = '"+ this.nome +"' AND " +
                     "datainizio = '"+ DataInizioTimestamp +"' AND " +
                     "datafine = '"+ DataFineTimestamp+ "' AND " +
                     "collocazione = "+ this.collocazione.toPK();
    }

    private Timestamp convertToTimestamp(Date data) {
        return new Timestamp(data.getTime());
    }

    public int toPK(){
        return this.getDao().getPK(this);
    }

    public String toString(){
        return this.nome + " | " + this.dataInizio + " | " + this.dataFine +" |";
    }
}
