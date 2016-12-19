package org.fire.spider.com.util;

public class StringUtils {

	/**
	 * 是否为空
	 * */
	public static boolean isEmpty(String str){
		if(str==null || "".equals(str)) return true;
		return false;
	}
	
}
