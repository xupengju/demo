package KID;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Milo on 2017/3/9.
 */
public class ExchangeStr {
    public static void main(String[] args) {
        String s = "abc def 123";

        String str = "abc";
        StringBuilder stringBuilder = new StringBuilder(str);


        char[] strs = s.toCharArray();
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
            linkedList.addFirst(strs[i]);
        }
        System.out.println(linkedList.toString().replace(",",""));

        stringBuilder.reverse();
        System.out.println(stringBuilder.toString());




    }
}
