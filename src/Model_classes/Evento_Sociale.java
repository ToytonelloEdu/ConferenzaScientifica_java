package Model_classes;

import DAO_classes.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Evento_Sociale extends Evento implements ModelClass{

    private String tipo_evsociale;
    private String descrizione;
    private Sessione sessione;
    private Evento_Sociale_DAO dao = new Evento_Sociale_DAO();

    public Evento_Sociale(){
    }

    public Evento_Sociale(LocalDateTime inizio, LocalDateTime fine, String tipo_evsociale, String descrizione, Sessione sessione){
        setInizio(inizio);
        setFine(fine);
        this.tipo_evsociale = tipo_evsociale;
        this.descrizione = descrizione;
        this.sessione = sessione;
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

    public Sessione getSessione() {
        return sessione;
    }

    public void setSessione(Sessione sessione) {
        this.sessione = sessione;
    }

    public Evento_Sociale_DAO getDao() {
        return dao;
    }

    @Override
    public String toSQLrow() {
        String ret = "'" + Timestamp.valueOf(getInizio()) + "', '"+ Timestamp.valueOf(getFine()) +"', '" + this.tipo_evsociale +"', '"
                     + this.descrizione +"', "+ this.getSessione().toPK() +"";
        return ret;
    }

    @Override
    public String toSQLctrl() {
        return "inizio = '"+ Timestamp.valueOf(getInizio()) +"' AND " +
                "fine = '"+ Timestamp.valueOf(getFine()) +"' AND " +
                "tipo_evsociale = '"+ this.tipo_evsociale + "' AND " +
                "descrizione = '"+ this.descrizione + "' AND " +
                "sessione = "+ this.getSessione().toPK();
    }

}
