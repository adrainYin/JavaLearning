package thread;

public class ThreadA extends Thread{
    private Service service;

    public ThreadA(Service service){
        this.service = service;
    }

    /**
     * 这里用的是类的实例化对象进行方法的调用，但是因为是静态方法，所以加的是类锁
     */
    @Override
    public void run() {
        Service.printA();
    }
}
