package GUI_classes;

import javax.swing.*;
import Business_Logic.*;
import Model_classes.ModelClass;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CF_AddIstanceClassFrame extends JFrame {

    private Controller business_logic;
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
    private JList<String> AddOnly_list11;
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

    private List<JComponent> DataInsertComponentList = new ArrayList<>(DataInsert_JPanel.getComponentCount());

    public CF_AddIstanceClassFrame(Controller c) {
        business_logic = c;
        CF_AddIstanceClassFrame FrameHolder = AddIstanceClassFrame_setup();



        leftButton9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftButton9Button.setEnabled(false);
                rightButton9Button.setEnabled(true);
            }
        });
        rightButton9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftButton9Button.setEnabled(true);
                rightButton9Button.setEnabled(false);
            }
        });
        NewButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewButton11Clicked();

            }
        });
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.confermaButtonClicked();
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
                business_logic.removeButtonClicked();
            }
        });
        addButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton10_clicked();
            }
        });
        addButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton14_clicked();
            }
        });
        addButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.addButton12_clicked();
            }
        });
        newButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.newButton14_clicked();
            }
        });
    }

    private CF_AddIstanceClassFrame AddIstanceClassFrame_setup() {
        setVisible(false);
        setContentPane(MainPanel);
        CF_AddIstanceClassFrame FrameHolder = this;
        for (Component comp : DataInsert_JPanel.getComponents())
            DataInsertComponentList.add((JComponent) comp);
        setTitle("Conferencer: aggiungi oggetto");
        setBounds(75, 75, 650, 650);
        DataInsert_JScrollPanel.getVerticalScrollBar().setUnitIncrement(8);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        return FrameHolder;
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

    public JList<String> getAddOnly_list11() {
        return AddOnly_list11;
    }

    public JPanel getAddOnly_JPanel11() {
        return AddOnly_JPanel11;
    }

    public JLabel getLabel11() {
        return label11;
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
    public JComboBox getSelect_comboBox14() {
        return Select_comboBox14;
    }
    public JList getSelectedItems_list14() {
        return SelectedItems_list14;
    }
    public JComboBox<ModelClass> getSelect_comboBox12() {
        return Select_comboBox12;
    }
    public JList<ModelClass> getSelectedItems_list12() {
        return SelectedItems_list12;
    }
    public JButton getnewButton10() {
        return newButton10;
    }
    public JButton getnewButton12() {
        return newButton12;
    }
}
