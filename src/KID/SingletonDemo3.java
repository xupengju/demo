package KID;

/**
 * Created by Milo on 2017/3/9.
 * ���Ч�ĵ���ģʽ
 */
public class SingletonDemo3 {
    private SingletonDemo3() {
    }

    private static volatile SingletonDemo3 singletonDemo3;

    public synchronized SingletonDemo3 getInstance() {
        if (singletonDemo3 == null) {
            return new SingletonDemo3();
        } else {
            return singletonDemo3;
        }
    }
}
