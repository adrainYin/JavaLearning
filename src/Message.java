public interface Message<T> { //接口定义的泛型，不需要再重载函数

    public void print(T t);
}
