package DAO_classes;

import Exceptions.InsertFailedException;
import Model_classes.ModelClass;

import java.util.List;

public interface DaoClass {
    static DaoClass getDao() {
        return null;
    }
    List<ModelClass> getAll();
    List<ModelClass> getAll_byAttribute(String Attr_in, String Value_in);
    Integer getPK(ModelClass Object);
    ModelClass getByPK(int PK);
    void Insert(ModelClass obj) throws InsertFailedException;
    void Delete(ModelClass obj);
    void Update(ModelClass oldObj, ModelClass newObj);

}
