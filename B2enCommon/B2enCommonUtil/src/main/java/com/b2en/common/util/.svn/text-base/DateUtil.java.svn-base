/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.b2en.common.util.exception.DateFormatException;


/**
 * DateUtil
 * 
 * @author ej.park
 *
 */
public class DateUtil {
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat dateTimeMinFormatter = new SimpleDateFormat("yyyyMMdd hh:mm");
	private static SimpleDateFormat dateTimeSecFormatter = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
	
	/**
	 * @return
	 */
	public static String getToday(){
		return dateFormatter.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * @param format
	 * @return
	 */
	public static String getToday(String format){
		SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
		return dateFormatter.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(String date, String format){
		if(date==null || date.isEmpty()){
			return "";
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
		return dateFormatter.format(getDate(date));
	}
	
	/**
	 * @param fromDate
	 * @param years
	 * @return
	 */
	public static String addYear(String fromDate, int years){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(getDate(fromDate));
        calendar.add (Calendar.YEAR, years);
        Date date = calendar.getTime();
        return dateFormatter.format(date);
	}
	
	/**
	 * @param fromDate
	 * @param months
	 * @return
	 */
	public static String addMonth(String fromDate, int months){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(getDate(fromDate));
        calendar.add (Calendar.MONTH, months);
        Date date = calendar.getTime();
        return dateFormatter.format(date);
	}
	
	/**
	 * @param fromDate
	 * @param days
	 * @return
	 */
	public static String addDay(String fromDate, int days){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(getDate(fromDate));
        calendar.add (Calendar.DATE, days);
        Date date = calendar.getTime();
        return dateFormatter.format(date);
	}
	
	/**
	 * @param fromDateTime
	 * @param toDateTime
	 * @return
	 * @throws DateFormatException
	 */
	public static int diffDaysBetweenDateTime(String fromDateTime , String toDateTime) throws DateFormatException {
		Date sourceDate1 = getDate(fromDateTime);
		Date sourceDate2 = getDate(toDateTime); 
        return diffDays(sourceDate1, sourceDate2);
	}
	
	/**
	 * @param fromDate
	 * @param years
	 * @return
	 * @throws DateFormatException
	 */
	public static int daysInYears(String fromDate , int years) throws DateFormatException{
		Date sourceDate1 = getDate(fromDate);
		Calendar calendar = new GregorianCalendar();
        calendar.setTime( sourceDate1 );
        calendar.add (Calendar.YEAR, years);
        Date sourceDate2 = calendar.getTime();
        return diffDays(sourceDate1, sourceDate2);
	}
	
	/**
	 * @param dateStr
	 * @return
	 */
	private static Date getDate(String dateStr){
		dateStr = dateStr.replaceAll("-", "").replaceAll("/", "");
		
		Date date = null;
		try{
			if(dateStr.length()==8){
				date = dateFormatter.parse(dateStr);
			}else if(dateStr.length()==14){
				date = dateTimeMinFormatter.parse(dateStr);
			}else if(dateStr.length()==17){
				date = dateTimeSecFormatter.parse(dateStr);
			}else{
				throw new DateFormatException("IllegalDateFormat["+dateStr+"]");
			}
		} catch (ParseException ex){
			throw new DateFormatException("IllegalDateFormat["+dateStr+"]", ex);
		}
		
		return date;
	}
	
	/**
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	private static int diffDays(Date fromDate, Date toDate){
		int diffDays=(int)(( toDate.getTime() - fromDate.getTime() ) / ( 1000 * 60 * 60 * 24 )) ;
		if(diffDays<0){
			return 0;
		}
        int mod = ( toDate.getTime() - fromDate.getTime() ) % ( 1000 * 60 * 60 *24 )==0L?0:1;
	        
        diffDays+=mod;
        return diffDays;
	}
}
