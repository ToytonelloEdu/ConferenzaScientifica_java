package GUI_classes;

import Business_Logic.AddInstance_controller;
import Business_Logic.Controller;

import javax.swing.*;

public class CF_NewSessioneFrame extends JFrame {
    private Controller business_logic;
    private AddInstance_controller AddInst_bl;
    private JPanel MainPanel;

    public CF_NewSessioneFrame(Controller c, AddInstance_controller aic){
        business_logic = c;
        AddInst_bl = aic;
        AddInst_bl.setNewSessioneFrame(this);
        setContentPane(MainPanel);
        setTitle("Nuova Sessione");
        setBounds(750, 75, 400, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
