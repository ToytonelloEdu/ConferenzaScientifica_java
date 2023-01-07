package Model_classes;

import DAO_classes.Conferenza_DAO;
import DAO_classes.Utente_DAO;

public abstract class Utente {

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

    public abstract String toSQLrow();

    public abstract String toSQLctrl();

}
