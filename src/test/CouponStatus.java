package test;

/**
 * Created by Milo on 2017/3/24.
 */
public enum CouponStatus {
    unused("过期"),
    access("可用"),
    used("已用"),
    overdue("过期"),
    delete("删除");

    private String state;

    CouponStatus(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }
}


class testThis{
    public static void main(String[] args) {
        System.out.println(CouponStatus.access.name());
        System.out.println(CouponStatus.access.getState());
    }
}

