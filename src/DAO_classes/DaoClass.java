package DAO_classes;

import Model_classes.ModelClass;
import Model_classes.Utente;

import java.util.List;

public interface DaoClass {
    List<ModelClass> getAll();
    List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in);
    Integer getPK(ModelClass Object);
    ModelClass getByPK(int PK);
    void Insert(ModelClass obj);
    void Delete(ModelClass obj);
    void Update(ModelClass oldObj, ModelClass newObj);

}
