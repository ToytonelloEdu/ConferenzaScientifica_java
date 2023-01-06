package GUI_classes;

import Business_Logic.*;

import javax.swing.*;
import java.awt.*;

public class CF_MainFrame extends JFrame {
    private Controller business_logic;
    private JPanel HomePanel;
    private JPanel Conferences_List_JPanel;

    public CF_MainFrame(Controller bl) {
        business_logic = bl;
        Conferences_List_JPanel.setLayout(new BoxLayout(Conferences_List_JPanel, BoxLayout.PAGE_AXIS));
        setContentPane(HomePanel);
        setVisible(true);
        setTitle("Conferencer");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Conferences_List_JPanel.add(new Conferences_Button_JComponent("Check 1"));
    }

    public void addConfButton(Conferences_Button_JComponent temp) {
        Conferences_List_JPanel.add(temp);

    }
}
