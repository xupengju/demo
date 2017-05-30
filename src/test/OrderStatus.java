package test;


import java.io.Serializable;

/**
 * Created by Fechin on 2017/1/4.
 */
public enum OrderStatus implements Serializable {
    unpaid("������"),
    unfill("������"),
    unshipped("������"),
    unreceived("���ջ�"),
    uncomment("������"),
    unopenfill("�Ѹ�����"),
    finished("�������"),
    closed("���׹ر�"),
    frozen("����"),
    fail("����ʧ��"),
    cancel("����ȡ��")

    ;

    public String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
