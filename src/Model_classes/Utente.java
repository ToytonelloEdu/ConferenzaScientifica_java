package Model_classes;

import DAO_classes.Conferenza_DAO;
import DAO_classes.DaoClass;
import DAO_classes.Utente_DAO;

import java.util.List;

public abstract class Utente implements ModelClass {

    private String Titolo;
    private String Nome;
    private String Cognome;
    private String Email;
    private Istituzione Istit_afferenza;

    public Utente(){

    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Istituzione getIstit_afferenza() {
        return Istit_afferenza;
    }

    public void setIstit_afferenza(Istituzione istit_afferenza) {
        Istit_afferenza = istit_afferenza;
    }

    public String toSQLctrlU(){
        return "titolo = '"+ this.getTitolo() +"' AND " +
                "nome = '"+ this.getNome() +"' AND " +
                "cognome = '"+ this.getCognome()+ "' AND " +
                "email = '"+ this.getEmail()+ "' AND " +
                "istit_afferenza = "+ this.getIstit_afferenza().toPK();
    }

    @Override
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

}
