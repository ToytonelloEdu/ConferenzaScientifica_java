package Model_classes;

import java.util.List;

public interface ModelClass {
    String toSQLrow();
    String toSQLctrl();
    int toPK();
    String toGUI_Output(List<ModelClass> outputList);
}
