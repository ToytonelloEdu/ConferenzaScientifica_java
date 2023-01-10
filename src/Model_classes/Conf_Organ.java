package Model_classes;

import DAO_classes.DaoClass;

import java.util.List;

public class Conf_Organ implements ModelClass{
    @Override
    public DaoClass getDao() {
        return null;
    }

    @Override
    public String toSQLrow() {
        return null;
    }

    @Override
    public String toSQLctrl() {
        return null;
    }

    @Override
    public int toPK() {
        return 0;
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return null;
    }
}
