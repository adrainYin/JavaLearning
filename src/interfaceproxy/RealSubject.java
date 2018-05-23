package interfaceproxy;

public class RealSubject implements Subject{
    @Override
    public void fun(int x, int y) {
        System.out.println(x+y);
    }
}
