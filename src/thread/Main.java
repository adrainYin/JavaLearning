package thread;

public class Main {

    public static void main(String[] args) {
        ATestThread aTestThread = new ATestThread();
        aTestThread.start();
        System.out.println("我想在子线程睡眠结束后再结束主线程，但是我不知道子线程要运行多久时间");
        //join()的作用是使得调用该函数的线程正常执行run()方法，而在哪个线程中调用的线程要无限等待直到
        //正常完成run()函数的线程销毁
        try {
            aTestThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
