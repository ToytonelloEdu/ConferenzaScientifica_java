package Business_Logic;

import DAO_classes.*;
import GUI_classes.*;

import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.Objects;

public class Controller {
    CF_MainFrame MainFrame;
    Conference_Detail_Panel Conf_Det_Panel;

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
        Conf_Det_Panel = new Conference_Detail_Panel(this);
        MainFrame = new CF_MainFrame(this);
    }

    private boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }

    public void MainFrame_searchButton_clicked(CF_MainFrame Frame) {
        Frame.getOutput_TextArea().setText("");
        String Class_SearchIn_str = (String) Frame.getClass_comboBox().getSelectedItem();
        if(isEmpty(Frame.getSearch_textField())){
            setOutput_textArea_noFilter(Frame, Class_SearchIn_str);
        }
        else{
            setOutput_textArea_Filtered(Frame, Class_SearchIn_str);
        }
    }

    private void setOutput_textArea_noFilter(CF_MainFrame Frame, String Class_SearchIn_str) {
        List Output_ObjList = getValues_forOutputTextArea(Class_SearchIn_str);
        for(Object o: Output_ObjList){
            printOutput_Indexes(Frame, Output_ObjList, o);
            Frame.getOutput_TextArea().append(o.toString()+ "\n");
        }
    }


    private List getValues_forOutputTextArea(String searchIn_str) {
        return new DBGetterByClassName().GetAllByClassName(searchIn_str);

    }


    private void setOutput_textArea_Filtered(CF_MainFrame Frame, String Class_SearchIn_str) {
        String Attr_SearchIn_str = (String) Frame.getAttribute_comboBox().getSelectedItem();
        String Value_Search_str = Frame.getSearch_textField().getText();
        List Output_ObjList = getValues_forOutputTextArea(Class_SearchIn_str, Attr_SearchIn_str, Value_Search_str);

        if(Output_ObjList.isEmpty()){
            Frame.getOutput_TextArea().setText("Nessun risultato per la ricerca eseguita...");
        }
        else
            for(Object o: Output_ObjList){
                printOutput_Indexes(Frame, Output_ObjList, o);
                Frame.getOutput_TextArea().append(o.toString()+ "\n");
            }
    }

    public List getValues_forOutputTextArea(String Class, String Attribute, String Value) {
        return new DBGetterByClassName().GetByClass_and_Attribute(Class, Attribute, Value);

    }

    private void printOutput_Indexes(CF_MainFrame Frame, List Output_ObjList, Object o){
        if(Output_ObjList.indexOf(o)+1 < 10)
            Frame.getOutput_TextArea().append(" "+ String.valueOf(Output_ObjList.indexOf(o)+1) + " | ");
        else if (Output_ObjList.indexOf(o)+1 < 100)
            Frame.getOutput_TextArea().append(Output_ObjList.indexOf(o)+1 + "| ");
    }

    public void Class_comboBox_ItemChanged(CF_MainFrame Frame) {
        Frame.getOutput_TextArea().setText("Esegui una ricerca...");
        clear_Attribute_comboBox(Frame);
        setValues_in_Attribute_comboBox(Frame);
    }

    private void clear_Attribute_comboBox(CF_MainFrame Frame) {
        while (Frame.getAttribute_comboBox().getItemCount() > 0) {
            Frame.getAttribute_comboBox().removeItemAt(0);
        }
    }

    public void setValues_in_Attribute_comboBox(CF_MainFrame Frame) {
        String Class_selected = (String) Frame.getClass_comboBox().getSelectedItem();
        Frame.getAttribute_comboBox().addItem("None"); //remove when select other
        for(String o: getValues_for_Attribute_comboBox(Class_selected)){
            Frame.getAttribute_comboBox().addItem(o);
        }
        Frame.getAttribute_comboBox().addItem("Ricerca avanzata"); //add events if selected
        Frame.getAttribute_comboBox().setSelectedIndex(0);
    }

    private List<String> getValues_for_Attribute_comboBox(String class_selected) {
        return new DBGetterByClassName().getColumns_Names(class_selected);
    }

    public void setDetailPanel(CF_MainFrame Frame) {
        Frame.setInterchange_Detail_panel(Conf_Det_Panel);
    }

    public void Attribute_comboBox_ItemChanged(CF_MainFrame Frame) {
        if(Objects.equals(Frame.getAttribute_comboBox().getSelectedItem(), "None"))
            return;
        if(Objects.equals(Frame.getAttribute_comboBox().getItemAt(0), "None"))
            Frame.getAttribute_comboBox().removeItemAt(0);
    }
}
