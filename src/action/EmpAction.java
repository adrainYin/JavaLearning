package action;

import unity.Emp;
import utils.BeanOperation;

import java.lang.reflect.InvocationTargetException;

public class EmpAction {
     private Emp emp ;

    public void setValue(String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        BeanOperation.setBeanValue(this , value);
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Emp getEmp() {
        return emp;
    }
}
