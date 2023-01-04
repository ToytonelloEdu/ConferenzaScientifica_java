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
        //Controller business_logic = new Controller();
        Sede temp = new Sede("Casa Ascione", "Via Cupa Camaldoli, 18", "Torre del Greco");
        Sede Sede_new = new Sede("Casa Anastasio", "Via San Tommaso Apostolo, 9", "Pomigliano D'Arco");
        Sede_DAO dao_temp = new Sede_DAO();
        dao_temp.UpdateSede(temp, Sede_new);
    }

    public Controller(){
        MainFrame = new CF_MainFrame();
    }
}
