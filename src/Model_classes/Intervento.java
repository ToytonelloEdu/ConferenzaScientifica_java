package Model_classes;

import DAO_classes.DaoClass;

import java.util.List;

public class Intervento extends Evento{

    public String toSQLrow() {
        return null;
    }

    @Override
    public String toSQLctrl() {
        return null;
    }

    @Override
    public int toPK() {
        return super.toPK();
    }

    @Override
    public String toGUI_Output(List<ModelClass> outputList) {
        return super.toGUI_Output(outputList);
    }

    @Override
    public DaoClass getDao() {
        return null;
    }

}
