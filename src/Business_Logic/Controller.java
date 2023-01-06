package Business_Logic;

import DAO_classes.*;
import GUI_classes.*;

import javax.swing.text.JTextComponent;
import java.util.List;

public class Controller {
    CF_MainFrame MainFrame;

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

    }

    public List MainFrame_searchButtonClicked(String searchIn_str) {
        return new DBGetterByClassName().GetAllByClassName(searchIn_str);

    }

    public List MainFrame_searchButtonClicked(String Class, String Attribute, String Value) {
        return new DBGetterByClassName().GetByClass_and_Attribute(Class, Attribute, Value);

    }


    public List<String> Class_ComboBox_change(String class_selected) {
        return new DBGetterByClassName().getColumns_Names(class_selected);
    }

    public boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }
}
