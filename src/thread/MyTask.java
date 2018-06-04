package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTask {
    private static Timer timer = new Timer();
    private static int count = 0;

    public static class MyTask1 extends TimerTask {
        @Override
        public void run() {
            System.out.println("begin运行了" + new Date());
            try {
                Thread.sleep(2000);
                System.out.println("end运行了" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask1 myTask1 = new MyTask1();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**
         * 当方法延时的时候函数策略和schedule是一样的
         * 当开始时间在定义时间之后时，就认为该调度是延时的
         */
        String currdate = "2018-06-04 15:30:00";
        try {
            Date date = (Date) simpleDateFormat.parseObject(currdate);
            timer.scheduleAtFixedRate(myTask1, date, 3000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
