package utils;

public class StringUtil {

    private StringUtil(){}
    public static String UpToCase(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }
}
