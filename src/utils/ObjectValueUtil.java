package utils;

import java.lang.reflect.Field;

/**
 * 转换各种额数据类型，通过unity中类的属性具体的数据类型
 * 可以有Long Integer String Double Date；（yyyy-MM-dd HH-mm-ss.SSS） (yyyy-MM-dd)
 */
public class ObjectValueUtil {
    private ObjectValueUtil(){}

    public static Object getAttributrValue(Object currObject ,String attribute,String value) throws NoSuchFieldException {
        Field field = currObject.getClass().getDeclaredField(attribute);
        if (field == null){
            field = currObject.getClass().getField(attribute);
        }
        if (field == null){
            return null;
        }



        return null;
    }

    private static Object transformType(String type,String value){


        return null;
    }
}
