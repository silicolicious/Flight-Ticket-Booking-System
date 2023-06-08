package utils;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeFormat{
    public static Timestamp stringToTimestamp(String timeString){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date date = formatter.parse(timeString);
            return new Timestamp(date.getTime());
        } catch(Exception e){
            System.out.println("Invalid time format");
        }
        return null;
    }
}
