package thread;

import com.sun.javafx.runtime.SystemProperties;
import exception.MyException;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread implements Runnable{

    protected int count = 10;
    private static int taskCount = 0;
    private final int id = taskCount ++;

    public MyThread(){}
    public MyThread(int count){
        this.count = count;
    }

    public String status(){
        return "#" + id + "(" + (count > 0 ? count : "任务结束") + ")";
    }


    @Override
    public void run() {
        while (count -- > 0){
            System.out.println(status());
            //Thread.yield();
        }
    }
}

class MyCallable implements Callable<String> {

    private static double id;
    public MyCallable(){}

    @Override
    public String call() throws Exception {
        id = Math.random();
        return "产生的id值是" + id;
    }
}

class ThreadTest implements Runnable{
    private   int id = 10;

    @Override
    public void run() {
        /**
         * 在run函数中线程只会只会执行自己的程序，可以对共有任务中的变量进行修改
         * 但是在run函数中定义的局部变量只能有私有的线程进行修改
         * 在run函数中定义的变量只用初始化一次，而不是每次轮到线程执行的时候都会执行一次初始化操作
         */
       int x = 10;
       while (x > 0){
           System.out.println(Thread.currentThread() + "    " + x --);
           Thread.yield();
       }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}

class Counter implements Runnable{
    private int count;

    public Counter() {
        count = 0;
    }

    public  void countAdd() {
        //这里的this的意思是对当前对象的当前代码块进行加锁，只能作用于当前代码块，而不能作用于其他代码块
        {
            for (int i = 0; i < 5; i ++) {
                {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
    public void printCount() {
        for (int i = 0; i < 5; i ++) {
             {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                 try {
                     Thread.sleep(100);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
        }
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            if (threadName.equals("A")) {
                countAdd();
            } else if (threadName.equals("B")) {
                printCount();
            }
        }
    }
}

class ADamon implements Runnable{

    @Override
    public void run() {
        System.out.println("当前线程开始执行");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            System.out.println("这句话一定会执行，如果不执行一定是出错了");
        }
    }
}

public class Client {
    public static void main(String[] args) {
        //Runnable runnable = new MyThread();
//        ExecutorService service = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            service.execute(new MyThread());
//        }
//        service.shutdown();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//
//        }

//        Runnable task = new ThreadTest();
//        for (int i = 0; i < 10; i++) {
//            new Thread(new ThreadTest(),"我的名字是" + i).start();
//        }

//        Thread thread = new Thread(new ADamon());
//        thread.setDaemon(true);
//        thread.start();

        Counter counter = new Counter();
        Thread thread1 = new Thread(counter, "A");
        Thread thread2 = new Thread(counter, "B");
        thread1.start();
        thread2.start();

    }
}
