package test;

import java.io.Serializable;

public class MyObject implements Serializable {

    private static final long serialVersionUID = 888L;

    private static class MyObjectHander{
        private static final MyObject myObject = new MyObject();
    }

    private MyObject(){}

    public static MyObject getInstance(){
        return MyObjectHander.myObject;
    }

    protected Object readResolve(){
        System.out.println("调用了非静态的实例获取方法");
        return MyObjectHander.myObject;
    }
}
