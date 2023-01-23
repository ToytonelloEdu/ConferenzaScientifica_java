package Model_classes;

import DAO_classes.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Pausa extends Evento implements ModelClass {

    private String tipo_pausa;
    private Sessione sessione;
    private Pausa_DAO dao = new Pausa_DAO();

    public Pausa() {
    }

    public Pausa(LocalDateTime inizio, LocalDateTime fine, String tipo_pausa, Sessione sessione){
        setInizio(inizio);
        setFine(fine);
        this.tipo_pausa = tipo_pausa;
        this.sessione = sessione;
    }

    public String getTipo_pausa() {
        return tipo_pausa;
    }

    public void setTipo_pausa(String tipo_pausa) {
        this.tipo_pausa = tipo_pausa;
    }

    public Sessione getSessione() {
        return sessione;
    }

    public void setSessione(Sessione sessione) {
        this.sessione = sessione;
    }

    public Pausa_DAO getDao() {
        return dao;
    }

    public String toSQLrow() {
        String ret = "'" + Timestamp.valueOf(getInizio()) + "', '"+ Timestamp.valueOf(getFine()) +"', '" + this.tipo_pausa +"', "
                     + this.getSessione().toPK() +"";
        return ret;
    }

    public String toSQLctrl(){

        return "inizio = '"+ Timestamp.valueOf(getInizio()) +"' AND " +
                "fine = '"+ Timestamp.valueOf(getFine()) +"' AND " +
                "tipo_pausa = '"+ this.tipo_pausa + "' AND " +
                "sessione = "+ this.getSessione().toPK();
    }

    public String toDetailsString(){
        String pausa = "";
        switch (tipo_pausa){
            case "Bagno", "Pranzo" ->
                pausa = "Pausa " + tipo_pausa;
            case "Coffee Break" ->
                pausa = tipo_pausa;
            default ->
                pausa = "O pesc";
        }
        return pausa + " || "+ getInizio() + " | " +  getFine() + " || ";
    }

}
