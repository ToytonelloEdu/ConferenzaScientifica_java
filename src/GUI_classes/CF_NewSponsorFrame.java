package GUI_classes;

import Business_Logic.AddEditFrameAppearanceController;
import Business_Logic.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CF_NewSponsorFrame extends JFrame{
    private Controller business_logic;
    private AddEditFrameAppearanceController AddInst_bl;
    private JPanel MainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton confermaButton;
    private JButton annullaButton;
    private JLabel label1;
    private JLabel label2;


    public CF_NewSponsorFrame(Controller c, AddEditFrameAppearanceController addInstFrame_c){
        business_logic = c;
        AddInst_bl = addInstFrame_c;
        AddInst_bl.setNewSponsorFrame(this);
        setContentPane(MainPanel);
        setTitle("Nuovo Sponsor");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(750, 75, 400, 200);
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInst_bl.NewSpons_annullaButtonClicked();
            }
        });
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInst_bl.NewSpons_confermaButtonClicked();
            }
        });
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getConfermaButton() {
        return confermaButton;
    }
}
