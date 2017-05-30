package KID;

/**
 * Created by Milo on 2017/3/9.
 */
public class ExchangeNumber {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        a = a ^ b; // 001 010  = 011  = 3
        System.out.println(a);
        System.out.println(b);

        b = a ^ b; // 011 010 = 001 = 1
        System.out.println(a);
        System.out.println(b);

        a = a ^ b; // 011 001 =  010 = 2
        System.out.println(a);
        System.out.println(b);

    }
}

