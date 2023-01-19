package GUI_classes;

import Business_Logic.Controller;

import javax.swing.*;

public class CF_NewSponsorFrame extends JFrame{
    private Controller business_logic;
    private JPanel MainPanel;


    public CF_NewSponsorFrame(Controller c){
        business_logic = c;
        setContentPane(MainPanel);
        setTitle("Nuovo Sponsor");
        setBounds(600, 50, 400, 300);


        setVisible(true);
    }
}
