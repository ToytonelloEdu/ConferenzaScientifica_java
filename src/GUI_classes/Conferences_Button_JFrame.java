package GUI_classes;

import javax.swing.*;

public class Conferences_Button_JFrame extends JComponent {
    private JButton OpenConference_Button;
    private JLabel ConferenceName_Label;
    private JPanel MainPanel;

    public Conferences_Button_JFrame() {
    }

    public void setNameLabel(String name) {
        ConferenceName_Label.setText(name);
    }

}
