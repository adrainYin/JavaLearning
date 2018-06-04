package thread;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Service {
//    public static synchronized void printA(){
//        try {
//            System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
//                    System.currentTimeMillis() + "调用方法A");
//            Thread.sleep(3000);
//            System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
//                    System.currentTimeMillis() + "离开方法A");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static synchronized void printB(){
//        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
//                System.currentTimeMillis() + "调用方法B");
//        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
//                System.currentTimeMillis() + "离开方法B");
//    }
//
//    public synchronized void printC(){
//        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
//                System.currentTimeMillis() + "调用方法C");
//        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
//                System.currentTimeMillis() + "离开方法C");
//    }

    /**
     * 测试getQueueLength(Condition condition)方法
     * boolean hasQueueThread(Thread thread)测试当前线程是否在等待锁
     * boolean hasQueueThreads()测试是否有线程在等待此锁
     */
    private ReentrantLock lock = new ReentrantLock();//如果不加boolean值，那么默认new的锁是非公平锁
    private Condition condition = lock.newCondition();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    /**
     * timer定时器，当Timer用构造器初始化的时候就会new一个新线程，使用该线程进行任务的调度运行
     */
    private Timer timer = new Timer();

    /**
     * 清除Timer调度的所有计划
     */
    public void cancel(){
        timer.cancel();
    }
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
        //清除该调度类的计划v
            timerTask.cancel();
        }
    };

//    public void waitMethod(){
//        lock.lock();
//        try {
//            condition.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void notofyMethod(){
//        try {
//            lock.lock();
//            System.out.println("有" + lock.getWaitQueueLength(condition) + "个线程在等待condition的notify");
//            condition.notify();
//        } finally {
//            lock.unlock();
//        }
//    }

    public void waitMethod(){
        try {
            /**
             * 如果第一次锁没有争用则立即会获得锁，如果锁被用则会等待一定时间
             * 等待时间内线程处于阻塞状态，如果阻塞期间能获得锁则立即获得结束阻塞
             * 若期间内没有获得锁则立即结束阻塞返回false
             * 若等待等待期间中断则立即抛出中断异常
             */
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println("  " + Thread.currentThread().getName() + "获得锁的时间为" +
                        System.currentTimeMillis());
                Thread.sleep(10000);
            } else {
                System.out.println("   " + Thread.currentThread().getName() + "没有获得锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }



}
