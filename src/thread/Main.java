package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThreadA threadA = new ThreadA();
        threadA.start();
        Thread.sleep(100);
        for (int i = 0; i < 10; i++) {
            System.out.println("在主线程中取得" + Tools.inheritableThreadLocalExp.get());
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Lock lock = new ReentrantLock();

    }
}
