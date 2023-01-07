package GUI_classes;

import Business_Logic.Controller;

import javax.swing.*;

public class Conference_Detail_Panel extends JPanel {
    private Controller business_logic;
    private JLabel Conference_Name;
    private JSpinner spinner1;

    public Conference_Detail_Panel(Controller c){
        business_logic = c;
    }
}
