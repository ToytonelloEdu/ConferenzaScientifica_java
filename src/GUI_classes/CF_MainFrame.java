package GUI_classes;

import Business_Logic.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
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
    private JSpinner Selection_spinner;
    private JLabel Detail_ObjectName_label;
    private JLabel FirstField_label;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JLabel SecondField_label;
    private JLabel ThirdField_label;
    private JLabel FourthField_label;
    private JTextArea FirstField_outputArea;
    private JTextArea SecondField_outputArea;
    private JTextArea ThirdField_outputArea;
    private JTextArea FourthField_outputArea;

    private List<JComponent> DetailsComp_list = new ArrayList<>(Details_panel.getComponentCount());

    public CF_MainFrame(Controller c) {
        business_logic = c;
        Details_panel.setVisible(false);
        for(Component comp : Details_panel.getComponents()){
            DetailsComp_list.add((JComponent) comp);
        }
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
        Attribute_comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.Attribute_comboBox_ItemChanged(MainFrame_holder);
            }
        });
        Selection_spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                business_logic.Selection_spinner_ItemChanged();
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

    public JSpinner getSelection_spinner() {
        return Selection_spinner;
    }

    public JLabel getDetail_ObjectName_label() {
        return Detail_ObjectName_label;
    }

    public JLabel getFirstField_label() {
        return FirstField_label;
    }

    public JLabel getSecondField_label() {
        return SecondField_label;
    }

    public JLabel getThirdField_label() {
        return ThirdField_label;
    }

    public JLabel getFourthField_label() {
        return FourthField_label;
    }

    public JTextArea getFirstField_outputArea() {
        return FirstField_outputArea;
    }

    public JTextArea getSecondField_outputArea() {
        return SecondField_outputArea;
    }

    public JTextArea getThirdField_outputArea() {
        return ThirdField_outputArea;
    }

    public JTextArea getFourthField_outputArea() {
        return FourthField_outputArea;
    }

    public List<JComponent> getDetailsComp_list() {
        return DetailsComp_list;
    }
}
