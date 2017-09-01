package J8;

/**
 * Created by Milo on 2017/5/28.
 */
public class StirngTest {
    public static void main(String[] args) {

        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);
    }
}

