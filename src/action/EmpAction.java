package action;

import unity.Emp;
import utils.BeanOperation;

import java.lang.reflect.InvocationTargetException;

public class EmpAction {
    private Emp emp = new Emp();

    public void setValue(String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        BeanOperation.setBeanValue(this , value);
    }
    public Emp getEmp() {
        return emp;
    }
}
