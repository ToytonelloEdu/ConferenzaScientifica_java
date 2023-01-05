import DAO_classes.*;
import Model_classes.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
        Controller business_logic = new Controller();
        Istituzione foo = new Istituzione("Università degli Studi di Pomigliano, Napoli", "Italia");
        Istituzione foo2 = new Istituzione("Università degli Studi di Liverpool, Londra", "Inghilterra");
        Istituzione_DAO prova = foo.getDao();
        prova.UpdateIstituzione(foo, foo2);
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
