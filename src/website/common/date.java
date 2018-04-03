package website.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * 将日期格式化
 */
public class date {

    public date(){}

    public String dateFormat(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String str=format.format(date);
        System.out.println(str);
        return str;
    }

    public Vector dateChange(String startTime, String endTime){
        Vector v=new Vector();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        long temp=0;
        try {
            Date begin=format.parse(startTime);
            Date end=format.parse(endTime);
            temp=(end.getTime())-(begin.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month= (int) (temp/(24*60*60*1000*31));
        int day=(int)(temp/(60 * 60 * 1000*24) - month * 30);
        v.add(month);
        v.add(day);
        return v;
    }
}
