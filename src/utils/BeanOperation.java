package utils;

import java.lang.reflect.InvocationTargetException;

public class BeanOperation {

    private BeanOperation(){}
    public static void setBeanValue(Object actionObject , String values) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {

        String result[] = values.split("\\|");
        for (int i = 0; i < result.length; i++) {
            String data[] = result[i].split(":");
            String fields[] = data[0].split("\\.");
            Object currObject = BeanUtil.getObject(actionObject , fields[0]);
            //得到了对象之后需要对其进行赋值操作
            BeanUtil.setObjectVaule(currObject,fields[1],data[1]);

        }
    }
}
