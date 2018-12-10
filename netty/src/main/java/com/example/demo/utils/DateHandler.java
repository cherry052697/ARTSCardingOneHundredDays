package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateHandler {
    private static Calendar calS = Calendar.getInstance();
    private static Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");// 定义整则表达式

    private DateHandler() {
    }

    /**
     * 计算剩余时间
     * 
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static String remainDateToString(String startDateStr, String endDateStr) {
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        calS.setTime(startDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        // 处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE) + 1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();
        if (endDate.compareTo(startDate) < 0) {
            return sBuilder.append("过期").toString();
        }
        int lday = endD - startD;
        if (lday < 0) {
            endM = endM - 1;
            lday = startDayOfMonth + lday;
        }
        // 处理天数问题，如：2011-01-01 到 2013-12-31 2年11个月31天 实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM + 1;
            lday = 0;
        }
        int mos = (endY - startY) * 12 + (endM - startM);
        int lyear = mos / 12;
        int lmonth = mos % 12;
        if (lyear > 0) {
            sBuilder.append(lyear + "年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth + "个月");
        }
        if (lday > 0) {
            sBuilder.append(lday + "天");
        }
        return sBuilder.toString();
    }

    /**
     * 计算剩余时间
     * 
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static Map<String, Object> remainDateToMap(String startDateStr, String endDateStr) {
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        calS.setTime(startDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        // 处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE) + 1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (endDate.compareTo(startDate) < 0) {
            return null;
        }
        int lday = endD - startD;
        if (lday < 0) {
            endM = endM - 1;
            lday = startDayOfMonth + lday;
        }
        // 处理天数问题，如：2011-01-01 到 2013-12-31 2年11个月31天 实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM + 1;
            lday = 0;
        }
        int mos = (endY - startY) * 12 + (endM - startM);
        int lyear = mos / 12;
        int lmonth = mos % 12;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", lyear);
        map.put("lmonth", lmonth);
        map.put("lday", lday);

        return map;
    }

    /**
     * 转换 dataAndTime 2013-12-31 23:59:59 到 date 2013-12-31
     * 
     * @param dateAndTime
     * @return
     */
    public static String getDate(String dateAndTime) {
        if (dateAndTime != null && !"".equals(dateAndTime.trim())) {
            Matcher m = p.matcher(dateAndTime);
            if (m.find()) {
                return dateAndTime.subSequence(m.start(), m.end()).toString();
            }
        }
        return "data error";
    }

    public static void main(String[] args) {
        System.out.println(remainDateToString("2004-02-29", "2005-03-01"));
        System.out.println("A");
        Date time1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tm = sdf.format(time1);// tm就是昨天的日期的字符串表示
        System.out.println(tm);

        System.out.println("a404".compareTo("a406"));

    }
}