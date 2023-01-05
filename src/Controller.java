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

    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
