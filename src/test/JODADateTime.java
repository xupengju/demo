package test;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Milo on 2017/2/24.
 */
public class JODADateTime {
    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        cal2.setTime(new Date());
        DateTime dateTime = new DateTime(cal.getTime());
        DateTime dateTime2 = new DateTime(new Date().getTime());
        System.out.println(Seconds.secondsBetween(dateTime2,dateTime).getSeconds() % 60+ " ");


        DateTime startTime = new DateTime();
        DateTime endTime = startTime.hourOfDay().addToCopy(-2);
        System.out.println(startTime);
        System.out.println(endTime);




    }
}
