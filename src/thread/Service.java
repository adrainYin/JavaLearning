package thread;


public class Service {
    public static synchronized void printA(){
        try {
            System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
                    System.currentTimeMillis() + "调用方法A");
            Thread.sleep(3000);
            System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
                    System.currentTimeMillis() + "离开方法A");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void printB(){
        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
                System.currentTimeMillis() + "调用方法B");
        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
                System.currentTimeMillis() + "离开方法B");
    }

    public synchronized void printC(){
        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
                System.currentTimeMillis() + "调用方法C");
        System.out.println("线程名为" + Thread.currentThread().getName() + "在" +
                System.currentTimeMillis() + "离开方法C");
    }

}
