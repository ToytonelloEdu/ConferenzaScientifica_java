import DAO_classes.Sede_DAO;
import Model_classes.Sede;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
        //Controller business_logic = new Controller();
        Sede foo = new Sede("Casa Ascione", "Via Cupa Camaldoli, 18", "Torre del Greco");
        Sede_DAO prova = foo.getDao();
        System.out.println(prova.getPK(foo));
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
