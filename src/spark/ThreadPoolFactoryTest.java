package spark;

/**
 * Created by Milo on 2017/5/1.
 */
public class ThreadPoolFactoryTest {

    public static void main(String[] args) {

        //异步处理 更新粉丝的新动态通知
        ThreadPoolFactory.getFixedInstance().execute((Runnable) () -> {

//            for (int i = 0; i < 20; i++) {
//                System.out.println(i);
//            }
            System.out.println("1111");
        });

        System.out.println("s");
    }
}
