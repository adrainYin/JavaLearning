package thread;

public class ATestThread extends Thread{
    @Override
    public void run() {
        int secondValue = (int)(Math.random() * 10000);
        System.out.println(secondValue);
        try {
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
