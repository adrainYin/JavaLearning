package thread;


import java.util.Calendar;

public class Run {
    public static void main(String[] args) {
//        PrintString printString = new PrintString();
//        new Thread(printString).start();
//        System.out.println("我要停止它！");
//        printString.setContinueCount(false);
//        for (int i = 0; i < 100; i++) {
//            new Thread(new ATestThread()).start();
//        }
        Service service = new Service();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "调用的时间为" +
            System.currentTimeMillis());
            service.waitMethod();
        };

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "调用的时间为" +
                    System.currentTimeMillis());
            service.waitMethod();
        };

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable);
        thread.setName("A");
        thread1.setName("B");
        thread2.setName("C");
        thread.start();
        thread2.start();
        thread1.start();


    }
}
