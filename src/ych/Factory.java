package ych;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

interface Fruit{
    public void eat();
}
interface Message {
    public void print();
}
class IMessage implements Message{

    @Override
    public void print() {
        System.out.println("这是一条消息");
    }
}
class Apple implements Fruit{
    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}
class Orange implements Fruit{

    @Override
    public void eat() {
        System.out.println("吃橘子");
    }
}
class FactoryImp {
    public static <T> T getInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aclass = Class.forName(className);
        T t = (T) aclass.newInstance();
        return t;
    }

}
class Person{
    private String name;
    private int age;
    public Person(){

    }
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
public class Factory {

    public static String upToCase(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }


    public static void main(String args[]) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
//       Fruit apple =  FactoryImp.getInstance("ych.Apple");
//       apple.eat();
//       Message message = FactoryImp.getInstance("ych.IMessage");
//       message.print();
//       Class<?> cls = Apple.class;
//       Orange orange = new Orange();
//       Class<?> aclass = orange.getClass();
//       String PackName = aclass.getPackage().getName();
//       String superClass = aclass.getSuperclass().getName();
//       Class<?> classes [] = aclass.getInterfaces();
//        for (int i = 0; i < classes.length; i++) {
//            System.out.println(classes[i].getName());
//        }
//        System.out.println(PackName);
//        System.out.println(superClass);
//        System.out.println("--------------------------------我是分割线----------------------------");
//        Class<?> cc = Person.class;
//        try {
//            Constructor<?> constructor = cc.getConstructor(String.class,int.class);
//            constructor.newInstance("张三",20);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        Class<?> cls = Class.forName("ych.Person");
        Object object = cls.newInstance();
        Field nameField = cls.getDeclaredField("name");
        nameField.setAccessible(true);
        /**
         * 在下列一行代码中，可以直接用Field.getType()方法得到该属性的CLass
         */
        Method nameMethod = cls.getMethod("setName", nameField.getType());
        Method nameMethod1 = cls.getMethod("getName");
        nameMethod.invoke(object,"啊哈哈哈哈");
        System.out.println(nameMethod1.invoke(object));
//        nameField.set(object,"张三");
//        System.out.println(nameField.get(object));
    }
}
