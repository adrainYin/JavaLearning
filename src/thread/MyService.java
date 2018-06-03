package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    /**
     * ReentrantLock是互斥锁对象，对synchronized关键字提供了良好的扩展，支持公平锁和非公平锁
     */
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void awaitA(){
        try {
            lock.lock();
            System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName());
            conditionA.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB(){
        lock.lock();
        System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName());
        try {
            conditionB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void singalA(){
        lock.lock();
        try {
            System.out.println(System.currentTimeMillis() + "  " + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalB(){
        lock.lock();
        try {
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
