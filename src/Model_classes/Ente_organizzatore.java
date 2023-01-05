package Model_classes;

import DAO_classes.Ente_Organizzatore_DAO;
import DAO_classes.Locazione_DAO;

public class Ente_organizzatore {

    private Conferenza conferenza;
    private Istituzione istituzione;
    private Ente_Organizzatore_DAO Dao = new Ente_Organizzatore_DAO();

    public Ente_organizzatore(){

    }

    public Ente_organizzatore(Conferenza conferenza, Istituzione istituzione){
            this.conferenza = conferenza;
            this.istituzione = istituzione;
    }

    public Conferenza getConferenza() {
        return conferenza;
    }

    public void setConferenza(Conferenza conferenza) {
        this.conferenza = conferenza;
    }

    public Istituzione getIstituzione() {
        return istituzione;
    }

    public void setIstituzione(Istituzione istituzione) {
        this.istituzione = istituzione;
    }

    public Ente_Organizzatore_DAO getDao() {
        return Dao;
    }

    public String toSQLrow() {
        String ret = "'"+ this.conferenza.toPK() +"', '"+ this.istituzione.toPK();
        return ret;
    }
}
