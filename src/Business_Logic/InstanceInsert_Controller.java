package Business_Logic;

import DAO_classes.*;
import Exceptions.DataInsertedException;
import Exceptions.InsertFailedException;
import GUI_classes.CF_AddEditClassFrame;
import GUI_classes.CF_NewSessioneFrame;
import Model_classes.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstanceInsert_Controller {
    Controller business_logic;
    AddEdit_ChecksController checksBL;
    CF_AddEditClassFrame AddEditClassFrame;
    ModelClass CurrentOggetto;

    private final DefaultListModel<ModelClass> dlModel10;
    private final DefaultListModel<ModelClass> dlModel11;
    private final DefaultListModel<ModelClass> dlModel12;
    private final DefaultListModel<ModelClass> dlModel14;

    public InstanceInsert_Controller(Controller c){
        business_logic = c;
        AddEditClassFrame = business_logic.AddEditClassFrame;
        dlModel10 = AddEditClassFrame.getDlModel10();
        dlModel11 = AddEditClassFrame.getDlModel11();
        dlModel12 = AddEditClassFrame.getDlModel12();
        dlModel14 = AddEditClassFrame.getDlModel14();
    }

    //CASO CONFERENZA:

    void InsertConferenza_Control() {
        try {
            if (checksBL.NoCampiVuoti_forConferenza()){
                insertConferenza();
                SuccessfulInsertCompleted();
            }else
                JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito: Dati mancanti");
        } catch (DataInsertedException e) {
            JOptionPane.showMessageDialog(AddEditClassFrame, e.getMessage());
        }
    }

    private void insertConferenza() {
        CurrentOggetto = new Conferenza();
        try {
            SetField_forInsertedConferenza();
            Conferenza_DAO.getDAO().Insert(CurrentOggetto);

            InsertConfSessioni();

            InsertConf_EntiOrganizzatori();

            InsertConfOrganizzatori();

            InsertConfSponsors();
        }catch (InsertFailedException ife){
            JOptionPane.showMessageDialog(AddEditClassFrame,"Inserimento fallito: "+ife.getMessage());
        }

    }

    private void SetField_forInsertedConferenza() {
        (((Conferenza) CurrentOggetto)).setNome(AddEditClassFrame.getTextField1().getText());
        try {
            (((Conferenza) CurrentOggetto)).setDataInizio(getInizioConferenzaInDate());
            (((Conferenza) CurrentOggetto)).setDataFine(getFineConferenzaInDate());
        } catch (ParseException ignored) {
            throw new DataInsertedException("Formato data incorretto");
        }
        (((Conferenza) CurrentOggetto)).setDescrizione(AddEditClassFrame.getTextField4().getText());
        (((Conferenza) CurrentOggetto)).setCollocazione((Sede) AddEditClassFrame.getSelectOne_comboBox13().getSelectedItem());
    }

    private Date getFineConferenzaInDate() throws ParseException {
        String strData2 = AddEditClassFrame.getTextField3().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData2);
    }

    private Date getInizioConferenzaInDate() throws ParseException{
        String strData1 = AddEditClassFrame.getTextField2().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData1);
    }

    private void InsertConfSessioni() throws InsertFailedException {
        ((Conferenza) CurrentOggetto).setSessioneList(new ArrayList<>());
        for(int i = 0; i < dlModel11.getSize(); i++) {
            Sessione sess = ((Sessione) dlModel11.getElementAt(i));
            sess.setConferenza(((Conferenza) CurrentOggetto));
            ((Conferenza) CurrentOggetto).getSessioneList().add(sess);
            Sessione_DAO.getDAO().Insert(sess);
            InsertConfEventi(sess);
        }
    }

    public void InsertConfEventi(Sessione sess) throws InsertFailedException {
            for (Evento ev: sess.getEventoList()) {
                ev.getDao().Insert(ev);
            }
    }

    private void InsertConf_EntiOrganizzatori() throws InsertFailedException {
        Ente_organizzatore enteorg = new Ente_organizzatore();
        enteorg.setConferenza((Conferenza) CurrentOggetto);
        for(int i = 0; i < dlModel10.getSize(); i++){
            enteorg.setIstituzione((Istituzione) dlModel10.getElementAt(i));
            Ente_Organizzatore_DAO.getDAO().Insert(enteorg);
        }
    }

    private void InsertConfOrganizzatori() throws InsertFailedException {
        Conf_Organ confOrgan = new Conf_Organ();
        confOrgan.setConferenza(((Conferenza) CurrentOggetto));
        confOrgan.setComitato("Locale");
        for(int i = 0; i < dlModel12.getSize(); i++){
            confOrgan.setOrganizzatore((Organizzatore) dlModel12.getElementAt(i));
            Conf_Organ_DAO.getDAO().Insert(confOrgan);
        }
    }

    private void InsertConfSponsors() throws InsertFailedException {
        Conf_Sponsor conf_sponsor = new Conf_Sponsor();
        conf_sponsor.setConferenza(((Conferenza) CurrentOggetto));
        conf_sponsor.setImporto(BigDecimal.valueOf(0));
        for(int i = 0; i < dlModel14.getSize(); i++){
            Sponsor sponsor = ((Sponsor) dlModel14.getElementAt(i));
            conf_sponsor.setSponsor(sponsor);
            try{
                Sponsor_DAO.getDAO().Insert(sponsor);
            }catch (InsertFailedException ignored){}
            Conf_Sponsor_DAO.getDAO().Insert(conf_sponsor);
        }
    }

    //CASO SEDE:

    void InsertSede_Control() {
        if(NoCampiVuoti_forSede()) {
            insertSede();
            business_logic.MainFrame.getAddButton().setEnabled(true);
        }
        else
            JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito: dati mancanti");
    }

    private boolean NoCampiVuoti_forSede() {
        return !(AddEditClassFrame.getTextField1().getText().equals("")) && !(AddEditClassFrame.getTextField2().getText().equals("")) && !(AddEditClassFrame.getTextField3().getText().equals(""));
    }

    private void insertSede() {
        CurrentOggetto = new Sede();
        ((Sede) CurrentOggetto).setNome(AddEditClassFrame.getTextField1().getText());
        ((Sede) CurrentOggetto).setIndirizzo(AddEditClassFrame.getTextField2().getText());
        ((Sede) CurrentOggetto).setCitta(AddEditClassFrame.getTextField3().getText());
        FillListByJListModel(((Sede) CurrentOggetto).getLocazioneList(), dlModel11);
        try {
            CommitSedeInsert();
            SuccessfulInsertCompleted();
        }
        catch (InsertFailedException e){
            JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito");
        }
    }

    private void CommitSedeInsert() throws InsertFailedException {
        CurrentOggetto.getDao().Insert(CurrentOggetto);
        for (Locazione l : ((Sede) CurrentOggetto).getLocazioneList()) {
            l.getDao().Insert(l);
        }
    }

    private void FillListByJListModel(List<Locazione> locazioneList, DefaultListModel<ModelClass> dlModel11) {
        for(int i = 0; i < dlModel11.getSize(); i++){
            locazioneList.add((Locazione) dlModel11.remove(0));
        }
    }

    //CASO UTENTE:

    void InsertUtente_Control() {
        if(NoCampiVuoti_forUtente()) {
            insertUtente();
            SuccessfulInsertCompleted();
        }
        else
            JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito: dati mancanti");
    }

    private boolean NoCampiVuoti_forUtente() {
        return !(AddEditClassFrame.getTextField1().getText().equals("")) && !(AddEditClassFrame.getTextField2().getText().equals(""))
                && !(AddEditClassFrame.getTextField3().getText().equals("") && !(AddEditClassFrame.getTextField4().getText().equals(""))
                && !(AddEditClassFrame.getTextField5().getText().equals("")));
    }

    private void insertUtente() {
        InstanceForTipo_Utente();
        SetUtenteFields();
        try {
            CurrentOggetto.getDao().Insert(CurrentOggetto);
            AddEditClassFrame.setVisible(false);
        }
        catch (InsertFailedException e){
            JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito");
        }
    }

    private void SetUtenteFields() {
        ((Utente) CurrentOggetto).setTitolo(AddEditClassFrame.getTextField1().getText());
        ((Utente) CurrentOggetto).setNome(AddEditClassFrame.getTextField2().getText());
        ((Utente) CurrentOggetto).setCognome(AddEditClassFrame.getTextField3().getText());
        ((Utente) CurrentOggetto).setEmail(AddEditClassFrame.getTextField4().getText());
        setIstituzioneForUtente();
    }

    private void InstanceForTipo_Utente() {
        if(!(AddEditClassFrame.getLeftButton9Button().isEnabled()))
            CurrentOggetto = new Partecipante();
        else
            CurrentOggetto = new Organizzatore();
    }

    private void setIstituzioneForUtente(){
        ModelClass IstituzioneSelected = (ModelClass) AddEditClassFrame.getSelectOne_comboBox13().getSelectedItem();
        ((Utente) CurrentOggetto).setIstit_afferenza((Istituzione) IstituzioneSelected);
    }

    //CASO ISTITUZIONE
    void InsertIstituzione_Control() {
        if (NoCampiVuoti_forIstituzione()){
            insertIstituzione();
            SuccessfulInsertCompleted();
        }
        else
            JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito: dati mancanti");
    }

    private boolean NoCampiVuoti_forIstituzione() {
        return !(AddEditClassFrame.getTextField1().getText().equals("")) && !(AddEditClassFrame.getTextField2().getText().equals(""));
    }

    private void insertIstituzione() {
        CurrentOggetto = new Istituzione();
        ((Istituzione) CurrentOggetto).setNome(AddEditClassFrame.getTextField1().getText());
        ((Istituzione) CurrentOggetto).setNazione(AddEditClassFrame.getTextField2().getText());
        try {
            CurrentOggetto.getDao().Insert(CurrentOggetto);
            AddEditClassFrame.setVisible(false);
        }
        catch (InsertFailedException e){
            JOptionPane.showMessageDialog(AddEditClassFrame, "Inserimento fallito");
        }
    }




















    private void SuccessfulInsertCompleted() {
        AddEditClassFrame.setVisible(false);
        business_logic.EmptyComboboxInAddFrame();
        AddEditClassFrame.EraseAllFieldsAndJLists();
        JOptionPane.showMessageDialog(business_logic.MainFrame, "Inserimento riuscito");
    }
}
