package Business_Logic;

import DAO_classes.*;
import Exceptions.DataInsertedException;
import GUI_classes.CF_AddEditClassFrame;
import GUI_classes.CF_NewLocazioneFrame;
import GUI_classes.CF_NewSessioneFrame;
import GUI_classes.CF_NewSponsorFrame;
import Model_classes.*;

import java.awt.*;
import java.lang.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEditFrameAppearanceController {
    Controller business_logic;
    CF_AddEditClassFrame AddEditClassFrame;
    CF_NewLocazioneFrame NewLocazioneFrame;
    CF_NewSponsorFrame NewSponsorFrame;
    CF_NewSessioneFrame NewSessioneFrame;
    String ClassSelected;

    private final DefaultListModel<ModelClass> dlModel10;
    private final DefaultListModel<ModelClass> dlModel11;
    private final DefaultListModel<ModelClass> dlModel12;
    private final DefaultListModel<ModelClass> dlModel14;

    public AddEditFrameAppearanceController(Controller c) {
        business_logic = c;
        AddEditClassFrame = business_logic.AddEditClassFrame;
        NewLocazioneFrame = business_logic.NewLocazioneFrame;
        NewSponsorFrame = business_logic.NewSponsorFrame;
        NewSessioneFrame = business_logic.NewSessioneFrame;
        dlModel10 = AddEditClassFrame.getDlModel10();
        dlModel11 = AddEditClassFrame.getDlModel11();
        dlModel12 = AddEditClassFrame.getDlModel12();
        dlModel14 = AddEditClassFrame.getDlModel14();
    }

    public DefaultListModel<ModelClass> getDlModel10() {
        return dlModel10;
    }

    public DefaultListModel<ModelClass> getDlModel11() {
        return dlModel11;
    }

    public DefaultListModel<ModelClass> getDlModel12() {
        return dlModel12;
    }

    public DefaultListModel<ModelClass> getDlModel14() {
        return dlModel14;
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

    void NewSessioneFrameSetUp() {
        setComboboxLocazioniforSessione();
        setComboboxChairforSessione();
        setComboboxKeynoteforSessione();
        setComboboxInterventistaforSessione();
        AddEditClassFrame.getNewButton11().setEnabled(false);
        AddEditClassFrame.getSelectOne_comboBox13().setEnabled(false);
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

    public void ChoiceClassAdd(String selectedClass) {
        ClassSelected = selectedClass;
        SetAllToVisible();
        switch (selectedClass) {
            case "Conferenza" -> setFieldsAdd_forConferenza();
            case "Sede" -> setFieldsAdd_forSede();
            case "Utente", "Organizzatore", "Partecipante" -> setFieldsAdd_forUtente();
            case "Istituzione" -> setFieldsAdd_forIstituzione();
        }
    }

    private void SetAllToVisible() {
        for(JComponent jc : AddEditClassFrame.getDataInsertComponentList())
            jc.setVisible(true);
        AddEditClassFrame.getNewButton11().setEnabled(true);
    }

    private void setFieldsAdd_forConferenza(){
        AddEditClassFrame.getNewButton11().setEnabled(false);
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
        AddEditClassFrame.getLabel1().setText("Nome");
        AddEditClassFrame.getTextField1().setText("");
    }
    private void setConf_secondField(){
        AddEditClassFrame.getLabel2().setText("Data Inizio");
        AddEditClassFrame.getTextField2().setText("");
    }
    private void setConf_thirdField(){
        AddEditClassFrame.getLabel3().setText("Data Fine");
        AddEditClassFrame.getTextField3().setText("");
    }
    private void setConf_fourthField(){
        AddEditClassFrame.getLabel4().setText("Descrizione");
        AddEditClassFrame.getTextField4().setText("");
    }
    private void setConf_FK(){
        AddEditClassFrame.getLabel13().setText("Collocazione");
        setValues_in_Select_comboBox13();
    }

    private List<ModelClass> getValues_for_Select_comboBox(String class_selected) {
        return new dbAccess_byClassName().GetAll_byClassName(class_selected);
    }

    private void setConf_SessioniField(){
        AddEditClassFrame.getLabel11().setText("Sessioni");
    }

    private void setConf_EntiOrgField(){
        AddEditClassFrame.getLabel10().setText("Enti organizzatori");
        setValues_in_Select_comboBox10("Istituzione");
    }

    private void setValues_in_Select_comboBox10(String ClassIntoComboBox) {
        for(ModelClass o : getValues_for_Select_comboBox(ClassIntoComboBox))
            AddEditClassFrame.getSelect_comboBox10().addItem(o);
    }

    private void setConf_OrganField() {
        AddEditClassFrame.getLabel12().setText("Organizzatori");
        setValues_in_Select_comboBox12("Organizzatori");
    }

    private void setValues_in_Select_comboBox12(String ClassIntoComboBox) {
        for(ModelClass o : getValues_for_Select_comboBox(ClassIntoComboBox))
            AddEditClassFrame.getSelect_comboBox12().addItem(o);
    }

    private void setConf_SponsorField() {
        AddEditClassFrame.getLabel14().setText("Sponsor");
        setValues_in_Select_comboBox14("Sponsor");
    }

    private void setValues_in_Select_comboBox14(String ClassIntoComboBox) {
        for(ModelClass o : getValues_for_Select_comboBox(ClassIntoComboBox))
            AddEditClassFrame.getSelect_comboBox14().addItem(o);
    }

    private void Hide_Conferenza_UnusedComponents(){
        Hide_Conferenza_Unusedlabel();
        Hide_Conferenza_UnusedTextField();
        Hide_Conferenza_UnusedButton();
    }
    private void Hide_Conferenza_Unusedlabel(){
        AddEditClassFrame.getLabel5().setVisible(false);
        AddEditClassFrame.getLabel6().setVisible(false);
        AddEditClassFrame.getLabel7().setVisible(false);
        AddEditClassFrame.getLabel8().setVisible(false);
        AddEditClassFrame.getLabel9().setVisible(false);
    }
    private void Hide_Conferenza_UnusedTextField(){
        AddEditClassFrame.getTextField5().setVisible(false);
        AddEditClassFrame.getTextField6().setVisible(false);
        AddEditClassFrame.getTextField7().setVisible(false);
        AddEditClassFrame.getTextField8().setVisible(false);
        AddEditClassFrame.getnewButton10().setVisible(false);
    }
    private void Hide_Conferenza_UnusedButton(){
        AddEditClassFrame.getTwoButton_JPanel().setVisible(false);
        AddEditClassFrame.getnewButton10().setVisible(false);
        AddEditClassFrame.getnewButton12().setVisible(false);
    }

    private void setFieldsAdd_forSede(){
        setSede_firstField();
        setSede_secondField();
        setSede_thirdField();
        setSede_LocazioneList();
        Hide_Sede_UnusedComponents();
    }
    private void setSede_firstField(){
        AddEditClassFrame.getLabel1().setText("Nome");
        AddEditClassFrame.getTextField1().setText("");
    }
    private void setSede_secondField(){
        AddEditClassFrame.getLabel2().setText("Indirizzo");
        AddEditClassFrame.getTextField2().setText("");
    }
    private void setSede_thirdField(){
        AddEditClassFrame.getLabel3().setText("Città");
        AddEditClassFrame.getTextField3().setText("");
    }
    private void setSede_LocazioneList(){
        AddEditClassFrame.getLabel11().setText("Locazioni");
        dlModel11.clear();
    }
    private void Hide_Sede_UnusedComponents(){
        Hide_Sede_Unusedlabel();
        Hide_Sede_UnusedTextField();
        Hide_Sede_UnusedJPanel();
        Hide_CheckboxDisponibilita();
        AddEditClassFrame.getSelectItems_JPanel10().setVisible(false);
    }

    private void Hide_Sede_Unusedlabel(){
        AddEditClassFrame.getLabel4().setVisible(false);
        AddEditClassFrame.getLabel5().setVisible(false);
        AddEditClassFrame.getLabel6().setVisible(false);
        AddEditClassFrame.getLabel7().setVisible(false);
        AddEditClassFrame.getLabel8().setVisible(false);
        AddEditClassFrame.getLabel9().setVisible(false);
        AddEditClassFrame.getLabel10().setVisible(false);
        AddEditClassFrame.getLabel12().setVisible(false);
        AddEditClassFrame.getLabel13().setVisible(false);
        AddEditClassFrame.getLabel14().setVisible(false);
    }

    private void Hide_Sede_UnusedTextField(){
        AddEditClassFrame.getTextField4().setVisible(false);
        AddEditClassFrame.getTextField5().setVisible(false);
        AddEditClassFrame.getTextField6().setVisible(false);
        AddEditClassFrame.getTextField7().setVisible(false);
        AddEditClassFrame.getTextField8().setVisible(false);
    }

    private void Hide_Sede_UnusedJPanel(){
        AddEditClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddEditClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddEditClassFrame.getSelectOnly_JPanel13().setVisible(false);
        AddEditClassFrame.getSelectItems_JPanel14().setVisible(false);
        AddEditClassFrame.getTwoButton_JPanel().setVisible(false);
    }

    private void Hide_CheckboxDisponibilita(){
        AddEditClassFrame.getCheckBox1().setVisible(false);
        AddEditClassFrame.getCheckDisponibilitaButton().setVisible(false);
        AddEditClassFrame.getCheckButtonLabel().setVisible(false);
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
        AddEditClassFrame.getLabel1().setText("Titolo");
        AddEditClassFrame.getTextField1().setText("");
    }
    private void setUtente_secondField(){
        AddEditClassFrame.getLabel2().setText("Nome");
        AddEditClassFrame.getTextField2().setText("");
    }
    private void setUtente_thirdField(){
        AddEditClassFrame.getLabel3().setText("Cognome");
        AddEditClassFrame.getTextField3().setText("");
    }
    private void setUtente_fourthField(){
        AddEditClassFrame.getLabel4().setText("Email");
        AddEditClassFrame.getTextField4().setText("");
    }
    private void setUtente_FK(){
        AddEditClassFrame.getLabel13().setText("Istituzione di afferenza");
        setValues_in_Select_comboBox13();
    }

    private void setUtente_tipo(){
        AddEditClassFrame.getLabel9().setText("Ruolo");
        AddEditClassFrame.getLeftButton9Button().setText("Partecipante");
        AddEditClassFrame.getRightButton9Button().setText("Organizzatore");
    }
    private void Hide_Utente_UnusedComponents(){
        Hide_Utente_UnusedLabel();
        Hide_Utente_UnusedTextField();
        Hide_Utente_UnusedJPanel();
        Hide_CheckboxDisponibilita();
    }
    private void Hide_Utente_UnusedLabel(){
        AddEditClassFrame.getLabel5().setVisible(false);
        AddEditClassFrame.getLabel6().setVisible(false);
        AddEditClassFrame.getLabel7().setVisible(false);
        AddEditClassFrame.getLabel8().setVisible(false);
        AddEditClassFrame.getLabel10().setVisible(false);
        AddEditClassFrame.getLabel11().setVisible(false);
        AddEditClassFrame.getLabel12().setVisible(false);
        AddEditClassFrame.getLabel14().setVisible(false);
    }
    private void Hide_Utente_UnusedTextField(){
        AddEditClassFrame.getTextField5().setVisible(false);
        AddEditClassFrame.getTextField6().setVisible(false);
        AddEditClassFrame.getTextField7().setVisible(false);
        AddEditClassFrame.getTextField8().setVisible(false);
    }
    private void Hide_Utente_UnusedJPanel(){
        AddEditClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddEditClassFrame.getAddOnly_JPanel11().setVisible(false);
        AddEditClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddEditClassFrame.getSelectItems_JPanel14().setVisible(false);
    }

    public void setValues_in_Select_comboBox13() {
        String classIntoComboBox = chooseClassIntoComboBox13();
        for(ModelClass o: getValues_for_Select_comboBox(classIntoComboBox)){
            try {
                AddEditClassFrame.getSelectOne_comboBox13().addItem(o);
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

    private void setFieldsAdd_forIstituzione(){
        setIstituzione_firstField();
        setIstituzione_secondField();
        Hide_Istituzione_UnusedComponents();
    }

    private void setIstituzione_firstField(){
        AddEditClassFrame.getLabel1().setText("Nome");
        AddEditClassFrame.getTextField1().setText("");
    }
    private void setIstituzione_secondField(){
        AddEditClassFrame.getLabel2().setText("Nazione");
        AddEditClassFrame.getTextField2().setText("");
    }
    private void Hide_Istituzione_UnusedComponents(){
        Hide_Istituzione_Unusedlabel();
        Hide_Istituzione_UnusedTextField();
        Hide_Istituzione_UnusedJPanel();
        Hide_CheckboxDisponibilita();
    }
    private void Hide_Istituzione_Unusedlabel(){
        AddEditClassFrame.getLabel3().setVisible(false);
        AddEditClassFrame.getLabel4().setVisible(false);
        AddEditClassFrame.getLabel5().setVisible(false);
        AddEditClassFrame.getLabel6().setVisible(false);
        AddEditClassFrame.getLabel7().setVisible(false);
        AddEditClassFrame.getLabel8().setVisible(false);
        AddEditClassFrame.getLabel9().setVisible(false);
        AddEditClassFrame.getLabel10().setVisible(false);
        AddEditClassFrame.getLabel11().setVisible(false);
        AddEditClassFrame.getLabel12().setVisible(false);
        AddEditClassFrame.getLabel13().setVisible(false);
        AddEditClassFrame.getLabel14().setVisible(false);
    }
    private void Hide_Istituzione_UnusedTextField(){
        AddEditClassFrame.getTextField3().setVisible(false);
        AddEditClassFrame.getTextField4().setVisible(false);
        AddEditClassFrame.getTextField5().setVisible(false);
        AddEditClassFrame.getTextField6().setVisible(false);
        AddEditClassFrame.getTextField7().setVisible(false);
        AddEditClassFrame.getTextField8().setVisible(false);
    }
    private void Hide_Istituzione_UnusedJPanel(){
        AddEditClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddEditClassFrame.getAddOnly_JPanel11().setVisible(false);
        AddEditClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddEditClassFrame.getSelectOnly_JPanel13().setVisible(false);
        AddEditClassFrame.getSelectItems_JPanel14().setVisible(false);
        AddEditClassFrame.getTwoButton_JPanel().setVisible(false);
    }

    public void NewLocOperations_afterConferma() {
        if(NoCampiVuotiInJPanel(NewLocazioneFrame.getMainPanel())){
            AddNewLocazioneToJList();
            business_logic.HideNewLocationFrame();
            NewLocazioneFrame.getConfermaButton().setEnabled(true);
        }
        else
            JOptionPane.showMessageDialog(NewLocazioneFrame, "ERRORE: Dati mancanti");
    }



    private void AddNewLocazioneToJList() {
        Locazione locazioneTemp = getInsertedLocazione();
        dlModel11.addElement(locazioneTemp);
    }

    private Locazione getInsertedLocazione() {
        Locazione locazioneTemp = new Locazione();
        locazioneTemp.setNome(NewLocazioneFrame.getTextField1().getText());
        locazioneTemp.setPostiDisponibili(Integer.parseInt(NewLocazioneFrame.getTextField2().getText()));
        return locazioneTemp;
    }


    public void removeButtonClicked(){
        int currentlistIndex = AddEditClassFrame.getAddOnly_list11().getSelectedIndex();
        if(!dlModel11.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel11.remove(currentlistIndex);
        }
    }

    private boolean ListIsSelected(int currentlistIndex) {
        return currentlistIndex != -1;
    }

    public void addButton10Clicked() {
        ModelClass selectedItem10 = (ModelClass) AddEditClassFrame.getSelect_comboBox10().getSelectedItem();
        if(!dlModel10.contains(selectedItem10))
            dlModel10.addElement(selectedItem10);
    }

    public void addButton12Clicked() {
        ModelClass selectedItem12 = (ModelClass) AddEditClassFrame.getSelect_comboBox12().getSelectedItem();
        if(!dlModel12.contains(selectedItem12))
            dlModel12.addElement(selectedItem12);
    }

    public void addButton14Clicked() {
        ModelClass selectedItem14 = (ModelClass) AddEditClassFrame.getSelect_comboBox14().getSelectedItem();
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
        int currentlistIndex = AddEditClassFrame.getSelectedItems_list10().getSelectedIndex();
        if(!dlModel10.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel10.remove(currentlistIndex);
        }
    }

    public void removeButton12Clicked(){
        int currentlistIndex = AddEditClassFrame.getSelectedItems_list12().getSelectedIndex();
        if(!dlModel12.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel12.remove(currentlistIndex);
        }
    }

    public void removeButton14Clicked(){
        int currentlistIndex = AddEditClassFrame.getSelectedItems_list14().getSelectedIndex();
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
        ModelClass SedeSelected = (ModelClass) AddEditClassFrame.getSelectOne_comboBox13().getSelectedItem();
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
        AddEditClassFrame.getNewButton11().setEnabled(true);
        AddEditClassFrame.getSelectOne_comboBox13().setEnabled(true);
    }

    //TODO: Sposta in controller accordingly
    public void NewSess_ConfermaButtonClicked() {
        try {
            if (CheckSessioneInserted()) {
                Sessione tempSessione = createSessione();
                dlModel11.addElement(tempSessione);
                NewSessioneFrame.setVisible(false);
                AddEditClassFrame.getNewButton11().setEnabled(true);
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
        tempListLDT.add(getInizioEventoInLDT());
        tempListLDT.add(getFineEventoInLDT());
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
        eventoTemp.setInizio(getInizioEventoInLDT());
        eventoTemp.setFine(getFineEventoInLDT());
    }

    private LocalDateTime getFineEventoInLDT() {
        String strData = NewSessioneFrame.getTextField4().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime getInizioEventoInLDT() {
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

    void EnableCheckButton(boolean b) {
        AddEditClassFrame.getCheckDisponibilitaButton().setEnabled(b);
        AddEditClassFrame.getCheckButtonLabel().setVisible(!b);
    }

    private Date getFineConferenzaInDate() throws ParseException {
        String strData2 = AddEditClassFrame.getTextField3().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData2);
    }

    private Date getInizioConferenzaInDate() throws ParseException{
        String strData1 = AddEditClassFrame.getTextField2().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData1);
    }

    void EnableConf_SessioneAdd(boolean b) {
        AddEditClassFrame.getCheckBox1().setSelected(b);
        AddEditClassFrame.getNewButton11().setEnabled(b);
    }

    private boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }

    private boolean NoCampiVuotiInJPanel(JPanel mainPanel) {
        for(Component comp: mainPanel.getComponents()){
            try{
                if(isEmpty((JTextField) comp))
                    return false;
            }catch (ClassCastException ignored){}
        }
        return true;
    }

    public void EnableEventiAdd() {
        NewSessioneFrame.getAddButtonLabel().setVisible(false);
        NewSessioneFrame.getAggiungiButton().setEnabled(true);
    }

    public void DisableEventiAdd() {
        NewSessioneFrame.getAddButtonLabel().setVisible(true);
        NewSessioneFrame.getAggiungiButton().setEnabled(false);
    }
}
