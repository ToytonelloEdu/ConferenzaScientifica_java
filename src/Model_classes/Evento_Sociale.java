package Model_classes;

import DAO_classes.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Evento_Sociale extends Evento implements ModelClass{

    private String tipo_evsociale;
    private String descrizione;
    private Evento_Sociale_DAO dao = new Evento_Sociale_DAO();

    public Evento_Sociale(){
    }

    public Evento_Sociale(LocalDateTime inizio, LocalDateTime fine, String tipo_evsociale, String descrizione, Sessione sessione){
        setInizio(inizio);
        setFine(fine);
        this.tipo_evsociale = tipo_evsociale;
        this.descrizione = descrizione;
        setSessione(sessione);
    }

    public String getTipo_evsociale() {
        return tipo_evsociale;
    }

    public void setTipo_evsociale(String tipo_evsociale) {
        this.tipo_evsociale = tipo_evsociale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Evento_Sociale_DAO getDao() {
        return dao;
    }

    @Override
    public String toSQLrow() {
        String ret = "'" + Timestamp.valueOf(getInizio()) + "', '"+ Timestamp.valueOf(getFine()) +"', '" + this.tipo_evsociale +"', '"
                     + adjustDoubleQuotes(descrizione) +"', "+ this.getSessione().toPK() +"";
        return ret;
    }

    @Override
    public String toSQLctrl() {
        return "inizio = '"+ Timestamp.valueOf(getInizio()) +"' AND " +
                "fine = '"+ Timestamp.valueOf(getFine()) +"' AND " +
                "tipo_evsociale = '"+ this.tipo_evsociale + "' AND " +
                "descrizione = '"+ adjustDoubleQuotes(descrizione) + "' AND " +
                "sessione = "+ this.getSessione().toPK();
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
    }

    @Override
    public String toDetailsString() {
        return tipo_evsociale + " || "+ getInizio() + " | " +  getFine() + " || ";
    }
}
