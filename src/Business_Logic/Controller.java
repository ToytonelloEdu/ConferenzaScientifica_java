package Business_Logic;

import DAO_classes.*;
import GUI_classes.*;

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

    }

    public Controller(){
        MainFrame = new CF_MainFrame(this);

    }

    public List searchButtonClicked(String searchIn_str) {
        return new DBGetterByClassName().GetAllByClassName(searchIn_str);

    }
}
