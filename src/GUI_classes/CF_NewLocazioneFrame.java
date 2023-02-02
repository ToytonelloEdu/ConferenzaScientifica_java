package GUI_classes;

import Business_Logic.AddEditFrameAppearanceController;
import Business_Logic.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CF_NewLocazioneFrame extends JFrame {
    private Controller business_logic;
    private AddEditFrameAppearanceController addInstance_FC;
    private JPanel MainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton confermaButton;
    private JLabel label1;
    private JLabel label2;
    private JButton annullaButton;

    public CF_NewLocazioneFrame(Controller c, AddEditFrameAppearanceController aifc){
        business_logic = c;
        addInstance_FC = aifc;
        addInstance_FC.setNewLocazioneFrame(this);
        setContentPane(MainPanel);
        setTitle("Nuova Locazione");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(600, 50, 400, 300);
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewLoc_AnnullaButton_clicked();
            }
        });
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewLoc_ConfermaButton_clicked();
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

    public JPanel getMainPanel() {
        return MainPanel;
    }
}
