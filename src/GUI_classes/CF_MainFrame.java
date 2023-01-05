package GUI_classes;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class CF_MainFrame extends JFrame {
    private JButton OKButton;
    private JPanel HomePanel;
    private JPanel Conferences_List_JPanel;
    private Conferences_Button_JFrame Conference_Button1;

    public CF_MainFrame() {
        setContentPane(HomePanel);
        setVisible(true);
        setTitle("Conferencer");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Conference_Button1.setNameLabel("Comicon di Napoli");
    }

}
