package interfaceproxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        subject.fun(1,2);
        System.out.println("*************我是一条分割线**********");
        Subject proxySubject = (Subject)Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new SubjectProxy(subject));
        proxySubject.fun(1,2);
    }
}
