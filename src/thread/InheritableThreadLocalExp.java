package thread;

import java.util.Date;

public class InheritableThreadLocalExp extends InheritableThreadLocal{
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + "这个是父线程在子线程汇中加的";
    }
}
