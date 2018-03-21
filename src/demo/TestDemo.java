package demo;

import action.EmpAction;

import java.lang.reflect.InvocationTargetException;

public class TestDemo {

    public static void main(String args[]) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String value = "emp.ename:dick|emp.eage:20|emp.dept.dname:技术部门|emp.dept.did:123|emp.dept.company.companyName:华师大|emp.dept.company.companyID:456";
        EmpAction empAction = new EmpAction();
        empAction.setValue(value);
        System.out.println(empAction.getEmp());

    }
}
