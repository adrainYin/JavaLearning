package exception;

public class MyException extends Exception{
    public static void main(String[] args) {
        try {
            //直接抛出异常
            throw new Exception("My Exception");
        } catch (Exception e) {
            System.out.println("Caught Exception");
            //得到异常信息
            System.out.println(e.getMessage());
            //得到异常信息
            System.out.println(e.toString());
            //将异常信息的栈轨迹输出到打印流
            e.printStackTrace(System.out);
            System.out.println("*********我是一条分割线************");
            StackTraceElement[] stackTraceElements  = e.getStackTrace();
            for (int i = 0; i < stackTraceElements.length; i++) {
                System.out.println(stackTraceElements[i].toString());
                System.out.println(stackTraceElements[i].getLineNumber());
                System.out.println(stackTraceElements[i].getMethodName());
            }
            try {
                /**
                 * 在异常处理程序中将异常信息再向上一级环境的异常处理程序中再抛
                 */
                throw e;
            } catch (Exception e1) {
                System.out.println("e1的异常处理程序");
                Throwable throwable =  e1.fillInStackTrace();
                throwable.printStackTrace();
            }

        }

    }
}


