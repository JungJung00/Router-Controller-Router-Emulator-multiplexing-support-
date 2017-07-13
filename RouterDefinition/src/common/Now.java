package common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jeong Taegyun on 2017-04-26.
 */
public class Now {
    public static String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("[yy.MM.dd hh:mm:ss]");
        return f.format(new Date());
    }
}
