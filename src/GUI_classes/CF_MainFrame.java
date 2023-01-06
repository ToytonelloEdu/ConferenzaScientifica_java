package GUI_classes;

import Business_Logic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CF_MainFrame extends JFrame {
    private Controller business_logic;
    private JPanel HomePanel;
    private JTextField Search_textField;
    private JTextArea Output_TextArea;
    private JButton searchButton;
    private JComboBox comboBox1;

    public CF_MainFrame(Controller bl) {
        business_logic = bl;
        setContentPane(HomePanel);
        setVisible(true);
        setTitle("Conferencer");
        setBounds(50, 50, 1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Output_TextArea.setText("");
                String SearchIn_str = (String) comboBox1.getSelectedItem();
                for(Object o: business_logic.searchButtonClicked(SearchIn_str)){
                    Output_TextArea.append(o.toString()+ "\n");

                }
            }
        });
    }
}
