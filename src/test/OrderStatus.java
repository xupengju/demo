package test;


import java.io.Serializable;

/**
 * Created by Fechin on 2017/1/4.
 */
public enum OrderStatus implements Serializable {
    unpaid("11"),
    unfill("22"),
    unshipped("33"),
    unreceived("44"),
    uncomment("5"),
    unopenfill("6"),
    finished("7"),
    closed("8"),
    frozen("9"),
    fail("10"),
    cancel("11")

    ;

    public String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
