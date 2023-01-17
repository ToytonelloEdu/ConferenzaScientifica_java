package Business_Logic;

import Exceptions.InsertFailedException;
import GUI_classes.CF_AddIstanceClassFrame;
import GUI_classes.CF_NewLocazioneFrame;
import Model_classes.Locazione;
import Model_classes.ModelClass;
import Model_classes.Sede;

import javax.swing.*;

public class AddInstanceFrame_controller {
    Controller business_logic;
    CF_AddIstanceClassFrame AddInstanceClassFrame;
    CF_NewLocazioneFrame NewLocazioneFrame;
    String ClassSelected;
    ModelClass CurrentOggetto;

    private DefaultListModel<String> dlModel10 = new DefaultListModel<>();
    private DefaultListModel<String> dlModel11 = new DefaultListModel<>();
    public AddInstanceFrame_controller(Controller c, CF_AddIstanceClassFrame aicf) {
        business_logic = c;
        AddInstanceClassFrame = aicf;
        AddInstanceClassFrame.getSelectedItems_list10().setModel(dlModel10);
        AddInstanceClassFrame.getAddOnly_list11().setModel(dlModel11);
    }

    public void setNewLocazioneFrame(CF_NewLocazioneFrame newLocazioneFrame) {
        NewLocazioneFrame = newLocazioneFrame;
    }

    public void NewButtonClicked() {
        if(ClassSelected.equals("Sede")){
            NewLocazioneFrame.setVisible(true);
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
        AddInstanceClassFrame.getLabel10().setText("Collocazione");
        business_logic.setValues_in_Select_comboBox(AddInstanceClassFrame);
    }
    private void Hide_Conferenza_UnusedComponents(){
        Hide_Conferenza_Unusedlabel();
        Hide_Conferenza_UnusedTextField_Button();
    }
    private void Hide_Conferenza_Unusedlabel(){
        AddInstanceClassFrame.getLabel5().setVisible(false);
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel9().setVisible(false);
    }
    private void Hide_Conferenza_UnusedTextField_Button(){
        AddInstanceClassFrame.getTextField5().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
        AddInstanceClassFrame.getLeftButton9Button().setVisible(false);
        AddInstanceClassFrame.getRightButton9Button().setVisible(false);
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
        Hide_Sede_UnusedTextField_Button();
        AddInstanceClassFrame.getSelectItems_JPanel10().setVisible(false);
    }

    private void Hide_Sede_Unusedlabel(){
        AddInstanceClassFrame.getLabel4().setVisible(false);
        AddInstanceClassFrame.getLabel5().setVisible(false);
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getLabel9().setVisible(false);
    }

    private void Hide_Sede_UnusedTextField_Button(){
        AddInstanceClassFrame.getTextField4().setVisible(false);
        AddInstanceClassFrame.getTextField5().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
        AddInstanceClassFrame.getLeftButton9Button().setVisible(false);
        AddInstanceClassFrame.getRightButton9Button().setVisible(false);
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
        AddInstanceClassFrame.getLabel6().setVisible(false);
        AddInstanceClassFrame.getTextField6().setVisible(false);
        AddInstanceClassFrame.getLabel7().setVisible(false);
        AddInstanceClassFrame.getTextField7().setVisible(false);
        AddInstanceClassFrame.getLabel8().setVisible(false);
        AddInstanceClassFrame.getTextField8().setVisible(false);
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
        if(!(AddInstanceClassFrame.getTextField1().getText().equals("")) && !(AddInstanceClassFrame.getTextField2().getText().equals("")) && !(AddInstanceClassFrame.getTextField3().getText().equals("")))
            insertSede();
        else
            JOptionPane.showMessageDialog(AddInstanceClassFrame, "Inserimento fallito");
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
    }
}
