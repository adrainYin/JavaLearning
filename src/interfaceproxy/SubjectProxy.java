package interfaceproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectProxy implements InvocationHandler {
    private Subject subject;

    public SubjectProxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这是一个前置条件");
        method.invoke(subject, args);
        System.out.println("这是一个后置条件");
        return null;
    }
}
