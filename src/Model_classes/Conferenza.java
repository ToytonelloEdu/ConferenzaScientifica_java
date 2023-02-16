package Model_classes;

import DAO_classes.Conferenza_DAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Conferenza implements ModelClass {
    private String nome;
    private Date dataInizio;
    private Date dataFine;
    private String descrizione;
    private Sede collocazione;

    private List<Sessione> sessioneList;

    private Conferenza_DAO dao = Conferenza_DAO.getDAO();

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

    public List<Sessione> getSessioneList() {
        return sessioneList;
    }

    public void setSessioneList(List<Sessione> sessioneList) {
        this.sessioneList = sessioneList;
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

        return "nomeconf = '"+ adjustDoubleQuotes(nome) +"' AND " +
                     "datainizio = '"+ DataInizioTimestamp +"' AND " +
                     "datafine = '"+ DataFineTimestamp+ "' AND " +
                     "collocazione = "+ collocazione.toPK();
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    private Timestamp convertToTimestamp(Date data) {
        return new Timestamp(data.getTime());
    }

    public int toPK(){
        return this.getDao().getPK(this);
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        String ret = this.nome.toUpperCase();
        ret = ret.concat(" || " + this.collocazione.getNome());
        return ret;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conferenza that = (Conferenza) o;

        if (!Objects.equals(nome, that.nome)) return false;
        if (!Objects.equals(dataInizio, that.dataInizio)) return false;
        if (!Objects.equals(dataFine, that.dataFine)) return false;
        if (!Objects.equals(descrizione, that.descrizione)) return false;
        return Objects.equals(collocazione, that.collocazione);
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (dataInizio != null ? dataInizio.hashCode() : 0);
        result = 31 * result + (dataFine != null ? dataFine.hashCode() : 0);
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (collocazione != null ? collocazione.hashCode() : 0);
        return result;
    }
}
