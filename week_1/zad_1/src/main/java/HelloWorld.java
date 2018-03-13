import org.apache.commons.lang3.StringUtils;

public class HelloWorld {
    public static void main(String[] args) {
        String message = "Hello world!";
        System.out.println(StringUtils.center(message, 30));
    }
}