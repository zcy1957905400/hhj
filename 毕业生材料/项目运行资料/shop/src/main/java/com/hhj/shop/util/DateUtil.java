package com.hhj.shop.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 输出指定日期的前一个月的每一天
     *
     * @param date_str 时间戳
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static List<String> date2OneMonth(String date_str, String format) {
        List<String> MonthDateList = new ArrayList<>();
        //String str = "20150623";      //指定日期
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);   //日期格式化
            Date date = sdf.parse(date_str);
            calendar.setTime(date);
            calendar.add(calendar.DAY_OF_MONTH, 1);    //得到当前日期减一个月的时间点
            //calendar.add(calendar.DAY_OF_YEAR, -1);
            Date enddate = calendar.getTime();
            //System.out.println(enddate.toString());

            calendar.setTime(date);
            calendar.add(calendar.MONTH, -1);    //得到当前日期减一个月的时间点

            //Date begindate = calendar.getTime();

            while (calendar.getTime().before(enddate)) {
                String date1 = sdf.format(calendar.getTime());
                //System.out.println(date1);
                MonthDateList.add(date1);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return MonthDateList;
    }

//    public static void main(String[] args) {
//        Long startTs = System.currentTimeMillis() / 1000; // 当前时间戳
//        //String.valueOf(startTs);
//        String date_str = timeStamp2Date(String.valueOf(startTs), "yyyy-MM-dd");
//        List<String> date2 = date2OneMonth(date_str, "yyyy-MM-dd");
//        List<List<String>> d = new ArrayList<>();
//        for (String d1 : date2) {
//            List<String> stringList = new ArrayList<>();
//            stringList.add(d1);
//            stringList.add("20.00");
//            d.add(stringList);
//        }
//        System.out.println(d.toString());
//    }

}
