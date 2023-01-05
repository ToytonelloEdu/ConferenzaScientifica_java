import DAO_classes.*;
import Model_classes.*;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
//        Controller business_logic = new Controller();
//        Conferenza_DAO prova = new Conferenza_DAO();
//        ArrayList<Conferenza> Lista = prova.getAllConferenza();
//        for(Conferenza o: Lista){
//            System.out.println(o.getDataInizio() +", "+ o.getDataFine());
//        }

        Conferenza_DAO conf = new Conferenza_DAO();
        Istituzione_DAO istit = new Istituzione_DAO();
        Ente_organizzatore foo = new Ente_organizzatore(conf.getByPK(2), istit.getByPK(3));
        Ente_organizzatore foo2 = new Ente_organizzatore(conf.getByPK(3), istit.getByPK(12));
        Ente_Organizzatore_DAO prova = foo.getDao();
        prova.UpdateEnteOrganizzatore(foo, foo2);
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
