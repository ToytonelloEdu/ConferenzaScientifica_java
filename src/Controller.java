import DAO_classes.*;
import Model_classes.*;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
        //Controller business_logic = new Controller();
        Sede foo = new Sede("Casa Ascione", "Via Cupa Camaldoli, 18", "Torre del Greco");
        Locazione foo2 = new Locazione(foo, "Studio di Antonio", 4);
        Locazione_DAO prova = foo2.getDao();
        prova.InsertLocazione(foo2);
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
