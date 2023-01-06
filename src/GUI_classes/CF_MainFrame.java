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
    private JComboBox Class_comboBox;
    private JComboBox Attribute_comboBox;
    private JPanel Details_panel;
    private JButton clearButton;

    public CF_MainFrame(Controller bl) {
        business_logic = bl;
        setContentPane(HomePanel);
        setTitle("Conferencer");
        setBounds(50, 50, 1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setValues_in_Attribute_comboBox();
        Search_textField.setText("");
        setVisible(true);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output_TextArea.setText("");
                String Class_SearchIn_str = (String) Class_comboBox.getSelectedItem();
                if(business_logic.isEmpty(Search_textField)){
                    List Output_ObjList = business_logic.MainFrame_searchButtonClicked(Class_SearchIn_str);
                    for(Object o: Output_ObjList){
                        Output_TextArea.append(o.toString()+ "\n");
                    }
                }
                else{
                    String Attr_SearchIn_str = (String) Attribute_comboBox.getSelectedItem();
                    String Value_Search_str = Search_textField.getText();
                    List Output_ObjList = business_logic.MainFrame_searchButtonClicked(Class_SearchIn_str, Attr_SearchIn_str, Value_Search_str);
                    if(Output_ObjList.isEmpty()){
                        Output_TextArea.setText("Nessun risultato per la ricerca eseguita");
                    }
                    else
                        for(Object o: Output_ObjList){
                            Output_TextArea.append(o.toString()+ "\n");
                        }

                }


            }
        });
        Class_comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output_TextArea.setText("");
                clear_Attribute_comboBox();
                setValues_in_Attribute_comboBox();
            }

        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search_textField.setText("");
            }
        });
    }



    private void setValues_in_Attribute_comboBox() {
        String Class_selected = (String) Class_comboBox.getSelectedItem();
        for(String o: business_logic.Class_ComboBox_change(Class_selected)){
            Attribute_comboBox.addItem(o);
        }
    }

    private void clear_Attribute_comboBox() {
        int i = 0;
        while(i < Attribute_comboBox.getItemCount()){
            Attribute_comboBox.removeItemAt(i);
        }
    }
}
