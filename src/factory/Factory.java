package factory;

import java.awt.*;

interface Fruit{
    public void eat();
    public void make();
}

interface Message{
    public void print();
    public void send();
}

class Apple implements Fruit{

    @Override
    public void eat() {
        System.out.println("吃苹果！");
    }

    @Override
    public void make() {
        System.out.println("洗一个苹果");
    }
}

class Orange implements Fruit{

    @Override
    public void eat() {
        System.out.println("吃橘子！");
    }

    @Override
    public void make() {
        System.out.println("洗橘子！");
    }
}

class IMessage implements Message{

    @Override
    public void print() {
        System.out.println("打印信息");
    }

    @Override
    public void send() {
        System.out.println("发送信息");
    }
}

class FactoryImp{
    /**
     * 通过泛型工厂来提高工厂的泛化性能，使得可以接受不同产品的实例化
     * @param classname 要生产的产品的完整类名称，包括路径
     * @param <T> 泛型参数
     * @return 返回具体的实例化对象
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getInstance(String classname) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName(classname);
        T  t = (T) cls.newInstance();
        return t;
    }
}
public class Factory {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Fruit apple = FactoryImp.getInstance("factory.Apple");
        apple.eat();
        apple.make();
    }
}
