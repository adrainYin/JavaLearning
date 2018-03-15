import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Data{
    private String title;
    private String name;
    private boolean isLock = false;

    public synchronized void set(String title,String name){
        for (int i = 0; i < 20; i++) {

            this.title = title;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.name = name;
        }
    }


    public synchronized void get(){
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(50);
                System.out.println(this.getTitle() + "    " + this.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class DataProvider implements Runnable {

    private Data data;

    public DataProvider(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                try {
                    data.setTitle("你好");
                    Thread.sleep(100);
                    data.setName("hello");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    data.setTitle("不好");
                    Thread.sleep(100);
                    data.setName("真的不好");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

class DataConsumer implements Runnable{
    private Data data;
    public DataConsumer(Data data){
        this.data = data;
    }
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(50);
                System.out.println(data.getTitle() + "  " + data.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class Main {

    public static void main(String args[]){
//        Data data = new Data();
//        //此时的data是唯一的
//        new Thread(new DataProvider(data)).start();
//        new Thread(new DataConsumer(data)).start();
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Message",locale);
        System.out.println(resourceBundle.getString("hello"));

    }
}