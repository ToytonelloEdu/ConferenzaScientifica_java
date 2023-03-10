package GUI_classes;

import Business_Logic.Controller;
import Model_classes.ModelClass;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CF_AddEditClassFrame extends JFrame {

    private final Controller business_logic;
    private Boolean EditModeB;
    private JPanel MainPanel;
    private JLabel ObjectAdded_label;
    private JPanel ObjectAdded_JPanel;
    private JPanel DataInsert_JPanel;
    private JButton confermaButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel Label6;
    private JLabel Label7;
    private JLabel Label8;
    private JButton leftButton9Button;
    private JButton rightButton9Button;
    private JLabel Label9;
    private JComboBox<ModelClass> Select_comboBox10;
    private JButton addButton10;
    private JButton newButton10;
    private JLabel Label10;
    private JList<ModelClass> SelectedItems_list10;
    private JPanel SelectItems_JPanel10;
    private JButton removeButton11;
    private JPanel AddOnly_JPanel11;
    private JList<ModelClass> AddOnly_list11;
    private JLabel label11;
    private JButton NewButton11;
    private JButton annullaButton;
    private JPanel SelectItems_JPanel12;
    private JComboBox<ModelClass> Select_comboBox12;
    private JButton newButton12;
    private JButton addButton12;
    private JLabel label12;
    private JList<ModelClass> SelectedItems_list12;
    private JComboBox<ModelClass> SelectOne_comboBox13;
    private JScrollPane DataInsert_JScrollPanel;
    private JLabel label13;
    private JPanel SelectOnly_JPanel13;
    private JPanel SelectItems_JPanel14;
    private JComboBox<ModelClass> Select_comboBox14;
    private JButton newButton14;
    private JButton addButton14;
    private JLabel label14;
    private JList<ModelClass> SelectedItems_list14;
    private JPanel BottomPanel;
    private JButton removeButton10;
    private JButton removeButton14;
    private JButton removeButton12;
    private JPanel TwoButton_JPanel;
    private JButton checkDisponibilitaButton;
    private JCheckBox checkBox1;
    private JLabel CheckButtonLabel;
    private JTextField textField9;
    private JPanel TwoButtons14_JPanel;
    private JButton localeButton;
    private JButton scientificoButton;
    private JLabel comitatoLabel;
    private JPanel ListsPanel12;
    private JList<String> SelectedItems_infoList12;
    private JList<Integer> SelectedItems_infoList14;
    private JPanel ListsPanel14;
    private JButton editButton11;

    private final DefaultListModel<ModelClass> dlModel10 = new DefaultListModel<>();
    private final DefaultListModel<ModelClass> dlModel11 = new DefaultListModel<>();
    private final DefaultListModel<ModelClass> dlModel12 = new DefaultListModel<>();
    private final DefaultListModel<ModelClass> dlModel14 = new DefaultListModel<>();
    private final DefaultListModel<String> dlModel12i = new DefaultListModel<>();
    private final DefaultListModel<Integer> dlModel14i = new DefaultListModel<>();

    private final List<JComponent> DataInsertComponentList = new ArrayList<>(DataInsert_JPanel.getComponentCount());

    public CF_AddEditClassFrame(Controller c) {
        business_logic = c;
        CF_AddEditClassFrame FrameHolder = AddIstanceClassFrame_setup();


        leftButton9Button.addActionListener(e -> business_logic.PartecipanteButtonClicked());
        rightButton9Button.addActionListener(e -> business_logic.OrganizzatoreButtonClicked());
        NewButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewButton11Clicked(EditModeB);
            }
        });
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(EditModeB.equals(false))
                    business_logic.confermaButtonClicked();
                else if(EditModeB.equals(true))
                    business_logic.confermaButtonClickedForEdit();
            }
        });
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.AddInstanceFrame_hidden();
            }
        });
        removeButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.removeButton11Clicked(EditModeB);
            }
        });
        addButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton10_clicked(EditModeB);
            }
        });
        addButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton14_clicked(EditModeB);
            }
        });
        addButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton12_clicked(EditModeB);
            }
        });
        removeButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.removeButton10_clicked(EditModeB);
            }
        });
        removeButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.removeButton12_clicked(EditModeB);
            }
        });
        removeButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.removeButton14_clicked(EditModeB);
            }
        });
        newButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.newButton14_clicked();
            }
        });
        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                business_logic.CheckCorrectConferenzaDates();
            }
        });
        textField3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                business_logic.CheckCorrectConferenzaDates();
            }
        });
        checkDisponibilitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(EditModeB.equals(false))
                    business_logic.CheckButtonClicked();
                else if(EditModeB.equals(true))
                    business_logic.CheckButtonClickedForEdit();
            }
        });
        localeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.LocaleButtonClicked();
            }
        });
        scientificoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.ScientificoButtonClicked();
            }
        });
        textField9.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                business_logic.textField9_contentChange(textField9.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                business_logic.textField9_contentChange(textField9.getText());

            }

            public void insertUpdate(DocumentEvent e) {
                business_logic.textField9_contentChange(textField9.getText());
            }
        });
        SelectedItems_list14.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                business_logic.AlignLists(SelectedItems_list14, SelectedItems_infoList14);
            }
        });
        SelectedItems_infoList14.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                business_logic.AlignLists(SelectedItems_infoList14, SelectedItems_list14);
            }
        });
        SelectedItems_list12.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                business_logic.AlignLists(SelectedItems_list12, SelectedItems_infoList12);
            }
        });
        SelectedItems_infoList12.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                business_logic.AlignLists(SelectedItems_infoList12, SelectedItems_list12);
            }
        });
        editButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.EditButton11_Clicked(EditModeB);
            }
        });
    }

    public void setEditModeB(Boolean editModeB) {
        EditModeB = editModeB;
    }

    public Boolean getEditModeB() {
        return EditModeB;
    }

    private CF_AddEditClassFrame AddIstanceClassFrame_setup() {
        setVisible(false);
        setContentPane(MainPanel);
        CF_AddEditClassFrame FrameHolder = this;
        for (Component comp : DataInsert_JPanel.getComponents())
            DataInsertComponentList.add((JComponent) comp);
        setTitle("Conferencer: aggiungi oggetto");
        setBounds(75, 75, 650, 650);
        DataInsert_JScrollPanel.getVerticalScrollBar().setUnitIncrement(8);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        NewButton11.setEnabled(false);
        SelectedItems_list10.setModel(dlModel10);
        AddOnly_list11.setModel(dlModel11);
        SelectedItems_list12.setModel(dlModel12);
        SelectedItems_list14.setModel(dlModel14);
        SelectedItems_infoList12.setModel(dlModel12i);
        SelectedItems_infoList14.setModel(dlModel14i);
        return FrameHolder;
    }

    public DefaultListModel<ModelClass> getDlModel10() {
        return dlModel10;
    }

    public DefaultListModel<ModelClass> getDlModel11() {
        return dlModel11;
    }

    public DefaultListModel<ModelClass> getDlModel12() {
        return dlModel12;
    }

    public DefaultListModel<ModelClass> getDlModel14() {
        return dlModel14;
    }

    public JLabel getObjectAdded_label() {
        return ObjectAdded_label;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public JTextField getTextField8() {
        return textField8;
    }

    public JTextField getTextField9() {
        return textField9;
    }

    public JLabel getLabel1() {
        return Label1;
    }

    public JLabel getLabel2() {
        return Label2;
    }

    public JLabel getLabel3() {
        return Label3;
    }

    public JLabel getLabel4() {
        return Label4;
    }

    public JLabel getLabel5() {
        return Label5;
    }

    public JLabel getLabel6() {
        return Label6;
    }

    public JLabel getLabel7() {
        return Label7;
    }

    public JLabel getLabel8() {
        return Label8;
    }

    public List<JComponent> getDataInsertComponentList() {
        return DataInsertComponentList;
    }

    public JLabel getLabel9() {
        return Label9;
    }

    public JButton getLeftButton9Button() {
        return leftButton9Button;
    }

    public JButton getRightButton9Button() {
        return rightButton9Button;
    }

    public JLabel getLabel10() {
        return Label10;
    }

    public JComboBox<ModelClass> getSelect_comboBox10() {
        return Select_comboBox10;
    }

    public JList<ModelClass> getSelectedItems_list10() {
        return SelectedItems_list10;
    }

    public JPanel getSelectItems_JPanel10() {
        return SelectItems_JPanel10;
    }

    public JList<ModelClass> getAddOnly_list11() {
        return AddOnly_list11;
    }

    public JPanel getAddOnly_JPanel11() {
        return AddOnly_JPanel11;
    }

    public JLabel getLabel11() {
        return label11;
    }

    public JButton getNewButton11() {
        return NewButton11;
    }

    public JComboBox<ModelClass> getSelectOne_comboBox13() {
        return SelectOne_comboBox13;
    }

    public JPanel getSelectOnly_JPanel13() {
        return SelectOnly_JPanel13;
    }

    public JLabel getLabel13() {
        return label13;
    }

    public JLabel getLabel12() {
        return label12;
    }

    public JLabel getLabel14() {
        return label14;
    }
    public JComboBox<ModelClass> getSelect_comboBox14() {
        return Select_comboBox14;
    }
    public JList<ModelClass> getSelectedItems_list14() {
        return SelectedItems_list14;
    }
    public JComboBox<ModelClass> getSelect_comboBox12() {
        return Select_comboBox12;
    }
    public JList<ModelClass> getSelectedItems_list12() {
        return SelectedItems_list12;
    }
    public JButton getAddButton10() {
        return addButton10;
    }
    public JButton getAddButton12() {
        return addButton12;
    }
    public JButton getAddButton14() {
        return addButton14;
    }
    public JButton getnewButton10() {
        return newButton10;
    }
    public JButton getnewButton12() {
        return newButton12;
    }

    public JPanel getSelectItems_JPanel14() {
        return SelectItems_JPanel14;
    }

    public JPanel getSelectItems_JPanel12() {
        return SelectItems_JPanel12;
    }

    public JPanel getTwoButton_JPanel() {
        return TwoButton_JPanel;
    }

    public JButton getCheckDisponibilitaButton() {
        return checkDisponibilitaButton;
    }

    public JLabel getCheckButtonLabel() {
        return CheckButtonLabel;
    }

    public JCheckBox getCheckBox1() {
        return checkBox1;
    }

    public JButton getLocaleButton() {
        return localeButton;
    }

    public JButton getScientificoButton() {
        return scientificoButton;
    }

    //TODO: Usa!
    public JList<String> getSelectedItems_infoList12() {
        return SelectedItems_infoList12;
    }

    public JList<Integer> getSelectedItems_infoList14() {
        return SelectedItems_infoList14;
    }

    public DefaultListModel<String> getDlModel12i() {
        return dlModel12i;
    }

    public DefaultListModel<Integer> getDlModel14i() {
        return dlModel14i;
    }

    public JButton getEditButton11() {
        return editButton11;
    }

    public void EraseAllFieldsAndJLists() {
        for(JComponent comp: getDataInsertComponentList()){
            try{
                ((JTextField) comp).setText("");
            }catch (ClassCastException ignored){}
        }

        dlModel10.clear();
        dlModel11.clear();
        dlModel12.clear();
        dlModel14.clear();
        dlModel12i.clear();
        dlModel14i.clear();
    }
}
