package com.cherry.senior;

import java.util.Calendar;
import java.util.Date;

import com.cherry.netty.utils.DateFormatUtils;


public class TestDate {
	public static void main(String[] args) throws Exception {
		Date d = new Date();
		String createdTimeDateStr = DateFormatUtils.getDateToString(d, "yyyy-MM-dd HH:mm:ss");
		System.out.println(createdTimeDateStr);
		int dayNum = Integer.valueOf(24*6);
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR_OF_DAY, +dayNum);//+N今天的时间加N天
        d = calendar.getTime();
        createdTimeDateStr = DateFormatUtils.getDateToString(d, "yyyy-MM-dd HH:mm:ss");
        System.out.println(createdTimeDateStr);
	}


}
