import Concrete_classes.*;
import DAO_classes.*;
import com.sun.tools.javac.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Controller {
    CF_MainFrame MainFrame;

    public static void main(String[] args) {
        Controller business_logic = new Controller();
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
