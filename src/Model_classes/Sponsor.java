package Model_classes;
import DAO_classes.Sponsor_DAO;

import java.util.List;
import java.util.Objects;

public class Sponsor implements ModelClass{

    private String Nome;
    private String PartitaIVA;
    private Sponsor_DAO Dao = Sponsor_DAO.getDAO();

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
        String ret = "'" + adjustDoubleQuotes(Nome) + "', '"+ adjustDoubleQuotes(PartitaIVA) +"'";
        return ret;
    }

    public String toSQLctrl() {
        String ret = "Nome = '"+ adjustDoubleQuotes(Nome) +
                     "' AND partitaiva = '"+ adjustDoubleQuotes(PartitaIVA) + "'";
        return ret;
    }

    private String adjustDoubleQuotes(String s){
        return s.replaceAll("'", "''");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        if (!Objects.equals(Nome, sponsor.Nome)) return false;
        return Objects.equals(PartitaIVA, sponsor.PartitaIVA);
    }

    @Override
    public int hashCode() {
        int result = Nome != null ? Nome.hashCode() : 0;
        result = 31 * result + (PartitaIVA != null ? PartitaIVA.hashCode() : 0);
        return result;
    }
}
