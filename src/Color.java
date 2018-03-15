/**
 * 多例的设计模式 不允许对象的共有创建 ，只能在初始化的时候创建好
 */
public class Color {

    private static final Color red = new Color("RED");
    private static final Color greed = new Color("GREEN");
    private static final Color blue = new Color("BLUE");

    private String color;

    private Color(String str){
        this.color = str;
    }
    /*
    toString函数返回的类的字符串表示
     */
    public String toString(){
        return this.color;
    }
    public static Color choose(int x){
        switch (x){
            case 0:return red;
            case 1:return greed;
            case 2:return blue;
            default:return null;
        }

    }
}
