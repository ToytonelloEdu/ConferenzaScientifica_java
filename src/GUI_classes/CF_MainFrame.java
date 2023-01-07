package GUI_classes;

import Business_Logic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CF_MainFrame extends JFrame {
    private Controller business_logic;
    private JPanel HomePanel;
    private JTextField Search_textField;
    private JTextArea Output_TextArea;
    private JButton searchButton;
    private JComboBox<String> Class_comboBox;
    private JComboBox<String> Attribute_comboBox;
    private JPanel Details_panel;
    private JButton clearButton;

    public CF_MainFrame(Controller c) {
        business_logic = c;
        CF_MainFrame MainFrame_holder = this;
        setContentPane(HomePanel);
        setTitle("Conferencer");
        setBounds(50, 50, 1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        business_logic.setValues_in_Attribute_comboBox(MainFrame_holder);
        Search_textField.setText("");
        setVisible(true);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.MainFrame_searchButton_clicked(MainFrame_holder);
            }
        });
        Class_comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.Class_comboBox_ItemChanged(MainFrame_holder);
            }

        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search_textField.setText("");
            }
        });
    }

    public Controller getBusiness_logic() {
        return business_logic;
    }

    public JPanel getHomePanel() {
        return HomePanel;
    }

    public JTextField getSearch_textField() {
        return Search_textField;
    }

    public JTextArea getOutput_TextArea() {
        return Output_TextArea;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JComboBox<String> getClass_comboBox() {
        return Class_comboBox;
    }

    public JComboBox<String> getAttribute_comboBox() {
        return Attribute_comboBox;
    }

    public JPanel getDetails_panel() {
        return Details_panel;
    }

    public JButton getClearButton() {
        return clearButton;
    }
}
