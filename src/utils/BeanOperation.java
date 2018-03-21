package utils;

import java.lang.reflect.InvocationTargetException;

public class BeanOperation {

    private BeanOperation(){}
    public static void setBeanValue(Object actionObject , String values) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        String result[] = values.split("\\|");
        for (int i = 0; i < result.length; i++) {
            //这时候数据可能是各种的数据类型了
            String data[] = result[i].split(":");
            String fields[] = data[0].split("\\.");
            if (fields.length > 2){
                Object currObject = actionObject;
                for (int j = 0; j < fields.length -1; j++) {
                   currObject = BeanUtil.getObject(currObject , fields[j]);
                }
                BeanUtil.setObjectVaule(currObject,fields[fields.length-1],data[1]);
            }else {
                Object currObject = BeanUtil.getObject(actionObject, fields[0]);
                //得到了对象之后需要对其进行赋值操作
                BeanUtil.setObjectVaule(currObject, fields[1], data[1]);
            }
        }
    }
}
