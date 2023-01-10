package Model_classes;

import DAO_classes.*;

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
        String ret = "'" + this.getInizio() + "', '"+ this.getFine() +"', '" + this.tipo_pausa +"', "
                     + this.getSessione().toPK() +"";
        return ret;
    }

    public String toSQLctrl(){

        return "inizio = '"+ this.getInizio() +"' AND " +
                "fine = '"+ this.getFine() +"' AND " +
                "tipo_pausa = '"+ this.tipo_pausa+ "' AND " +
                "sessione = "+ this.getSessione().toPK();
    }

}
