import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by xpj on 2016/10/28.
 */
public class TestString {


    public static void main(String[] args) {
        String s = "<<";
        try {
            String s1 = URLEncoder.encode(s,"UTF-8");
            System.out.println(s1);
            System.out.println(URLDecoder.decode(s1,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
