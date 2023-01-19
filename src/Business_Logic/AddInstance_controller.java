package Business_Logic;

import DAO_classes.dbAccess_byClassName;
import Exceptions.InsertFailedException;
import GUI_classes.CF_AddIstanceClassFrame;
import GUI_classes.CF_NewLocazioneFrame;
import GUI_classes.CF_NewSponsorFrame;
import Model_classes.Locazione;
import Model_classes.ModelClass;
import Model_classes.Sede;
import Model_classes.Sponsor;

import javax.swing.*;
import java.util.List;

public class AddInstance_controller {
    Controller business_logic;
    CF_AddIstanceClassFrame AddInstanceClassFrame;
    CF_NewLocazioneFrame NewLocazioneFrame;
    CF_NewSponsorFrame NewSponsorFrame;
    String ClassSelected;
    ModelClass CurrentOggetto;

    private DefaultListModel<ModelClass> dlModel10 = new DefaultListModel<>();
    private DefaultListModel<String> dlModel11 = new DefaultListModel<>();
    private DefaultListModel<ModelClass> dlModel12 = new DefaultListModel<>();
    private DefaultListModel<ModelClass> dlModel14 = new DefaultListModel<>();

    public AddInstance_controller(Controller c, CF_AddIstanceClassFrame aicf) {
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

    public void NewButton11Clicked() {
        if(ClassSelected.equals("Sede")){
            NewLocazioneFrame.setVisible(true);
        }
        else if (ClassSelected.equals("Conferenza")){

        }
    }

    public void ChoiseClassAdd(String selectedClass) {
        ClassSelected = selectedClass;
        SetAllToVisible();
        switch (selectedClass) {
            case "Conferenza" ->
                    setFieldsAdd_forConferenza();
            case "Sede" -> {
                CurrentOggetto = new Sede();
                setFieldsAdd_forSede();
            }
            case "Utente", "Organizzatore", "Partecipante" ->
                    setFieldsAdd_forUtente();
            case "Istituzione" ->
                    setFieldsAdd_forIstituzione();
        }
    }

    private void SetAllToVisible() {
        for(JComponent jc : AddInstanceClassFrame.getDataInsertComponentList())
            jc.setVisible(true);
    }

    private void setFieldsAdd_forConferenza(){
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
        AddInstanceClassFrame.getLeftButton9Button().setVisible(false);
        AddInstanceClassFrame.getRightButton9Button().setVisible(false);
        AddInstanceClassFrame.getnewButton10().setVisible(false);
        AddInstanceClassFrame.getnewButton12().setVisible(false);
    }
    private void Hide_Conferenza_UnusedButton(){
        AddInstanceClassFrame.getLeftButton9Button().setVisible(false);
        AddInstanceClassFrame.getRightButton9Button().setVisible(false);
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

    private void setFieldsAdd_forUtente(){
        setUtente_firstField();
        setUtente_secondField();
        setUtente_thirdField();
        setUtente_fourthField();
        setUtente_fifthField();
        setUtente_tipo();
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
    private void setUtente_fifthField(){
        AddInstanceClassFrame.getLabel5().setText("Istituzione di afferenza");
        AddInstanceClassFrame.getTextField5().setText("");
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
    }
    private void Hide_Utente_UnusedLabel(){
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel10().setVisible(false);
        AddInstanceClassFrame.getLabel11().setVisible(false);
        AddInstanceClassFrame.getLabel12().setVisible(false);
        AddInstanceClassFrame.getLabel13().setVisible(false);
        AddInstanceClassFrame.getLabel14().setVisible(false);
    }
    private void Hide_Utente_UnusedTextField(){
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
    }
    private void Hide_Utente_UnusedJPanel(){
        AddInstanceClassFrame.getSelectItems_JPanel10().setVisible(false);
        AddInstanceClassFrame.getAddOnly_JPanel11().setVisible(false);
        AddInstanceClassFrame.getSelectItems_JPanel12().setVisible(false);
        AddInstanceClassFrame.getSelectOnly_JPanel13().setVisible(false);
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
            dlModel11.addElement(l.getNome());
        NewLocazioneFrame.setVisible(false);
        NewLocazioneFrame.getTextField1().setText("");
        NewLocazioneFrame.getTextField2().setText("");
        NewLocazioneFrame.getConfermaButton().setEnabled(true);
    }

    public void confermaButtonClicked() {
        switch (ClassSelected){
            case "Sede" -> {
                InsertSede_Control();
            }
        }
    }

    private void InsertSede_Control() {
        if(NoCampiVuoti_forSede())
            insertSede();
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

    private boolean ListIsSelected(int currentlistIndex) {
        return currentlistIndex != -1;
    }

    public void addButton10Clicked() {
        ModelClass selectedItem10 = (ModelClass) AddInstanceClassFrame.getSelect_comboBox10().getSelectedItem();
        dlModel10.addElement(selectedItem10);
    }

    public void addButton12Clicked() {
        ModelClass selectedItem12 = (ModelClass) AddInstanceClassFrame.getSelect_comboBox12().getSelectedItem();
        dlModel12.addElement(selectedItem12);
    }

    public void addButton14Clicked() {
        ModelClass selectedItem14 = (ModelClass) AddInstanceClassFrame.getSelect_comboBox14().getSelectedItem();
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
}