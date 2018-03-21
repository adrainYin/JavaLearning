package agent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

//定义操作接口，在用户真是进行操作的时候只能看到该接口，而具体的方法实现类将会对用户隐藏
interface Subject{
    public void eat(String name,int num);
}



//具体的方法实现类，对于用户来说该类是隐藏的。
//在具体的调用关系中，将由动态代理类来实例化一个接口伪对象，从而调用真实类的方法，实现业务逻辑
class RealSubject implements Subject{
    @Override
    public void eat(String name, int num) {
        System.out.println("吃" + name + num + "份");
    }
}

/**
 * 动态代理类，可以代理所有的接口操作。核心是将具体的接口与动态代理生成的伪对象接口进行绑定
 */
class ProxySubject implements InvocationHandler{
    //定义一个Object对象，将作为动态代理生成的伪对象
    private Object realobject;

    public Object bind(Object object){
        //保存真实的对象
        this.realobject = object;
        /**
         * 核心方法，是Proxy类中的newProxyInstance方法（静态方法，可以直接调用，返回绑定后的伪对象）
         * @param CLassLoader 类加载器，实现类对象对应的类加载器
         * @param Interfaces[] 代理所需要实现的接口
         * @param InvocationHandler 表示一个InvocationHander 因为此动态代理类直接实现了该接口，所以可以用this指代
         * 注意，这个this很重要，应为这个this使得该类会自动调用覆写的invoke方法，使得proxy和method和Object[]自动注入
         * @return Oject 返回值就是生成的动态代理的伪对象，但是可以调用具体对象的具体方法
         */
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    /**
     * 核心业务实现方法
     * @param proxy 生成的代理的伪对象
     * @param method 接口的具体实现类的方法
     * @param args 方法接收的所有的参数
     * @return 方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(realobject,args);
        return null;
    }
}
public class Agent {
    public static void main(String[] args){
        Subject subject = (Subject) new ProxySubject().bind(new RealSubject());
        subject.eat("好吃的",100);

    }
}
