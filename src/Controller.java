import DAO_classes.*;
import Model_classes.*;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
        Controller business_logic = new Controller();
//        Conferenza_DAO prova = new Conferenza_DAO();
//        ArrayList<Conferenza> Lista = prova.getAllConferenza();
//        for(Conferenza o: Lista){
//            System.out.println(o.getDataInizio() +", "+ o.getDataFine());
//        }

//        Sponsor foo = new Sponsor("Fastweb", "12345678912");
//        Sponsor foo2 = new Sponsor("Iliad", "98765432198");
//        Sponsor_DAO prova = foo.getDao();
//        prova.UpdateSponsor(foo, foo2);
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
