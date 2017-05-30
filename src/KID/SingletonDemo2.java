package KID;

/**
 * Created by Milo on 2017/3/9.
 * ��������ģʽ
 */
public class SingletonDemo2 {
    private SingletonDemo2() {
    }

    private static SingletonDemo2 singletonDemo2;

    //�̲߳���ȫ ���̷߳��ʻ��ظ���ʼ������
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
