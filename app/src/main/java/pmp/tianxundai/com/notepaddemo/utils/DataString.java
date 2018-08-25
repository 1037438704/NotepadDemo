package pmp.tianxundai.com.notepaddemo.utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by dell-pc on 2018/8/17.
 */

public class DataString {
    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;
    private static String time;
    private static int month;
    private static int day;

    public static String StringShiJian() {
        //获取当前时间
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int ampm = c.get(Calendar.AM_PM);
        if (ampm == 0){

        }
        time = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        return time;
    }
    //获取当前月份
    public static String StringYue(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);
        return mMonth;
    }
    //获取当前日期
    public static int IntDay(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        // 获取当前年份
        mYear = String.valueOf(c.get(Calendar.YEAR));
        // 获取当前月份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);
        // 获取当前月份的日期号码
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        //星期几
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mYear + "年" + mMonth + "月" + mDay + "日" + "\t星期" + mWay;
    }

}