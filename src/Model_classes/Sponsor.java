package Model_classes;
import DAO_classes.Sponsor_DAO;

import java.util.List;

public class Sponsor implements ModelClass{

    private String Nome;
    private String PartitaIVA;
    private Sponsor_DAO Dao = new Sponsor_DAO();

    public Sponsor(){

    }

    public Sponsor(String Nome, String PartitaIVA){
            this.Nome = Nome;
            this.PartitaIVA = PartitaIVA;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getPartitaIVA() {
        return PartitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        PartitaIVA = partitaIVA;
    }

    public Sponsor_DAO getDao() {
        return Dao;
    }

    public String toSQLrow() {
        String ret = "'" + this.Nome + "', '"+ this.PartitaIVA +"'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Nome = '"+ this.Nome +
                     "' AND partitaiva = '"+ this.PartitaIVA + "'";
        return ret;
    }

    public int toPK(){
        return this.getDao().getPK(this);
    }

    public String toGUI_Output(List<ModelClass> outputList) {
        return this.Nome + " | " + this.PartitaIVA;
    }

    @Override
    public String toString() {
        return Nome;
    }
}
