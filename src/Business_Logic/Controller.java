package Business_Logic;

import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

import java.util.List;

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

        Conf_Sponsor foo = new Conf_Sponsor(new Sponsor_DAO().getByPK(1), new Conferenza_DAO().getByPK(8), 20000.00);
        Conf_Sponsor_DAO prova = foo.getDao();
        prova.InsertConf_Sponsor(foo);

    }

    public Controller(){
        MainFrame = new CF_MainFrame(this);

    }
}
