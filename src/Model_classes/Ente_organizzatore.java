package Model_classes;

import DAO_classes.Ente_Organizzatore_DAO;
import DAO_classes.Locazione_DAO;

import java.util.List;

public class Ente_organizzatore implements ModelClass{

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
        String ret = this.conferenza.toPK() +", "+ this.istituzione.toPK();
        return ret;
    }

    public String toSQLctrl() {
        return  "conferenza = "+ this.conferenza.toPK() +" AND " +
                "istituzione = "+ this.istituzione.toPK()+";";
    }

    @Override
    public int toPK() {
        return 0;
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }
}
