package exception;

/**
 * 测试demo
 * 如果在异常处理程序中没有处理异常而是继续抛出RuntimeException异常，那么在没有继续处理异常的情况下程序会直接运行到
 * main()方法，并且自动调用printStackTrace()方法输出错误路径。
 * 错误信息按照栈的方法打印，输出异常抛出的代码行，直到输出到main()方法
 *
 * 使用场景：变成错误 ，比如空指针异常或者数组越界。那么直接抛出RuntimeException异常可以直接不用处理而直接输出到
 * 控制台定位错误的代码行
 */
public class NeverCaught {
    public static void f(){
        throw new RuntimeException("from f()");
    }

    public static void g(){
        f();
    }

    public static void main(String[] args) {
        g();
    }
}
