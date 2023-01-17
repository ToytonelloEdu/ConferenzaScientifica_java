package GUI_classes;

import javax.swing.*;
import Business_Logic.*;

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
    private JComboBox Select_comboBox10;
    private JButton addButton10;
    private JButton newButton10;
    private JLabel Label10;

    private List<JComponent> DataInsertComponentList = new ArrayList<>(DataInsert_JPanel.getComponentCount());

    public CF_AddIstanceClassFrame(Controller c) {
        business_logic = c;
        CF_AddIstanceClassFrame FrameHolder = AddIstanceClassFrame_setup();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                business_logic.AddInstanceFrame_hidden();
            }
        });
        leftButton9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.leftButton9_clicked();
            }
        });
        rightButton9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.rightButton9_clicked();
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
        setBounds(50, 50, 500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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

    public JComboBox getSelect_comboBox10() {
        return Select_comboBox10;
    }

    public JButton getAddButton10() {
        return addButton10;
    }
}
