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

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class test1 {
    public static void main(String[] args) {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123

    }
}
