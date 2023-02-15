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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

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
    private final DefaultListModel<String> dlModel12i;
    private final DefaultListModel<Integer> dlModel14i;

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
        dlModel12i = AddEditClassFrame.getDlModel12i();
        dlModel14i = AddEditClassFrame.getDlModel14i();
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

    public Sessione SetValuesForSessioneToEdit() {
        Sessione sessione = (Sessione) AddEditClassFrame.getAddOnly_list11().getSelectedValue();
        NewSessioneFrame.getTextField0().setText(sessione.getNome());
        NewSessioneFrame.getTextField1().setText(sessione.getInizio().toLocalDate().toString());
        NewSessioneFrame.getTextField1_1().setText(sessione.getInizio().toLocalTime().toString());
        NewSessioneFrame.getTextField2().setText(sessione.getFine().toLocalDate().toString());
        NewSessioneFrame.getTextField2_1().setText(sessione.getFine().toLocalTime().toString());
        NewSessioneFrame.getComboBox3().setSelectedItem(sessione.getLocazione());
        NewSessioneFrame.getComboBox4().setSelectedItem(sessione.getChair());
        if(isNull(sessione.getKeynote_speaker()))
            business_logic.NewSess_NessunoButtonClicked();
        else
            NewSessioneFrame.getComboBox5().setSelectedItem(sessione.getKeynote_speaker());
        for(Evento e: sessione.getEventoList()){
            NewSessioneFrame.getEvDLModel().addElement(e);
        }
        return sessione;
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

    void AddNewLocazioneToJList(Locazione tempLocazione) {
        dlModel11.addElement(tempLocazione);
        HideNewLocationFrame();
        NewLocazioneFrame.getConfermaButton().setEnabled(true);
    }

    void HideNewLocationFrame() {
        NewLocazioneFrame.setVisible(false);
        NewLocazioneFrame.getTextField1().setText("");
        NewLocazioneFrame.getTextField2().setText("");
    }

    public ModelClass RemoveSelectedItemFromList11(){
        int currentlistIndex = AddEditClassFrame.getAddOnly_list11().getSelectedIndex();
        if(isListSelected(AddEditClassFrame.getAddOnly_list11())){
            return dlModel11.remove(currentlistIndex);
        }
        throw new DataInsertedException("Cannot remove object");
    }

    private boolean ListIsSelected(int currentlistIndex) {
        return currentlistIndex != -1;
    }

    public ModelClass AddSelectedItemToList10() {
        ModelClass selectedItem10 = (ModelClass) AddEditClassFrame.getSelect_comboBox10().getSelectedItem();
        if(!dlModel10.contains(selectedItem10)) {
            dlModel10.addElement(selectedItem10);
            return selectedItem10;
        }
        throw new DataInsertedException("Object already added");
    }

    public ModelClass AddSelectedItemToList12() {
        ModelClass selectedItem12 = (ModelClass) AddEditClassFrame.getSelect_comboBox12().getSelectedItem();
        if(!dlModel12.contains(selectedItem12)) {
            dlModel12.addElement(selectedItem12);
            dlModel12i.addElement(getComitato());
            return selectedItem12;
        }
        throw new DataInsertedException("Object already added");
    }

    String getComitato() {
        if(AddEditClassFrame.getLocaleButton().isEnabled())
            return "Scientifico";
        else
            return "Locale";
    }

    public ModelClass AddSelectedItemToList14() {
        ModelClass selectedItem14 = (ModelClass) AddEditClassFrame.getSelect_comboBox14().getSelectedItem();
        if(!dlModel14.contains(selectedItem14)) {
            dlModel14.addElement(selectedItem14);
            dlModel14i.addElement(getImporto());
            return selectedItem14;
        }
        throw new DataInsertedException("Object already added");
    }

    Integer getImporto() {
        Integer importo = Integer.parseInt(AddEditClassFrame.getTextField9().getText());
        return importo;
    }

    void ClearImporto(){
        AddEditClassFrame.getTextField9().setText("");
    }

    public void ShowNewSponsorFrame() {
        NewSponsorFrame.getConfermaButton().setEnabled(false);
        NewSponsorFrame.setVisible(true);
    }

    public void AddNewSponsToJList(Sponsor tempSponsor) {
        dlModel14.addElement(tempSponsor);
        HideNewSponsorFrame();
    }

    public void AddNewImportoToJList(Integer importo) {
        dlModel14i.addElement(importo);
    }

    void HideNewSponsorFrame() {
        NewSponsorFrame.setVisible(false);
        NewSponsorFrame.getTextField1().setText("");
        NewSponsorFrame.getTextField2().setText("");
    }

    public ModelClass RemoveSelectedItemFromList10(){
        int currentlistIndex = AddEditClassFrame.getSelectedItems_list10().getSelectedIndex();
        if(!dlModel10.isEmpty() && ListIsSelected(currentlistIndex)){
            return dlModel10.remove(currentlistIndex);
        }
        throw new DataInsertedException("Cannot remove object");
    }

    public ModelClass RemoveSelectedItemFromList12(){
        int currentlistIndex = AddEditClassFrame.getSelectedItems_list12().getSelectedIndex();
        if(!dlModel12.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel12i.remove(currentlistIndex);
            return dlModel12.remove(currentlistIndex);
        }
        throw new DataInsertedException("Cannot remove object");
    }

    public ModelClass RemoveSelectedItemFromList14(){
        int currentlistIndex = AddEditClassFrame.getSelectedItems_list14().getSelectedIndex();
        if(!dlModel14.isEmpty() && ListIsSelected(currentlistIndex)){
            dlModel14i.remove(currentlistIndex);
            return dlModel14.remove(currentlistIndex);
        }
        throw new DataInsertedException("Cannot remove object");
    }

    public void Select_interventoButton() {
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

    public void Select_eventoSocialeButton() {
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

    public void Select_pausaButton() {
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

    public void HideNewSessFrame() {
        NewSessioneFrame.setVisible(false);
        AddEditClassFrame.getNewButton11().setEnabled(true);
        if(dlModel11.isEmpty())
            AddEditClassFrame.getSelectOne_comboBox13().setEnabled(true);
        EraseAllFieldsInNewSessionFrame();
        NewSessioneFrame.getEventoData_JPanel().setVisible(false);
        NewSessioneFrame.getPausaButton().setEnabled(true);
        NewSessioneFrame.getEventoSocialeButton().setEnabled(true);
        NewSessioneFrame.getInterventoButton().setEnabled(true);
    }

    public void InsertNewSessioneInJList(Sessione tempSessione) {
        dlModel11.addElement(tempSessione);
        HideNewSessFrame();
    }

    private void EraseAllFieldsInNewSessionFrame() {
        for(Component comp: NewSessioneFrame.getComponents()){
            try{
                ((JTextField) comp).setText("");
            }catch (ClassCastException ignored){}
        }
    }

    public void AddNewEventoToJList(Evento eventoTemp) {
        if (eventoTemp != null) {
            NewSessioneFrame.getEvDLModel().addElement(eventoTemp);
        }
        EraseAllTextFieldsInNewEventoJPanel();

    }

    private void EraseAllTextFieldsInNewEventoJPanel() {
        for(JComponent jc : NewSessioneFrame.getEventoDataComponents()){
            try{
                ((JTextField) jc).setText("");
            }catch (ClassCastException ignored){}
        }
    }

    public void NewSess_Switch_NoKeynote() {
        NewSessioneFrame.getComboBox5().setEnabled(!NewSessioneFrame.getComboBox5().isEnabled());
        if(NewSessioneFrame.getComboBox5().isEnabled()){
            NewSessioneFrame.getNessunoButton().setText("Nessuno");
        }
        else if(!NewSessioneFrame.getComboBox5().isEnabled()){
            NewSessioneFrame.getNessunoButton().setText("Scegli");
        }
    }

    public void NewSess_RemoveSelectedEvento() {
        if(isListSelected(NewSessioneFrame.getList1())){
            NewSessioneFrame.getEvDLModel().remove(NewSessioneFrame.getList1().getSelectedIndex());
        }
    }

    private boolean isListSelected(JList list) {
        return !(list.getModel().getSize() == 0) && list.getSelectedIndex() != -1;
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

    public void EnableEventiAdd() {
        NewSessioneFrame.getAddButtonLabel().setVisible(false);
        NewSessioneFrame.getAggiungiButton().setEnabled(true);
    }

    public void DisableEventiAdd() {
        NewSessioneFrame.getAddButtonLabel().setVisible(true);
        NewSessioneFrame.getAggiungiButton().setEnabled(false);
    }

    public void switchLocaleButton(boolean b) {
        AddEditClassFrame.getLocaleButton().setEnabled(b);
        AddEditClassFrame.getScientificoButton().setEnabled(!b);
        AddEditClassFrame.getAddButton12().setEnabled(true);
    }

    public void EnableSponsorAdd(boolean b) {
        AddEditClassFrame.getAddButton14().setEnabled(b);
    }

    public void setValuesForObjectToEdit(String classSelected, ModelClass currentObject) {
        switch (ClassSelected){
            case "Conferenza" -> SetConferenzaValues((Conferenza) currentObject);
            case "Sede" -> SetSedeValues((Sede) currentObject);
            case "Utente", "Partecipante", "Organizzatore" -> SetUtenteValues((Utente) currentObject);
            case "Istituzione" -> SetIstituzioneValues((Istituzione) currentObject);
        }
    }

    private void SetConferenzaValues(Conferenza currentObject) {
        AddEditClassFrame.getTextField1().setText(currentObject.getNome());
        AddEditClassFrame.getTextField2().setText(getDateString(currentObject.getDataInizio()));
        AddEditClassFrame.getTextField3().setText(getDateString(currentObject.getDataFine()));
        AddEditClassFrame.getTextField4().setText(currentObject.getDescrizione());
        AddEditClassFrame.getSelectOne_comboBox13().setSelectedItem(currentObject.getCollocazione());
        dlModel11.addAll(currentObject.getSessioneList());
        dlModel10.addAll(getAllIstituzioniByConferenza(currentObject));
        dlModel12.addAll(getAllOrganizzatoriByConferenza(currentObject));
        dlModel12i.addAll(getAllComitatiByConferenza(currentObject));
        dlModel14.addAll(getAllSponsorByConferenza(currentObject));
        dlModel14i.addAll(getAllImportiByConferenza(currentObject));
    }

    private String getDateString(Date dataInizio) {
        return String.valueOf(dataInizio);
    }

    private Collection<? extends ModelClass> getAllIstituzioniByConferenza(Conferenza currentObject) {
        List<Istituzione> istitList = new ArrayList<>();
        for(ModelClass eo : Ente_Organizzatore_DAO.getDAO().getAll_byAttribute("conferenza", String.valueOf(currentObject.toPK()))){
            istitList.add(((Ente_organizzatore) eo).getIstituzione());
        }
        return istitList;
    }

    private Collection<? extends ModelClass> getAllOrganizzatoriByConferenza(Conferenza currentObject) {
        List<Organizzatore> orgList = new ArrayList<>();
        for(ModelClass org : Conf_Organ_DAO.getDAO().getAll_byAttribute("conferenza", String.valueOf(currentObject.toPK()))){
            orgList.add(((Conf_Organ) org).getOrganizzatore());
        }
        return orgList;
    }

    private Collection<String> getAllComitatiByConferenza(Conferenza currentObject) {
        List<String> comitatiList = new ArrayList<>();
        for(ModelClass org: Conf_Organ_DAO.getDAO().getAll_byAttribute("conferenza", String.valueOf(currentObject.toPK()))){
            comitatiList.add(((Conf_Organ) org).getComitato());
        }
        return comitatiList;
    }

    private Collection<? extends ModelClass> getAllSponsorByConferenza(Conferenza currentObject) {
        List<Sponsor> sponsList = new ArrayList<>();
        for(ModelClass confspo: Conf_Sponsor_DAO.getDAO().getAll_byAttribute("conferenza", String.valueOf(currentObject.toPK()))){
            sponsList.add(((Conf_Sponsor) confspo).getSponsor());
        }
        return sponsList;
    }

    private Collection<Integer> getAllImportiByConferenza(Conferenza currentObject) {
        List<Integer> importiList = new ArrayList<>();
        for(ModelClass confspo: Conf_Sponsor_DAO.getDAO().getAll_byAttribute("conferenza", String.valueOf(currentObject.toPK()))){
            importiList.add(getImporto(confspo));
        }
        return importiList;
    }

    private Integer getImporto(ModelClass confspo) {
        return ((Conf_Sponsor) confspo).getImporto().intValue();
    }





    private void SetSedeValues(Sede currentObject) {
    }

    private void SetUtenteValues(Utente currentObject) {
    }

    private void SetIstituzioneValues(Istituzione currentObject) {
        
    }


    public String getComitatoSelected() {
        if(isListSelected(AddEditClassFrame.getSelectedItems_infoList12()))
            return AddEditClassFrame.getSelectedItems_infoList12().getSelectedValue();
        throw new DataInsertedException("List is not selected");
    }

    public Integer getImportoSelected() {
        if(isListSelected(AddEditClassFrame.getSelectedItems_infoList14()))
            return AddEditClassFrame.getSelectedItems_infoList14().getSelectedValue();
        throw new DataInsertedException("List is not selected");
    }

    public boolean RemoveOldSessioneFromJList(Sessione oldSessione) {
        return dlModel11.removeElement(oldSessione);
    }


}
