package Business_Logic;


import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

import javax.security.auth.spi.LoginModule;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.sql.Timestamp;
import java.util.List;

public class Controller {
    CF_MainFrame MainFrame;

    CF_AddInstanceClassFrame AddIstanceClassFrame;
    List<ModelClass> Current_Main_outputList;
    DetailsPanel_setter detailsPanel_setter;
    AddInstance_controller addInstFrame_controller;
    CF_SessionDetailsFrame SessionDetailsFrame;
    CF_NewLocazioneFrame NewLocazioneFrame;
    CF_NewSponsorFrame NewSponsorFrame;
    CF_NewSessioneFrame NewSessioneFrame;
    CF_LoginFrame NewLoginFrame;
    dbAccess_byClassName dbAccess_instance = new dbAccess_byClassName();

    Organizzatore AccessUser;

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
        MainFrame = new CF_MainFrame(this);
        SessionDetailsFrame = new CF_SessionDetailsFrame(this);
        detailsPanel_setter = new DetailsPanel_setter(this);
        MainFrame.getDetPanel_FirstList().setModel(detailsPanel_setter.getdListModel());
        AddIstanceClassFrame = new CF_AddInstanceClassFrame(this);
        addInstFrame_controller = new AddInstance_controller(this, AddIstanceClassFrame);
        NewLocazioneFrame = new CF_NewLocazioneFrame(this, addInstFrame_controller);
        NewSponsorFrame = new CF_NewSponsorFrame(this, addInstFrame_controller);
        NewSessioneFrame = new CF_NewSessioneFrame(this, addInstFrame_controller);
        NewLoginFrame = new CF_LoginFrame(this);
    }

    public CF_NewLocazioneFrame getNewLocazioneFrame() {
        return NewLocazioneFrame;
    }

    private boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }

    public void MainFrame_searchButton_clicked(CF_MainFrame Frame) {
        List<ModelClass> Output_list;
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
        Frame.getAttribute_comboBox().addItem("Ricerca avanzata"); //add events if selected
        Frame.getDbAttr_comboBox().addItem("Ricerca avanzata");
        Frame.getAttribute_comboBox().setSelectedIndex(0);
    }

    private List<String> getValues_for_Attribute_comboBox(String class_selected) {
        return new dbAccess_byClassName().getColumns_Names(class_selected);
    }

    public void Attribute_comboBox_ItemChanged(CF_MainFrame Frame) {
        try {
            MainFrame.getDbAttr_comboBox().setSelectedIndex(MainFrame.getAttribute_comboBox().getSelectedIndex());
        }catch (IllegalArgumentException ignored){}


        //inserire caso per Ricerca Avanzata;
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
            setSessioneDetails();
    }

    private void setSessioneDetails() {
        SessionDetailsFrame.getListModel().clear();
        int currentIndex = (int) MainFrame.getSelection_spinner().getValue() -1;
        int currentListIndex = MainFrame.getDetPanel_FirstList().getSelectedIndex();
        try {
            Conferenza selectedConferenza = (Conferenza) Current_Main_outputList.get(currentIndex);
            Sessione selectedSessione = selectedConferenza.getSessioneList().get(currentListIndex);
            SessionDetailsFrame.setCurrentEventoList(selectedSessione.getEventoList());
            SessionDetailsFrame.getSessTitle_Label().setText(selectedSessione.getNome());
            SessionDetailsFrame.getInizioSessione_textArea().setText(Timestamp.valueOf(selectedSessione.getInizio()).toString());
            SessionDetailsFrame.getFineSessione_textArea().setText(Timestamp.valueOf(selectedSessione.getFine()).toString());
            SessionDetailsFrame.getChair_textArea().setText(selectedSessione.getChair().toDetailString());
            try {
                SessionDetailsFrame.getKeynoteSpeaker_textArea().setText(selectedSessione.getKeynote_speaker().toDetailString());
            } catch (NullPointerException e) {
                SessionDetailsFrame.getKeynoteSpeaker_textArea().setText("Non presente");
            }
            SessionDetailsFrame.getLocazione_textArea().setText(selectedSessione.getLocazione().getNome());
            for (Evento e : selectedSessione.getEventoList())
                SessionDetailsFrame.getListModel().addElement(e.toDetailsString());
        }catch (IndexOutOfBoundsException ignored){}
    }

    public void EventoList_SelectedItem_changed() {

        int currentIndex = SessionDetailsFrame.getEventi_JList().getSelectedIndex();
        try {
            Evento selectedEvento = SessionDetailsFrame.getCurrentEventoList().get(currentIndex);
            try {
                Intervento selectedIntervento = (Intervento) selectedEvento;
                SessionDetailsFrame.getDescrizione_Label().setText("Abstract");
                SessionDetailsFrame.getDescrizione_textArea().setText(selectedIntervento.getAbstract());
                SessionDetailsFrame.getDescrizione_JPanel().setVisible(true);
                return;
            } catch (ClassCastException e) {
                try {
                    Evento_Sociale selectedEvSociale = (Evento_Sociale) selectedEvento;
                    SessionDetailsFrame.getDescrizione_Label().setText("Descrizione");
                    SessionDetailsFrame.getDescrizione_textArea().setText(selectedEvSociale.getDescrizione());
                    SessionDetailsFrame.getDescrizione_JPanel().setVisible(true);
                    return;
                } catch (ClassCastException ignored) {
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        SessionDetailsFrame.getDescrizione_JPanel().setVisible(false);
    }

    public void addButton_clicked(){
        MainFrame.getAddButton().setEnabled(false);
        String Class_Selected = (String) MainFrame.getClass_comboBox().getSelectedItem();
        addInstFrame_controller.ChoiseClassAdd(Class_Selected);
        AddIstanceClassFrame.getObjectAdded_label().setText("Aggiungi "+ Class_Selected);
        AddIstanceClassFrame.setVisible(true);
    }

    public void AddInstanceFrame_hidden() {
        if(!NewLocazioneFrame.isVisible() && !NewSponsorFrame.isVisible())
        {
            AddIstanceClassFrame.setVisible(false);
            MainFrame.getAddButton().setEnabled(true);

        }
    }

    public void PartecipanteButtonClicked() {
        AddIstanceClassFrame.getLeftButton9Button().setEnabled(false);
        AddIstanceClassFrame.getRightButton9Button().setEnabled(true);
    }

    public void OrganizzatoreButtonClicked() {
        AddIstanceClassFrame.getLeftButton9Button().setEnabled(true);
        AddIstanceClassFrame.getRightButton9Button().setEnabled(false);
    }

    public void NewButton11Clicked() {
        addInstFrame_controller.NewButton11Clicked();
    }

    public void confermaButtonClicked() {
        addInstFrame_controller.confermaButtonClicked();
    }

    public void removeButtonClicked(){
        addInstFrame_controller.removeButtonClicked();
    }

    public void addButton10_clicked() {
        addInstFrame_controller.addButton10Clicked();
    }

    public void addButton14_clicked(){ addInstFrame_controller.addButton14Clicked();}

    public void addButton12_clicked(){ addInstFrame_controller.addButton12Clicked();}

    public void newButton14_clicked() {addInstFrame_controller.newButton14Clicked();}

    public void removeButton10_clicked(){
        addInstFrame_controller.removeButton10Clicked();
    }
    public void removeButton12_clicked(){
        addInstFrame_controller.removeButton12Clicked();
    }
    public void removeButton14_clicked(){
        addInstFrame_controller.removeButton14Clicked();
    }

    public void AnnullaButtonLoginClicked(){
        NewLoginFrame.setVisible(false);
        NewLoginFrame.getTextField1().setText("");
        NewLoginFrame.getTextField2().setText("");
    }

    public void AccediButtonLoginClicked(){
        if(CheckNoCampiVuoti()) {
            String emailInserita = NewLoginFrame.getTextField1().getText();
            String passwordInserita = NewLoginFrame.getTextField2().getText();
            if(check_accesso(emailInserita, passwordInserita)) {
                JOptionPane.showMessageDialog(NewLoginFrame, "Accesso eseguito");
                NewLoginFrame.setVisible(false);
            }
        }
        else
            JOptionPane.showMessageDialog(NewLoginFrame, "Inserimento fallito: dati mancanti");
    }

    public boolean CheckNoCampiVuoti() {
        return !(NewLoginFrame.getTextField1().getText().equals("")) && !(NewLoginFrame.getTextField2().getText().equals(""));
    }

    public boolean check_accesso(String emailInserita, String passwordInserita) {
        try {
            Organizzatore organizzatore_temp = new Organizzatore_DAO().getByEmail(emailInserita);
            System.out.println(organizzatore_temp.toDetailString());
            return check_password(passwordInserita);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(NewLoginFrame, "Email errata!");
            return false;
        }
    }

    private boolean check_password(String passwordInserita) {
        if(!passwordInserita.equals("StaniLobo") && !passwordInserita.equals("sangio")) {
            JOptionPane.showMessageDialog(NewLoginFrame, "Password errata!");
            return false;
        }
        else
            return true;
    }

    public void LoginButtonClicked() {
        NewLoginFrame.setVisible(true);
    }
}
