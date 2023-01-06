package Business_Logic;

import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

import java.math.BigDecimal;
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

        Conf_Sponsor foo = new Conf_Sponsor(new Sponsor_DAO().getByPK(1), new Conferenza_DAO().getByPK(8), "200000.00");
        Conf_Sponsor foo2 = new Conf_Sponsor(new Sponsor_DAO().getByPK(2), new Conferenza_DAO().getByPK(6), "800000.00");
        Conf_Sponsor_DAO prova = foo.getDao();
        prova.UpdateConf_Sponsor(foo, foo2);

    }

    public Controller(){
        MainFrame = new CF_MainFrame(this);

    }
}
