package Model_classes;

import DAO_classes.DaoClass;

import java.util.List;

public interface ModelClass {
    DaoClass getDao();
    String toSQLrow();
    String toSQLctrl();
    int toPK();
    String toGUI_Output(List<ModelClass> outputList);
}
