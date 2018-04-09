import javax.swing.text.Segment;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Person implements java.util.Observer{

    /*
    o是观察的对象，arg是观察的内容
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof House){
            if (arg instanceof Double){
                System.out.println("房价上涨了，现在的价格为" + arg);
            }
        }
    }
}

class House extends Observable{
    private double price;
    public House(double price){
        this.price = price;
    }
    public void setPrice(double price){
        if (this.price < price){
            super.setChanged(); //提醒父类，内容发生了改变
            super.notifyObservers(price);//唤醒所有的观察者
            this.price = price;
        }
    }


}
public class Test {
    public static void main(String args[]) throws IOException{
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        House house = new House(80000.0);
        house.addObserver(p1);
        house.addObserver(p2);
        house.addObserver(p3);
        house.setPrice(100000.0);







//        File file =new File("src" + File.separator + "hello.txt");
//        if (file.exists()){
//            Reader reader = new FileReader(file);
//            char [] data = new char[1024];
//            int len = reader.read(data);
//            System.out.println(new String(data,0,len));
//        }
//        List<String> list = new ArrayList<>();
//        list.add("java");
//        list.add("javascript");
//        list.add("python");
//        list.add("C");
//        list.add("C++");
//        //取得Stream流
//        Stream<String>  stream= list.stream();
//        //使用过滤器取得符合条件的所有元素，并且用collect方法，将元素收集到一个集合中
//        List<String> strings =  stream.filter((e) -> e.contains("a")).collect(Collectors.toList());
//        //用foreach方法对集合进行操作
//        //用labbda表达式
//        strings.forEach((x) -> {
//                    x += "nihao";
//                    System.out.println(x);
//                }
//            );




        //        String string = "WWgj3-_.fsw2@fjf-._.com.cn";
//        String reg = "[a-zA-Z][a-zA-Z\\._\\-0-9]{5,14}@[a-zA-Z\\._\\-0-9]+\\.(com|cn|com\\.cn|gov|org)";
//        Pattern pattern = Pattern.compile(reg);
//        Matcher matcher = pattern.matcher(string);
//        System.out.println(matcher.matches());
    }
    public static void listAllFile(File file){
        if ( file.isDirectory()){
            File listFile[] = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                listAllFile(listFile[i]);
            }
        }
        System.out.println(file.getName());
    }
}
class MyMessage{
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

class MessageConsumer{
    public void print(){
        System.out.println(Thread.currentThread().getName() + "   " + MyUtil.getMessage().getNote());
    }
}

class MyUtil {

    // public static MyMessage myMessage;
    public static ThreadLocal<MyMessage> threadLocal = new ThreadLocal<>();
    public static MyMessage getMessage(){
        return threadLocal.get();
    }
    public static void setMessage(MyMessage message){
        threadLocal.set(message);
    }
}
