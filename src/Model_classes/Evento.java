package Model_classes;

import DAO_classes.DaoClass;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Evento implements ModelClass{

    private LocalDateTime inizio;
    private LocalDateTime fine;
    private Sessione sessione;

    public Evento(){
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

    public Sessione getSessione() {
        return sessione;
    }

    public void setSessione(Sessione sessione) {
        this.sessione = sessione;
    }

    public abstract DaoClass getDao();
    public abstract String toSQLrow();
    public abstract String toSQLctrl();
    @Override
    public int toPK(){
        return this.getDao().getPK(this);
    }
    @Override
    public String toGUI_Output(List<ModelClass> outputList){
        return null;
    }

    public abstract String toDetailsString();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " ("+ inizio + ", " +fine+")";
    }
}
