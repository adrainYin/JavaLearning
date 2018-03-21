package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtil {

    private BeanUtil(){}

    /**
     * 该方法的作用是得到具体的unity类，通过对unityAction类的反射得到.因为在action类中定义了get和set方法
     * @param object
     * @param attribute
     * @return
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getObject(Object object , String attribute) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String methodName = "get" + StringUtil.UpToCase(attribute);
        //在这里因为action设置了一个unity属性所以需要调用get方法得到
        Field field = object.getClass().getDeclaredField(attribute);
        if (field == null){
            field = object.getClass().getField(attribute);
        }
        if (field == null){
            return null;
        }
        Method method = object.getClass().getMethod(methodName );
        Object o =  method.invoke(object);
        //如果没有实例化对象，那么就需要实例化对象，并且将该对象set给上一层
        if (o == null){
            /*
            第一步是根据对象得到类型，再根据类型自自动实例化对象
            第二步就是将该实例化对象那个设置到上一层
             */
            o = field.getType().newInstance();
            setObjectVaule(object,attribute,o);
        }
        return o;

    }

    public static void setObjectVaule(Object object , String attribute , Object value) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(attribute);
        /*
        在这里需要加入判断语句，判断这个方法对应的属性是否存在
         */
        if (field == null){
            field = object.getClass().getField(attribute);
        }
        if (field == null){
            return;
        }

        String methodName = "set" + StringUtil.UpToCase(attribute);
        Method method = object.getClass().getMethod(methodName,field.getType());
        method.invoke(object,value);

    }

}
