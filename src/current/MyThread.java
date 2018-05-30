package current;

import java.util.ArrayList;
import java.util.List;

/**
 * 把握几个重点：stop变量、run方法中的sleep()、interrupt()、InterruptedException。串接起
 * 来就是这个意思：当我们在run方法中调用sleep（或其他阻塞线程的方法）时，如果线程阻塞的
 * 时间过长，比如10s，那在这10s内，线程阻塞，run方法不被执行，但是如果在这10s内，stop被
 * 设置成true，表明要终止这个线程，但是，现在线程是阻塞的，它的run方法不能执行，自然也就
 * 不能检查stop，所 以线程不能终止，这个时候，我们就可以用interrupt()方法了：我们在
 * thread.stop = true;语句后调用thread.interrupt()方法， 该方法将在线程阻塞时抛出一个中断
 * 信号，该信号将被catch语句捕获到，一旦捕获到这个信号，线程就提前终结自己的阻塞状态，这
 * 样，它就能够 再次运行run 方法了，然后检查到stop = true，while循环就不会再被执行，在执
 * 行了while后面的清理工作之后，run方法执行完 毕，线程终止。
 */
class DemoThread extends Thread{
    public boolean isStop = false;

    @Override
    public void run() {
        super.run();
        while (! isStop){
            System.out.println("线程还在运行");
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                /**
                 * 在第二次休眠的时候，外界main函数给出了中断信号，那么该线程会立即结束休眠状态
                 * 从而继续执行while语句。
                 * 因为外界对共享变量isStop做出了修改，所以不满足判定条件直接退出while循环
                 */
                System.out.println("线程中断");
                e.printStackTrace();
            }
        }
        System.out.println("线程退出");
    }
}


public class MyThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DemoThread();
        thread.start();
        Thread.sleep(3000);
        System.out.println("让线程停止");
        thread.interrupt();
        //((DemoThread) thread).isStop = true;
    }
}
