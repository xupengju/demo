package test;

/**
 * Created by Milo on 2017/3/24.
 */
public enum CouponStatus {
    unused("����"),
    access("����"),
    used("����"),
    overdue("����"),
    delete("ɾ��");

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

