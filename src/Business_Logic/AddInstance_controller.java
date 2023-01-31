package Business_Logic;

import DAO_classes.*;
import Exceptions.DataInsertedException;
import Exceptions.InsertFailedException;
import GUI_classes.CF_AddInstanceClassFrame;
import GUI_classes.CF_NewLocazioneFrame;
import GUI_classes.CF_NewSessioneFrame;
import GUI_classes.CF_NewSponsorFrame;
import Model_classes.*;

import java.awt.*;
import java.lang.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddInstance_controller {
    Controller business_logic;
    CF_AddInstanceClassFrame AddInstanceClassFrame;
    CF_NewLocazioneFrame NewLocazioneFrame;
    CF_NewSponsorFrame NewSponsorFrame;
    CF_NewSessioneFrame NewSessioneFrame;
    String ClassSelected;
    ModelClass CurrentOggetto;

    private final DefaultListModel<ModelClass> dlModel10 = new DefaultListModel<>();
    private final DefaultListModel<ModelClass> dlModel11 = new DefaultListModel<>();
    private final DefaultListModel<ModelClass> dlModel12 = new DefaultListModel<>();
    private final DefaultListModel<ModelClass> dlModel14 = new DefaultListModel<>();

    public AddInstance_controller(Controller c, CF_AddInstanceClassFrame aicf) {
        business_logic = c;
        AddInstanceClassFrame = aicf;
        AddInstanceClassFrame.getSelectedItems_list10().setModel(dlModel10);
        AddInstanceClassFrame.getAddOnly_list11().setModel(dlModel11);
        AddInstanceClassFrame.getSelectedItems_list12().setModel(dlModel12);
        AddInstanceClassFrame.getSelectedItems_list14().setModel(dlModel14);
    }

    public void NewSpons_annullaButtonClicked() {
        NewSponsorFrame.setVisible(false);
    }

    public void setNewLocazioneFrame(CF_NewLocazioneFrame newLocazioneFrame) {
        NewLocazioneFrame = newLocazioneFrame;
    }

    public void setNewSponsorFrame(CF_NewSponsorFrame newSponsorFrame) {
        NewSponsorFrame = newSponsorFrame;
    }

    public void setNewSessioneFrame(CF_NewSessioneFrame newSessioneFrame) {
        NewSessioneFrame = newSessioneFrame;
    }

    public void NewButton11Clicked() {
        if(ClassSelected.equals("Sede")){
            NewLocazioneFrame.setVisible(true);
        }
        else if (ClassSelected.equals("Conferenza")){
            NewSessioneFrameSetUp();
            NewSessioneFrame.setVisible(true);
        }
    }

    private void NewSessioneFrameSetUp() {
        setComboboxLocazioniforSessione();
        setComboboxChairforSessione();
        setComboboxKeynoteforSessione();
        setComboboxInterventistaforSessione();
        AddInstanceClassFrame.getNewButton11().setEnabled(false);
        AddInstanceClassFrame.getSelectOne_comboBox13().setEnabled(false);
    }

    private void setComboboxInterventistaforSessione() {
        if(NewSessioneFrame.getInterv_comboBox().getItemCount() == 0)
            for(ModelClass u : getAllPartecipantiforCombobox()){
                NewSessioneFrame.getInterv_comboBox().addItem((Partecipante) u);
            }
    }

    private void setComboboxKeynoteforSessione() {
        if(NewSessioneFrame.getComboBox5().getItemCount() == 0) {
            for (ModelClass u : getAllPartecipantiforCombobox()) {
                NewSessioneFrame.getComboBox5().addItem((Partecipante) u);
            }
        }
    }

    private List<ModelClass> getAllPartecipantiforCombobox() {
        return Partecipante_DAO.getDAO().getAll();
    }

    private void setComboboxChairforSessione() {
        if(NewSessioneFrame.getComboBox4().getItemCount() == 0)
            for(ModelClass u : getAllUtentiforCombobox()){
                NewSessioneFrame.getComboBox4().addItem((Utente) u);
            }
    }

    private List<ModelClass> getAllUtentiforCombobox() {
        return Partecipante_DAO.getDAO().getAllUtenti();
    }

    public void ChoiseClassAdd(String selectedClass) {
        ClassSelected = selectedClass;
        SetAllToVisible();
        switch (selectedClass) {
            case "Conferenza" -> {

                setFieldsAdd_forConferenza();
            }
            case "Sede" -> {
                    CurrentOggetto = new Sede();
                    setFieldsAdd_forSede();
                }
            case "Utente", "Organizzatore", "Partecipante" ->
                    setFieldsAdd_forUtente();
            case "Istituzione" ->{
                    CurrentOggetto = new Istituzione();
                    setFieldsAdd_forIstituzione();
                }
        }
    }

    private void SetAllToVisible() {
        for(JComponent jc : AddInstanceClassFrame.getDataInsertComponentList())
            jc.setVisible(true);
        AddInstanceClassFrame.getNewButton11().setEnabled(true);
    }

    private void setFieldsAdd_forConferenza(){
        AddInstanceClassFrame.getNewButton11().setEnabled(false);
        setConf_firstField();
        setConf_secondField();
        setConf_thirdField();
        setConf_fourthField();
        setConf_FK();
        setConf_SessioniField();
        setConf_EntiOrgField();
        setConf_OrganField();
        setConf_SponsorField();
        Hide_Conferenza_UnusedComponents();
    }

    private void setConf_firstField(){
        AddInstanceClassFrame.getLabel1().setText("Nome");
        AddInstanceClassFrame.getTextField1().setText("");
    }
    private void setConf_secondField(){
        AddInstanceClassFrame.getLabel2().setText("Data Inizio");
        AddInstanceClassFrame.getTextField2().setText("");
    }
    private void setConf_thirdField(){
        AddInstanceClassFrame.getLabel3().setText("Data Fine");
        AddInstanceClassFrame.getTextField3().setText("");
    }
    private void setConf_fourthField(){
        AddInstanceClassFrame.getLabel4().setText("Descrizione");
        AddInstanceClassFrame.getTextField4().setText("");
    }
    private void setConf_FK(){
        AddInstanceClassFrame.getLabel13().setText("Collocazione");
        setValues_in_Select_comboBox13();
    }

    private List<ModelClass> getValues_for_Select_comboBox(String class_selected) {
        return new dbAccess_byClassName().GetAll_byClassName(class_selected);
    }

    private void setConf_SessioniField(){
        AddInstanceClassFrame.getLabel11().setText("Sessioni");
    }

    private void setConf_EntiOrgField(){
        AddInstanceClassFrame.getLabel10().setText("Enti organizzatori");
        setValues_in_Select_comboBox10("Istituzione");
    }

    private void setValues_in_Select_comboBox10(String ClassIntoComboBox) {
        for(ModelClass o : getValues_for_Select_comboBox(ClassIntoComboBox))
            AddInstanceClassFrame.getSelect_comboBox10().addItem(o);
    }

    private void setConf_OrganField() {
        AddInstanceClassFrame.getLabel12().setText("Organizzatori");
        setValues_in_Select_comboBox12("Organizzatori");
    }

    private void setValues_in_Select_comboBox12(String ClassIntoComboBox) {
        for(ModelClass o : getValues_for_Select_comboBox(ClassIntoComboBox))
            AddInstanceClassFrame.getSelect_comboBox12().addItem(o);
    }

    private void setConf_SponsorField() {
        AddInstanceClassFrame.getLabel14().setText("Sponsor");
        setValues_in_Select_comboBox14("Sponsor");
    }

    private void setValues_in_Select_comboBox14(String ClassIntoComboBox) {
        for(ModelClass o : getValues_for_Select_comboBox(ClassIntoComboBox))
            AddInstanceClassFrame.getSelect_comboBox14().addItem(o);
    }

    private void Hide_Conferenza_UnusedComponents(){
        Hide_Conferenza_Unusedlabel();
        Hide_Conferenza_UnusedTextField();
        Hide_Conferenza_UnusedButton();
    }
    private void Hide_Conferenza_Unusedlabel(){
        AddInstanceClassFrame.getLabel5().setVisible(false);
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel9().setVisible(false);
    }
    private void Hide_Conferenza_UnusedTextField(){
        AddInstanceClassFrame.getTextField5().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
        AddInstanceClassFrame.getnewButton10().setVisible(false);
    }
    private void Hide_Conferenza_UnusedButton(){
        AddInstanceClassFrame.getTwoButton_JPanel().setVisible(false);
        AddInstanceClassFrame.getnewButton10().setVisible(false);
        AddInstanceClassFrame.getnewButton12().setVisible(false);
    }

    private void setFieldsAdd_forSede(){
        setSede_firstField();
        setSede_secondField();
        setSede_thirdField();
        setSede_LocazioneList();
        Hide_Sede_UnusedComponents();
    }
    private void setSede_firstField(){
        AddInstanceClassFrame.getLabel1().setText("Nome");
        AddInstanceClassFrame.getTextField1().setText("");
    }
    private void setSede_secondField(){
        AddInstanceClassFrame.getLabel2().setText("Indirizzo");
        AddInstanceClassFrame.getTextField2().setText("");
    }
    private void setSede_thirdField(){
        AddInstanceClassFrame.getLabel3().setText("Città");
        AddInstanceClassFrame.getTextField3().setText("");
    }
    private void setSede_LocazioneList(){
        AddInstanceClassFrame.getLabel11().setText("Locazioni");
        dlModel11.clear();
    }
    private void Hide_Sede_UnusedComponents(){
        Hide_Sede_Unusedlabel();
        Hide_Sede_UnusedTextField();
        Hide_Sede_UnusedJPanel();
        Hide_CheckboxDisponibilita();
        AddInstanceClassFrame.getSelectItems_JPanel10().setVisible(false);
    }

    private void Hide_Sede_Unusedlabel(){
        AddInstanceClassFrame.getLabel4().setVisible(false);
        AddInstanceClassFrame.getLabel5().setVisible(false);
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel9().setVisible(false);
        AddInstanceClassFrame.getLabel10().setVisible(false);
        AddInstanceClassFrame.getLabel12().setVisible(false);
        AddInstanceClassFrame.getLabel13().setVisible(false);
        AddInstanceClassFrame.getLabel14().setVisible(false);
    }

    private void Hide_Sede_UnusedTextField(){
        AddInstanceClassFrame.getTextField4().setVisible(false);
        AddInstanceClassFrame.getTextField5().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
    }

    private void Hide_Sede_UnusedJPanel(){
        AddInstanceClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddInstanceClassFrame.getSelectOnly_JPanel13().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel14().setVisible(false);
        AddInstanceClassFrame.getTwoButton_JPanel().setVisible(false);
    }

    private void Hide_CheckboxDisponibilita(){
        AddInstanceClassFrame.getCheckBox1().setVisible(false);
        AddInstanceClassFrame.getCheckDisponibilitaButton().setVisible(false);
        AddInstanceClassFrame.getCheckButtonLabel().setVisible(false);
    }

    private void setFieldsAdd_forUtente(){
        setUtente_firstField();
        setUtente_secondField();
        setUtente_thirdField();
        setUtente_fourthField();
        setUtente_tipo();
        setUtente_FK();
        Hide_Utente_UnusedComponents();
    }
    private void setUtente_firstField(){
        AddInstanceClassFrame.getLabel1().setText("Titolo");
        AddInstanceClassFrame.getTextField1().setText("");
    }
    private void setUtente_secondField(){
        AddInstanceClassFrame.getLabel2().setText("Nome");
        AddInstanceClassFrame.getTextField2().setText("");
    }
    private void setUtente_thirdField(){
        AddInstanceClassFrame.getLabel3().setText("Cognome");
        AddInstanceClassFrame.getTextField3().setText("");
    }
    private void setUtente_fourthField(){
        AddInstanceClassFrame.getLabel4().setText("Email");
        AddInstanceClassFrame.getTextField4().setText("");
    }
    private void setUtente_FK(){
        AddInstanceClassFrame.getLabel13().setText("Istituzione di afferenza");
        setValues_in_Select_comboBox13();
    }

    private void setUtente_tipo(){
        AddInstanceClassFrame.getLabel9().setText("Ruolo");
        AddInstanceClassFrame.getLeftButton9Button().setText("Partecipante");
        AddInstanceClassFrame.getRightButton9Button().setText("Organizzatore");
    }
    private void Hide_Utente_UnusedComponents(){
        Hide_Utente_UnusedLabel();
        Hide_Utente_UnusedTextField();
        Hide_Utente_UnusedJPanel();
        Hide_CheckboxDisponibilita();
    }
    private void Hide_Utente_UnusedLabel(){
        AddInstanceClassFrame.getLabel5().setVisible(false);
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel10().setVisible(false);
        AddInstanceClassFrame.getLabel11().setVisible(false);
        AddInstanceClassFrame.getLabel12().setVisible(false);
        AddInstanceClassFrame.getLabel14().setVisible(false);
    }
    private void Hide_Utente_UnusedTextField(){
        AddInstanceClassFrame.getTextField5().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
    }
    private void Hide_Utente_UnusedJPanel(){
        AddInstanceClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddInstanceClassFrame.getAddOnly_JPanel11().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel14().setVisible(false);
    }

    public void setValues_in_Select_comboBox13() {
        String classIntoComboBox = chooseClassIntoComboBox13();
        for(ModelClass o: getValues_for_Select_comboBox(classIntoComboBox)){
            try {
                AddInstanceClassFrame.getSelectOne_comboBox13().addItem(o);
            }
            catch (Exception ignored){}
        }
    }

    private String chooseClassIntoComboBox13() {
        switch (ClassSelected){
            case "Conferenza" -> {
                return "Sede";
            }
            case "Utente", "Organizzatore", "Partecipante" -> {
                return "Istituzione";
            }
            default -> {
                return "";
            }
        }
    }

    public void NewLoc_AnnullaButton_clicked() {
        NewLocazioneFrame.setVisible(false);
        NewLocazioneFrame.getTextField1().setText("");
        NewLocazioneFrame.getTextField2().setText("");

    }

    public void NewLoc_ConfermaButton_clicked() {
        NewLocazioneFrame.getConfermaButton().setEnabled(false);
        Locazione locazioneTemp = new Locazione();
        locazioneTemp.setCollocazione(((Sede) CurrentOggetto));
        locazioneTemp.setNome(NewLocazioneFrame.getTextField1().getText());
        locazioneTemp.setPostiDisponibili(Integer.parseInt(NewLocazioneFrame.getTextField2().getText()));
        ((Sede) CurrentOggetto).getLocazioneList().add(locazioneTemp);
        dlModel11.clear();
        for(Locazione l : ((Sede) CurrentOggetto).getLocazioneList())
            dlModel11.addElement(l);
        NewLocazioneFrame.setVisible(false);
        NewLocazioneFrame.getTextField1().setText("");
        NewLocazioneFrame.getTextField2().setText("");
        NewLocazioneFrame.getConfermaButton().setEnabled(true);
    }

    public void confermaButtonClicked() {
        switch (ClassSelected){
            case "Sede" -> InsertSede_Control();
            case "Conferenza" -> InsertConferenza_Control();
            case "Utente", "Organizzatore", "Partecipante" -> InsertUtente_Control();
            case "Istituzione" -> InsertIstituzione_Control();
        }
    }

    private void InsertConferenza_Control() {
        try {
            if(NoCampiVuoti_forConferenza())
                insertConferenza();
            else
                JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito: Dati mancanti");
        } catch (DataInsertedException e) {
            JOptionPane.showMessageDialog(AddInstanceClassFrame, e.getMessage());
        }
    }

    private boolean NoCampiVuoti_forConferenza() {
        return  !AddInstanceClassFrame.getTextField1().getText().equals("") &&
                !AddInstanceClassFrame.getTextField2().getText().equals("") &&
                !AddInstanceClassFrame.getTextField3().getText().equals("") &&
                !AddInstanceClassFrame.getTextField4().getText().equals("");
    }

    private void insertConferenza() {
        CurrentOggetto = new Conferenza();
        try {
            SetField_forInsertedConferenza();
            Conferenza_DAO.getDAO().Insert(CurrentOggetto);

            InsertConfSessioni();
            InsertConfEventi();
            
            InsertConf_EntiOrganizzatori();

            InsertConfOrganizzatori();

            InsertConfSponsors();
            AddInstanceClassFrame.setVisible(false);
            EraseAllFieldsInAddFrame();
            JOptionPane.showMessageDialog(business_logic.MainFrame, "Inserimento riuscito");
        }catch (InsertFailedException ife){
            JOptionPane.showMessageDialog(AddInstanceClassFrame ,"Inserimento fallito: "+ife.getMessage());
        }

    }

    private void SetField_forInsertedConferenza() {
        (((Conferenza) CurrentOggetto)).setNome(AddInstanceClassFrame.getTextField1().getText());
        try {
            (((Conferenza) CurrentOggetto)).setDataInizio(getInizioConferenzaInDate());
            (((Conferenza) CurrentOggetto)).setDataFine(getFineConferenzaInDate());
        } catch (ParseException ignored) {
            throw new DataInsertedException("Formato data incorretto");
        }
        (((Conferenza) CurrentOggetto)).setDescrizione(AddInstanceClassFrame.getTextField4().getText());
        (((Conferenza) CurrentOggetto)).setCollocazione((Sede) AddInstanceClassFrame.getSelectOne_comboBox13().getSelectedItem());
    }

    private void InsertConfSessioni() throws InsertFailedException {
        ((Conferenza) CurrentOggetto).setSessioneList(new ArrayList<>());
        for(int i = 0; i < dlModel11.getSize(); i++) {
            Sessione sess = ((Sessione) dlModel11.getElementAt(i));
            sess.setConferenza(((Conferenza) CurrentOggetto));
            ((Conferenza) CurrentOggetto).getSessioneList().add(sess);
            Sessione_DAO.getDAO().Insert(sess);
        }
    }

    public void InsertConfEventi() throws InsertFailedException {
        for(Sessione sess: ((Conferenza) CurrentOggetto).getSessioneList())
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

    private void EraseAllFieldsInAddFrame() {
        for(JComponent comp: AddInstanceClassFrame.getDataInsertComponentList()){
            try{
                ((JTextField) comp).setText("");
            }catch (ClassCastException ignored){}
        }
        dlModel10.clear();
        dlModel11.clear();
        dlModel12.clear();
        dlModel14.clear();
        business_logic.MainFrame.getAddButton().setEnabled(true);
    }

    private void InsertSede_Control() {
        if(NoCampiVuoti_forSede()) {
            insertSede();
            business_logic.MainFrame.getAddButton().setEnabled(true);
        }
        else
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito: dati mancanti");
    }

    private boolean NoCampiVuoti_forSede() {
        return !(AddInstanceClassFrame.getTextField1().getText().equals("")) && !(AddInstanceClassFrame.getTextField2().getText().equals("")) && !(AddInstanceClassFrame.getTextField3().getText().equals(""));
    }

    private void insertSede() {
        ((Sede) CurrentOggetto).setNome(AddInstanceClassFrame.getTextField1().getText());
        ((Sede) CurrentOggetto).setIndirizzo(AddInstanceClassFrame.getTextField2().getText());
        ((Sede) CurrentOggetto).setCitta(AddInstanceClassFrame.getTextField3().getText());
        try {
            CurrentOggetto.getDao().Insert(CurrentOggetto);
            for (Locazione l : ((Sede) CurrentOggetto).getLocazioneList()) {
                l.getDao().Insert(l);
            }
            AddInstanceClassFrame.setVisible(false);
            EraseAllFieldsInAddFrame();
            JOptionPane.showMessageDialog(business_logic.MainFrame, "Inserimento riuscito");
        }
        catch (InsertFailedException e){
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito");
        }
    }


    public void removeButtonClicked(){
        int currentlistIndex = AddInstanceClassFrame.getAddOnly_list11().getSelectedIndex();
        if(!dlModel11.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel11.remove(currentlistIndex);
            switch (ClassSelected) {
                case "Sede"-> {
                    ((Sede) CurrentOggetto).getLocazioneList().remove(currentlistIndex);
                }
            }
        }
    }

    private void InsertUtente_Control() {
        if(NoCampiVuoti_forUtente()) {
            insertUtente();
            AddInstanceClassFrame.setVisible(false);
            EraseAllFieldsInAddFrame();
            JOptionPane.showMessageDialog(business_logic.MainFrame, "Inserimento riuscito");
        }
        else
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito: dati mancanti");
    }

    private boolean NoCampiVuoti_forUtente() {
        return !(AddInstanceClassFrame.getTextField1().getText().equals("")) && !(AddInstanceClassFrame.getTextField2().getText().equals(""))
                && !(AddInstanceClassFrame.getTextField3().getText().equals("") && !(AddInstanceClassFrame.getTextField4().getText().equals(""))
                && !(AddInstanceClassFrame.getTextField5().getText().equals("")));
    }

    private void insertUtente() {
        Tipo_Utente();
        ((Utente) CurrentOggetto).setTitolo(AddInstanceClassFrame.getTextField1().getText());
        ((Utente) CurrentOggetto).setNome(AddInstanceClassFrame.getTextField2().getText());
        ((Utente) CurrentOggetto).setCognome(AddInstanceClassFrame.getTextField3().getText());
        ((Utente) CurrentOggetto).setEmail(AddInstanceClassFrame.getTextField4().getText());
        setIstituzione();
        try {
            CurrentOggetto.getDao().Insert(CurrentOggetto);
            AddInstanceClassFrame.setVisible(false);
        }
        catch (InsertFailedException e){
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito");
        }
    }

    private void Tipo_Utente() {
        if(!(AddInstanceClassFrame.getLeftButton9Button().isEnabled()))
            CurrentOggetto = new Partecipante();
        else
            CurrentOggetto = new Organizzatore();
    }

    private void setIstituzione(){
        ModelClass IstituzioneSelected = (ModelClass) AddInstanceClassFrame.getSelectOne_comboBox13().getSelectedItem();
        ((Utente) CurrentOggetto).setIstit_afferenza((Istituzione) IstituzioneSelected);
    }

    private void setFieldsAdd_forIstituzione(){
        setIstituzione_firstField();
        setIstituzione_secondField();
        Hide_Istituzione_UnusedComponents();
    }

    private void setIstituzione_firstField(){
        AddInstanceClassFrame.getLabel1().setText("Nome");
        AddInstanceClassFrame.getTextField1().setText("");
    }
    private void setIstituzione_secondField(){
        AddInstanceClassFrame.getLabel2().setText("Nazione");
        AddInstanceClassFrame.getTextField2().setText("");
    }
    private void Hide_Istituzione_UnusedComponents(){
        Hide_Istituzione_Unusedlabel();
        Hide_Istituzione_UnusedTextField();
        Hide_Istituzione_UnusedJPanel();
        Hide_CheckboxDisponibilita();
    }
    private void Hide_Istituzione_Unusedlabel(){
        AddInstanceClassFrame.getLabel3().setVisible(false);
        AddInstanceClassFrame.getLabel4().setVisible(false);
        AddInstanceClassFrame.getLabel5().setVisible(false);
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel9().setVisible(false);
        AddInstanceClassFrame.getLabel10().setVisible(false);
        AddInstanceClassFrame.getLabel11().setVisible(false);
        AddInstanceClassFrame.getLabel12().setVisible(false);
        AddInstanceClassFrame.getLabel13().setVisible(false);
        AddInstanceClassFrame.getLabel14().setVisible(false);
    }
    private void Hide_Istituzione_UnusedTextField(){
        AddInstanceClassFrame.getTextField3().setVisible(false);
        AddInstanceClassFrame.getTextField4().setVisible(false);
        AddInstanceClassFrame.getTextField5().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
    }
    private void Hide_Istituzione_UnusedJPanel(){
        AddInstanceClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddInstanceClassFrame.getAddOnly_JPanel11().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddInstanceClassFrame.getSelectOnly_JPanel13().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel14().setVisible(false);
        AddInstanceClassFrame.getTwoButton_JPanel().setVisible(false);
    }

    private void InsertIstituzione_Control() {
        if (NoCampiVuoti_forIstituzione()){
            insertIstituzione();
            AddInstanceClassFrame.setVisible(false);
            EraseAllFieldsInAddFrame();
            JOptionPane.showMessageDialog(business_logic.MainFrame, "Inserimento riuscito");
        }
        else
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito: dati mancanti");
    }

    private boolean NoCampiVuoti_forIstituzione() {
        return !(AddInstanceClassFrame.getTextField1().getText().equals("")) && !(AddInstanceClassFrame.getTextField2().getText().equals(""));
    }

    private void insertIstituzione() {
        ((Istituzione) CurrentOggetto).setNome(AddInstanceClassFrame.getTextField1().getText());
        ((Istituzione) CurrentOggetto).setNazione(AddInstanceClassFrame.getTextField2().getText());
        try {
            CurrentOggetto.getDao().Insert(CurrentOggetto);
            AddInstanceClassFrame.setVisible(false);
        }
        catch (InsertFailedException e){
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito");
        }
    }

    private boolean ListIsSelected(int currentlistIndex) {
        return currentlistIndex != -1;
    }

    public void addButton10Clicked() {
        ModelClass selectedItem10 = (ModelClass) AddInstanceClassFrame.getSelect_comboBox10().getSelectedItem();
        if(!dlModel10.contains(selectedItem10))
            dlModel10.addElement(selectedItem10);
    }

    public void addButton12Clicked() {
        ModelClass selectedItem12 = (ModelClass) AddInstanceClassFrame.getSelect_comboBox12().getSelectedItem();
        if(!dlModel12.contains(selectedItem12))
            dlModel12.addElement(selectedItem12);
    }

    public void addButton14Clicked() {
        ModelClass selectedItem14 = (ModelClass) AddInstanceClassFrame.getSelect_comboBox14().getSelectedItem();
        if(!dlModel14.contains(selectedItem14))
            dlModel14.addElement(selectedItem14);
    }

    public void newButton14Clicked() {
        NewSponsorFrame.setVisible(true);
    }

    public void NewSpons_confermaButtonClicked() {
        NewSponsorFrame.getConfermaButton().setEnabled(false);
        if(NoCampiVuoti_forSpons()) {
            Sponsor tempSponsor = new Sponsor();
            tempSponsor.setNome(NewSponsorFrame.getTextField1().getText());
            tempSponsor.setPartitaIVA(NewSponsorFrame.getTextField2().getText());
            dlModel14.addElement(tempSponsor);
            NewSponsorFrame.setVisible(false);
            NewSponsorFrame.getTextField1().setText("");
            NewSponsorFrame.getTextField2().setText("");
        }
        else
            JOptionPane.showMessageDialog(NewSponsorFrame, "Dati mancanti");
    }

    private boolean NoCampiVuoti_forSpons() {
        return !(NewSponsorFrame.getTextField1().getText().equals("") || NewSponsorFrame.getTextField2().getText().equals(""));
    }

    public void removeButton10Clicked(){
        int currentlistIndex = AddInstanceClassFrame.getSelectedItems_list10().getSelectedIndex();
        if(!dlModel10.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel10.remove(currentlistIndex);
        }
    }

    public void removeButton12Clicked(){
        int currentlistIndex = AddInstanceClassFrame.getSelectedItems_list12().getSelectedIndex();
        if(!dlModel12.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel12.remove(currentlistIndex);
        }
    }

    public void removeButton14Clicked(){
        int currentlistIndex = AddInstanceClassFrame.getSelectedItems_list14().getSelectedIndex();
        if(!dlModel14.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel14.remove(currentlistIndex);
        }
    }

    public void interventoButton_clicked() {
        NewSessioneFrame.setEventoSelected("Intervento");
        NewSessioneFrame.getInterventoButton().setEnabled(false);
        NewSessioneFrame.getEventoSocialeButton().setEnabled(true);
        NewSessioneFrame.getPausaButton().setEnabled(true);
        NewSessioneFrame.getEventoData_JPanel().setVisible(true);
        SetAllEventoDataCompVisible();
        HideInterventoUnused();
    }

    private void HideInterventoUnused() {
        NewSessioneFrame.getDescrizioneLabel().setVisible(false);
        NewSessioneFrame.getTextField6().setVisible(false);
        NewSessioneFrame.getEventoLabel().setVisible(false);
        NewSessioneFrame.getTipoES_comboBox().setVisible(false);
        NewSessioneFrame.getTipologiaLabel().setVisible(false);
        NewSessioneFrame.getTipoP_comboBox().setVisible(false);
    }

    private void SetAllEventoDataCompVisible() {
        for(JComponent jc : NewSessioneFrame.getEventoDataComponents()){
            jc.setVisible(true);
        }
    }

    public void eventoSocialeButton_clicked() {
        NewSessioneFrame.setEventoSelected("Evento Sociale");
        NewSessioneFrame.getInterventoButton().setEnabled(true);
        NewSessioneFrame.getEventoSocialeButton().setEnabled(false);
        NewSessioneFrame.getPausaButton().setEnabled(true);
        NewSessioneFrame.getEventoData_JPanel().setVisible(true);
        SetAllEventoDataCompVisible();
        HideEventoSocialeUnused();
    }

    private void HideEventoSocialeUnused() {
        NewSessioneFrame.getTipologiaLabel().setVisible(false);
        NewSessioneFrame.getTipoP_comboBox().setVisible(false);
        NewSessioneFrame.getIntervLabel().setVisible(false);
        NewSessioneFrame.getInterv_comboBox().setVisible(false);
        NewSessioneFrame.getAbstractLabel().setVisible(false);
        NewSessioneFrame.getTextField5().setVisible(false);
    }

    public void pausaButton_clicked() {
        NewSessioneFrame.setEventoSelected("Pausa");
        NewSessioneFrame.getInterventoButton().setEnabled(true);
        NewSessioneFrame.getEventoSocialeButton().setEnabled(true);
        NewSessioneFrame.getPausaButton().setEnabled(false);
        NewSessioneFrame.getEventoData_JPanel().setVisible(true);
        SetAllEventoDataCompVisible();
        HidePausaUnused();
    }

    private void HidePausaUnused() {
        NewSessioneFrame.getDescrizioneLabel().setVisible(false);
        NewSessioneFrame.getTextField6().setVisible(false);
        NewSessioneFrame.getEventoLabel().setVisible(false);
        NewSessioneFrame.getTipoES_comboBox().setVisible(false);
        NewSessioneFrame.getIntervLabel().setVisible(false);
        NewSessioneFrame.getInterv_comboBox().setVisible(false);
        NewSessioneFrame.getAbstractLabel().setVisible(false);
        NewSessioneFrame.getTextField5().setVisible(false);
    }

    public void setComboboxLocazioniforSessione(){
        ModelClass SedeSelected = (ModelClass) AddInstanceClassFrame.getSelectOne_comboBox13().getSelectedItem();
        int Sede_PK = SedeSelected.toPK();
        NewSessioneFrame.getComboBox3().removeAllItems();
        for(Locazione o : getValues_for_LocazioniforSessione_comboBox(Sede_PK, (Sede) SedeSelected))
            NewSessioneFrame.getComboBox3().addItem(o);
    }

    private List<Locazione> getValues_for_LocazioniforSessione_comboBox(int Sede_PK, Sede sede) {
        Locazione_DAO locazione_temp = Locazione_DAO.getDAO();
        return locazione_temp.getAll_bySede(Sede_PK, sede);
    }

    public void NewSess_AnnullaButton_clicked() {
        NewSessioneFrame.setVisible(false);
        EraseAllTextFieldsInNewSessionFrame();
        NewSessioneFrame.getEventoData_JPanel().setVisible(false);
        AddInstanceClassFrame.getNewButton11().setEnabled(true);
        AddInstanceClassFrame.getSelectOne_comboBox13().setEnabled(true);
    }

    public void NewSess_ConfermaButtonClicked() {
        try {
            if (CheckSessioneInserted()) {
                Sessione tempSessione = createSessione();
                dlModel11.addElement(tempSessione);
                NewSessioneFrame.setVisible(false);
                AddInstanceClassFrame.getNewButton11().setEnabled(true);
                EraseAllTextFieldsInNewSessionFrame();
            }
        }
        catch (IllegalArgumentException ile){
            JOptionPane.showMessageDialog(NewSessioneFrame, "Formato Data e Ora non corretti");
        }
        catch (DataInsertedException efe){
            JOptionPane.showMessageDialog(NewSessioneFrame, efe.getMessage());
        }
    }

    private void EraseAllTextFieldsInNewSessionFrame() {
        for(Component comp: NewSessioneFrame.getComponents()){
            try{
                ((JTextField) comp).setText("");
            }catch (ClassCastException ignored){}
        }
    }

    private Sessione createSessione() {
        Sessione tempSessione = new Sessione();
        tempSessione.setNome(NewSessioneFrame.getTextField0().getText());
        tempSessione.setInizio(getInizioSessioneInLDT());
        tempSessione.setFine(getFineSessioneInLDT());
        tempSessione.setLocazione(((Locazione) NewSessioneFrame.getComboBox3().getSelectedItem()));
        tempSessione.setChair(((Utente) NewSessioneFrame.getComboBox4().getSelectedItem()));
        if(NewSessioneFrame.getComboBox5().isEnabled())
            tempSessione.setKeynote_speaker(((Partecipante) NewSessioneFrame.getComboBox5().getSelectedItem()));
        else
            tempSessione.setKeynote_speaker(null);
        List<Evento> tempEventoList = new ArrayList<>();
        while(!NewSessioneFrame.getEvDLModel().isEmpty()){
            Evento tempEvento = NewSessioneFrame.getEvDLModel().get(0);
            tempEvento.setSessione(tempSessione);
            tempEventoList.add(tempEvento);
            NewSessioneFrame.getEvDLModel().remove(0);
        }
        tempSessione.setEventoList(tempEventoList);
        return tempSessione;
    }

    private boolean CheckSessioneInserted() {
        return CheckNoCampiVuotiForSessione()
               && CheckDate()
               && CheckNoOverlapSessione()
               && CheckChair_e_Keynote();

    }

    private boolean CheckNoOverlapSessione() {
        List<LocalDateTime> listLDT = TryLocalDateTimeConversion();
        List<ModelClass> sessList = getAllbyDLModel(dlModel11);
        for(ModelClass sess: sessList){
            if (SessionsOverlap(listLDT.get(0), listLDT.get(1), (Sessione) sess))
                throw new DataInsertedException("Locazione scelta già occupata nell'intervallo di tempo");
        }
        return true;
    }

    private boolean SessionsOverlap(LocalDateTime Inizio, LocalDateTime Fine, Sessione sess) {
        if(sess.getLocazione().equals(NewSessioneFrame.getComboBox3().getSelectedItem())){
            if     (Inizio.isAfter(sess.getInizio())  && (Fine.isBefore(sess.getFine()))) return true;
            if     (Inizio.isBefore(sess.getInizio()) && Fine.isAfter(sess.getFine())) return true;
            if     (Inizio.isBefore(sess.getInizio()) && Fine.isAfter(sess.getInizio())) return true;
            if     (Inizio.isBefore(sess.getFine()) && Fine.isAfter(sess.getFine())) return true;
            return (Inizio.equals(sess.getInizio())) || (Fine.equals(sess.getFine()));
        }
        else
            return false;
    }

    private List<ModelClass> getAllbyDLModel(DefaultListModel<ModelClass> dlModel11) {
        List<ModelClass> tempList = new ArrayList<>();
        for(int i = 0; i < dlModel11.getSize(); i++){
            tempList.add( dlModel11.getElementAt(i));
        }
        return tempList;
    }

    private Boolean CheckNoCampiVuotiForSessione() {
        if(NoCampiVuoti())
            return true;
        else
            throw new DataInsertedException("Uno o più campi vuoti");

    }

    private boolean NoCampiVuoti() {
        return !(NewSessioneFrame.getTextField1().getText().equals(""))
                && !(NewSessioneFrame.getTextField1_1().getText().equals(""))
                && !(NewSessioneFrame.getTextField2().getText().equals(""))
                && !(NewSessioneFrame.getTextField2_1().getText().equals(""))
                && !(NewSessioneFrame.getEvDLModel().isEmpty())
                && !(NewSessioneFrame.getTextField0().getText().equals(""));
    }

    private boolean CheckDate() {
        List<LocalDateTime> LDTList = TryLocalDateTimeConversion();

        if (LDTList.get(0).isBefore(LDTList.get(1)))
            return true;
        else
            throw new DataInsertedException("Inizio e fine sono invertiti (o coincidono)");
    }

    private List<LocalDateTime> TryLocalDateTimeConversion() {
        List<LocalDateTime> retList = new ArrayList<>(2);
        List<String> strDatas = new ArrayList<>(2);
        strDatas.add(NewSessioneFrame.getTextField1().getText()+" "+NewSessioneFrame.getTextField1_1().getText());
        strDatas.add(NewSessioneFrame.getTextField2().getText()+" "+NewSessioneFrame.getTextField2_1().getText());
        for(String strData : strDatas) {
            Timestamp tempTStamp = Timestamp.valueOf(strData);
            retList.add(tempTStamp.toLocalDateTime());
        }
        return retList;
    }

    private boolean CheckChair_e_Keynote() {
        if(NewSessioneFrame.getComboBox5().isEnabled()) {
            if (NewSessioneFrame.getComboBox4().getSelectedItem().equals(NewSessioneFrame.getComboBox5().getSelectedItem())) {
                throw new DataInsertedException("Keynote Speaker e Chair combaciano");
            } else
                return true;
        }
        else
            return true;

    }

    public void NewSess_AggiungiButtonClicked() {
        try{
            CheckEventoInserted();
            Evento eventoTemp = null;
            switch (NewSessioneFrame.getEventoSelected()) {
                case "Intervento" -> {
                    eventoTemp = new Intervento();
                    setInterventoFields(eventoTemp);
                }
                case "Evento Sociale" -> {
                    eventoTemp = new Evento_Sociale();
                    setEvSocialeFields(eventoTemp);
                }
                case "Pausa" -> {
                    eventoTemp = new Pausa();
                    setPausaFields(eventoTemp);
                }
            }
            if (eventoTemp != null) {
                NewSessioneFrame.getEvDLModel().addElement(eventoTemp);
            }
            EraseAllTextFieldsInNewEventoJPanel();
        }catch (IllegalArgumentException ile){
            JOptionPane.showMessageDialog(NewSessioneFrame.getEventoJPanel(), "Formato Data e Ora non corretti");
        }catch (DataInsertedException efe){
            JOptionPane.showMessageDialog(NewSessioneFrame, "Inserimento fallito: "+efe.getMessage());
        }
    }

    private void CheckEventoInserted() {
        CheckNoCampiVuotiForEvento();
        List<LocalDateTime> listLDT = CheckDateForEvento();
        CheckDateEventoInSessione(listLDT);
        CheckNoOverlap(listLDT);
    }

    private List<LocalDateTime> CheckDateForEvento(){
        List<LocalDateTime> listLDT = TryLocalDateTimeConversionForEvento();
        if(!(listLDT.get(0).isBefore(listLDT.get(1))))
            throw new DataInsertedException("Fine e Inizio sono temporalmente invertiti");
        return listLDT;
    }

    private List<LocalDateTime> TryLocalDateTimeConversionForEvento() {
        List<LocalDateTime> tempListLDT = new ArrayList<>(2);
        tempListLDT.add(getInizioInLDT());
        tempListLDT.add(getFineInLDT());
        return tempListLDT;
    }

    private void CheckDateEventoInSessione(List<LocalDateTime> listLDT) {
        LocalDateTime InizioSess = getInizioSessioneInLDT();
        LocalDateTime FineSess = getFineSessioneInLDT();
        if (!DateEventoInSessione(listLDT.get(0), listLDT.get(1), InizioSess, FineSess))
            throw new DataInsertedException("L'evento non rientra temporalmente nella sessione");
    }

    private boolean DateEventoInSessione(LocalDateTime InizioEv, LocalDateTime FineEv, LocalDateTime InizioSess, LocalDateTime FineSess){
        return  InizioEv.isAfter(InizioSess) || InizioEv.equals(InizioSess) &&
                FineEv.isAfter(InizioSess) &&
                InizioEv.isBefore(FineSess)  &&
                FineEv.isBefore(FineSess)  || FineEv.equals(FineSess);
    }

    private void CheckNoOverlap(List<LocalDateTime> listLDT) {
        List<Evento> EventiInseritiList = getAllEventiInseriti();
        for (Evento e : EventiInseritiList) {
            if(EventsOverlap(listLDT, e))
                throw new DataInsertedException("Evento inserito fa conflitto con un evento precedente");
        }
    }

    private boolean EventsOverlap(List<LocalDateTime> listLDT, Evento e) {
        return (listLDT.get(0).isAfter(e.getInizio()) && listLDT.get(1).isBefore(e.getFine()))
             ||(listLDT.get(0).isBefore(e.getInizio()) && listLDT.get(1).isAfter(e.getFine()))
             ||(listLDT.get(0).isBefore(e.getFine()) && listLDT.get(1).isAfter(e.getFine()))
             ||(listLDT.get(0).isBefore(e.getInizio()) && listLDT.get(1).isAfter(e.getInizio()));
    }

    private List<Evento> getAllEventiInseriti() {
        List<Evento> tempList = new ArrayList<>(NewSessioneFrame.getEvDLModel().getSize());
        for(int i = 0; i < NewSessioneFrame.getEvDLModel().getSize(); i++)
            tempList.add(NewSessioneFrame.getEvDLModel().getElementAt(i));
        return tempList;
    }



    private void CheckNoCampiVuotiForEvento() {
        if(CampiVuotiForEvento())
            throw new DataInsertedException("Dati mancanti");

    }

    private boolean CampiVuotiForEvento() {
        return NewSessioneFrame.getTextField3().getText().equals("") ||
               NewSessioneFrame.getTextField4().getText().equals("") ||
               ((NewSessioneFrame.getTextField5().getText().equals("") && NewSessioneFrame.getTextField6().getText().equals(""))
               && NewSessioneFrame.getPausaButton().isEnabled());
    }

    private void EraseAllTextFieldsInNewEventoJPanel() {
        for(JComponent jc : NewSessioneFrame.getEventoDataComponents()){
            try{
                ((JTextField) jc).setText("");
            }catch (ClassCastException ignored){}
        }
    }

    private void setInterventoFields(Evento eventoTemp) {
        SetInizio_e_Fine(eventoTemp);
        ((Intervento) eventoTemp).setPartecipante((Partecipante) NewSessioneFrame.getInterv_comboBox().getSelectedItem());
        ((Intervento) eventoTemp).setAbstract(NewSessioneFrame.getTextField5().getText());
    }

    private void setEvSocialeFields(Evento eventoTemp) {
        SetInizio_e_Fine(eventoTemp);
        ((Evento_Sociale) eventoTemp).setTipo_evsociale((String) NewSessioneFrame.getTipoES_comboBox().getSelectedItem());
        ((Evento_Sociale) eventoTemp).setDescrizione(NewSessioneFrame.getTextField6().getText());
    }

    private void setPausaFields(Evento eventoTemp) {
        SetInizio_e_Fine(eventoTemp);
        ((Pausa) eventoTemp).setTipo_pausa((String) NewSessioneFrame.getTipoP_comboBox().getSelectedItem());
    }

    private void SetInizio_e_Fine(Evento eventoTemp) {
        eventoTemp.setInizio(getInizioInLDT());
        eventoTemp.setFine(getFineInLDT());
    }

    private LocalDateTime getFineInLDT() {
        String strData = NewSessioneFrame.getTextField4().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime getInizioInLDT() {
        String strData = NewSessioneFrame.getTextField3().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime getInizioSessioneInLDT() {
        String strData = NewSessioneFrame.getTextField1().getText()
                         +" "+NewSessioneFrame.getTextField1_1().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime getFineSessioneInLDT() {
        String strData = NewSessioneFrame.getTextField2().getText()
                         +" "+NewSessioneFrame.getTextField2_1().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime convertToLocalDateTime(java.util.Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toLocalDateTime();
    }

    public void NewSess_NessunoButtonClicked() {
        NewSessioneFrame.getComboBox5().setEnabled(!NewSessioneFrame.getComboBox5().isEnabled());
        if(NewSessioneFrame.getComboBox5().isEnabled()){
            NewSessioneFrame.getNessunoButton().setText("Nessuno");
        }
        else if(!NewSessioneFrame.getComboBox5().isEnabled()){
            NewSessioneFrame.getNessunoButton().setText("Scegli");
        }
    }

    public void NewSess_RimuoviButtonClicked() {
        if(!NewSessioneFrame.getEvDLModel().isEmpty() && NewSessioneFrame.getList1().getSelectedIndex() != -1){
            NewSessioneFrame.getEvDLModel().remove(NewSessioneFrame.getList1().getSelectedIndex());
        }
    }

    public void CheckCorrectSessioneDates() {
        try{
            getInizioSessioneInLDT();
            getFineSessioneInLDT();
            NewSessioneFrame.getAddButtonLabel().setVisible(false);
            NewSessioneFrame.getAggiungiButton().setEnabled(true);
        }
        catch (IllegalArgumentException iae){
            NewSessioneFrame.getAddButtonLabel().setVisible(true);
            NewSessioneFrame.getAggiungiButton().setEnabled(false);
        }
    }

    public void CheckCorrectConferenzaDates() {
        if(ClassSelected.equals("Conferenza")) {
            try {
                getInizioConferenzaInDate();
                getFineConferenzaInDate();
                AddInstanceClassFrame.getCheckDisponibilitaButton().setEnabled(true);
                AddInstanceClassFrame.getCheckButtonLabel().setVisible(false);
            } catch (IllegalArgumentException | DateTimeParseException | ParseException TypeE) {
                System.out.println(TypeE.getMessage());
                AddInstanceClassFrame.getCheckDisponibilitaButton().setEnabled(false);
                AddInstanceClassFrame.getCheckButtonLabel().setVisible(true);
            }
        }
    }

    private Date getFineConferenzaInDate() throws ParseException {
        String strData2 = AddInstanceClassFrame.getTextField3().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData2);
    }

    private Date getInizioConferenzaInDate() throws ParseException{
        String strData1 = AddInstanceClassFrame.getTextField2().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData1);
    }

    private List<Date> GetDateFromInsertedConferenza() throws ParseException {
        List<Date> tempList = new ArrayList<>(2);
        tempList.add(getInizioConferenzaInDate());
        tempList.add(getFineConferenzaInDate());
        return tempList;
    }

    public void CheckButtonClicked() {
        try {
            if (CheckNoOverlapConferenze() && CheckDatesOrdered()) {
                AddInstanceClassFrame.getCheckBox1().setSelected(true);
                AddInstanceClassFrame.getNewButton11().setEnabled(true);
            } else {
                AddInstanceClassFrame.getCheckBox1().setSelected(false);
                AddInstanceClassFrame.getNewButton11().setEnabled(false);
            }
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }catch(DataInsertedException die){
            AddInstanceClassFrame.getCheckBox1().setSelected(false);
            AddInstanceClassFrame.getNewButton11().setEnabled(false);
            JOptionPane.showMessageDialog(AddInstanceClassFrame, die.getMessage());
        }
    }

    private boolean CheckDatesOrdered() throws ParseException {
        List<Date> DateConferenza = GetDateFromInsertedConferenza();
        if(DateConferenza.get(0).before(DateConferenza.get(1)))
            return true;
        else
            throw new DataInsertedException("Date di Inizio e Fine invertite");
    }

    private boolean CheckNoOverlapConferenze() throws ParseException {
        List<Date> DateConferenza = GetDateFromInsertedConferenza();
        for(ModelClass c : Conferenza_DAO.getDAO().getAll())
            if(OverlapDate(DateConferenza.get(0), DateConferenza.get(1), (Conferenza) c)) {
                throw new DataInsertedException("Sede occupata nelle date scelte");
            }
        return true;
    }

    private boolean OverlapDate(Date Inizio, Date Fine, Conferenza c) {
        if(c.getCollocazione().equals(AddInstanceClassFrame.getSelectOne_comboBox13().getSelectedItem())) {
            if     (Inizio.after(c.getDataInizio())  && (Fine.before(c.getDataFine()))) return true;
            if     (Inizio.before(c.getDataInizio()) && Fine.after(c.getDataFine())) return true;
            if     (Inizio.before(c.getDataInizio()) && Fine.after(c.getDataInizio())) return true;
            if     (Inizio.before(c.getDataFine()) && Fine.after(c.getDataFine())) return true;
            return (Inizio.equals(c.getDataInizio())) || (Fine.equals(c.getDataFine()));
        }
        else
            return false;
    }
}
