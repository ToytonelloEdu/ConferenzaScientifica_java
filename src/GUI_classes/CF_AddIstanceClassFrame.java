package GUI_classes;

import javax.swing.*;
import Business_Logic.*;
import Model_classes.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CF_AddIstanceClassFrame extends JFrame {

    private Controller business_logic;
    private JPanel MainPanel;

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
        CF_AddIstanceClassFrame FrameHolder = this;
        setBounds(50, 50, 500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return FrameHolder;
    }

}
