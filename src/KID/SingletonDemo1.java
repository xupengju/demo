package KID;

/**
 * Created by Milo on 2017/3/9.
 * ¶öººµ¥ÀýÄ£Ê½
 */
public class SingletonDemo1 {

    private static final SingletonDemo1 singleton = new SingletonDemo1();

    private SingletonDemo1() {
    }

    public static SingletonDemo1 getInstance() {

        return singleton;
    }
}
