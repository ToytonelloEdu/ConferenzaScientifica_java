package DAO_classes;

import Model_classes.ModelClass;
import Model_classes.Utente;

import java.util.List;

public interface DaoClass {
    List<ModelClass> getAll();
    Integer getPK(ModelClass Object);

}
