import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
        try {
            Controller business_logic = new Controller();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        Conferenza conferenza = new Conferenza();
        Sponsor sponsor = new Sponsor();
        Conf_Sponsor foo = new Conf_Sponsor(sponsor.getDao().getByPK(1), conferenza.getDao().getByPK(8), 20000.00);
        Conf_Sponsor_DAO prova = foo.getDao();
        prova.InsertConf_Sponsor(foo);

    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
