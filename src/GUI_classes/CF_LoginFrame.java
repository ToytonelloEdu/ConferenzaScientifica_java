package GUI_classes;

import Business_Logic.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CF_LoginFrame extends JFrame{
    private Controller business_logic;
    private JPanel HomePanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton buttonAnnulla;
    private JButton buttonAccedi;
    private JLabel LabelEmail;
    private JLabel LabelPasswordDB;

    public CF_LoginFrame(Controller c){
        business_logic = c;
        CF_LoginFrame FrameHolder = LoginFrame_setup();
        setVisible(true);

        buttonAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.AnnullaButtonLoginClicked();
            }
        });
        buttonAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.AccediButtonLoginClicked();
            }
        });
    }

    private CF_LoginFrame LoginFrame_setup(){
        CF_LoginFrame FrameHolder = this;
        setContentPane(HomePanel);
        setTitle("Login Organizzatore");
        setBounds(150, 150, 400, 200);
        return FrameHolder;
    }

    public JTextField getTextField1() {
        return textField1;
    }
    public JTextField getTextField2() {
        return textField2;
    }
}
