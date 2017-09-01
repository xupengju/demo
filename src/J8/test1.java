package J8;

/**
 * Created by Milo on 2017/9/1.
 */
class test1 {
    public static void main(String[] args) {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123

    }
}
