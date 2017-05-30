package KID;

/**
 * Created by Milo on 2017/3/9.
 * 饱汉单例模式
 */
public class SingletonDemo2 {
    private SingletonDemo2() {
    }

    private static SingletonDemo2 singletonDemo2;

    //线程不安全 多线程访问会重复初始化对象
    public static SingletonDemo2 getInstance() {
        if (singletonDemo2 == null) {
            return new SingletonDemo2();
        } else {
            return singletonDemo2;
        }

    }

    public static synchronized  SingletonDemo2 getInstance2(){
        if(singletonDemo2 == null){
            return new SingletonDemo2();
        }else{
            return singletonDemo2;
        }
    }




}
