import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xpj on 2016/10/24.
 */
public class TestDate {

    public static void main(String[] args) throws ParseException {
        String startTime = "2016-01-01 11:11:11";
        String endTime = "2017-09-01 11:11:11";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
       // System.out.println(sdf.parse(startTime));


       // System.out.println(getActivityStatus(sdf.parse(startTime),sdf.parse(endTime)));
        System.out.println(sdf.format(new Date(1477557532000l)));

        byte[] time = new byte[]{49,52,55,55,53,53,56,53,51,56};
        System.out.println(new String(time));

    }



    public static  String getActivityStatus(Date startTime, Date endTime) {
        Date data = new Date();
        if (startTime.before(data) && data.before(endTime)) {
            //��ʼʱ���ڵ�ǰʱ��֮ǰ ���� ��ǰʱ���ڽ���ʱ��֮�� proceed
            return "proceed";
        } else if (startTime.after(data) && endTime.after(data)) {
            //��ʼʱ���ڵ�ǰʱ��֮�� ���� ����ʱ���ڵ�ǰʱ��֮��  wait
            return "wait";
        } else {
            // ����
            return "overdue";
        }
    }

}
