package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public abstract class DateFormatUtils {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 判断给定的日期是否为出诊日期.
     * 
     * @param schedule
     *            出诊计划
     * @param date
     *            日期
     * @return true or false
     */
    /*
     * public static boolean isMatch(final DoctorSchedule schedule, final Date
     * date) { Calendar calendar = Calendar.getInstance();
     * calendar.setTime(date); if (null != schedule.getDateExp()) { List<String>
     * dateList = splitString(schedule.getDateExp()); String currentDate = "" +
     * calendar.get(Calendar.DATE); if (dateList.contains(currentDate)) { return
     * true; } return false; }
     * 
     * List<String> weekList = splitString(schedule.getWeekExp());
     * convertWeek(weekList); String week = "" +
     * calendar.get(Calendar.DAY_OF_WEEK); if (!weekList.contains(week)) {
     * return false; }
     * 
     * if (null != schedule.getWeekInMonth()) { int currentWeek =
     * calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH); List<String> expectWeek =
     * splitString(schedule.getWeekInMonth()); if ((currentWeek == 1 &&
     * expectWeek.contains("F")) || expectWeek.contains("" + currentWeek)) { int
     * weekDate = calendar.get(Calendar.DAY_OF_WEEK); return
     * weekList.contains("" + weekDate); } if (expectWeek.contains("L")) { int
     * currentDate = calendar.get(Calendar.DATE); int maxDate =
     * calendar.getActualMaximum(Calendar.DATE); int startDate = maxDate - 7; if
     * (startDate <= currentDate && currentDate <= maxDate) { if
     * (weekList.contains(week)) { return true; } } } return false; }
     * 
     * return true; }
     *//**
       * 获取给定日期的下一个符合规则的日期.
       * 
       * @param schedule
       *            s
       * @param date
       *            date
       * @return 符合规则的日期.
       */
    /*
     * private static Date nextWithDateExp(final DoctorSchedule schedule, final
     * Date date) { Calendar calendar = Calendar.getInstance();
     * calendar.setTime(date);
     * 
     * List<String> dateList = splitString(schedule.getDateExp()); int month =
     * calendar.get(Calendar.MONTH); Collections.sort(dateList, new
     * Comparator<String>() {
     * 
     * @Override public int compare(final String o1, final String o2) {
     * 
     * return Integer.parseInt(o1) - Integer.parseInt(o2); } }); String
     * currentDate = "" + calendar.get(Calendar.DATE); String nextDate = null;
     * for (String d : dateList) { if (Integer.parseInt(currentDate) -
     * Integer.parseInt(d) < 0) { nextDate = d; break; } } if (null != nextDate)
     * { int sub = Integer.parseInt(nextDate) - Integer.parseInt(currentDate);
     * calendar.add(Calendar.DATE, sub); if (month !=
     * calendar.get(Calendar.MONTH)) { calendar.set(Calendar.DATE,
     * Integer.parseInt(dateList.get(0))); } } else {
     * calendar.add(Calendar.MONTH, 1); calendar.set(Calendar.DATE,
     * Integer.parseInt(dateList.get(0))); } return calendar.getTime(); }
     *//**
       * 获取给定日期的下一个符合规则的日期.
       * 
       * @param schedule
       *            s
       * @param date
       *            date
       * @return 符合规则的日期.
       */
    /*
     * public static Date next(final DoctorSchedule schedule, final Date date) {
     * Calendar calendar = Calendar.getInstance(); calendar.setTime(date); if
     * (null != schedule.getDateExp()) { return nextWithDateExp(schedule, date);
     * }
     * 
     * List<String> weekList = splitString(schedule.getWeekExp());
     * convertWeek(weekList); Collections.sort(weekList);
     * 
     * if (null == schedule.getWeekInMonth()) { Calendar cal =
     * Calendar.getInstance(); cal.setTime(calendar.getTime());
     * cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_WEEK)); while (true) {
     * for (String d : weekList) { int intDate = Integer.parseInt(d) - 1;
     * cal.add(Calendar.DATE, intDate); if (cal.getTime().compareTo(date) > 0) {
     * return cal.getTime(); } cal.add(Calendar.DATE, -intDate); }
     * cal.add(Calendar.DATE, 7); } }
     * 
     * weekList = splitString(schedule.getWeekExp()); List<String> expectWeek =
     * splitString(schedule.getWeekInMonth()); Collections.sort(expectWeek, new
     * Comparator<String>() {
     * 
     * @Override public int compare(final String o1, final String o2) { String
     * a1 = o1; String a2 = o2; if (a1.equals("F")) { a1 = "1"; } if
     * (a1.equals("L")) { a1 = "9"; } if (a2.equals("F")) { a2 = "1"; } if
     * (a2.equals("L")) { a2 = "9"; } return Integer.parseInt(a1) -
     * Integer.parseInt(a2); } });
     * 
     * Date newDate = date; //本月不存在，在下一个月找. for (int i = 0; i < 12; i++) { for
     * (String weekNumStr : expectWeek) { List<Date> result = new
     * LinkedList<Date>(); for (String sund : weekList) { Date d =
     * getExpectDate(newDate, weekNumStr, Integer.parseInt(sund)); if (null != d
     * && d.after(date)) { result.add(d); } } if (result.size() > 0) {
     * Collections.sort(result); return result.get(0); } }
     * 
     * calendar.add(Calendar.MONTH, 1); newDate = calendar.getTime(); } throw
     * new RuntimeException("在一年内没有匹配的日期");
     * 
     * }
     */

    /**
     * 获取date所在月份的第index个星期weeknum.
     * 
     * @param date
     *            d
     * @param weeknum
     *            w
     * @param index
     *            i
     * @return 如何期望的日期不存在，那么返回null，否则返回相应的日期.
     */
    public static Date getExpectDate(final Date date, final String index, final int weeknum) {
        String weekIndex = index;
        if (weekIndex.equals("F")) {
            weekIndex = "1";
        }
        int week = weeknum + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        int firstWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekIndex.equals("1") && firstWeek == week) {
            return calendar.getTime();
        }
        int actualDate = 0;
        int maxDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (!"L".equals(weekIndex)) {
            int num = Integer.parseInt(weekIndex) - 1;
            if (firstWeek <= week) {
                actualDate = num * 7 + week - firstWeek;
            } else {
                actualDate = num * 7 + 7 + week - firstWeek;
            }
        } else {
            calendar.set(Calendar.DATE, maxDayInMonth - 7);
            firstWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (week - firstWeek > 0) {
                actualDate = week - firstWeek;
            } else {
                actualDate = 7 - firstWeek + week;
            }
        }
        if (actualDate + calendar.get(Calendar.DATE) > maxDayInMonth) {
            return null;
        }

        calendar.add(Calendar.DATE, actualDate);
        return calendar.getTime();
    }

    @SuppressWarnings("unused")
	private static void convertWeek(final List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, "" + (Integer.parseInt(list.get(i)) + 1));
        }
    }

    @SuppressWarnings("unused")
	private static List<String> splitString(final String exp) {
        List<String> result = new LinkedList<String>();
        String[] array = exp.split(",");
        for (int i = 0; i < array.length; i++) {
            int index = array[i].indexOf('-');
            if (index > 0) {
                int start = Integer.parseInt(array[i].substring(0, index));
                int end = Integer.parseInt(array[i].substring(index + 1));
                if (end < start) {
                    start = start + end;
                    end = start - end;
                    start = start - end;
                }
                for (int j = start; j <= end; j++) {
                    result.add("" + j);
                }
            } else {
                result.add(array[i]);
            }
        }
        return result;
    }

    /**
     * 通用日期处理
     * 
     * @param date
     * @param formatPattern
     * @return String
     */
    public static String getDateToString(Date date, String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            if ((formatPattern == null) || formatPattern.equals("")) {
                formatPattern = "yyyy-MM-dd HH:mm:ss";
            }
            sdf.applyPattern(formatPattern);
            return sdf.format(date);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * 通用日期处理
     * 
     * @param date
     * @param formatPattern
     * @return
     */
    public static Date getStringDateTime(String date, String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            if ((formatPattern == null) || formatPattern.equals("")) {
                formatPattern = "yyyy-MM-dd HH:mm:ss";
            }
            sdf.applyPattern(formatPattern);
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getStringDateTime(Date date, String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            if ((formatPattern == null) || formatPattern.equals("")) {
                formatPattern = "yyyy-MM-dd HH:mm:ss";
            }
            sdf.applyPattern(formatPattern);
            return sdf.parse(sdf.format(date));
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    // 得到日期的星期
    @SuppressWarnings("unused")
	public static String getDayOfIntWeek(Date date) {
        String str = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c == null)
            return str;

        int day = c.get(Calendar.DAY_OF_WEEK);
        switch (day) {
        case Calendar.SUNDAY:
            str = "7";
            break;
        case Calendar.MONDAY:
            str = "1";
            break;
        case Calendar.TUESDAY:
            str = "2";
            break;
        case Calendar.WEDNESDAY:
            str = "3";
            break;
        case Calendar.THURSDAY:
            str = "4";
            break;
        case Calendar.FRIDAY:
            str = "5";
            break;
        case Calendar.SATURDAY:
            str = "6";
            break;
        default:
            str = "";
        }
        return str;
    }

    // 得到日期的星期
    public static String getDayOfIntWeek(String weekDay) {
        String str = "";
        if (weekDay == null)
            return str;

        int day = Integer.parseInt(weekDay);
        switch (day) {
        case 1:
            str = "星期一";
            break;
        case 2:
            str = "星期二";
            break;
        case 3:
            str = "星期三";
            break;
        case 4:
            str = "星期四";
            break;
        case 5:
            str = "星期五";
            break;
        case 6:
            str = "星期六";
            break;
        case 7:
            str = "星期天";
            break;
        default:
            str = "";
        }
        return str;
    }

    /**
     * 在day时间加上几天
     * 
     * @param date
     *            :yyyy-MM-dd HH:mm
     * @param x
     * @return
     */
    public static Date addDDToDate(Date date, int x) {

        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, x);
        date = cal.getTime();
        cal = null;
        return date;
    }

    /**
     * 在day时间加上小时
     * 
     * @param date
     *            :yyyy-MM-dd HH:mm
     * @param x
     * @return
     */
    public static Date addHHToDate(Date date, int x) {

        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);
        date = cal.getTime();
        cal = null;
        return date;
    }

    public static int getDay() {
        Date d = getStringDateTime(new Date(), "yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.setTime(d);
        return cale.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据用户生日计算年龄
     * 
     * @param birthday
     * @return
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }

    /**
     * 精确时间时间相差几天 2017-05-04 15:30:30 和 2017-05-05 14:30:30 相差 0天
     * 
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalDays(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {

            return -1;

        }

        long intervalMilli = oDate.getTime() - fDate.getTime();

        return (int) (intervalMilli / (24 * 60 * 60 * 1000));

    }

    /**
     * 计算相差天数 不管时间 2017-05-04 15:30:30 和 2017-05-05 14:30:30 相差 1天
     * 
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }

    /**
     * 根据生日获取年龄 (1)不足1个月，显示xx天 (2)不足1岁，显示x个月（按15天判断，15天内x个月，15天以上x+1个月）
     * (3)超过1岁，显示x（按6个月判断，6个月内显示x，6个月以上x+1）
     * 
     * @param birthDay
     * @return
     */
    public static String getAgeByBirthDay(Date birthDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(birthDay);
        String endDate = format.format(new Date());
        Map<String, Object> map = DateHandler.remainDateToMap(startDate, endDate);
        int year = (int) map.get("year");
        int month = (int) map.get("lmonth");
        int day = (int) map.get("lday");

        if (year > 0) {// 大于1岁
            if (month > 6) { // 按6个月判断，6个月内显示X岁，6个月以上X+1岁
                return String.valueOf(year + 1);
            } else if (month == 6) {
                if (day > 15) {
                    return String.valueOf(year + 1);
                }
            }
            return String.valueOf(year);
        } else {// 小于1岁
            if (month > 0) {// 小于1岁 大于1个月
                if (day > 15) {// 按15天判断，15天内X个月，15天以上X+1个月
                    if ((month + 1) == 12) {
                        return "1";
                    }
                    return (month + 1) + "个月";
                } else {
                    if (month == 12) {
                        return "1";
                    }
                    return month + "个月";
                }
            } else {// 小于1个月 返回天数
                return day + "天";
            }
        }
    }

    public static void main(String[] args) {
        String year = "1989";
        String year2 = "01";
        String year3 = "02";
        System.out.println(addDDToDate(new Date(), -7));
        System.out.println(getAgeByBirthday(
                getStringDateTime(year + "-" + year2 + "-" + year3, "yyyy-MM-dd")));
    }
}
