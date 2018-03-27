import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


class Singleton{
    private static  volatile Singleton singleton;
    private Singleton(){
        System.out.println(Thread.currentThread().getName());
    }

    public static Singleton getSingleton(){
            synchronized (Singleton.class){
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }

        return singleton;
    }
}


public class Point {
    public static void main(String[] args) throws NoSuchMethodException {
        new Thread(()->{
            Singleton.getSingleton();
        },"线程A").start();
        new Thread(()->{
            Singleton.getSingleton();
        },"线程B").start();
        new Thread(()->{
            Singleton.getSingleton();
        },"线程C").start();
        new Thread(()->{
            Singleton.getSingleton();
        },"线程D").start();
        new Thread(()->{
            Singleton.getSingleton();
        },"线程E").start();
        new Thread(()->{
            Singleton.getSingleton();
        },"线程F").start();
    }

}
