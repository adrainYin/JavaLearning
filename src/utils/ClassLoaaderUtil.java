package utils;

import java.io.*;

public class ClassLoaaderUtil extends ClassLoader{

    /**
     * 实现类的加载，返回加载后的class类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public  Class<?> loadClass1(String name) throws Exception {
        byte classData[] = this.loadCLassData();
        return super.defineClass(name,classData,0,classData.length);
    }


    @Override
    protected Class<?> findClass(String name) {
        try {
            byte classData[] = this.loadCLassData();
            return super.defineClass(name,classData,0,classData.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 工具类，读取class文件中的所有数据
     * @return
     * @throws IOException
     */
    private byte[] loadCLassData() throws IOException {
        //程序定义死，之后可以根据情况实时调整
        InputStream inputStream = new FileInputStream("D:" + File.separator + "Person.class");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte data[] = new byte[10];
        int temp;
        while ((temp = inputStream.read(data)) != -1){
            byteArrayOutputStream.write(data,0,temp);
        }
        byte result[] = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        inputStream.close();
        return result;
    }

    public static void main(String[] args) throws Exception {
        //此处需要实例化对象来输出内容
       System.out.println(new ClassLoaaderUtil().findClass("unity.Person").newInstance());
    }
}
