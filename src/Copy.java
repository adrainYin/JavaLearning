import java.io.*;

/**
 * 定义拷贝文件工具类，而且只定义方法。
 * 方法全部定义为静态方法，直接调用
 */
class CopyUtil{
    private CopyUtil(){}

    /**
     * 判断原文件是否存在
     * @param inFilePath
     * @return 布尔值
     */
    public static boolean fileIsExist(String inFilePath){
        return new File(inFilePath).exists();
    }

    /**
     * 判断目标文件的目录是否存在，如果不存在则直接新建目录
     * @param outFilePath
     */
    public static void OutFileIsExist(String outFilePath){
        File outFile = new File(outFilePath);
        if (! outFile.getParentFile().exists()){
            outFile.getParentFile().mkdirs();
        }
    }

    /**
     * 拷贝对象资源的创建
     * @param inFilePath
     * @param outFilePath
     */
    public static boolean copyProgram(String inFilePath , String outFilePath) throws IOException{
        long start = System.currentTimeMillis();
        boolean isCopy = false;
        File inFile = new File(inFilePath);
        File outFIle = new File(outFilePath);
        /*
        声明对象，在try/catch中进行对象的创建，最后调用finally方法关闭。
        是处理资源操作的常规用法：IO操作，网络连接，数据库连接等
         */
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(inFile);
            outputStream = new FileOutputStream(outFIle);
            copy(inputStream,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                isCopy = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("程序执行时间为：" + (end - start));
        return isCopy;
    }

    /**
     * 文件拷贝方法
     * @param inputStream
     * @param outputStream
     * @return 返回true则意味着文件拷贝完成
     * @throws IOException
     */
    public static void copy(InputStream inputStream , OutputStream outputStream) throws IOException{
        //boolean isCopyComplete = false;
        int len; // 动态显示当前数组长度，如果为-1则表示文件读取完毕
        byte [] data = new byte[2048];
        while ((len = inputStream.read(data)) != -1){
            outputStream.write(data,0,len);
        }
        System.out.println("文件拷贝完成");
        //isCopyComplete = true;
        //return isCopyComplete;
    }


}





public class Copy {
    public static void main(String args[]) throws IOException{

        //判断外部参数的正确性，拷贝模型对应的外部参数是原文件路径和目标问津路径
        if(args.length < 2){
            System.out.println("格式错误，正确的格式为： 原文件路径   目标文件路径");
            System.exit(1);
        }
        /*
        如果原文件存在，那么开始正常的拷贝程序
        如果原文件不存在，那么提示错误
         */
        if (CopyUtil.fileIsExist(args[0])){
            CopyUtil.OutFileIsExist(args[1]);
        }else {
            System.out.println("原文件不存在");
        }
        CopyUtil.copyProgram(args[0] , args[1]);



    }
}
