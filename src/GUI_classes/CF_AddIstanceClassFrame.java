package GUI_classes;

import javax.swing.*;
import Business_Logic.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CF_AddIstanceClassFrame extends JFrame {

    private Controller business_logic;
    private JPanel MainPanel;
    private JLabel ObjectAdded_label;
    private JPanel ObjectAdded_JPanel;
    private JPanel DataInsert_JPanel;
    private JButton aggiungiAlDatabaseButton;
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

    public CF_AddIstanceClassFrame(Controller c){
        business_logic = c;
        CF_AddIstanceClassFrame FrameHolder = AddIstanceClassFrame_setup();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                business_logic.AddInstanceFrame_hidden();
            }
        });
    }

    private CF_AddIstanceClassFrame AddIstanceClassFrame_setup(){
        setVisible(false);
        setContentPane(MainPanel);
        CF_AddIstanceClassFrame FrameHolder = this;
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

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public void setTextField4(JTextField textField4) {
        this.textField4 = textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public void setTextField5(JTextField textField5) {
        this.textField5 = textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public void setTextField6(JTextField textField6) {
        this.textField6 = textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public void setTextField7(JTextField textField7) {
        this.textField7 = textField7;
    }

    public JTextField getTextField8() {
        return textField8;
    }

    public void setTextField8(JTextField textField8) {
        this.textField8 = textField8;
    }

    public JLabel getLabel1() {
        return Label1;
    }

    public void setLabel1(JLabel label1) {
        Label1 = label1;
    }

    public JLabel getLabel2() {
        return Label2;
    }

    public void setLabel2(JLabel label2) {
        Label2 = label2;
    }

    public JLabel getLabel3() {
        return Label3;
    }

    public void setLabel3(JLabel label3) {
        Label3 = label3;
    }

    public JLabel getLabel4() {
        return Label4;
    }

    public void setLabel4(JLabel label4) {
        Label4 = label4;
    }

    public JLabel getLabel5() {
        return Label5;
    }

    public void setLabel5(JLabel label5) {
        Label5 = label5;
    }

    public JLabel getLabel6() {
        return Label6;
    }

    public void setLabel6(JLabel label6) {
        Label6 = label6;
    }

    public JLabel getLabel7() {
        return Label7;
    }

    public void setLabel7(JLabel label7) {
        Label7 = label7;
    }

    public JLabel getLabel8() {
        return Label8;
    }

    public void setLabel8(JLabel label8) {
        Label8 = label8;
    }
}
