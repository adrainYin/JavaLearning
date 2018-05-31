package thread;

public class PrintString implements Runnable{
    private boolean isContinueCount = true;

    public boolean isContinueCount() {
        return isContinueCount;
    }

    public void setContinueCount(boolean continueCount) {
        isContinueCount = continueCount;
    }

    public void printStringMethod(){
        try {
        while(isContinueCount == true){
            System.out.println("执行这个计数线程");

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}
