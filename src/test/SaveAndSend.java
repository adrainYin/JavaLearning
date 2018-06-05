package test;

import java.io.*;

/**
 * 这是一个测试用例，测试将对象以流的形式写入文件，再从文件把对象流读取出来
 * 测试发现前后两个对象的hashcode值不一样，证明这两个对象并不是相同的对象
 */
public class SaveAndSend {
    public static void main(String[] args) throws IOException {
        MyObject myObject = MyObject.getInstance();
        //实例化文件输出流和对象流，将对象以流的形式写入文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("myObjectFile.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(myObject);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println(myObject.hashCode());

        FileInputStream fileInputStream = new FileInputStream(new File("myObjectFile.txt"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        try {
            MyObject object = (MyObject) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(object.hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
