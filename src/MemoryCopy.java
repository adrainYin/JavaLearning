import java.io.*;

public class MemoryCopy {

    public static String readFile(File file) throws IOException{
        if (file.exists()){
            InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte data[] = new byte[10];
            int len;
            while ((len = inputStream.read(data))!= -1){
                byteArrayOutputStream.write(data , 0 , len);
            }
            byteArrayOutputStream.close();
            inputStream.close();
            return new String(byteArrayOutputStream.toByteArray());
        }
        return null;
    }


    public static void main(String args[]) throws IOException{
        File file[] = new File[]{
                new File("src" + File.separator + "testA.txt"),
                new File("src" + File.separator + "testB.txt")
        };
//        String data[] = new String[file.length];
////        for (int i = 0; i < file.length; i++) {
////            data[i] = readFile(file[i]);
////        }
////        StringBuffer stringBuffer = new StringBuffer();
////        String dataA[] = data[0].split(" ");
////        String dataB[] = data[1].split(" ");
////        for (int i = 0; i < dataA.length; i++) {
////            stringBuffer.append(dataA[i]).append("(").append(dataB[i]).append(")").append(" ");
////        }
////        System.out.println(stringBuffer);
        //打印流
//        /**
//         * PringStream打印流只能接收OutputStream对象，并且不能打印二进制数据
//         */
//        PrintStream printStream = new PrintStream(new FileOutputStream(file[0]));
//        printStream.println(1234);
//        printStream.println("hello world");
//
//        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file[1]));
//        printWriter.write(12346);
//        printWriter.write("\r\n");
//        printWriter.write("nihaoshijie!!!");

        OutputStream outputStream = System.out;
        String string = "hello world";
        byte data[] = string.getBytes();
        outputStream.write(data);

        InputStream inputStream = System.in;
        StringBuffer stringBuffer = new StringBuffer();
        int temp;
        while ((temp = inputStream.read()) != -1){
            if (temp == '\n'){
                break;
            }

            stringBuffer.append((char) temp);
        }
        System.out.println(stringBuffer);
    }
}
