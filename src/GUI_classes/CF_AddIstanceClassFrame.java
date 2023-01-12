package GUI_classes;

import javax.swing.*;
import Business_Logic.*;

public class CF_AddIstanceClassFrame extends JFrame {

    private Controller business_logic;

    public CF_AddIstanceClassFrame(Controller c){
        business_logic = c;
        setTitle("Aggiungi Conferenza");
    }

}
