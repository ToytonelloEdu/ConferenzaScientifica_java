package Business_Logic;


import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

public class Controller {
    CF_MainFrame MainFrame;

    CF_AddIstanceClassFrame AddIstanceClassFrame;
    List<ModelClass> Current_Main_outputList;
    DetailsPanel_setter detailsPanel_setter;
    CF_SessionDetailsFrame SessionDetailsFrame;
    dbAccess_byClassName dbAccess_instance = new dbAccess_byClassName();

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
        AddIstanceClassFrame = new CF_AddIstanceClassFrame(this);
        MainFrame.getDetPanel_FirstList().setModel(detailsPanel_setter.getdListModel());
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
        ChoiseClassAdd(Class_Selected);
        AddIstanceClassFrame.getObjectAdded_label().setText("Aggiungi "+ Class_Selected);
        AddIstanceClassFrame.setVisible(true);
    }

    public void AddInstanceFrame_hidden() {
        AddIstanceClassFrame.setVisible(false);
        MainFrame.getAddButton().setEnabled(true);
    }

    public void ChoiseClassAdd(String Class_Selected) {
        switch (Class_Selected) {
            case "Conferenza" ->
                    setFieldsAdd_forConferenza();
            case "Sede" ->
                    setFieldsAdd_forSede();
            case "Utente" ->
                    setFieldsAdd_forUtente();
        }
    }

    public void setFieldsAdd_forConferenza(){
        setConf_firstField();
        setConf_secondField();
        setConf_thirdField();
        setConf_fourthField();
        setConf_fifthField();
        Hide_Conferenza_UnusedComponents();
    }
    public void setConf_firstField(){
        AddIstanceClassFrame.getLabel1().setText("Nome");
        AddIstanceClassFrame.getTextField1().setText("");
    }
    public void setConf_secondField(){
        AddIstanceClassFrame.getLabel2().setText("Data Inizio");
        AddIstanceClassFrame.getTextField2().setText("");
    }
    public void setConf_thirdField(){
        AddIstanceClassFrame.getLabel3().setText("Data Fine");
        AddIstanceClassFrame.getTextField3().setText("");
    }
    public void setConf_fourthField(){
        AddIstanceClassFrame.getLabel4().setText("Descrizione");
        AddIstanceClassFrame.getTextField4().setText("");
    }
    public void setConf_fifthField(){
        AddIstanceClassFrame.getLabel5().setText("Collocazione");
        AddIstanceClassFrame.getTextField5().setText("");
    }
    public void Hide_Conferenza_UnusedComponents(){
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
    }

    public void setFieldsAdd_forSede(){
        setSede_firstField();
        setSede_secondField();
        setSede_thirdField();
        Hide_Sede_UnusedComponents();
    }
    public void setSede_firstField(){
        AddIstanceClassFrame.getLabel1().setText("Nome");
        AddIstanceClassFrame.getTextField1().setText("");
    }
    public void setSede_secondField(){
        AddIstanceClassFrame.getLabel2().setText("Indirizzo");
        AddIstanceClassFrame.getTextField2().setText("");
    }
    public void setSede_thirdField(){
        AddIstanceClassFrame.getLabel3().setText("Città");
        AddIstanceClassFrame.getTextField3().setText("");
    }
    public void Hide_Sede_UnusedComponents(){
        AddIstanceClassFrame.getLabel4().setVisible(false);
        AddIstanceClassFrame.getTextField4().setVisible(false);
        AddIstanceClassFrame.getLabel5().setVisible(false);
        AddIstanceClassFrame.getTextField5().setVisible(false);
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
    }

    public void setFieldsAdd_forUtente(){
        setUtente_firstField();
        setUtente_secondField();
        setUtente_thirdField();
        setUtente_fourthField();
        setUtente_fifthField();
        setUtente_sixthField();
        Hide_Utente_UnusedComponents();
    }
    public void setUtente_firstField(){
        AddIstanceClassFrame.getLabel1().setText("Titolo");
        AddIstanceClassFrame.getTextField1().setText("");
    }
    public void setUtente_secondField(){
        AddIstanceClassFrame.getLabel2().setText("Nome");
        AddIstanceClassFrame.getTextField2().setText("");
    }
    public void setUtente_thirdField(){
        AddIstanceClassFrame.getLabel3().setText("Cognome");
        AddIstanceClassFrame.getTextField3().setText("");
    }
    public void setUtente_fourthField(){
        AddIstanceClassFrame.getLabel4().setText("Email");
        AddIstanceClassFrame.getTextField4().setText("");
    }
    public void setUtente_fifthField(){
        AddIstanceClassFrame.getLabel5().setText("Tipo utente");
        AddIstanceClassFrame.getTextField5().setText("");
    }
    public void setUtente_sixthField(){
        AddIstanceClassFrame.getLabel6().setText("Istituzione di afferenza");
        AddIstanceClassFrame.getTextField6().setText("");
    }
    public void Hide_Utente_UnusedComponents(){
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
    }

}
