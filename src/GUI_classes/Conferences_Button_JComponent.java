package GUI_classes;

import javax.swing.*;

public class Conferences_Button_JComponent extends JComponent {
    private JButton OpenConference_Button;
    private JLabel ConferenceName_Label;
    private JPanel MainPanel;

    public Conferences_Button_JComponent() {
    }

    public Conferences_Button_JComponent(String name) {
        ConferenceName_Label.setText(name);
        this.setBounds(0, 0, 200, 50);
    }

    public void setNameLabel(String name) {
        ConferenceName_Label.setText(name);
    }

}
