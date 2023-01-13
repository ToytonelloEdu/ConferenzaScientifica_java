package GUI_classes;

import javax.swing.*;
import Business_Logic.*;
import Model_classes.*;
import java.awt.*;

public class CF_AddIstanceClassFrame extends JFrame {

    private Controller business_logic;

    public CF_AddIstanceClassFrame(Controller c){
        business_logic = c;
        CF_AddIstanceClassFrame FrameHolder = AddIstanceClassFrame_setup();

    }

    private CF_AddIstanceClassFrame AddIstanceClassFrame_setup(){
        setVisible(false);
        CF_AddIstanceClassFrame FrameHolder = this;
        setBounds(50, 50, 500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        return FrameHolder;
    }

}
