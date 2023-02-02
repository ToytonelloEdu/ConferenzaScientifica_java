package Business_Logic;

import GUI_classes.CF_AddEditClassFrame;
import GUI_classes.CF_NewLocazioneFrame;
import GUI_classes.CF_NewSessioneFrame;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class AddEdit_ChecksController {
    Controller business_logic;
    CF_AddEditClassFrame AddEditClassFrame;
    CF_NewSessioneFrame NewSessioneFrame;

    public AddEdit_ChecksController(Controller c){
        business_logic = c;
        AddEditClassFrame = business_logic.AddEditClassFrame;
        NewSessioneFrame = business_logic.NewSessioneFrame;
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

    boolean NoCampiVuoti_forConferenza() {
        return  !isEmpty(AddEditClassFrame.getTextField1()) &&
                !isEmpty(AddEditClassFrame.getTextField2()) &&
                !isEmpty(AddEditClassFrame.getTextField3()) &&
                !isEmpty(AddEditClassFrame.getTextField4());
    }
}
