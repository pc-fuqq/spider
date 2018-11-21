package org.fire.spider.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * desc：时间日期工具类
 *
 * @version SVN $Revision: 1.1 $ $Date: 2005/07/12 02:06:49 $
 */
public class DateTools {
	/**
	 * 
	 * desc：获取当前时间格式为yyyy-MM-dd HH:mm:ss
	 *
	 * @version SVN $Revision: 1.1 $ $Date: 2005/07/12 02:06:49 $
	 */
	public static String getCurrentTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	
	public static String getCtime6(String source){
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = sdf.parse(source);
			sdf = new SimpleDateFormat("HHmmss");
			result = sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getDateYYYYMMDD(String source){
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = sdf.parse(source);
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getNextDay(String source){
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(source);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DAY_OF_MONTH, 1);
			result = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getPrevDay(String source){
		String result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(source);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DAY_OF_MONTH, -1);
			result = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 按照某种format生成字符串
	 * */
	public static String getTimeStr(String format, Date date){
		if(StringUtils.isEmpty(format))
			format = "yyyy-MM-dd HH:mm:ss";
		if(date==null)
			date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 将时间字符串转换成yyyyMMdd
	 * */
	public static String transToYYYYMMDD(String str){
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			return new SimpleDateFormat("yyyyMMdd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 按照某种format将指定日期增加N天
	 * */
	public static String addDate(String format, Date date, int days){
		if(StringUtils.isEmpty(format)) format = "yyyyMMdd";
		if(date==null) date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return new SimpleDateFormat(format).format(calendar.getTime());
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式字符串转换成日期格式
	 * */
	public static Date transStrToDate(String date){
		if(StringUtils.isEmpty(date)) return null;
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(transStrToDate(null));
	}
	
}
