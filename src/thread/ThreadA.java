package thread;

public class ThreadA extends Thread{
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("在子线程中取值" + Tools.inheritableThreadLocalExp.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
