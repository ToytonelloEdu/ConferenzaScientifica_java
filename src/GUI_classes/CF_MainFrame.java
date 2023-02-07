package GUI_classes;

import Business_Logic.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    private JScrollPane OutputScrollPanel;
    private JPanel MainPanel;
    private JList<String> DetPanel_FirstList;
    private JScrollPane Details_JScrollPanel;
    private JScrollPane FirstList_ScrollPanel;
    private JButton detailsButton;
    private JPanel FirstList_Panel;
    private JComboBox<String> dbAttr_comboBox;
    private JLabel TitleLabel;
    private JButton loginButton;
    private JLabel LabelLogin;
    private JSpinner meseSpinner;

    private List<JComponent> DetailsComp_list = new ArrayList<>(Details_panel.getComponentCount());

    public CF_MainFrame(Controller c) {
        business_logic = c;
        CF_MainFrame MainFrame_holder = MainFrame_setUp();
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
                business_logic.ClearButton_clicked();
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
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.DetailsButton_clicked();
            }
        });
        DetPanel_FirstList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                business_logic.FirstList_SelectedItem_changed();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton_clicked();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.LoginButtonClicked();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.DeleteButtonClicked();
            }
        });
        meseSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                business_logic.meseSpinner_Changed();
            }
        });
    }

    private CF_MainFrame MainFrame_setUp() {
        DetailsPanel_setup();
        CF_MainFrame MainFrame_holder = this;
        setContentPane(HomePanel);
        setTitle("Conferencer");
        setBounds(50, 50, 1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        business_logic.setValues_in_Attribute_comboBox(MainFrame_holder);
        Search_textField.setText("");
        dbAttr_comboBox.setVisible(false);
        LabelLogin.setVisible(false);
        meseSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        return MainFrame_holder;
    }

    private void DetailsPanel_setup() {
        Details_panel.setVisible(false);
        FillDetailsComp_list();
        FirstField_outputArea.setLineWrap(true);
        FirstField_outputArea.setWrapStyleWord(true);
        SecondField_outputArea.setLineWrap(true);
        SecondField_outputArea.setWrapStyleWord(true);
        ThirdField_outputArea.setLineWrap(true);
        ThirdField_outputArea.setWrapStyleWord(true);
        FourthField_outputArea.setLineWrap(true);
        FourthField_outputArea.setWrapStyleWord(true);
    }

    private void FillDetailsComp_list() {
        for(Component comp : Details_panel.getComponents()){
            DetailsComp_list.add((JComponent) comp);
        }
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

    public JScrollPane getOutputScrollPanel() {
        return OutputScrollPanel;
    }

    public JList<String> getDetPanel_FirstList() {
        return DetPanel_FirstList;
    }

    public JScrollPane getFirstList_ScrollPanel() {
        return FirstList_ScrollPanel;
    }

    public JPanel getFirstList_Panel() {
        return FirstList_Panel;
    }

    public JComboBox<String> getDbAttr_comboBox() {
        return dbAttr_comboBox;
    }

    public JButton getDetailsButton() {
        return detailsButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getLabelLogin() {
        return LabelLogin;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JSpinner getMeseSpinner() {
        return meseSpinner;
    }
}
