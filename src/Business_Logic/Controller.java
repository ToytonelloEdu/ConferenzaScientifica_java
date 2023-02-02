package Business_Logic;


import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.List;

public class Controller {
    CF_MainFrame MainFrame;
    CF_AddEditClassFrame AddEditClassFrame;
    List<ModelClass> Current_Main_outputList;
    DetailsPanel_setter detailsPanel_setter;
    AddEditFrameAppearanceController AddEditFrame_controller;
    CF_SessionDetailsFrame SessionDetailsFrame;
    CF_NewLocazioneFrame NewLocazioneFrame;
    CF_NewSponsorFrame NewSponsorFrame;
    CF_NewSessioneFrame NewSessioneFrame;
    CF_LoginFrame NewLoginFrame;
    UserLogin_Controller login_controller;
    InstanceInsert_Controller instInsert_controller;
    AddEdit_ChecksController addEdit_checksController;
    dbAccess_byClassName dbAccess_instance = new dbAccess_byClassName();
    String ClassSelected;

    public static void main(String[] args) {
        try {
           Controller business_logic = new Controller();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Controller(){
        SessionDetailsFrame = new CF_SessionDetailsFrame(this);
        detailsPanel_setter = new DetailsPanel_setter(this);
        AddEditClassFrame = new CF_AddEditClassFrame(this);
        AddEditFrame_controller = new AddEditFrameAppearanceController(this);
        NewLocazioneFrame = new CF_NewLocazioneFrame(this, AddEditFrame_controller);
        NewSponsorFrame = new CF_NewSponsorFrame(this, AddEditFrame_controller);
        NewSessioneFrame = new CF_NewSessioneFrame(this, AddEditFrame_controller);

        login_controller = new UserLogin_Controller(this);
        instInsert_controller = new InstanceInsert_Controller(this);
        addEdit_checksController = new AddEdit_ChecksController(this);
        NewLoginFrame = new CF_LoginFrame(this);
        MainFrame = new CF_MainFrame(this);
        MainFrame.getDetPanel_FirstList().setModel(detailsPanel_setter.getdListModel());
    }

    private boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }

    public void MainFrame_searchButton_clicked(CF_MainFrame Frame) {
        detailsPanel_setter.getdListModel().clear();
        Frame.getOutput_TextArea().setText("");
        String Class_SearchIn_str = (String) Frame.getClass_comboBox().getSelectedItem();
        if(isEmpty(Frame.getSearch_textField()) || isNo_filter()){
            Current_Main_outputList = setOutput_textArea_noFilter(Frame, Class_SearchIn_str);
        }
        else{
            Current_Main_outputList = setOutput_textArea_Filtered(Frame, Class_SearchIn_str);
        }
        try {
            if (!(Current_Main_outputList.isEmpty()))
                detailsPanel_setter.setDetailPanel_onSearch(MainFrame, Current_Main_outputList);
        }catch (NullPointerException e){
            MainFrame.getDetails_panel().setVisible(false);
        }
    }

    private boolean isNo_filter() {
        return ((String) MainFrame.getAttribute_comboBox().getSelectedItem()).equals("No Filter");
    }


    private List<ModelClass> setOutput_textArea_noFilter(CF_MainFrame Frame, String Class_SearchIn_str) {
        List<ModelClass> Output_ObjList = getValues_forOutputTextArea(Class_SearchIn_str);
        try {
            for (ModelClass o : Output_ObjList) {
                printOutput_Indexes(Frame, Output_ObjList, o);
                MainFrame.getOutput_TextArea().append(o.toGUI_Output(Output_ObjList) + "\n");
                MainFrame.getOutput_TextArea().setCaretPosition(0);
            }
        }
        catch (NullPointerException throwable){
            MainFrame.getOutput_TextArea().setText("Si è verificato un errore, riprova...");
        }
        return Output_ObjList;
    }


    private List<ModelClass> getValues_forOutputTextArea(String searchIn_str) {
        return new dbAccess_byClassName().GetAll_byClassName(searchIn_str);
    }


    private List<ModelClass> setOutput_textArea_Filtered(CF_MainFrame Frame, String Class_SearchIn_str) {
        String Attr_SearchIn_str = (String) Frame.getDbAttr_comboBox().getSelectedItem();
        String Value_Search_str = Frame.getSearch_textField().getText();
        List<ModelClass> Output_ObjList = getValues_forOutputTextArea(Class_SearchIn_str, Attr_SearchIn_str, Value_Search_str);

        if(Output_ObjList.isEmpty()){
            Frame.getOutput_TextArea().setText("Nessun risultato per la ricerca eseguita...");
        }
        else {
            for (ModelClass o : Output_ObjList) {
                printOutput_Indexes(Frame, Output_ObjList, o);
                Frame.getOutput_TextArea().append(o.toGUI_Output(Output_ObjList) + "\n");
                MainFrame.getOutput_TextArea().setCaretPosition(0);
            }
            return Output_ObjList;
        }
        return null;
    }

    public List<ModelClass> getValues_forOutputTextArea(String Class, String Attribute, String Value) {
        return new dbAccess_byClassName().GetByClass_and_Attribute(Class, Attribute, Value);

    }

    private void printOutput_Indexes(CF_MainFrame Frame, List<ModelClass> Output_ObjList, ModelClass currentModelClass){
        if(Output_ObjList.indexOf(currentModelClass)+1 < 10)
            Frame.getOutput_TextArea().append(" "+ String.valueOf(Output_ObjList.indexOf(currentModelClass)+1) + " | ");
        else if (Output_ObjList.indexOf(currentModelClass)+1 < 100)
            Frame.getOutput_TextArea().append(Output_ObjList.indexOf(currentModelClass)+1 + "| ");
    }

    public void Class_comboBox_ItemChanged(CF_MainFrame Frame) {
        SessionDetailsFrame.setVisible(false);
        MainFrame.getDetails_panel().setVisible(false);
        MainFrame.getSearch_textField().setText("");
        detailsPanel_setter.setAllDetailsComp_visible(MainFrame.getDetailsComp_list());
        Frame.getOutput_TextArea().setText("Esegui una ricerca...");
        clear_Attribute_comboBoxes(Frame);
        setValues_in_Attribute_comboBox(Frame);
        ClassSelected = (String) MainFrame.getClass_comboBox().getSelectedItem();
    }

    private void clear_Attribute_comboBoxes(CF_MainFrame Frame) {
        while (Frame.getAttribute_comboBox().getItemCount() > 0) {
            Frame.getAttribute_comboBox().removeItemAt(0);
        }
        while (Frame.getDbAttr_comboBox().getItemCount() > 0) {
            Frame.getDbAttr_comboBox().removeItemAt(0);
        }
    }

    public void setValues_in_Attribute_comboBox(CF_MainFrame Frame) {
        String Class_selected = (String) Frame.getClass_comboBox().getSelectedItem();
        Frame.getAttribute_comboBox().addItem("No Filter");
        Frame.getDbAttr_comboBox().addItem("No Filter");
        for(String o: getValues_for_Attribute_comboBox(Class_selected)){
            try {
                Frame.getAttribute_comboBox().addItem(dbAccess_instance.AdjustColumnName(o));
                Frame.getDbAttr_comboBox().addItem(dbAccess_instance.DiscardColumnName(o));
            }
            catch (Exception ignored){}
        }
        Frame.getAttribute_comboBox().setSelectedIndex(0);
    }

    private List<String> getValues_for_Attribute_comboBox(String class_selected) {
        return new dbAccess_byClassName().getColumns_Names(class_selected);
    }

    public void Attribute_comboBox_ItemChanged(CF_MainFrame Frame) {
        try {
            MainFrame.getDbAttr_comboBox().setSelectedIndex(MainFrame.getAttribute_comboBox().getSelectedIndex());
        }catch (IllegalArgumentException ignored){}
    }

    public void Selection_spinner_ItemChanged() {
        JSpinner Spinner = MainFrame.getSelection_spinner();
        int CurrentSpinnerValue = (Integer) Spinner.getValue() - 1;
        detailsPanel_setter.getdListModel().clear();
        detailsPanel_setter.setData_onDPanel_byClass(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
        if(SessionDetailsFrame.isVisible()){
            MainFrame.getDetPanel_FirstList().setSelectedIndex(0);
        }
    }

    public void ClearButton_clicked() {
        MainFrame.getSearch_textField().setText("");
        MainFrame.getAttribute_comboBox().setSelectedIndex(0);
    }

    public void DetailsButton_clicked() {
        if(MainFrame.getClass_comboBox().getSelectedItem().equals("Conferenza"))
            SessionDetailsFrame.setVisible(!SessionDetailsFrame.isVisible());
    }

    public void FirstList_SelectedItem_changed() {
        if(! (MainFrame.getDetailsButton().isEnabled()))
            MainFrame.getDetailsButton().setEnabled(true); //inserisci in Focus Listeners
        if(MainFrame.getClass_comboBox().getSelectedItem().equals("Conferenza"))
            detailsPanel_setter.setSessioneDetails(SessionDetailsFrame);
    }

    public void EventoList_SelectedItem_changed() {
        detailsPanel_setter.SetDescrizioneForEventoSelected(SessionDetailsFrame);
    }


    public void addButton_clicked(){
        AddInstanceFrame_initialization();
        if(MainFrame.getLoginButton().isVisible()) {
            NewLoginFrame.setVisible(true);
        } else {
            AddEditClassFrame.setVisible(true);
        }
    }

    private void AddInstanceFrame_initialization() {
        MainFrame.getAddButton().setEnabled(false);
        String Class_Selected = (String) MainFrame.getClass_comboBox().getSelectedItem();
        AddEditFrame_controller.ChoiceClassAdd(Class_Selected);
        AddEditClassFrame.getObjectAdded_label().setText("Aggiungi " + Class_Selected);
    }

    public void AddInstanceFrame_hidden() {
        if(!NewLocazioneFrame.isVisible() && !NewSponsorFrame.isVisible() && !NewSessioneFrame.isVisible())
        {
            AddEditClassFrame.setVisible(false);
            EmptyComboboxInAddFrame();
            MainFrame.getAddButton().setEnabled(true);

        }
    }

    void EmptyComboboxInAddFrame() {
        AddEditClassFrame.getSelect_comboBox10().removeAllItems();
        AddEditClassFrame.getSelect_comboBox12().removeAllItems();
        AddEditClassFrame.getSelect_comboBox14().removeAllItems();
        AddEditClassFrame.getSelectOne_comboBox13().removeAllItems();
    }

    public void PartecipanteButtonClicked() {
        AddEditClassFrame.getLeftButton9Button().setEnabled(false);
        AddEditClassFrame.getRightButton9Button().setEnabled(true);
    }

    public void OrganizzatoreButtonClicked() {
        AddEditClassFrame.getLeftButton9Button().setEnabled(true);
        AddEditClassFrame.getRightButton9Button().setEnabled(false);
    }

    public void NewButton11Clicked() {
        if(ClassSelected.equals("Sede")){
            NewLocazioneFrame.setVisible(true);
        }
        else if (ClassSelected.equals("Conferenza")){
            AddEditFrame_controller.NewSessioneFrameSetUp();
            NewSessioneFrame.setVisible(true);
        }
    }

    public void confermaButtonClicked() {
        switch (ClassSelected){
            case "Sede" -> instInsert_controller.InsertSede_Control();
            case "Conferenza" -> instInsert_controller.InsertConferenza_Control();
            case "Istituzione" -> instInsert_controller.InsertIstituzione_Control();
            case "Utente", "Organizzatore", "Partecipante" -> instInsert_controller.InsertUtente_Control();
        }
    }

    public void removeButtonClicked(){
        AddEditFrame_controller.removeButtonClicked();
    }

    public void addButton10_clicked() {
        AddEditFrame_controller.addButton10Clicked();
    }

    public void addButton14_clicked(){ AddEditFrame_controller.addButton14Clicked();}

    public void addButton12_clicked(){ AddEditFrame_controller.addButton12Clicked();}

    public void newButton14_clicked() {
        AddEditFrame_controller.newButton14Clicked();}

    public void removeButton10_clicked(){
        AddEditFrame_controller.removeButton10Clicked();
    }
    public void removeButton12_clicked(){
        AddEditFrame_controller.removeButton12Clicked();
    }
    public void removeButton14_clicked(){
        AddEditFrame_controller.removeButton14Clicked();
    }



    public void CheckCorrectConferenzaDates() {
        AddEditFrame_controller.CheckCorrectConferenzaDates();
    }

    public void CheckButtonClicked() {
        AddEditFrame_controller.CheckButtonClicked();
    }

    public void LoginButtonClicked() {
        login_controller.LoginButtonClicked();
    }

    public void DeleteButtonClicked() {
        login_controller.DeleteButtonClicked();
    }

    public void AnnullaButtonLoginClicked() {
        login_controller.AnnullaButtonLoginClicked();
    }

    public void AccediButtonLoginClicked() {
        login_controller.AccediButtonLoginClicked();
    }

    public void deleteObject() {
        int CurrentSpinnerValue = (Integer) MainFrame.getSelection_spinner().getValue() - 1;
        ModelClass CurrentObjectOutput = Current_Main_outputList.get(CurrentSpinnerValue);
        int risposta = JOptionPane.showConfirmDialog(MainFrame.getDetails_panel(), "Vuoi cancellare l'oggetto: "+ CurrentObjectOutput +"?");
        if(risposta == 0) {
            CurrentObjectOutput.getDao().Delete(CurrentObjectOutput);
            MainFrame_searchButton_clicked(MainFrame);
        }
        MainFrame.getDeleteButton().setEnabled(true);
    }


    public void NewLoc_ConfermaButton_clicked() {
        NewLocazioneFrame.getConfermaButton().setEnabled(false);
        AddEditFrame_controller.NewLocOperations_afterConferma();
    }

    public void NewLoc_AnnullaButton_clicked() {
        HideNewLocationFrame();
    }

    void HideNewLocationFrame() {
        NewLocazioneFrame.setVisible(false);
        NewLocazioneFrame.getTextField1().setText("");
        NewLocazioneFrame.getTextField2().setText("");
    }
}
