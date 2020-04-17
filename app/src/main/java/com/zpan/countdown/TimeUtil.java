package com.zpan.countdown;

import android.annotation.SuppressLint;
import android.content.Context;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间转换工具类
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtil {

    /**
     * 获取当前秒数
     */
    public static long getCurrentMiles() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentDisplayTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm");

        Date curDate = new Date(System.currentTimeMillis());

        return formatter.format(curDate);
    }

    public static String getCurrentTime() {
        String tempTime = getCurrentDisplayTime();

        return tempTime.substring(tempTime.lastIndexOf(" ") + 1, tempTime.length());
    }

    /**
     * 获取当前年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某个时间的年份
     */
    public static int getYearWithTime(long dateLong) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dateLong));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取某个时间的月份
     */
    public static int getMonthWithTime(long dateLong) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dateLong));
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取某个时间的日期
     */
    public static int getDayWithTime(long dateLong) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dateLong));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断两个时间是否是同一天 毫秒数
     */
    public static boolean twoDateIsTheSame(long firstLong, long secendLong) {
        return getYearWithTime(firstLong) == getYearWithTime(secendLong)
            & getMonthWithTime(firstLong) == getMonthWithTime(secendLong)
            & getDayWithTime(firstLong) == getDayWithTime(secendLong);
    }

    /**
     * 消息中心 时间格式化
     */
    public static String messageListFormatTime(long current, long time) {
        Date timeDate = new Date(time);
        if (twoDateIsTheSame(current, time)) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "今天 " + sdf.format(timeDate);
        }
        if (twoDateIsTheSameWeak(time)) {
            return getWeekWithTime(timeDate);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(timeDate);
    }

    /**
     * 判断某个时间（毫秒）是否是本周
     */
    public static boolean twoDateIsTheSameWeak(long time) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return time >= c.getTimeInMillis();
    }

    /**
     * 毫秒转换为星期一 11：23格式
     */
    public static String getWeekWithTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE  HH:mm");
        String week = sdf.format(date);
        return week;
    }

    /**
     * 秒转换为指定格式的日期
     */
    private String secondToDate(long second, String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }

    /**
     * 获取当前时间  HH:mm
     */
    public static String getHHmmTimeString(long dateLong) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        Date targetDate = new Date(dateLong);

        return formatter.format(targetDate);
    }

    /**
     * 获取当前时间  HH:mm
     */
    public static String getHHmmTimeString(long dateLong, boolean needMonth, boolean needDay) {
        SimpleDateFormat formatter;
        if (needMonth) {
            formatter = new SimpleDateFormat("MM月dd日 HH:mm");
        } else if (needDay) {
            formatter = new SimpleDateFormat("dd日 HH:mm");
        } else {
            formatter = new SimpleDateFormat("HH:mm");
        }

        Date targetDate = new Date(dateLong);

        return formatter.format(targetDate);
    }

    /**
     * 格式：yyyy-MM-dd HH:mm
     */
    public static String getyyyyMMddHHmmTimeString(long dateL) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm");

        Date targetDate = new Date(dateL);

        return formatter.format(targetDate);
    }

    /**
     * 格式：yyyy-MM-dd HH:mm
     */
    public static String getyyyyMMddString(long dateL) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

        Date targetDate = new Date(dateL);

        return formatter.format(targetDate);
    }

    /**
     * MM-dd   HH:mm:ss
     */
    public static String getMMddHHmmssTimeString(long dateL) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd   HH:mm:ss");

        Date targetDate = new Date(dateL);

        return formatter.format(targetDate);
    }

    /**
     * yyyy-MM-dd   HH:mm
     */
    public static String getyyyyMMddHHmmTimeString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm");
        return formatter.format(date);
    }

    /**
     * yyyy.MM.dd
     */
    public static String getyyyyMMddTimeString(long dateL) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date targetDate = new Date(dateL);
        return formatter.format(targetDate);
    }

    /**
     * yyyy-MM-dd
     */
    public static String getyyyyMMddTimeString(Date targetDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(targetDate);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getyyyyMMddHHmmssTimeString(long dateL) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date targetDate = new Date(dateL);

        return formatter.format(targetDate);
    }

    public static String getCurrentyyyyMMddHHmmssTimeString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date targetDate = new Date(System.currentTimeMillis());

        return formatter.format(targetDate);
    }

    /**
     * 获取日期
     */
    public static Date getDateTime(int year, int month, int day, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hourOfDay, minute, second);
        return calendar.getTime();
    }

    public static Date getDateTime(int year, int month, int day) {
        return getDateTime(year, month, day, 0, 0, 0);
    }

    /**
     * MM-dd HH:mm"
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateToMessageTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(new Date(time));
    }

    /**
     * 获取秒数
     */
    public static String getVoiceTime(int time) {
        int second = time / 1000;
        if (second <= 0) {
            second = 1;
        }
        return String.valueOf(second) + "\"";
    }

    /**
     * 获取当今天0点时间戳
     */
    public static long getTodayStartTime() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar1.getTime();
        long startTime = startDate.getTime();
        return startTime;
    }

    /**
     * 获取日期字符串
     */
    public static String getDateString(String pattern, long milliseconds) {
        if (0 == milliseconds) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(milliseconds));
    }

    public static Calendar getDate2Calendar(long milliseconds) {
        Date date = new Date(milliseconds);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Get date ("xxxx年xx月xx日")
     */
    public static String getDate2CHString(Context context, long milliseconds) {
        Calendar calendar = getDate2Calendar(milliseconds);
        return context.getString(R.string.date_ch_template, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取日期 xxxx年xx月xx日
     *
     * @param hasDayOfMonth false :  xxxx年xx月   true : xxxx年xx月xx日
     */
    public static String getDate2CHString(Context context, boolean hasDayOfMonth, long milliseconds) {
        if (hasDayOfMonth) {
            return getDate2CHString(context, milliseconds);
        }

        Calendar calendar = getDate2Calendar(milliseconds);
        return context.getString(R.string.date_ch_no_day_template, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 获取给定日期的第二天日期
     */
    public static Date getNextNDate(Date date, int n) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, n);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();

        return date;
    }

    /**
     * 返回日期字符串
     *
     * @param format 如yyyy-MM-dd HH:mm:ss
     */
    public static String getDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 返回日期字符串
     *
     * @param format 如yyyy-MM-dd HH:mm:ss
     */
    public static String getDateString(long milliseconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(milliseconds));
    }

    /**
     * 根据日期字符串返回日期
     *
     * @param format 如yyyy-MM-dd HH:mm:ss
     */
    public static Date getDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 根据一个日期，返回是星期几的字符串, 如"星期一"
     */
    public static String getWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.CHINA).format(date);
    }

    /**
     * 返回一周的第几天, 1-7表示周一到周日
     */
    public static int getDayForWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * @param date 返回一周的第几天
     * @return 返回 一到日
     */
    public static String getWeekDay(int date) {
        if (date == 1) {
            return " 周一";
        } else if (date == 2) {
            return " 周二";
        } else if (date == 3) {
            return " 周三";
        } else if (date == 4) {
            return " 周四";
        } else if (date == 5) {
            return " 周五";
        } else if (date == 6) {
            return " 周六";
        } else {
            return " 周日";
        }
    }

    /**
     * 返回给定时间距离当前时间的小时数
     */
    public static double getHourDistance(Date date) {
        long curTime = System.currentTimeMillis();
        long time = date.getTime();

        double hour = (time - curTime) * 1.0 / (60 * 60 * 1000);

        return hour;
    }

    /**
     * 获取与当前时间的相隔天数
     *
     * @param dest milliseconds 毫秒数
     */
    public static int daysBetween(long dest) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTimeInMillis(dest);
        caled.setTimeInMillis(System.currentTimeMillis());
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 获取n天后第m小时的date<br>
     * 如n=1, m=12时, 返回第二天12:00的Date
     */
    public static Date getNDaysMHoursDate(int n, int m) {
        Date d = getNextNDate(new Date(), n);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, m);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    /**
     * 返回给定时间距离当前时间的小时数
     */
    public static int getHourDistance(long time) {
        long curTime = System.currentTimeMillis();
        int hour = (int) ((curTime - time) * 1.0 / (60 * 60 * 1000));

        return hour;
    }

    /**
     * 返回给定时间距离当前时间的分钟数
     */
    public static int getMinDistance(long time) {
        long curTime = System.currentTimeMillis();
        int min = (int) ((curTime - time) * 1.0 / (60 * 1000));

        return min;
    }

    /**
     * 返回距离当前时间多少天（不够一天显示小时，不够一小时显示分）
     */
    public static String getTime(long temp) {
        if (daysBetween(temp) == 0) {
            if (getHourDistance(temp) == 0) {
                int mins = getMinDistance(temp);
                if (mins >= 1) {
                    return getMinDistance(temp) + "分钟前";
                } else {
                    return "刚刚";
                }
            } else {
                return getHourDistance(temp) + "小时前";
            }
        } else {
            return daysBetween(temp) + "天前";
        }
    }

    /**
     * 给定一个秒数，转化为00:00:00格式的时间
     */
    public static String formateTime(int time) {
        int mHour, mMinute, mSecond;//时分秒
        StringBuilder sbTime = new StringBuilder();
        mHour = time / 3600;
        int num9 = 9;
        if (mHour > num9) {
            sbTime.append(mHour + ":");
        } else if (mHour >= 0) {
            sbTime.append("0" + mHour + ":");
        } else {
            sbTime.append("00:");
        }
        time = time % 3600;
        mMinute = time / 60;
        if (mMinute > num9) {
            sbTime.append(mMinute + ":");
        } else if (mMinute >= 0) {
            sbTime.append("0" + mMinute + ":");
        } else {
            sbTime.append("00:");
        }
        mSecond = time % 60;
        if (mSecond > num9) {
            sbTime.append(mSecond);
        } else if (mSecond >= 0) {
            sbTime.append("0" + mSecond);
        }
        return sbTime.toString();
    }

    /**
     * @return 根据秒数 返回多久前
     */
    public static String getBeforeTime(long sec) {
        if (sec == 0) {
            return "0秒";
        }
        StringBuilder beforeStr = new StringBuilder();
        int day = (int) (sec / (60 * 60 * 24));
        int hour = (int) ((sec % (60 * 60 * 24)) / (60 * 60));

        int minute = (int) ((sec % (60 * 60)) / (60));
        int second = (int) ((sec % (60)) / 1000);
        if (day > 0) {
            beforeStr.append(day + "天");
        }
        if (hour > 0) {
            beforeStr.append(hour + "小时");
        }
        if (minute > 0) {
            beforeStr.append(minute + "分钟");
        }
        if (second > 0) {
            beforeStr.append(second + "秒");
        }
        return beforeStr.toString();
    }

    /**
     * @return 根据秒数 返回多久前 简写
     */
    public static String getBeforeTimeOnit(long sec) {

        int day = (int) (sec / (60 * 60 * 24));
        int hour = (int) ((sec % (60 * 60 * 24)) / (60 * 60));

        int minute = (int) ((sec % (60 * 60)) / (60));
        int second = (int) ((sec % (60)) / 1000);
        if (day > 0) {
            return day + "天";
        }
        if (hour > 0) {
            return hour + "小时";
        }
        if (minute > 0) {
            return minute + "分钟";
        }
        if (second > 0) {
            return second + "秒";
        }
        return "0秒";
    }

    /**
     * 日期转换为时间戳 (精确到毫秒)
     */
    public static long timeToStamp(String timers) {
        Date d = new Date();
        long timeStemp = 0;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d = sf.parse(timers);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeStemp = d.getTime();
        return timeStemp;
    }
}