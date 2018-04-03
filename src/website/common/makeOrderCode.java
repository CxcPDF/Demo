package website.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成唯一的订单号
 */
public class makeOrderCode {
    public String make() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String temp=format.format(new Date());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<temp.length();i++){
            if(temp.charAt(i)<='9'&&temp.charAt(i)>='0'){
                sb.append(temp.charAt(i));
            }
        }
        return sb.toString();
    }
}
