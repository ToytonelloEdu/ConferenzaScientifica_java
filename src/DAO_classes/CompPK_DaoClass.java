package DAO_classes;

import Model_classes.ModelClass;

public interface CompPK_DaoClass extends DaoClass{
    ModelClass getByCompositePK(Object firstPK, Object secondPK);
}
