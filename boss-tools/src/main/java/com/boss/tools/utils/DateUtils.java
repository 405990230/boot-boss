package com.boss.tools.utils;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Null;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static final String[] ALL_DAY = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",};

    private static final String PAD_STR = "0";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    /**
     * @param day yyyyMMdd
     */
    public static Map<String, Object> getTrailerTime(String day) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = StringUtils.isBlank(day) ? getCurrentDay() : format.parse(day);
        map.put("start", date);
        map.put("end", getEnd(date));
        map.put("week", "%" + getWeek(date) + "%");
        map.put("month1", "" + getMonth(date) + ",%");
        map.put("month2", getMonth(date));
        map.put("month3", "%," + getMonth(date));
        map.put("month4", "%," + getMonth(date) + ",%");
        return map;
    }


    private static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    private static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK);
        return i == 1 ? 7 : i - 1;
    }

    public static int getWeek(int i) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, i);
        return c.get(Calendar.DAY_OF_WEEK) == 1 ? 7 : c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static Date getEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 0);
        return c.getTime();
    }
    /**
     * 获取当天
     * @return
     */
    public static Date getCurrentDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取距离当天的指定天数
     * @param i
     * @return
     */
    public static Date getCurrentDay(@Null int i) {
        Calendar c = Calendar.getInstance();
        c.add(c.DATE, i);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天 加小时
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateHours(int i)  {
        Calendar c = Calendar.getInstance();
        c.add(c.DATE, 0);
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 距离当前时间几分钟
     * @param i
     * @return
     */
    public static Date getBeforeCurrentDay(@Null int i) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, i);
        return c.getTime();
    }


    /**
     * 获取指定格式的日期字符串
     * @param d
     * @return
     */
    private static int getDayNum(Date d) {
        if (d == null) return 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(format.format(d));
    }

    private static String subDay(String day) {
        if (StringUtils.isBlank(day))
            return null;
        String s = day.substring(6);
        if (s.startsWith("0"))
            s = s.substring(1);
        return s;
    }

    public static String getDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public static String getyyyyMMddHHmmDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String getDDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getCDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    public static String getCDay(int i) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        return format.format(c.getTime());
    }
    public static String getSDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        return format.format(new Date());
    }
    public static String getSDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        return format.format(date);
    }

    public static String getDcDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String getDcDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getHcDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getDyDay() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(new Date());
    }

    public static String getDyDay(Long time) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(date);
    }

    public static Date parseTime(String timeStamp) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.parse(timeStamp);
    }

    public static String getTradeStyleAddDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime()) + " 00:00:00";
    }

    public static Date getUserPermissAddDays(Date date, int days) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static Date getUserPermissAddMonths(Date date, int days) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(sdf.format(c.getTime()));
    }

    public static Date getDateToString(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        return sdf.parse(date);
    }

    public static List<String> getDays() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, i);
            list.add(String.valueOf(c.get(Calendar.YEAR)) + StringUtils.leftPad(String.valueOf(c.get(Calendar.MONTH) + 1), 2, PAD_STR)
                    + StringUtils.leftPad(String.valueOf(c.get(Calendar.DAY_OF_MONTH)), 2, PAD_STR));
        }
        return list;
    }

    private static String add0(int i) {
        String s = String.valueOf(i);
        if (s.length() == 1) return "0" + i;
        return s;
    }

    /**
     * 两时间相减 返回天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getSubTwoTimeYY(String endTime, String startTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            Long diff = d1.getTime() - d2.getTime();
            Long days = diff / (1000 * 60 * 60 * 24);
            return days.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param playTime 1514304000000
     * @param date     20180326000000
     */
    public static boolean equals(Long playTime, String date) {
        if (playTime != null && StringUtils.isNotBlank(date)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String format1 = format.format(new Date(playTime)) + "000000";
            return format1.equals(date);
        }
        return false;
    }

    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    public static int backMonthToDay() throws ParseException {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

    public static Date formatTime(String day, Date startTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.set(Calendar.YEAR, Integer.valueOf(day.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.valueOf(day.substring(4, 6)) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day.substring(6, 8)));
        return c.getTime();
    }

    public static boolean currentDay(Date timeStamp) {
        if (timeStamp == null) return false;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(timeStamp).equals(format.format(new Date()));
    }

    public static Long getCurrentDayMinutes() {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, -5);
        return nowTime.getTime().getTime();
    }

    public static Long getCurrentDayMoth(int day) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, -day);
        return nowTime.getTime().getTime();
    }

    public static Long getCurrentDayHour(int hour) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, -hour);
        return nowTime.getTime().getTime();
    }

    public static String getOpenAccountStartTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.format(dayFormat.parse(getDcDay() + " 00:00:00"));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getOpenAccountEndTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return format.format(new Date());
    }

    public static Long CurrentDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static Long getBehaviorTime(String time) {
        if (Objects.equals(time, "0.2")) {
            return getCurrentDayHour(2);
        }
        if (Objects.equals(time, "1")) {
            return CurrentDateByDay();
        }
        return getCurrentDayMoth(Integer.valueOf(time));
    }

    /**
     * 获取当天0点时间的毫秒值
     * @return
     *
     */
    public static Long getCurrentZeroTime(){

        long nowTime = System.currentTimeMillis();
        long todayStartTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        return todayStartTime;
    }

    /**
     * 根据时间戳格式化当前日期(月/日)
     * @param dateStamp
     * @return
     */
    public static String formatDay(Long dateStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String date = dateFormat.format(dateStamp);
        return date;
    }

    /**
     * 根据时间戳格式化当前日期(月/日)
     * @param dateStamp
     * @return
     */
    public static String formatDays(Long dateStamp) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dayFormat.format(dateStamp);
        return date;
    }

    private static Set<String> getMonthDayByWeekDay(String mon, String day, int start, int end) {
        Set<String> set = new LinkedHashSet<>();
        Calendar c = Calendar.getInstance();
        int d = Integer.parseInt(day);
        d = d == 7 ? 1 : d + 1;
        c.set(Calendar.DAY_OF_WEEK, d);
        if (StringUtils.isNotBlank(day)) {
            for (int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH) - c.get(Calendar.DAY_OF_MONTH); i = i + 7) {
                Calendar cc = Calendar.getInstance();
                cc.setTime(c.getTime());
                cc.add(Calendar.DAY_OF_MONTH, i);
                String s = mon + StringUtils.leftPad(String.valueOf(cc.get(Calendar.DAY_OF_MONTH)), 2, PAD_STR);
                if (start <= Integer.valueOf(s) && Integer.valueOf(s) < end)
                    set.add(s);
            }
            for (int i = 0; i > c.get(Calendar.DAY_OF_MONTH) - c.getActualMinimum(Calendar.DAY_OF_MONTH); i = i - 7) {
                Calendar cc = Calendar.getInstance();
                cc.setTime(c.getTime());
                cc.add(Calendar.DAY_OF_MONTH, -i);
                String s = mon + StringUtils.leftPad(String.valueOf(cc.get(Calendar.DAY_OF_MONTH)), 2, PAD_STR);
                if (start <= Integer.valueOf(s) && Integer.valueOf(s) < end)
                    set.add(s);
            }
        }
        return set;
    }

    /**
     * 判断当前时间是否在某段时间内
     * @param start
     * @param end
     * @return
     */
    public static boolean isInBetweenTimeByNow(Date start, Date end) {
        boolean flag = false;
        Date now = new Date();
        if(now.getTime() >= start.getTime() && now.getTime() <= end.getTime()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断某个时间是否在某段时间内
     * @param d
     * @param start
     * @param end
     * @return
     */
    public static boolean isInBetweenTimeByDate(Date d, Date start, Date end) {
        boolean flag = false;
        if(d.getTime() >= start.getTime() && d.getTime() <= end.getTime()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 比较时间先后,返回较大的日期
     * @param day1
     * @param day2
     * @return
     */
    public static Date getLargerDate(Date day1, Date day2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(day1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(day2);
        if(c1.before(c2)) {
            return day2;
        }else {
            return day1;
        }
    }

    /**
     * 判断时间是否大于等于当前时间
     * @param d
     * @return
     */
    public static boolean isLargerNow(Date d) {
        Date now = new Date();
        if(d.getTime() >= now.getTime()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * 功能描述:
     *  判断是否是今天
     * @param date
     * @return
     * Author:
     * Date:     2016年12月13日13:44:56
     */
    public static boolean isToday(Date date){
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date, new Date());
    }

    /**
     * 判断日期是否大于当前日期
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isLargerNowDay(Date date1,Date date2){
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        String time1 = sd.format(date1);
        String time2 = sd.format(date2);
        try {
            Date dateOne = sd.parse(time1);
            Date dateTwo = sd.parse(time2);
            long  s1 = dateOne.getTime() ;//时间的毫秒
            long s2 = dateTwo.getTime() ;
            if(s1>s2){
                return true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Date getDateByDay(Date date1,int i) {//获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(date1.getTime());
        cd.add(Calendar.DATE, i);
        dat = cd.getTime();
        return dat;
    }

    /**
     *
     * 功能描述:
     *  获取两个日期之间的日期差
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     * Author:   jgYang
     * Date:     2016年10月31日 下午6:03:44
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 功能描述:
     * 两个日期比较大小
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @return
     * Author:   jgYang
     * Date:     2016年12月14日 下午2:43:49
     */
    public static boolean compareDate(Date startDate,Date endDate){
        return endDate.getTime() > startDate.getTime();
    }

    /**
     * 功能描述:
     * 两个日期比较大小（日期格式：yyyy-MM-dd hh:mm:ss）
     * @param startDateString 起始时间
     * @param endDateString 结束时间
     * @return
     * Author:   jgYang
     * Date:     2016年12月14日 下午2:43:49
     * @throws ParseException
     */
    public static boolean compareDateByString(String startDateString,String endDateString) throws ParseException{
        return compareDate(dateFormat.parse(startDateString),dateFormat.parse(endDateString));
    }
}
