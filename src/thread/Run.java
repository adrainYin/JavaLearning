package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Run {
    public static void main(String[] args) {
//        PrintString printString = new PrintString();
//        new Thread(printString).start();
//        System.out.println("我要停止它！");
//        printString.setContinueCount(false);
        for (int i = 0; i < 100; i++) {
            new Thread(new ATestThread()).start();
        }
    }
}
