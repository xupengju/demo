package test;

/**
 * Created by Milo on 2017/3/24.
 */
public enum CouponStatus {
    unused("55"),
    access("444"),
    used("333"),
    overdue("222"),
    delete("111");

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

