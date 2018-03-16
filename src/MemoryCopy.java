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
        String data[] = new String[file.length];
        for (int i = 0; i < file.length; i++) {
            data[i] = readFile(file[i]);
        }
        StringBuffer stringBuffer = new StringBuffer();
        String dataA[] = data[0].split(" ");
        String dataB[] = data[1].split(" ");
        for (int i = 0; i < dataA.length; i++) {
            stringBuffer.append(dataA[i]).append("(").append(dataB[i]).append(")").append(" ");
        }
        System.out.println(stringBuffer);
    }
}
